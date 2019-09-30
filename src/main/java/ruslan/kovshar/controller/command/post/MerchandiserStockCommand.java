package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.service.ProductService;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.controller.validator.IntegerValidator;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;

public class MerchandiserStockCommand implements Command {

    private ProductService productService = ProductService.getInstance();
    private StockService stockService = StockService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(Params.NAME);
        String count = request.getParameter(Params.COUNT_ON_STOCK);
        Integer code = IntegerValidator.integerValidator(name);

        Product product;
        try {
            //todo: попробуй вынести в одельный метод
            if (code != null) {
                product = productService.findProductByCode(code);
            } else {
                product = productService.findProductByName(name);
            }
            stockService.updateStock(product, -Integer.parseInt(count));
        } catch (Exception e) {
            e.printStackTrace();
            return URI.REDIRECT + request.getServletPath() + URI.MERCHANDISER + Params.PARAM + Params.PRODUCT_NOT_FOUND;
        }

        return URI.REDIRECT + request.getServletPath() + URI.MERCHANDISER;
    }
}
