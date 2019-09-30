package ruslan.kovshar.controller.security;

import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecurityUtils {

    // Проверить требует ли данный 'request' входа в систему или нет.
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

    private static String getUrlPattern(HttpServletRequest request) {
        String urlPath = "/";
        if (request.getPathInfo() != null) {
            urlPath = request.getPathInfo().replaceAll("/\\d+", "");
        }
        return urlPath;
    }

    // Проверить имеет ли данный 'request' подходящую роль?
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
