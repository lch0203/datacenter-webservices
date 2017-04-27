package cn.ynou.controller;

import cn.ynou.model.PersonsOfStartYearAndSubject;
import cn.ynou.model.PersonsOfSubject;
import cn.ynou.model.Schoolrolls;
import cn.ynou.model.SchoolroolsCount;
import cn.ynou.service.SchoolrollsService;
import io.swagger.annotations.Api;
import jdk.internal.instrumentation.InstrumentationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lch on 2017/4/24.
 */
@RestController
@RequestMapping("/schoolrools")
@Api(tags={"schoolrolls"})
@CrossOrigin
public class SchoolrollsController {
    @Autowired
    private SchoolrollsService schoolrollsService;

    /***
     * 获取schoolrools记录总数（学生总数）
     * @return
     */
    @GetMapping("/count")
    public SchoolroolsCount getSchoolrollsCount() {
        return schoolrollsService.getCount();
    }

    /***
     * 获取schoolrools指定行数（num）的数据
     * @param num
     * @return
     */
    @GetMapping("/list/{num}")
    public List<Schoolrolls> listNumberOfSchoolrolls(@PathVariable long num) {
        long rows = num;
        if (rows <= 0) {
            return null;
        }
        return schoolrollsService.listNumberOfSchoolrolls(rows);
    }

    /***
     * 获取指定入学年份，数据源，专业的人数（指定rows行）
     * @param ds
     * @param year
     * @param rows
     * @return
     */
    @GetMapping("/startyear/subject/persons/{ds}/{year}/{rows}")
    public List<PersonsOfStartYearAndSubject> getPersonsWithYearAndSubject(
            @PathVariable String ds,
            @PathVariable String year,
            @PathVariable Integer rows) {
        return schoolrollsService.getPersonsWithYearAndSubject(ds, year, rows);
    }

    /***
     * 获取指定数据源（ds）中学生数最多的几行（rows）
     * @param ds
     * @param rows
     * @return
     */
    @GetMapping("/subject/persons/{ds}/{rows}")
    public List<PersonsOfSubject> getPersonsOfSubject(
            @PathVariable String ds,
            @PathVariable Integer rows){
        return schoolrollsService.getPersonsOfSubject(ds,rows);
    }

    /***
     * 按学号查找Schoolrolls实体
     * @param studentId
     * @return
     */
    @GetMapping("/studentid/{studentId}")
    public List<Schoolrolls> findByStudentid(@PathVariable String studentId) {
        return schoolrollsService.findByStudentid(studentId);
    }

    /***
     * 按姓名查找Schoolrolls实体
     * @param name
     * @return
     */
    @PostMapping("/name")
    public List<Schoolrolls> findByName(@RequestParam String name) {
        return schoolrollsService.findByName(name);
    }

    /***
     * 按姓名查找记录的记录数
     * @param name
     * @return
     */
    @PostMapping("/countbyname")
    public Long countByName(@RequestParam String name) {
        return schoolrollsService.countByName(name);
    }


    /***
     * 模糊查询学号的Schoolrolls实体
     * @param studentId
     * @return
     */
    //@GetMapping("/studentidlike/{studentId}")
    //public List<Schoolrolls> findByStudentidLike(@PathVariable  String studentId) {
    @PostMapping("/studentidlike")
    public List<Schoolrolls> findByStudentidLike(@RequestParam String studentId) {
        return schoolrollsService.findByStudentidLike(studentId);
    }


    /***
     * 模糊查询姓名的Schoolrolls实体
     * @param name
     * @return
     */
    @PostMapping("/namelike")
    public List<Schoolrolls> findByNamelike(@RequestParam String name) {
        return schoolrollsService.findByNameLike(name);
    }

    /***
     * 模糊查询姓名的记录数
     * @param name
     * @return
     */
    @PostMapping("/countbynamelike")
    public Long countByNameLike(@RequestParam String name) {
        return schoolrollsService.countByNameLike(name);
    }
}
