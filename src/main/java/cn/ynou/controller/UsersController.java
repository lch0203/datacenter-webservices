package cn.ynou.controller;


import cn.ynou.model.UserLogin;
import cn.ynou.service.UserService;
import cn.ynou.model.Tempusers;
import cn.ynou.util.MD5Utils;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

/**
 * Created by lch on 2017/4/22.
 */
@RestController
@RequestMapping("/user")
@Api(tags={"users"})
@CrossOrigin
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<Tempusers> getUsersList() {
        List<Tempusers> list = userService.findAll();
        return list;
    }

    @PutMapping("/add")
    public Tempusers addUser(@RequestParam String username, @RequestParam String psd) {
        Tempusers user = new Tempusers();
        if (userService.findByUsername(username) != null) {
            user.setId(null);
        } else {
            user.setUsername(username);
            user.setPsd(MD5Utils.getMD5(psd));
            user.setServicescode(MD5Utils.getMD5(username));
            user.setLastlogin(null);
            user.setLogincount(0);
            userService.save(user);
        }
        return user;
    }

    @PostMapping("/login")
    public UserLogin userLogin(@RequestParam String username, @RequestParam String psd) {
        UserLogin ulogin = new UserLogin();
        ulogin.setLogin(false);
        ulogin.setUser(null);
        String password = MD5Utils.getMD5(psd);
        List<Tempusers> list = userService.findByPsd(password);
        for (Tempusers user : list) {
            if (user.getUsername().equals(username)) {
                user.setLogincount(user.getLogincount() + 1);
                user.setLastlogin(new Date());
                userService.save(user);
                ulogin.setUser(user);
                ulogin.setLogin(true);
                break;
            }
        }
        return ulogin;
    }

    @DeleteMapping("/del/{id}")
    public List<Tempusers> delUser(@PathVariable Integer id){
       return userService.removeById(id);
    }
}
