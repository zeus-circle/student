package com.student.entity;

import com.student.util.annnotation.Column;
import com.student.util.annnotation.Label;

/**
 * @version 1.0
 * @Author 瑾瑜风禾
 * @Date 2023/5/21 16:30
 * @注释
 */
@Column(label = @Label("teacher"))
public class teaEntity {
    @Column(label = @Label("教师工号"),isKey = true)
    private String teaId;
    @Column(label = @Label("教师姓名"))
    private String teaName;
    @Column(label = @Label("教师性别"))
    private String teaSex;
    @Column(label = @Label("教师年龄"))
    private Integer teaAge;
    @Column(label = @Label("任职班级信息"))
    private String teaClass;

    public teaEntity() {
    }

    public teaEntity(String teaId, String teaName, String teaSex, Integer teaAge, String teaClass){
        this.teaId = teaId;
        this.teaName = teaName;
        this.teaSex = teaSex;
        this.teaAge = teaAge;
        this.teaClass = teaClass;
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    public String getTeaName() {
        return teaName;
    }

    public void setTeaName(String teaName) {
        this.teaName = teaName;
    }

    public String getTeaSex() {
        return teaSex;
    }

    public void setTeaSex(String teaSex) {
        this.teaSex = teaSex;
    }

    public Integer getTeaAge() {
        return teaAge;
    }

    public void setTeaAge(Integer teaAge) {
        this.teaAge = teaAge;
    }

    public String getTeaClass() {
        return teaClass;
    }

    public void setTeaClass(String teaClass) {
        this.teaClass = teaClass;
    }
}
