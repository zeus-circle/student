package com.student.entity;

import com.student.util.annnotation.Column;
import com.student.util.annnotation.Label;

/**
 * @version 1.0
 * @Author 瑾瑜风禾
 * @Date 2023/5/21 20:02
 * @注释
 */
@Column(label = @Label("admin"))
public class adminEntity {
    @Column(label = @Label("姓名"))
    private String name;
    @Column(label = @Label("用户名"),isKey = true)
    private String adminName;
    @Column(label = @Label("用户密码"))
    private String password;

    public adminEntity(){

    }

    public adminEntity(String name, String adminName, String password){
        this.name = name;
        this.adminName = adminName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
