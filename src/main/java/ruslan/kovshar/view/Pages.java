package ruslan.kovshar.view;

public interface Pages {
    String WEB_INF = "/WEB-INF/view/";
    String JSP = ".jsp";

    String HOME_PAGE = WEB_INF + "home" + JSP;
    String LOGIN_PAGE = WEB_INF + "login" + JSP;
    String MERCHANDISER_PAGE = WEB_INF + "merchandiser" + JSP;
    String CHECK_PAGE = WEB_INF + "check" + JSP;
    String PRODUCT_PAGE = WEB_INF + "product" + JSP;
    String REGISTRATION_PAGE = WEB_INF + "registration" + JSP;
    String X_REPORT_PAGE = WEB_INF + "x-report" + JSP;
    String Z_REPORT_PAGE = WEB_INF + "z_report" + JSP;
    String ERROR_404_PAGE = WEB_INF + "404" + JSP;
}
