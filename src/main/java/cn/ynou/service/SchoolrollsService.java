package cn.ynou.service;

import cn.ynou.model.PersonsOfStartYearAndSubject;
import cn.ynou.model.PersonsOfSubject;
import cn.ynou.model.Schoolrolls;
import cn.ynou.model.SchoolroolsCount;
import cn.ynou.repository.SchoolrollsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lch on 2017/4/24.
 */
@Service
@Transactional
public class SchoolrollsService {
    @Autowired
    private SchoolrollsRepository schoolrollsRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /***
     * 得到Schoolrolls所有数据
     * @return
     */
    public List<Schoolrolls> findAll() {
        return schoolrollsRepository.findAll();
    }

    /***
     * 获取Schoolrolls所有记录数
     * @return
     */
    public SchoolroolsCount getCount() {
        Long total = schoolrollsRepository.count();
        SchoolroolsCount cont = new SchoolroolsCount();
        cont.setTotal(total);
        return cont;
    }

    /***
     * 根据数据源（生源），入学年份，统计专业招生情况前几（mRowNumber）名
     * @param mDs  数据源
     * @param mYear 入学年份
     * @param mRowNumber 显示前几行
     * @return
     */
    public List<PersonsOfStartYearAndSubject> getPersonsWithYearAndSubject(String mDs, String mYear, Integer mRowNumber) {
        String sql = "SELECT * FROM (\n" +
                "        SELECT STARTYEAR,SUBJECT,COUNT（*） AS persons \n" +
                "        FROM SCHOOLROLLS\n" +
                "        WHERE DS = ? \n" +
                "        GROUP BY STARTYEAR ,SUBJECT\n" +
                "        HAVING STARTYEAR = ? \n" +
                "        ORDER BY STARTYEAR,persons DESC) Temp \n" +
                "WHERE rownum <= ? ";
        List<PersonsOfStartYearAndSubject> list = jdbcTemplate.query(sql,
                new PersonsOfYearAndSubjectRowmapper(),
                new Object[]{mDs, mYear, mRowNumber});
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    /***
     * 根据数据源（生源），统计专业招生情况前几（mRowNumber）名
     * @param mDs
     * @param mRowNumber
     * @return
     */
    public List<PersonsOfSubject> getPersonsOfSubject(String mDs, Integer mRowNumber){
        String sql = "SELECT * FROM (\n" +
                "SELECT SUBJECT ,COUNT(*) AS persons FROM SCHOOLROLLS\n" +
                "WHERE ds = ?\n" +
                "GROUP BY SUBJECT\n" +
                "ORDER BY persons DESC\n" +
                ") temp\n" +
                "WHERE ROWNUM <= ?";
        List<PersonsOfSubject> list = jdbcTemplate.query(sql,
                new PersonsOfSubjectRowMapper(),
                new Object[]{mDs,mRowNumber});
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }

    }


