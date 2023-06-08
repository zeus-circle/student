package com.student.entity;

import com.student.util.annnotation.Column;
import com.student.util.annnotation.Label;

/**
 * @version 1.0
 * @Author 瑾瑜风禾
 * @Date 2023/5/21 19:30
 * @注释
 */
@Column(label = @Label("student"))
public class stuEntity {

    @Column(label = @Label("学生学号"),isKey = true)
    private String stuId;
    @Column(label = @Label("学生姓名"))
    private String stuName;
    @Column(label = @Label("学生性别"))
    private String stuSex;
    @Column(label = @Label("学生年龄"))
    private Integer stuAge;
    @Column(label = @Label("学生年级"))
    private Integer stuGrade;
    @Column(label = @Label("班级编码"))
    private String stuClass;

    public stuEntity(){
    }

    public stuEntity(String stuId, String stuName, String stuSex, Integer stuAge, Integer stuGrade, String stuClass){
        this.stuId = stuId;
        this.stuName = stuName;
        this.stuSex = stuSex;
        this.stuAge = stuAge;
        this.stuGrade = stuGrade;
        this.stuClass = stuClass;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    public Integer getStuGrade() {
        return stuGrade;
    }

    public void setStuGrade(Integer stuGrade) {
        this.stuGrade = stuGrade;
    }

    public String getStuClass() {
        return stuClass;
    }

    public void setStuClass(String stuClass) {
        this.stuClass = stuClass;
    }

}
