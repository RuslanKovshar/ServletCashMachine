package ruslan.kovshar.controller.dto;

public class UserDTO {

    private String email;
    private String password;
    private String firstNameUA;
    private String secondNameUA;
    private String firstNameEN;
    private String secondNameEN;

    public UserDTO(String email, String password, String firstNameUA, String secondNameUA, String firstNameEN, String secondNameEN) {
        this.email = email;
        this.password = password;
        this.firstNameUA = firstNameUA;
        this.secondNameUA = secondNameUA;
        this.firstNameEN = firstNameEN;
        this.secondNameEN = secondNameEN;
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
}
