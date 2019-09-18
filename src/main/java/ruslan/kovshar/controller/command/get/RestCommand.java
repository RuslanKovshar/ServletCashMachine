package ruslan.kovshar.controller.command.get;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.model.entity.User;
import ruslan.kovshar.model.service.CheckService;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RestCommand implements Command {

    private CheckService checkService = new CheckService();

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user.getId());

        final List<Check> allChecks = checkService.getAllChecks(user);
        System.out.println(allChecks);
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(allChecks);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(jsonString);

        // Java objects to JSON string - pretty-print
        String jsonInString2 = null;
        try {
            jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(allChecks);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(jsonInString2);
        return URI.REDIRECT + request.getServletPath();
    }
}
