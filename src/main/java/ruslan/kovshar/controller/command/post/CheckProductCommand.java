package ruslan.kovshar.controller.command.post;

import org.apache.log4j.Logger;
import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.service.ProductService;
import ruslan.kovshar.controller.validator.IntegerValidator;
import ruslan.kovshar.textconstants.Params;
import ruslan.kovshar.textconstants.URI;

import javax.servlet.http.HttpServletRequest;

public class CheckProductCommand implements Command {

    private static final Logger log = Logger.getLogger(CheckProductCommand.class);

    private ProductService productService = ProductService.getInstance();

    /**
     * finds product in the stock
     *
     * @param request http servlet request
     * @return redirect to product page if product found,
     * redirect to check page with error if not
     */
    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(Params.NAME);
        Integer code = IntegerValidator.validate(name);

        Product product;
        try {
            if (code != null) {
                product = productService.findProductByCode(code);
            } else {
                product = productService.findProductByName(name);
            }
            request.getSession().setAttribute(Params.PRODUCT, product);
        } catch (Exception e) {
            log.error(e);
            return URI.REDIRECT + request.getServletPath() + URI.CHECK + Params.PARAM + Params.ERROR;
        }
        return URI.REDIRECT + request.getServletPath() + URI.CHECK + URI.PRODUCT;
    }
}
