package ruslan.kovshar.controller.security;

import ruslan.kovshar.model.enums.Roles;
import ruslan.kovshar.view.URI;

import java.util.*;

class SecurityConfig {
    private static Map<Roles, List<String>> pagesByRoles = new HashMap<>();
    private static List<String> pagesForAllUsers = new ArrayList<>();

    static {
        pagesForAllUsers.addAll(Arrays.asList(URI.LOGIN, URI.LOGOUT));

        pagesByRoles.put(Roles.CASHIER, Arrays.asList(URI.HOME));

        pagesByRoles.put(Roles.MERCHANDISER, Arrays.asList(URI.MERCHANDISER));

        pagesByRoles.put(Roles.SENIOR_CASHIER, Arrays.asList(URI.SENIOR_CASHIER));
    }

    public static Set<Roles> getAllAppRoles() {
        return pagesByRoles.keySet();
    }

    public static List<String> getUrlPatternsForRole(Roles role) {
        return pagesByRoles.get(role);
    }

    static Map<Roles, List<String>> getPagesByRoles() {
        return pagesByRoles;
    }

    static List<String> getPagesForAllUsers() {
        return pagesForAllUsers;
    }
}
