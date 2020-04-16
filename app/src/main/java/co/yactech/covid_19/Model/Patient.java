package co.yactech.covid_19.Model;

public class Patient {

    //Username, Email, Password, Confirm Password, Image, Phone, address

    String Username,Email,Password,Image,Phone,address;

    public Patient(String username, String email, String password, String phone, String address,String image) {
        Username = username;
        Email = email;
        Password = password;
        Image = image;
        Phone = phone;
        this.address = address;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
