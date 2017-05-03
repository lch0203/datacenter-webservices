package cn.ynou.controller;

import cn.ynou.model.ListSchool;
import cn.ynou.service.ListSchoolService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lch on 2017/5/3.
 */

@RestController
@RequestMapping("/listschoolinfo")
@Api(tags={"listschoolinfo"})
@CrossOrigin
public class ListSchoolInfoController {
    @Autowired
    private ListSchoolService listSchoolService;

    @GetMapping("/jxd") //教学点
    public ListSchool getListJXD(){
        return listSchoolService.getSchoolCoordinateListInfo();
    }

    @GetMapping("/ssfx") //省属分校
    public ListSchool getlistProvince(){
        return listSchoolService.getProvinceSchoolCoordinateListInfo();
    }

    @GetMapping("/ycy") //省属分校
    public ListSchool getlistYcy(){
        return listSchoolService.getYcySchoolCoordinateListInfo();
    }

}
