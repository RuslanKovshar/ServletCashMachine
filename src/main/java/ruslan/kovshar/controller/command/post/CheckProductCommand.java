package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.service.ProductService;
import ruslan.kovshar.model.validator.Validator;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;

public class CheckProductCommand implements Command {

    private ProductService productService = ProductService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        final String name = request.getParameter(Params.NAME);

        Integer code = Validator.integerValidator(name);

        Product product;
        try {
            if (code != null) {
                product = productService.findProductByCode(code);
            } else {
                product = productService.findProductByName(name);
            }
            request.getSession().setAttribute("product", product);
        } catch (Exception e) {
            e.printStackTrace();
            return URI.REDIRECT + request.getServletPath() + URI.CHECK + Params.PARAM + Params.ERROR;
        }
        return URI.REDIRECT + request.getServletPath() + URI.CHECK + URI.PRODUCT;
    }
}
