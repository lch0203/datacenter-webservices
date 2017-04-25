package cn.ynou.service;

import cn.ynou.repository.UserRepository;
import cn.ynou.model.Tempusers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by lch on 2017/4/24.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<Tempusers> findAll() {
        return userRepository.findAll();
    }

    public Tempusers save(Tempusers user) {
        return userRepository.save(user);
    }

    public List<Tempusers>  findByPsd(String psd){
        return userRepository.findByPsd(psd);
    }

    public Tempusers findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Tempusers findOne(Integer id){
        return userRepository.findOne(id);
    }

    public List<Tempusers> removeById(Integer id){
        return userRepository.removeById(id);
    }
}
