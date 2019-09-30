package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.controller.validator.IntegerValidator;
import ruslan.kovshar.view.Pages;

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
        User user = (User) request.getSession().getAttribute("user");
        String pageNumberParameter = request.getParameter("page");
        String sortParameter = request.getParameter("sort");
        String maxResultParameter = request.getParameter("maxResult");

        Integer pageNumber = IntegerValidator.integerValidator(pageNumberParameter);
        Integer maxResult = IntegerValidator.integerValidator(maxResultParameter);

        Page pageInfo = new Page(pageNumber, maxResult, sortParameter);

        request.setAttribute("page", checkService.findPageWithChecks(user, pageInfo));
        return Pages.ALL_CHECKS_PAGE;
    }
}
