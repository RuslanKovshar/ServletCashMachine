package ruslan.kovshar.controller;

import ruslan.kovshar.controller.command.*;
import ruslan.kovshar.controller.command.get.*;
import ruslan.kovshar.controller.command.post.*;
import ruslan.kovshar.view.Pages;
import ruslan.kovshar.view.TextConstants;
import ruslan.kovshar.view.URI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init() throws ServletException {
        commands.put(TextConstants.POST + URI.LOGIN,                        new LoginCommand());
        commands.put(TextConstants.POST + URI.REGISTRATION,                 new RegistrationCommand());
        commands.put(TextConstants.POST + URI.LOGOUT,                       new LogoutCommand());
        commands.put(TextConstants.POST + URI.MERCHANDISER + URI.PRODUCT,   new MerchandiserProductCommand());
        commands.put(TextConstants.POST + URI.MERCHANDISER + URI.STOCK,     new MerchandiserStockCommand());
        commands.put(TextConstants.POST + URI.OPEN_CHECK,                   new OpenCheckCommand());
        commands.put(TextConstants.POST + URI.CHECK + URI.PRODUCT,          new CheckProductCommand());
        commands.put(TextConstants.POST + URI.CHECK + URI.ADD_PRODUCT,      new CheckAddProduct());

        commands.put(TextConstants.GET + URI.MERCHANDISER,                  new MerchandiserPageCommand());
        commands.put(TextConstants.GET + URI.HOME,                          new HomePageCommand());
        commands.put(TextConstants.GET + URI.LOGIN,                         new LoginPageCommand());
        commands.put(TextConstants.GET + URI.REGISTRATION,                  new RegistrationPageCommand());
        commands.put(TextConstants.GET + URI.CHECK,                         new CheckPageCommand());
        commands.put(TextConstants.GET + URI.CHECK + URI.PRODUCT,          new CheckProductPageCommand());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doRequest(req, resp);
    }

    private void doRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = getPage(req);
        if (page.startsWith(URI.REDIRECT)) {
            resp.sendRedirect(page.replaceAll(URI.REDIRECT, ""));
        } else {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }

    private String getPage(HttpServletRequest req) {
        String path = req.getRequestURI();
        path = path.replaceAll(".*/api" , "");
        path = req.getMethod() + ':' + path;
        Command command = commands.getOrDefault(path, (r) -> Pages.ERROR_404_PAGE);
        return command.execute(req);
    }
}
