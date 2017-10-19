package cn.ynou.service;

import cn.ynou.model.ListSchool;
import cn.ynou.model.ShowOneSchoolInfo;
import cn.ynou.model.YnouJiaoxuedian;
import cn.ynou.model.YnouXdhzStudent;
import cn.ynou.repository.YnouJiaoxuedianRepository;
import cn.ynou.repository.YnouXdhzStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.MessageFormat;
import java.util.Map;

/**
 * Created by lch on 2017/5/3.
 */
@Service
@Transactional
public class ListSchoolService {
    @Autowired
    private YnouJiaoxuedianRepository ynouJiaoxuedianRepository;

    @Autowired
    private YnouXdhzStudentRepository ynouXdhzStudentRepository;


//    @Autowired
//    private JdbcTemplate jdbcTemplate;

    private static final String WEBSERVERADDRESS = "http://softdev.cmxy.ynou.edu.cn:8080/";
    //private static final String WEBSERVERADDRESS = "http://127.0.0.1:8080/";
    private static final String IMGROOTPATH = WEBSERVERADDRESS + "img/";
    private static Map<String, String> imgMap;

    static {
        imgMap = new HashMap<String, String>();
        imgMap.put("宾川县职业高级中学", "binchuan.jpg");
        imgMap.put("沧源县职业高级中学", "cangyuan.jpg");
        imgMap.put("大理漾濞职业高级中学", "yangbi.jpg");
        imgMap.put("迪庆开放学院", "diqing.jpg");
        imgMap.put("凤庆县职教中心", "fengqing.jpg");
        imgMap.put("临翔区职业教育中心", "linxiang.jpg");
        imgMap.put("文山市职业高级中学教学点", "wenshan.jpg");
        imgMap.put("武定县委党校教学点", "wuding.jpg");
        imgMap.put("砚山县委党校", "yanshan.jpg");
        imgMap.put("永德县职教中心", "yongde.jpg");
        imgMap.put("云南开放大学继续教育学院（宾川）", "binchuan.jpg");
        imgMap.put("云县电大工作站", "yunxian.jpg");
        imgMap.put("沾益县职业教育培训中心", "zhanyi.jpg");
        imgMap.put("镇康县职教中心", "zhenkang.jpg");
        imgMap.put("中共马龙县委党校", "malong.jpg");
    }

    private static String getImgPath(String ycyName) {
        if (!imgMap.isEmpty()) {
            if (!(imgMap.get(ycyName) == null)) {
                return imgMap.get(ycyName);
            }
        }
        return "default.jpg";
    }

    //定义表格样式
    private static final String TABLESTYLE = "table table-bordered table-striped table-hover table-condensed table-responsive";
    //定义图片样式
    private static final String IMAGESTYLE = "img-responsive center-block img-rounded ";

    //定义html上半部分
    private static final String HTMLHEAD = "<!DOCTYPE html>" +
            "<html lang='zh-CN'>" +
            "  <head>" +
            "    <meta charset='utf-8'>" +
            "    <meta http-equiv='X-UA-Compatible' content='IE=edge'>" +
            "    <meta name='viewport' content='width=device-width, initial-scale=1'>" +
            "    <title></title>" +
            "    <link rel='stylesheet' href='https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css' integrity='sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u' crossorigin='anonymous'>" +
            "  </head>" +
            "  <body>" +
            "  <div class='container'>" +
            //"   <table class = 'table table-bordered table-striped table-hover table-condensed table-responsive'>";
            "   <table class = ''{0}''>";


    //定义html下半部分
    private static final String HTMLTAIL =  "   </table>" +
            "  <div>" +
            "  </body>" +
            "</html>";




    private static final String YCYHTMLFRAGMENT =
            //"<table width=400 height=300 border=''1'' bgcolor= ''#fffff0'' style= ''border: beige''>" +
            //"<table width=400  height=300 border='1' bgcolor='#fffff0' style='border: beige'  >" +  IMAGESTYLE
                    "  <tr class = 'info'>" +
                    "  <td width='100'><b>教学点名称：</b></td>" +
                    "        <td>{1}</td>" +
                    "  </tr>" +
                    "  <tr>" +
                    "  <td width='400' colspan='2' height=200 > <img class = ''{2}'' src= ''{3}'' height='200' width='400' alt = {4}/> </td>" +
                    "  </tr>" +
                    "" +
                    "  <tr>" +
                    "  <td width='400' colspan='2'><b>招生情况</b></td>" +
                    "  </tr>" +
                    "  <tr>" +
                    "  <td width='100'><b>2015秋</b></td>" +
                    "        <td width='300'>{5}</td>" +
                    "  </tr>" +
                    "  <tr>" +
                    "  <td width='100'><b>2015春</b></td>" +
                    "        <td width='300'>{6}</td>" +
                    "  </tr>" +
                    "  <tr>" +
                    "  <td width='100'><b>2016秋</b></td>" +
                    "        <td width='300'>{7}</td>" +
                    "  </tr>";


    private static final String JXDHTMLFRAGMENT =
                    "    <tr class = 'info'>" +
                    "        <td><b>教学点名称</b></td>" +
                    "        <td colspan='3'>{1}</td>" +
                    "    </tr>" +
                    "    <tr>" +
                    "        <td colspan='4'><img class = ''{2}'' src=''{3}'' width='400' height='200' alt={4}></td>" +
                    "    </tr>" +
                    "    <tr>" +
                    "        <td><b>教学点属性</b></td>" +
                    "        <td colspan='3'>{5}</td>" +
                    "    </tr>" +
                    "    <tr>" +
                    "        <td><b>教学点地址</b></td>" +
                    "        <td colspan='3'>{6}</td>" +
                    "    </tr>" +
                    "    <tr>" +
                    "        <td><b>联系人</b></td>" +
                    "        <td>{7}</td>" +
                    "        <td><b>电话</b></td>" +
                    "        <td>{8}</td>" +
                    "    </tr>" +
                    "    <tr>" +
                    "        <td><b>办学层次</b></td>" +
                    "        <td colspan='3'>{9}</td>" +
                    "    </tr>";


