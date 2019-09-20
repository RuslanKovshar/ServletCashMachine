package ruslan.kovshar.controller.command.get;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.view.Pages;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ZReportPageCommand implements Command {

    private CheckService checkService;

    public ZReportPageCommand(CheckService checkService) {
        this.checkService = checkService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        List<Check> allChecks = checkService.getAllChecks(user);

        int countOfChecks = allChecks.size();
        double totalMoney = allChecks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();

        try (FileWriter fileWriter = new FileWriter("C:\\REPORTS\\" + LocalDate.now() + ".txt")) {
            fileWriter.write("Z-Order from: " + LocalTime.now().toString() + System.lineSeparator());
            fileWriter.write("Cashier: " + user.getFirstNameEN() + "  " + user.getSecondNameEN() + System.lineSeparator());
            fileWriter.write("Total checks: " + countOfChecks + System.lineSeparator());
            fileWriter.write("Total money: " + totalMoney);
        } catch (IOException e) {
            e.printStackTrace();
        }


        request.setAttribute("count", allChecks.size());
        double sum = allChecks.stream().mapToDouble(s -> s.getTotalPrice().doubleValue()).sum();
        request.setAttribute("TOTAL_SUM", sum);
        request.setAttribute("ENTITY_ID", user.getId());

        allChecks.forEach(checkService::deleteCheck);
        return Pages.Z_REPORT_PAGE;
    }
}
