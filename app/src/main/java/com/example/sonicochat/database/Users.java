package com.example.sonicochat.database;


public class Users {
    String profilepic,mail,userName,password,pas2,userId,lastMessage,status;

    public  Users(){}

    public Users(String userId, String userName, String maill, String password, String profilepic, String status) {
        this.userId = userId;
        this.userName = userName;
        this.mail = maill;
        this.password = password;
        this.profilepic = profilepic;
        this.status = status;
    }
    public Users(String profilepic, String userName, String mail, String password1, String password2){
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.password = password1;
        this.pas2 = password2;
    }


    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

