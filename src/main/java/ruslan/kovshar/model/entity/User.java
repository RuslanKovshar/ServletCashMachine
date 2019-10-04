package ruslan.kovshar.model.entity;

import ruslan.kovshar.model.enums.Roles;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Represents an User Entity
 */
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
    private BigDecimal userCash;

    public static class Builder {
        private Long id;
        private String email;
        private String password;
        private String firstNameUA;
        private String secondNameUA;
        private String firstNameEN;
        private String secondNameEN;
        private Set<Roles> authorities;
        private Set<Check> checks;
        private BigDecimal userCash;

        public Builder() {

        }

        public Builder id(Long val){
            id = val;
            return this;
        }

        public Builder email(String val){
            email = val;
            return this;
        }

        public Builder password(String val){
            password = val;
            return this;
        }

        public Builder firstNameUA(String val){
            firstNameUA = val;
            return this;
        }

        public Builder secondNameUA(String val){
            secondNameUA = val;
            return this;
        }

        public Builder firstNameEN(String val){
            firstNameEN = val;
            return this;
        }

        public Builder secondNameEN(String val){
            secondNameEN = val;
            return this;
        }

        public Builder authorities(Set<Roles> val){
            authorities = val;
            return this;
        }

        public Builder checks(Set<Check> val) {
            checks = val;
            return this;
        }

        public Builder userCash(BigDecimal val) {
            userCash = val;
            return this;
        }

        public User build(){
            return new User(this);
        }

    }

    private User(Builder builder) {
        id = builder.id;
        email = builder.email;
        password = builder.password;
        firstNameUA = builder.firstNameUA;
        secondNameUA = builder.secondNameUA;
        firstNameEN = builder.firstNameEN;
        secondNameEN = builder.secondNameEN;
        authorities = builder.authorities;
        checks = builder.checks;
        userCash = builder.userCash;
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

    public BigDecimal getUserCash() {
        return userCash;
    }

    public void setUserCash(BigDecimal userCash) {
        this.userCash = userCash;
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

    public boolean isAdmin() {
        return authorities.contains(Roles.ADMIN);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstNameUA='" + firstNameUA + '\'' +
                ", secondNameUA='" + secondNameUA + '\'' +
                ", firstNameEN='" + firstNameEN + '\'' +
                ", secondNameEN='" + secondNameEN + '\'' +
                ", authorities=" + authorities +
                ", checks=" + checks +
                ", userCash=" + userCash +
                '}';
    }
}
