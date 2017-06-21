package top.onee.ssm.modal.DO;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户信息实体类
 * Created by VOREVER on 23/04/2017.
 */
public class UserInfo {

    private long id;                // 主键ID
    private String userName;        // 用户名
    private int age;                // 年龄
    private BigDecimal balance;     // 余额
    private boolean vip;            // 是否是VIP
    private Date gmtCreate;         // 创建时间
    private Date gmtModified;       // 修改时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                ", balance=" + balance +
                ", vip=" + vip +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
