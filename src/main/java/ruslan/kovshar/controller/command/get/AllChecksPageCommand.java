package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.controller.validator.IntegerValidator;
import ruslan.kovshar.textconstants.Pages;
import ruslan.kovshar.textconstants.Params;

import javax.servlet.http.HttpServletRequest;

public class AllChecksPageCommand implements Command {

    private CheckService checkService = CheckService.getInstance();

    /**
     * displays checks page
     *
     * @param request http servlet request
     * @return checks page
     */
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Params.USER);
        String pageNumberParameter = request.getParameter(Params.PAGE);
        String sortParameter = request.getParameter(Params.SORT_TYPE);
        String maxResultParameter = request.getParameter(Params.MAX_RESULT);

        Integer pageNumber = IntegerValidator.validate(pageNumberParameter);
        Integer maxResult = IntegerValidator.validate(maxResultParameter);

        Page pageInfo = new Page(pageNumber, maxResult, sortParameter);

        request.setAttribute(Params.PAGE, checkService.findPageWithChecks(user, pageInfo));
        return Pages.ALL_CHECKS_PAGE;
    }
}
