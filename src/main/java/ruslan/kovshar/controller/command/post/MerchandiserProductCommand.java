package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.dto.ProductDTO;
import ruslan.kovshar.model.entity.CountProduct;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;
import ruslan.kovshar.model.entity.WeightProduct;
import ruslan.kovshar.model.enums.Types;
import ruslan.kovshar.model.service.ProductService;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.view.RequestParams;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class MerchandiserProductCommand implements Command {

    private ProductService productService = new ProductService();
    private StockService stockService = new StockService();

    @Override
    public String execute(HttpServletRequest request) {
        String name_ua = request.getParameter(RequestParams.NAME_UA);
        String name_en = request.getParameter(RequestParams.NAME_EN);
        String code = request.getParameter(RequestParams.CODE);
        String price = request.getParameter(RequestParams.PRICE);
        String count = request.getParameter(RequestParams.COUNT_ON_STOCK);
        Types type = Types.valueOf(request.getParameter(RequestParams.TYPE));

        Product product;
        if (type.equals(Types.PIECE_PRODUCT)) {
            product = new CountProduct(Integer.parseInt(code),
                    name_ua,
                    name_en,
                    new BigDecimal(price),
                    type);
        } else {
            product = new WeightProduct(Integer.parseInt(code),
                    name_ua,
                    name_en,
                    new BigDecimal(price),
                    type);
        }
        productService.createProduct(product);
        Stock stock = new Stock(product, Integer.parseInt(count));
        stockService.addProductToStock(stock);
        return URI.REDIRECT + request.getServletPath() + URI.MERCHANDISER;
    }
}