    /***
     * 得到Schoolrolls前几行数据
     * @param rowNumber
     * @return
     */
    public List<Schoolrolls> listNumberOfSchoolrolls(long rowNumber) {
        String sql = "SELECT * FROM schoolrolls WHERE rownum <= ?";
        List<Schoolrolls> list = jdbcTemplate.query(sql, new SchoolrollsRowMapper(), new Object[]{rowNumber});
        if (list != null && list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

    /***
     * 根据学号得到Schoolrolls实体（列表）
     * @param studentId
     * @return
     */
    public List<Schoolrolls> findByStudentid(String studentId) {
        return schoolrollsRepository.findByStudentid(studentId);
    }

    /***
     * 根据姓名得到Schoolrolls实体（列表）
     * @param name
     * @return
     */
    public List<Schoolrolls> findByName(String name) {
//        String sql = "SELECT * FROM schoolrolls WHERE name = ?";
//        List<Schoolrolls> list = jdbcTemplate.query(sql, new SchoolrollsRowMapper(), new Object[]{name});
//        if (list != null && list.size() > 0) {
//            return list;
//        } else {
//            return null;
//        }
        return schoolrollsRepository.findByName(name);
    }

    /***
     * 统计姓名的个数
     * @param name
     * @return
     */
    public Long countByName(String name) {
//        String sql = "SELECT COUNT(name) FROM schoolrolls WHERE name = ?";
//        return jdbcTemplate.queryForObject(sql,Long.class,new Object[]{name});
        return schoolrollsRepository.countByName(name);
    }

    public List<Schoolrolls> findByStudentidLike(String studentId){
        return schoolrollsRepository.findByStudentidLike(studentId);
    }

    public List<Schoolrolls> findByNameLike(String name) {
        return schoolrollsRepository.findByNameLike(name);
    }

    public Long countByNameLike(String name) {
        return schoolrollsRepository.countByNameLike(name);
    }


    class PersonsOfSubjectRowMapper implements RowMapper<PersonsOfSubject>{
        @Override
        public PersonsOfSubject mapRow(ResultSet resultSet, int i) throws SQLException {
            PersonsOfSubject personsOfSubject = new PersonsOfSubject();
            personsOfSubject.setSubject(resultSet.getString("subject"));
            personsOfSubject.setNumberOfPersons(resultSet.getInt("persons"));
            return personsOfSubject;
        }
    }


    class PersonsOfYearAndSubjectRowmapper implements RowMapper<PersonsOfStartYearAndSubject> {

        @Override
        public PersonsOfStartYearAndSubject mapRow(ResultSet resultSet, int i) throws SQLException {
            PersonsOfStartYearAndSubject personsOfYearAndSubject = new PersonsOfStartYearAndSubject();
            personsOfYearAndSubject.setStartYear(resultSet.getString("startyear"));
            personsOfYearAndSubject.setSubject(resultSet.getString("subject"));
            personsOfYearAndSubject.setNumberOfPersons(resultSet.getInt("persons"));
            return personsOfYearAndSubject;
        }
    }

    class SchoolrollsRowMapper implements RowMapper<Schoolrolls> {
        @Override
        public Schoolrolls mapRow(ResultSet resultSet, int i) throws SQLException {
            Schoolrolls schoolrolls = new Schoolrolls();
            schoolrolls.setId(resultSet.getString("id"));
            schoolrolls.setStudentid(resultSet.getString("studentid"));
            schoolrolls.setName(resultSet.getString("name"));
            schoolrolls.setSex(resultSet.getString("sex"));
            schoolrolls.setNation(resultSet.getString("nation"));
            schoolrolls.setPoliticalstatus(resultSet.getString("politicalstatus"));
            schoolrolls.setNativeplace(resultSet.getString("nativeplace"));
            schoolrolls.setEducation(resultSet.getString("education"));
            schoolrolls.setMaritalstatus(resultSet.getString("maritalstatus"));
            schoolrolls.setPersonid(resultSet.getString("personid"));
            schoolrolls.setLearncenterid(resultSet.getString("learncenterid"));
            schoolrolls.setDepartment(resultSet.getString("department"));
            schoolrolls.setSubject(resultSet.getString("subject"));
            schoolrolls.setClassname(resultSet.getString("classname"));
            schoolrolls.setTrainschemid(resultSet.getString("trainschemid"));
            schoolrolls.setStartyear(resultSet.getString("startyear"));
            schoolrolls.setStartterm(resultSet.getString("startterm"));
            schoolrolls.setSchoolrollsstatus(resultSet.getString("schoolrollsstatus"));
            schoolrolls.setContact(resultSet.getString("contact"));
            schoolrolls.setPostaladdress(resultSet.getString("postaladdress"));
            schoolrolls.setPostalcode(resultSet.getString("postalcode"));
            schoolrolls.setVerifystatus(resultSet.getString("verifystatus"));
            schoolrolls.setVerifyfaieldreason56(resultSet.getString("verifyfaieldreason56"));
            schoolrolls.setBirthday(resultSet.getDate("birthday"));
            schoolrolls.setDs(resultSet.getString("ds"));
            return schoolrolls;
        }
    }

}

