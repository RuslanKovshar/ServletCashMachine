package ruslan.kovshar.model.dao;

import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;

import java.util.List;
import java.util.Optional;

public interface CheckDao extends GenericDao<Check> {
    List<Check> findAllByUser(User user);
    Page<Check> findPageAllByUser(User user, Page pageInfo);
}
