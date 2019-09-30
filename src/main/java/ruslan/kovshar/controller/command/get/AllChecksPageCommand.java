package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.pagination.Page;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.model.validator.Validator;
import ruslan.kovshar.view.Pages;

import javax.servlet.http.HttpServletRequest;

public class AllChecksPageCommand implements Command {

    private CheckService checkService = CheckService.getInstance();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        String pageNumberParameter = request.getParameter("page");
        String sortParameter = request.getParameter("sort");
        String maxResultParameter = request.getParameter("maxResult");
        Integer pageNumber = Validator.integerValidator(pageNumberParameter);
        Integer maxResult = Validator.integerValidator(maxResultParameter);

        Page pageInfo = new Page(pageNumber, maxResult, sortParameter);
        System.out.println(pageInfo.toString());

        request.setAttribute("page", checkService.findPageWithChecks(user,pageInfo));
        return Pages.ALL_CHECKS_PAGE;
    }
}
