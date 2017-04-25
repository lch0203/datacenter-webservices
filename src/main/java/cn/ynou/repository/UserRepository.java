package cn.ynou.repository;

import cn.ynou.model.Tempusers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lch on 2017/4/23.
 */
public interface UserRepository extends JpaRepository<Tempusers,Integer> {
    List<Tempusers> findByPsd(String psd);
    List<Tempusers> findById(Integer id);
    Tempusers findByUsername(String username);
    List<Tempusers> removeById(Integer id);

}
