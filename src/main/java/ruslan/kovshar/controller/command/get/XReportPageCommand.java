package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.view.Pages;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class XReportPageCommand implements Command {

    private CheckService checkService = CheckService.getInstance();

    /**
     * displays x_report page
     *
     * @param request http servlet request
     * @return x_report page
     */
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Check> allChecks = checkService.getAllUserChecks(user);

        int countOfChecks = allChecks.size();
        double totalMoney = allChecks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();

        request.setAttribute("count", countOfChecks);
        request.setAttribute("TOTAL_SUM", totalMoney);
        return Pages.X_REPORT_PAGE;
    }
}
