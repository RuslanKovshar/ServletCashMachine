package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.enums.Roles;

import java.util.Set;

public interface RoleDao extends GenericDao<Roles> {
    void setUserRole(User user, Roles role);
}
