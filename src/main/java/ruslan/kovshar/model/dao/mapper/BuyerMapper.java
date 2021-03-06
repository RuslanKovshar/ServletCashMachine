package ruslan.kovshar.model.dao.mapper;

import ruslan.kovshar.model.entity.Buyer;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * extracts buyer from result set
 */
public class BuyerMapper extends Mapper<Buyer> {
    @Override
    public Buyer extractFromResultSet(ResultSet rs) throws SQLException {
        Buyer buyer = new Buyer();
        buyer.setId(rs.getLong("buyer_id"));
        buyer.setCardNumber(rs.getString("card_number"));
        buyer.setNameOnCard(rs.getString("name_on_card"));
        return makeUnique(buyer, buyer.getId());
    }
}
