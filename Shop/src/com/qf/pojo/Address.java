package com.qf.pojo;

//收货地址类
public class Address {

    private int id;
    private String shouhuoren;
    private String phone;
    private String address;
    private int userid;
    private String isdefault;

    public Address() {
    }

    public Address(int id, String shouhuoren, String phone, String address, int userid, String isdefault) {
        this.id = id;
        this.shouhuoren = shouhuoren;
        this.phone = phone;
        this.address = address;
        this.userid = userid;
        this.isdefault = isdefault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShouhuoren() {
        return shouhuoren;
    }

    public void setShouhuoren(String shouhuoren) {
        this.shouhuoren = shouhuoren;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", shouhuoren='" + shouhuoren + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", userid=" + userid +
                ", isdefault='" + isdefault + '\'' +
                '}';
    }
}
