package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;

/**
 * Role DAO
 */
public interface RoleDao extends GenericDao<Roles> {
    /**
     * sets role for user
     *
     * @param user user
     * @param role role
     */
    void setUserRole(User user, Roles role);
}
