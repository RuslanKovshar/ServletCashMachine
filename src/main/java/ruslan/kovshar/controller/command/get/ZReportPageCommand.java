package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.model.service.ReportService;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.textconstants.Pages;
import ruslan.kovshar.textconstants.Params;
import ruslan.kovshar.textconstants.TextConstants;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class ZReportPageCommand implements Command {

    private CheckService checkService = CheckService.getInstance();
    private UserService userService = UserService.getInstance();
    private ReportService reportService = ReportService.getInstance();

    /**
     * displays z_report page
     *
     * @param request http servlet request
     * @return z_report page
     */
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute(Params.USER);
        List<Check> checks = checkService.getAllUserChecks(user);

        int countOfAllChecks = checks.size();
        double totalMoney = checks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();
        reportService.makeReport(countOfAllChecks, totalMoney, request);
        reportService.writeZReport(user, countOfAllChecks, totalMoney);

        checks.forEach(checkService::deleteCheck);
        user.setUserCash(user.getUserCash().subtract(BigDecimal.valueOf(totalMoney)));
        userService.updateUser(user);
        return Pages.Z_REPORT_PAGE;
    }
}
