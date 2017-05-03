package cn.ynou.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by lch on 2017/5/3.
 */
@Entity
public class YnouJiaoxuedian {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String stateName;
    private String county;
    private String name;
    private String attribute;
    private String address;
    private String contact;
    private String phone;
    private String code;
    private String studentBk;
    private String studentZk;
    private String studentYcy;
    private Double jd;
    private Double wd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStudentBk() {
        return studentBk;
    }

    public void setStudentBk(String studentBk) {
        this.studentBk = studentBk;
    }

    public String getStudentZk() {
        return studentZk;
    }

    public void setStudentZk(String studentZk) {
        this.studentZk = studentZk;
    }

    public String getStudentYcy() {
        return studentYcy;
    }

    public void setStudentYcy(String studentYcy) {
        this.studentYcy = studentYcy;
    }

    public Double getJd() {
        return jd;
    }

    public void setJd(Double jd) {
        this.jd = jd;
    }

    public Double getWd() {
        return wd;
    }

    public void setWd(Double wd) {
        this.wd = wd;
    }
}
