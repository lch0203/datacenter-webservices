package cn.ynou.repository;


import cn.ynou.model.YnouJiaoxuedian;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
/**
 * Created by lch on 2017/5/3.
 */
public interface YnouJiaoxuedianRepository extends JpaRepository<YnouJiaoxuedian,String> {
    public List<YnouJiaoxuedian> findByAttribute(String Attribute);
}
