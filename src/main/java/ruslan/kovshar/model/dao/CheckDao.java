package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;

import java.util.List;

/**
 * Check DAO
 */
public interface CheckDao extends GenericDao<Check> {
    /**
     * finds all users checks
     *
     * @param user user
     * @return list of users checks
     */
    List<Check> findAllByUser(User user);

    /**
     * finds all user checks and adds them to page
     *
     * @param user     user
     * @param pageInfo page information
     * @return page with checks
     */
    Page<Check> findPageAllByUser(User user, Page pageInfo);
}
