package cn.ynou.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by lch on 2017/4/24.
 */
@Entity
public class Schoolrolls {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String studentid;
    private String name;
    private String sex;
    private String nation;
    private String politicalstatus;
    private String nativeplace;
    private String education;
    private String maritalstatus;
    private String personid;
    private String learncenterid;
    private String department;
    private String subject;
    private String classname;
    private String trainschemid;
    private String startyear;
    private String startterm;
    private String schoolrollsstatus;
    private String contact;
    private String postaladdress;
    private String postalcode;
    private String verifystatus;
    private String verifyfaieldreason56;
    private Date   birthday;
    private String ds;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getPoliticalstatus() {
        return politicalstatus;
    }

    public void setPoliticalstatus(String politicalstatus) {
        this.politicalstatus = politicalstatus;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getPersonid() {
        return personid;
    }

    public void setPersonid(String personid) {
        this.personid = personid;
    }

    public String getLearncenterid() {
        return learncenterid;
    }

    public void setLearncenterid(String learncenterid) {
        this.learncenterid = learncenterid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTrainschemid() {
        return trainschemid;
    }

    public void setTrainschemid(String trainschemid) {
        this.trainschemid = trainschemid;
    }

    public String getStartyear() {
        return startyear;
    }

    public void setStartyear(String startyear) {
        this.startyear = startyear;
    }

    public String getStartterm() {
        return startterm;
    }

    public void setStartterm(String startterm) {
        this.startterm = startterm;
    }

    public String getSchoolrollsstatus() {
        return schoolrollsstatus;
    }

    public void setSchoolrollsstatus(String schoolrollsstatus) {
        this.schoolrollsstatus = schoolrollsstatus;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPostaladdress() {
        return postaladdress;
    }

    public void setPostaladdress(String postaladdress) {
        this.postaladdress = postaladdress;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getVerifystatus() {
        return verifystatus;
    }

    public void setVerifystatus(String verifystatus) {
        this.verifystatus = verifystatus;
    }

    public String getVerifyfaieldreason56() {
        return verifyfaieldreason56;
    }

    public void setVerifyfaieldreason56(String verifyfaieldreason56) {
        this.verifyfaieldreason56 = verifyfaieldreason56;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }
}
