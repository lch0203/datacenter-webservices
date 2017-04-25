package cn.ynou.controller;

import cn.ynou.model.PersonsOfStartYearAndSubject;
import cn.ynou.model.Schoolrolls;
import cn.ynou.model.SchoolroolsCount;
import cn.ynou.service.SchoolrollsService;
import jdk.internal.instrumentation.InstrumentationTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by lch on 2017/4/24.
 */
@RestController
@RequestMapping("/schoolrools")
public class SchoolrollsController {
    @Autowired
    private SchoolrollsService schoolrollsService;

    @GetMapping("/count")
    public SchoolroolsCount getSchoolrollsCount() {
        return schoolrollsService.getCount();
    }

    @GetMapping("/list/{num}")
    public List<Schoolrolls> listNumberOfSchoolrolls(@PathVariable long num) {
        long rows = num;
        if (rows <= 0) {
            return null;
        }
        return schoolrollsService.listNumberOfSchoolrolls(rows);
    }

    @GetMapping("/startyear/subject/persons/{ds}/{year}/{rows}")
    public List<PersonsOfStartYearAndSubject> getNumberofPersonsWithYearAndSubject(
            @PathVariable String ds,
            @PathVariable String year,
            @PathVariable Integer rows) {
        return schoolrollsService.getNumberofPersonsWithYearAndSubject(ds, year, rows);
    }

    @GetMapping("/studentid/{studentId}")
    public List<Schoolrolls> findByStudentid(@PathVariable String studentId) {
        return schoolrollsService.findByStudentid(studentId);
    }

    @PostMapping("/name")
    public List<Schoolrolls> findByName(@RequestParam String name) {
        return schoolrollsService.findByName(name);
    }

    @PostMapping("/countbyname")
    public Long countByName(@RequestParam String name) {
        return schoolrollsService.countByName(name);
    }

    //@GetMapping("/studentidlike/{studentId}")
    //public List<Schoolrolls> findByStudentidLike(@PathVariable  String studentId) {
    @PostMapping("/studentidlike")
    public List<Schoolrolls> findByStudentidLike(@RequestParam String studentId) {
        return schoolrollsService.findByStudentidLike(studentId);
    }

    @PostMapping("/namelike")
    public List<Schoolrolls> findByNamelike(@RequestParam String name) {
        return schoolrollsService.findByNameLike(name);
    }

    @PostMapping("/countbynamelike")
    public Long countByNameLike(@RequestParam String name) {
        return schoolrollsService.countByNameLike(name);
    }
}
