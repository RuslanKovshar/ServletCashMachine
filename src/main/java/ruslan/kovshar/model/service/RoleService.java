package ruslan.kovshar.model.service;

import ruslan.kovshar.model.dao.DaoFactory;
import ruslan.kovshar.model.dao.RoleDao;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;

import java.util.Set;

public class RoleService {

    private DaoFactory daoFactory = DaoFactory.getInstance();

    private void addUserRole(User user, Roles role) {
        try(final RoleDao roleDao = daoFactory.createRoleDao()) {
            roleDao.setUserRole(user, role);
        }
    }

    public Set<Roles> getUserRoles(User user) {
        try (final RoleDao roleDao = daoFactory.createRoleDao()) {
            return roleDao.findByUserId(user);
        }
    }

}
