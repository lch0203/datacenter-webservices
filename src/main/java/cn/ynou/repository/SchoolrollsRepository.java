package cn.ynou.repository;

import cn.ynou.model.Schoolrolls;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by lch on 2017/4/24.
 */
public interface SchoolrollsRepository extends JpaRepository<Schoolrolls,String> {
    List<Schoolrolls> findByStudentid(String studentId);
    List<Schoolrolls> findByName(String name);
    Long countByName(String name);
    List<Schoolrolls> findByStudentidLike(String studentId);
    List<Schoolrolls> findByNameLike(String name);
    Long countByNameLike(String name);
}
