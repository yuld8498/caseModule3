package Model;

public class User{
    private int userID;
    private String userName;
    private String password;
    private String fullName;
    private int age;
    private String email;
    private String phoneNumber;
    private String address;

    public User() {
    }

    public User(int userID, String fullName, int age, String email, String phoneNumber, String address) {
        this.userID = userID;
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(String fullName, int age, String email, String phoneNumber, String address) {
        this.fullName = fullName;
        this.age = age;
        this.email=email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(int userID, String userName, String password, String fullName, int age, String email, String phoneNumber, String address) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.email=email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
