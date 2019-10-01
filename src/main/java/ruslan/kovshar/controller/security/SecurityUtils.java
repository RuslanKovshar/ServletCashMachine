package ruslan.kovshar.controller.security;

import ruslan.kovshar.model.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class SecurityUtils {

    /**
     * checks if the page is protected
     *
     * @param request http servlet request
     * @return true if yes, false if not
     */
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);

        Set<Roles> roles = SecurityConfig.getAllAppRoles();

        for (Roles role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets the url
     *
     * @param request http servlet request
     * @return urlPath
     */
    private static String getUrlPattern(HttpServletRequest request) {
        String urlPath = "/";
        if (request.getPathInfo() != null) {
            urlPath = request.getPathInfo().replaceAll("/\\d+", "");
        }
        return urlPath;
    }


    /**
     * checks for permission
     *
     * @param request http servlet request
     * @return true if yes, false if not
     */
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = getUrlPattern(request);

        Set<Roles> allRoles = SecurityConfig.getAllAppRoles();

        for (Roles role : allRoles) {
            if (!request.isUserInRole(role.name())) {
                continue;
            }
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