    private List<YnouJiaoxuedian> getListYnouJiaoxuedian() {
        return ynouJiaoxuedianRepository.findAll();
    }

    private List<YnouJiaoxuedian> getListProvinceYnouJiaoxuedian() {
        return ynouJiaoxuedianRepository.findByAttribute("省属分校");
    }

    private List<YnouXdhzStudent> getYnouXdhzStudentlist() {
        return ynouXdhzStudentRepository.findAll();
    }


    /***
     * 获取所有一村一
     * @return
     */
    private List<ShowOneSchoolInfo> getYcySchoolInfoList() {
        List<ShowOneSchoolInfo> listSchoolInfo = new ArrayList<ShowOneSchoolInfo>();
        List<YnouXdhzStudent> ynouXdhzStudentList = getYnouXdhzStudentlist();
        for (YnouXdhzStudent temp : ynouXdhzStudentList) {
            ShowOneSchoolInfo oneSchoolInfo = new ShowOneSchoolInfo();
            oneSchoolInfo.setSchool(temp.getName());
            oneSchoolInfo.setLatitude(temp.getWd().toString());
            oneSchoolInfo.setLongitude(temp.getJd().toString());
            oneSchoolInfo.setInfomation(MessageFormat.format(HTMLHEAD + YCYHTMLFRAGMENT + HTMLTAIL,
                    TABLESTYLE,//{0}
                    temp.getName(),   //{1}
                    IMAGESTYLE,//{2}
                    IMGROOTPATH + getImgPath(temp.getName()), //{3}
                    temp.getName(), //{4}
                    temp.getPeriod1(), //{5}
                    temp.getPeriod2(), ////{6}
                    temp.getPeriod3())); ////{7}
            listSchoolInfo.add(oneSchoolInfo);
        }
        //System.out.printf("getYcySchoolInfoList() length is :" + listSchoolInfo.size());
        return listSchoolInfo;
    }

    /***
     * 获取教学点信息
     * @param isProvince 是否为省直属高校
     * @return
     */
    private List<ShowOneSchoolInfo> getSchoolInfoList(boolean isProvince) {
        List<ShowOneSchoolInfo> listSchoolInfo = new ArrayList<ShowOneSchoolInfo>();
        List<YnouJiaoxuedian> listYnouJiaoxuedian;
        if (isProvince) {
            listYnouJiaoxuedian = getListProvinceYnouJiaoxuedian();
        } else {
            listYnouJiaoxuedian = getListYnouJiaoxuedian();
        }
        for (YnouJiaoxuedian temp : listYnouJiaoxuedian) {
            ShowOneSchoolInfo oneSchoolInfo = new ShowOneSchoolInfo();
            oneSchoolInfo.setSchool(temp.getName());
            oneSchoolInfo.setLatitude(temp.getWd().toString());
            oneSchoolInfo.setLongitude(temp.getJd().toString());
            oneSchoolInfo.setInfomation(MessageFormat.format(HTMLHEAD + JXDHTMLFRAGMENT + HTMLTAIL,
                    TABLESTYLE, //0
                    temp.getName(), //1
                    IMAGESTYLE, //2
                    IMGROOTPATH + getImgPath(temp.getName()),//3
                    temp.getName(), //4
                    temp.getAttribute(), //5
                    temp.getAddress(), //6
                    temp.getContact(), //7
                    temp.getPhone(), //8
                    getSchoolEduMode(temp) //9
            ));
            listSchoolInfo.add(oneSchoolInfo);
        }
        //System.out.printf("getListSchoolInfo() length is :" + listSchoolInfo.size());
        return listSchoolInfo;
    }

    /***
     * 获取指定教学点的办学层次（本科，专科，一村一）
     * @param ynouJiaoxuedian
     * @return
     */
    private String getSchoolEduMode(YnouJiaoxuedian ynouJiaoxuedian) {
        String result = "";
        if (!(ynouJiaoxuedian.getStudentBk() == null || ynouJiaoxuedian.getStudentBk().equals("null"))) {
            result += ynouJiaoxuedian.getStudentBk() + "、";
        }
        if (!(ynouJiaoxuedian.getStudentZk() == null || ynouJiaoxuedian.getStudentZk().equals("null"))) {
            result += ynouJiaoxuedian.getStudentZk() + "、";
        }
        if (!(ynouJiaoxuedian.getStudentYcy() == null || ynouJiaoxuedian.getStudentYcy().equals("null"))) {
            result += ynouJiaoxuedian.getStudentYcy();
        }
        if (result.endsWith("、")) {
            result.substring(0, result.length() - 1);
        }
        return result;
    }


    public ListSchool getSchoolCoordinateListInfo() {
        ListSchool listSchool = new ListSchool();
        listSchool.setMarkers(getSchoolInfoList(false));
        return listSchool;
    }

    public ListSchool getProvinceSchoolCoordinateListInfo() {
        ListSchool listSchool = new ListSchool();
        listSchool.setMarkers(getSchoolInfoList(true));
        return listSchool;
    }

    public ListSchool getYcySchoolCoordinateListInfo() {
        ListSchool listSchool = new ListSchool();
        listSchool.setMarkers(getYcySchoolInfoList());
        return listSchool;
    }


}
