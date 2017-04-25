package cn.ynou.model;

/**
 * Created by lch on 2017/4/24.
 */
public class UserLogin {
    private boolean isLogin;
    private Tempusers user;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public Tempusers getUser() {
        return user;
    }

    public void setUser(Tempusers user) {
        this.user = user;
    }
}
