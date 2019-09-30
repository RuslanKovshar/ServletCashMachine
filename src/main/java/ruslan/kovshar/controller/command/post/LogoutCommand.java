package ruslan.kovshar.controller.command.post;

import ruslan.kovshar.controller.command.Command;
import ruslan.kovshar.view.Params;
import ruslan.kovshar.view.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    /**
     * removes user from session
     *
     * @param request http servlet request
     * @return redirect to login page with logout param
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return URI.REDIRECT + request.getServletPath() + URI.LOGIN + Params.PARAM + Params.LOGOUT;
    }
}
