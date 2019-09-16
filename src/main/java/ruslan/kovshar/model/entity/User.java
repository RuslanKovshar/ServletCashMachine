package ruslan.kovshar.model.entity;

import ruslan.kovshar.model.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Set;

public class User {

    private Long id;
    private String email;
    private String password;
    private String firstNameUA;
    private String secondNameUA;
    private String firstNameEN;
    private String secondNameEN;
    private Set<Roles> authorities;
    private Set<Check> checks;

    public User() {
    }

    public User(String email, String password, String firstNameUA, String secondNameUA, String firstNameEN, String secondNameEN) {
        this.email = email;
        this.password = password;
        this.firstNameUA = firstNameUA;
        this.secondNameUA = secondNameUA;
        this.firstNameEN = firstNameEN;
        this.secondNameEN = secondNameEN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstNameUA() {
        return firstNameUA;
    }

    public void setFirstNameUA(String firstNameUA) {
        this.firstNameUA = firstNameUA;
    }

    public String getSecondNameUA() {
        return secondNameUA;
    }

    public void setSecondNameUA(String secondNameUA) {
        this.secondNameUA = secondNameUA;
    }

    public String getFirstNameEN() {
        return firstNameEN;
    }

    public void setFirstNameEN(String firstNameEN) {
        this.firstNameEN = firstNameEN;
    }

    public String getSecondNameEN() {
        return secondNameEN;
    }

    public void setSecondNameEN(String secondNameEN) {
        this.secondNameEN = secondNameEN;
    }

    public Set<Roles> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Roles> authorities) {
        this.authorities = authorities;
    }

    public Set<Check> getChecks() {
        return checks;
    }

    public void setChecks(Set<Check> checks) {
        this.checks = checks;
    }

    public String i18nFirstName(HttpServletRequest request) {

        System.out.println(request.getLocalName());
        return  firstNameUA;
    }

    public boolean isCashier() {
        return authorities.contains(Roles.CASHIER);
    }

    public boolean isMerchandiser() {
        return authorities.contains(Roles.MERCHANDISER);
    }

    public boolean isSeniorCashier() {
        return authorities.contains(Roles.SENIOR_CASHIER);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstNameUA='" + firstNameUA + '\'' +
                ", secondNameUA='" + secondNameUA + '\'' +
                ", firstNameEN='" + firstNameEN + '\'' +
                ", secondNameEN='" + secondNameEN + '\'' +
                '}';
    }
}
