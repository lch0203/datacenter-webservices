package cn.ynou.model;

/**
 * Created by lch on 2017/4/25.
 */
public class PersonsOfStartYearAndSubject {
    private String  startYear;
    private String  subject;
    private Integer numberOfPersons;

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(Integer numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }
}
