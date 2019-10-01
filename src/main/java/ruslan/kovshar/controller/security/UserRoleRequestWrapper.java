package ruslan.kovshar.controller.security;

import ruslan.kovshar.model.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;
import java.util.List;
import java.util.Set;

/**
 * wrapper of HttpServletRequestWrapper with user info
 */
public class UserRoleRequestWrapper extends HttpServletRequestWrapper {

    private String user;
    private Set<Roles> roles;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapper(String user, Set<Roles> roles, HttpServletRequest request) {
        super(request);
        this.user = user;
        this.roles = roles;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (roles == null) {
            return this.realRequest.isUserInRole(role);
        }
        return roles.contains(Roles.valueOf(role));
    }

    @Override
    public Principal getUserPrincipal() {
        if (this.user == null) {
            return realRequest.getUserPrincipal();
        }

        return () -> user;
    }
}
