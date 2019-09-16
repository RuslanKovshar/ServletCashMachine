package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.service.ProductService;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.view.Pages;
import ruslan.kovshar.view.RequestParams;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;

public class MerchandiserStockCommand implements Command {

    private ProductService productService = new ProductService();
    private StockService stockService = new StockService();

    @Override
    public String execute(HttpServletRequest request) {
        final String name = request.getParameter(RequestParams.NAME);
        final String count = request.getParameter(RequestParams.COUNT_ON_STOCK);

        Integer code = null;
        try {
            code = Integer.parseInt(name);
        } catch (NumberFormatException ignored) {
        }

        Product product;
        try {
            if (code != null) {
                product = productService.findProductByCode(code);
            } else {
                product = productService.findProductByName(name);
            }
            stockService.updateStock(product, -Integer.parseInt(count));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return URI.REDIRECT + request.getServletPath() + URI.MERCHANDISER;
    }
}
