package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.model.service.ReportService;
import ruslan.kovshar.textconstants.Pages;
import ruslan.kovshar.textconstants.Params;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class XReportPageCommand implements Command {

    private CheckService checkService = CheckService.getInstance();
    private ReportService reportService = ReportService.getInstance();

    /**
     * displays x_report page
     *
     * @param request http servlet request
     * @return x_report page
     */
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Params.USER);
        List<Check> checks = checkService.getAllUserChecks(user);
        int countOfAllChecks = checks.size();
        double totalMoney = checks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();
        reportService.makeReport(countOfAllChecks, totalMoney, request);
        return Pages.X_REPORT_PAGE;
    }
}
