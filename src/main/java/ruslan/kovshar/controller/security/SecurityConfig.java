package ruslan.kovshar.controller.security;

import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.textconstants.URI;

import java.util.*;

/**
 * contains pages by roles
 */
class SecurityConfig {
    private static Map<Roles, List<String>> pagesByRoles = new HashMap<>();

    static {
        pagesByRoles.put(Roles.CASHIER, Arrays.asList(URI.HOME,
                URI.OPEN_CHECK,
                URI.CLOSE_CHECK,
                URI.ADD_PRODUCT));

        pagesByRoles.put(Roles.MERCHANDISER, Arrays.asList(URI.HOME,
                URI.MERCHANDISER,
                URI.MERCHANDISER + URI.STOCK,
                URI.MERCHANDISER + URI.PRODUCT));

        pagesByRoles.put(Roles.SENIOR_CASHIER, Arrays.asList(URI.HOME,
                URI.CHECKS,
                URI.OPEN_CHECK,
                URI.CLOSE_CHECK,
                URI.PRODUCT,
                URI.CANCEL_CHECK,
                URI.Z_REPORT,
                URI.X_REPORT));

        pagesByRoles.put(Roles.ADMIN, Arrays.asList(
                URI.USERS,
                URI.USER
        ));
    }

    static Set<Roles> getAllAppRoles() {
        return pagesByRoles.keySet();
    }

    static List<String> getUrlPatternsForRole(Roles role) {
        return pagesByRoles.get(role);
    }
}
