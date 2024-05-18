package sample.eknbankapp;

public class Employee {

    String firstName,lastName,identityNumber,mail,password;

    public Employee(String firstName, String lastName, String identityNumber, String mail, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
        this.mail = mail;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String identityNumber2() {
        EmployeeController employeeController = new EmployeeController();
        String iden = employeeController.identityNumber;
        return iden;
    }

}
