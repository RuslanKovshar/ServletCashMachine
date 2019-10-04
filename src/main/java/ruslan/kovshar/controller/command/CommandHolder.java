package ruslan.kovshar.controller.command;

import ruslan.kovshar.controller.command.get.*;
import ruslan.kovshar.controller.command.post.*;
import ruslan.kovshar.textconstants.TextConstants;
import ruslan.kovshar.textconstants.URI;

import java.util.Map;

/**
 * contains all commands
 */
public class CommandHolder {

    public static void init(Map<String,Command> commands) {
        commands.put(TextConstants.POST + URI.LOGIN,                        new LoginCommand());
        commands.put(TextConstants.POST + URI.REGISTRATION,                 new RegistrationCommand());
        commands.put(TextConstants.POST + URI.LOGOUT,                       new LogoutCommand());
        commands.put(TextConstants.POST + URI.MERCHANDISER + URI.PRODUCT,   new MerchandiserProductCommand());
        commands.put(TextConstants.POST + URI.MERCHANDISER + URI.STOCK,     new MerchandiserStockCommand());
        commands.put(TextConstants.POST + URI.OPEN_CHECK,                   new OpenCheckCommand());
        commands.put(TextConstants.POST + URI.CHECK + URI.PRODUCT,          new CheckProductCommand());
        commands.put(TextConstants.POST + URI.CHECK + URI.ADD_PRODUCT,      new CheckAddProduct());
        commands.put(TextConstants.POST + URI.CLOSE_CHECK,                  new CloseCheckCommand());
        commands.put(TextConstants.POST + URI.CHECK + URI.REMOVE_PRODUCT,   new CheckRemoveProductCommand());
        commands.put(TextConstants.POST + URI.PAYMENT,                      new PaymentCommand());
        commands.put(TextConstants.POST + URI.CANCEL_CHECK,                 new CancelCheckCommand());
        commands.put(TextConstants.POST + URI.USER,                         new EditUserCommand());

        commands.put(TextConstants.GET + URI.USER,                          new EditUserPageCommand());
        commands.put(TextConstants.GET + URI.USERS,                         new AllUsersPageCommand());
        commands.put(TextConstants.GET + URI.CHECKS,                        new AllChecksPageCommand());
        commands.put(TextConstants.GET + URI.PAYMENT,                       new PayingPageCommand());
        commands.put(TextConstants.GET + URI.MERCHANDISER,                  new MerchandiserPageCommand());
        commands.put(TextConstants.GET + URI.HOME,                          new HomePageCommand());
        commands.put(TextConstants.GET + URI.LOGIN,                         new LoginPageCommand());
        commands.put(TextConstants.GET + URI.REGISTRATION,                  new RegistrationPageCommand());
        commands.put(TextConstants.GET + URI.CHECK,                         new CheckPageCommand());
        commands.put(TextConstants.GET + URI.CHECK + URI.PRODUCT,           new CheckProductPageCommand());
        commands.put(TextConstants.GET + URI.Z_REPORT,                      new ZReportPageCommand());
        commands.put(TextConstants.GET + URI.X_REPORT,                      new XReportPageCommand());
    }
}
