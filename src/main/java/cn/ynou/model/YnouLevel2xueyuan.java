package cn.ynou.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by lch on 2017/10/21.
 */
@Entity
public class YnouLevel2xueyuan {  //YNOU_LEVEL2XUEYUAN  YnouLevel2xueyuan
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String name;
    private String pzTime;
    private String gpTime;
    private String ytjs;
    private String stateName; //STATE_NAME
    private String address;
    private String contact;//CONTACT;
    private String zhiwu;
    private String phone;
    private String yuanzhang;
    private String yzPhone;
    private Double jd;
    private Double wd;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPzTime() {
        return pzTime;
    }

    public void setPzTime(String pzTime) {
        this.pzTime = pzTime;
    }

    public String getGpTime() {
        return gpTime;
    }

    public void setGpTime(String gpTime) {
        this.gpTime = gpTime;
    }

    public String getYtjs() {
        return ytjs;
    }

    public void setYtjs(String ytjs) {
        this.ytjs = ytjs;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public String getZhiwu() {
        return zhiwu;
    }

    public void setZhiwu(String zhiwu) {
        this.zhiwu = zhiwu;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getYuanzhang() {
        return yuanzhang;
    }

    public void setYuanzhang(String yuanzhang) {
        this.yuanzhang = yuanzhang;
    }

    public String getYzPhone() {
        return yzPhone;
    }

    public void setYzPhone(String yzPhone) {
        this.yzPhone = yzPhone;
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
