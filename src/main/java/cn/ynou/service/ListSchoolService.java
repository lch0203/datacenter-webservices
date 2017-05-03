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
import java.util.List;
import java.text.MessageFormat;

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

//    private final  String  outHtmlFragment = MessageFormat.format("<table width=400 height=200 border=1>\n" +
//            "  <tr>\n" +
//            "  \t<td>{0}</td>\n" +
//            "  </tr>\n" +
//            "  <tr>\n" +
//            "  \t<td width='400'> <img src='{1}'> \n" +
//            "  </td></tr>\n" +
//            "\n" +
//            "</table>",);

    private final  String outHtmlFragment = "<table width=400 height=300 border= 1>" +
            "  <tr>" +
            "  <td width='100'><b>教学点名称：</b></td>" +
            "        <td>{0}</td>" +
            "  </tr>" +
            "  <tr>" +
            "  <td width='400' colspan='2' height=200 > <img src= ''{1}'' height='200' width='400' /> </td>" +
            "  </tr>" +
            "" +
            "  <tr>" +
            "  <td width='400' colspan='2'><b>招生情况</b></td>" +
            "  </tr>" +
            "  <tr>" +
            "  <td width='100'><b>2015秋</b></td>" +
            "        <td width='300'>{2}</td>" +
            "  </tr>" +
            "  <tr>" +
            "  <td width='100'><b>2015春</b></td>" +
            "        <td width='300'>{3}</td>" +
            "  </tr>" +
            "  <tr>" +
            "  <td width='100'><b>2016秋</b></td>" +
            "        <td width='300'>{4}</td>" +
            "  </tr>" +
            "" +
            "</table>";
    private  List<YnouJiaoxuedian> getListYnouJiaoxuedian(){
        return ynouJiaoxuedianRepository.findAll();
    }

    private  List<YnouJiaoxuedian> getListProvinceYnouJiaoxuedian(){
        return ynouJiaoxuedianRepository.findByAttribute("省属分校");
    }

    private List<YnouXdhzStudent> getYnouXdhzStudentlist(){
        return ynouXdhzStudentRepository.findAll();
    }



    private List<ShowOneSchoolInfo> getYcySchoolInfoList(){
        List<ShowOneSchoolInfo> listSchoolInfo = new ArrayList<ShowOneSchoolInfo>();
        List<YnouXdhzStudent> ynouXdhzStudentList = getYnouXdhzStudentlist();
        for (YnouXdhzStudent temp: ynouXdhzStudentList) {
            ShowOneSchoolInfo oneSchoolInfo = new ShowOneSchoolInfo();
            oneSchoolInfo.setSchool(temp.getName());
            oneSchoolInfo.setLatitude(temp.getWd().toString());
            oneSchoolInfo.setLongitude(temp.getJd().toString());
//            oneSchoolInfo.setInfomation("教学点名称"+temp.getName()+"\n"+
//                    "招生情况：\n"+
//                    "2015秋:\t"+temp.getPeriod1()+"\n"+
//                    "2016春:\t"+temp.getPeriod2()+"\n"+
//                    "2016秋:\t"+temp.getPeriod3());
//            oneSchoolInfo.setInfomation(MessageFormat.format(outHtmlFragment,temp.getName(),
//                    "127.0.0.1:8080/img/binchuan.jpg",
//                    temp.getPeriod1(),
//                    temp.getPeriod2(),
//                    temp.getPeriod3()
//            ));
            oneSchoolInfo.setInfomation(MessageFormat.format(outHtmlFragment,
                    temp.getName(),
                    "http://softdev.cmxy.ynou.edu.cn:8080/img/binchuan.jpg",
                    temp.getPeriod1(),
                    temp.getPeriod2(),
                    temp.getPeriod3()));
            listSchoolInfo.add(oneSchoolInfo);
        }
        System.out.printf("getYcySchoolInfoList() length is :" + listSchoolInfo.size());
        return  listSchoolInfo;
    }

    private   List<ShowOneSchoolInfo> getSchoolInfoList(){
        List<ShowOneSchoolInfo> listSchoolInfo = new ArrayList<ShowOneSchoolInfo>();
        List<YnouJiaoxuedian> listYnouJiaoxuedian = getListYnouJiaoxuedian();
        for (YnouJiaoxuedian temp : listYnouJiaoxuedian ) {
            ShowOneSchoolInfo oneSchoolInfo = new ShowOneSchoolInfo();
            oneSchoolInfo.setSchool(temp.getName());
            oneSchoolInfo.setLatitude(temp.getWd().toString());
            oneSchoolInfo.setLongitude(temp.getJd().toString());
            oneSchoolInfo.setInfomation("教学点名称：" + temp.getName()+"\n"+
                "本科生数量：" + temp.getStudentBk()+"\n"+
                "专科生数量：" + temp.getStudentZk()+"\n"+
                "一对一数量：" + temp.getStudentYcy()+"\n");
            listSchoolInfo.add(oneSchoolInfo);
        }
        System.out.printf("getListSchoolInfo() length is :" + listSchoolInfo.size());
        return listSchoolInfo;
    }

    /***
     * 获取省属。。。。
     * @return
     */
    private   List<ShowOneSchoolInfo> getProvinceSchoolInfoList(){
        List<ShowOneSchoolInfo> listSchoolInfo = new ArrayList<ShowOneSchoolInfo>();
        List<YnouJiaoxuedian> listYnouJiaoxuedian = getListProvinceYnouJiaoxuedian();
        for (YnouJiaoxuedian temp : listYnouJiaoxuedian ) {
            ShowOneSchoolInfo oneSchoolInfo = new ShowOneSchoolInfo();
            oneSchoolInfo.setSchool(temp.getName());
            oneSchoolInfo.setLatitude(temp.getWd().toString());
            oneSchoolInfo.setLongitude(temp.getJd().toString());
            oneSchoolInfo.setInfomation("教学点名称：" + temp.getName()+"\n"+
                    "本科生数量：" + temp.getStudentBk()+"\n"+
                    "专科生数量：" + temp.getStudentZk()+"\n"+
                    "一对一数量：" + temp.getStudentYcy()+"\n");
            listSchoolInfo.add(oneSchoolInfo);
        }
        System.out.printf("getListProvinceSchoolInfo() length is :" + listSchoolInfo.size());
        return listSchoolInfo;
    }


    public ListSchool getSchoolCoordinateListInfo(){
        ListSchool listSchool = new ListSchool();
        listSchool.setMarkers(getSchoolInfoList());
        return listSchool;
    }

    public ListSchool getProvinceSchoolCoordinateListInfo(){
        ListSchool listSchool = new ListSchool();
        listSchool.setMarkers(getProvinceSchoolInfoList());
        return listSchool;
    }

    public ListSchool getYcySchoolCoordinateListInfo(){
        ListSchool listSchool = new ListSchool();
        listSchool.setMarkers(getYcySchoolInfoList());
        return  listSchool;
    }


}
