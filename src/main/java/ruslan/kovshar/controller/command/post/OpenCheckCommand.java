package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.model.entity.Check;
import ruslan.kovshar.textconstants.Params;
import ruslan.kovshar.textconstants.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OpenCheckCommand implements Command {
    /**
     * adds new check to session, if check is not in session
     *
     * @param request http servlet request
     * @return redirect to check page
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute(Params.CHECK) == null) {
            session.setAttribute(Params.CHECK, new Check());
        }
        return URI.REDIRECT + request.getServletPath() + URI.CHECK;
    }
}
