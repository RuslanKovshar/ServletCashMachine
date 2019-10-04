package ruslan.kovshar.model.service;

import org.apache.log4j.Logger;
import ruslan.kovshar.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static ruslan.kovshar.textconstants.SQL.COUNT_OF_CHECKS;
import static ruslan.kovshar.textconstants.TextConstants.*;

public class ReportService {

    private static final Logger log = Logger.getLogger(ProductService.class);

    private static volatile ReportService instance;

    private ReportService() {
    }

    /**
     * @return instance
     */
    public static ReportService getInstance() {
        if (instance == null) {
            synchronized (UserService.class) {
                if (instance == null) {
                    instance = new ReportService();
                }
            }
        }
        return instance;
    }

    public void makeReport(int countOfAllChecks, double totalMoney, HttpServletRequest request) {
        request.setAttribute(COUNT_OF_CHECKS, countOfAllChecks);
        request.setAttribute(TOTAL_SUM, totalMoney);
    }

    public void writeZReport(User user, int countOfAllChecks, double totalMoney) {
        try (FileWriter fileWriter = new FileWriter(REPORT_FILE_PATH + REPORT_TYPE + LocalDate.now().toString() + TXT)) {
            fileWriter.write(REPORT_FROM + LocalTime.now().toString() + ENDL);
            fileWriter.write(REPORT_CASHIER + user.getFirstNameEN() + "  " + user.getSecondNameEN() + ENDL);
            fileWriter.write(REPORT_COUNT_OF_CHECKS + countOfAllChecks + ENDL);
            fileWriter.write(REPORT_TOTAL_MONEY + totalMoney);
        } catch (IOException e) {
            log.error(e);
        }
    }
}
