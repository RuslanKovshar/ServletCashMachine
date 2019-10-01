package ruslan.kovshar.controller.filter;

import ruslan.kovshar.controller.security.SecurityUtils;
import ruslan.kovshar.controller.security.UserRoleRequestWrapper;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) servletRequest;
        final HttpServletResponse res = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Params.USER);

        final String requestURI = req.getPathInfo();

        if (requestURI != null && requestURI.equals(URI.LOGIN)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletRequest wrapRequest = req;

        if (user != null) {
            String email = user.getEmail();
            Set<Roles> roles = user.getAuthorities();
            wrapRequest = new UserRoleRequestWrapper(email, roles, req);
        }

        if (SecurityUtils.isSecurityPage(req)) {
            if (user == null) {
                String reqUri = req.getRequestURI();
                session.setAttribute(Params.REDIRECTED_URI, reqUri);
                String servletPath = wrapRequest.getServletPath();
                servletPath = servletPath.equals("/") ? "/api" : servletPath;
                res.sendRedirect(wrapRequest.getContextPath() + servletPath + URI.LOGIN);
                return;
            }

            boolean hasPermission = SecurityUtils.hasPermission(wrapRequest);
            if (!hasPermission) {
                res.sendError(403);
                return;
            }
        }
        filterChain.doFilter(wrapRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
