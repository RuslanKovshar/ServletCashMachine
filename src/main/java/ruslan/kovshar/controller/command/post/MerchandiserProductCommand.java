package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.controller.dto.ProductDTO;
import ruslan.kovshar.controller.validator.IntegerValidator;
import ruslan.kovshar.controller.validator.ProductValidator;
import ruslan.kovshar.controller.validator.Validator;
import ruslan.kovshar.model.entity.Product;
import ruslan.kovshar.model.entity.Stock;
import ruslan.kovshar.model.enums.Types;
import ruslan.kovshar.model.service.ProductService;
import ruslan.kovshar.model.service.StockService;
import ruslan.kovshar.textconstants.Pages;
import ruslan.kovshar.textconstants.Params;
import ruslan.kovshar.textconstants.URI;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class MerchandiserProductCommand implements Command {

    private ProductService productService = ProductService.getInstance();
    private StockService stockService = StockService.getInstance();

    /**
     * creates the product and adds to the stock
     *
     * @param request http servlet request
     * @return redirect to merchandiser page if product created,
     * merchandiser page with productExist param if not
     */
    @Override
    public String execute(HttpServletRequest request) {
        ProductDTO productDTO = getProductDTO(request);
        Validator<ProductDTO> validator = new ProductValidator();
        validator.validate(productDTO);

        if (validator.hasErrors()) {
            request.setAttribute(Params.PRODUCT_DTO, productDTO);
            validator.getErrors().forEach(request::setAttribute);
            return Pages.MERCHANDISER_PAGE;
        }

        Product product = productService.createProduct(productDTO);
        if (productService.createProduct(product)) {
            Stock stock = new Stock(product, productDTO.getCountOfProduct());
            stockService.addProductToStock(stock);
            return URI.REDIRECT + request.getServletPath() + URI.MERCHANDISER;
        } else {
            request.setAttribute(Params.PRODUCT_DTO, productDTO);
            request.setAttribute(Params.PRODUCT_EXIST, true);
            return Pages.MERCHANDISER_PAGE;
        }
    }

    /**
     * executes productDTO from the request
     *
     * @param request http servlet request
     * @return productDTO
     */
    private ProductDTO getProductDTO(HttpServletRequest request) {
        String name = request.getParameter(Params.NAME);
        String code = request.getParameter(Params.CODE);
        String price = request.getParameter(Params.PRICE);
        Types type = Types.valueOf(request.getParameter(Params.TYPE));
        String count = request.getParameter(Params.COUNT_ON_STOCK);
        return new ProductDTO(IntegerValidator.validate(code),
                name,
                price.equals("") ? BigDecimal.ZERO : new BigDecimal(price),
                type,
                IntegerValidator.validate(count));
    }
}
