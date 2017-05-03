package cn.ynou.model;

import org.codehaus.groovy.runtime.dgmimpl.arrays.IntegerArrayGetAtMetaMethod;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by lch on 2017/5/3.
 */
@Entity
public class YnouXdhzStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;
    private String name;
    private Integer period1; //2015秋
    private Integer period2; //2016春
    private Integer period3; //2016秋
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

    public Integer getPeriod1() {
        return period1;
    }

    public void setPeriod1(Integer period1) {
        this.period1 = period1;
    }

    public Integer getPeriod2() {
        return period2;
    }

    public void setPeriod2(Integer period2) {
        this.period2 = period2;
    }

    public Integer getPeriod3() {
        return period3;
    }

    public void setPeriod3(Integer period3) {
        this.period3 = period3;
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
