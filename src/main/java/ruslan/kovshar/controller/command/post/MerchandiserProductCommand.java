package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;
import ruslan.kovshar.model.entity.WeightProduct;
import ruslan.kovshar.model.enums.Types;
import ruslan.kovshar.model.service.ProductService;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class MerchandiserProductCommand implements Command {

    private ProductService productService = ProductService.getInstance();
    private StockService stockService = StockService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(Params.NAME);
        String code = request.getParameter(Params.CODE);
        String price = request.getParameter(Params.PRICE);
        String count = request.getParameter(Params.COUNT_ON_STOCK);
        Types type = Types.valueOf(request.getParameter(Params.TYPE));

        Product product;
        if (type.equals(Types.PIECE_PRODUCT)) {
            product = new CountProduct(Integer.parseInt(code),
                    name,
                    new BigDecimal(price),
                    type);
        } else {
            product = new WeightProduct(Integer.parseInt(code),
                    name,
                    new BigDecimal(price),
                    type);
        }
        if (productService.createProduct(product)) {
            Stock stock = new Stock(product, Integer.parseInt(count));
            stockService.addProductToStock(stock);
            return URI.REDIRECT + request.getServletPath() + URI.MERCHANDISER;
        } else {
            return URI.REDIRECT + request.getServletPath() + URI.MERCHANDISER + Params.PARAM + Params.PRODUCT_EXIST;
        }

    }
}
