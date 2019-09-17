package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.service.ProductService;
import ruslan.kovshar.view.RequestParams;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;

public class CheckProductCommand implements Command {

    private ProductService productService = new ProductService();

    @Override
    public String execute(HttpServletRequest request) {
        final String name = request.getParameter(RequestParams.NAME);

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
            System.out.println(product);
            request.getSession().setAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
            return URI.REDIRECT + request.getServletPath() + URI.CHECK + URI.PRODUCT + RequestParams.PARAM + RequestParams.ERROR;
        }
        return URI.REDIRECT + request.getServletPath() + URI.CHECK + URI.PRODUCT;
    }
}
