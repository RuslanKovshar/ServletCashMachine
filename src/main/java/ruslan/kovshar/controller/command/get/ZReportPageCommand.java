package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.model.service.UserService;
import ruslan.kovshar.view.Pages;

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

    /**
     * displays z_report page
     *
     * @param request http servlet request
     * @return z_report page
     */
    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Check> allChecks = checkService.getAllUserChecks(user);

        int countOfChecks = allChecks.size();
        double totalMoney = allChecks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();

        request.setAttribute("count", countOfChecks);
        request.setAttribute("TOTAL_SUM", totalMoney);

        try (FileWriter fileWriter = new FileWriter("C:\\REPORTS\\" + LocalDate.now() + ".txt")) {
            fileWriter.write("Z-Order from: " + LocalTime.now().toString() + System.lineSeparator());
            fileWriter.write("Cashier: " + user.getFirstNameEN() + "  " + user.getSecondNameEN() + System.lineSeparator());
            fileWriter.write("Total checks: " + countOfChecks + System.lineSeparator());
            fileWriter.write("Total money: " + totalMoney);
        } catch (IOException e) {
            e.printStackTrace();
        }

        allChecks.forEach(checkService::deleteCheck);
        user.setUserCash(user.getUserCash().subtract(BigDecimal.valueOf(totalMoney)));
        userService.updateUser(user);
        return Pages.Z_REPORT_PAGE;
    }
}
