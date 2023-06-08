package com.student.entity;

import com.student.util.annnotation.Column;
import com.student.util.annnotation.Label;

/**
 * @version 1.0
 * @Author 瑾瑜风禾
 * @Date 2023/5/21 19:51
 * @注释
 */
@Column(label = @Label("classes"))
public class classEntity {
    @Column(label = @Label("班级编号"),isKey = true)
    private String classId;
    @Column(label = @Label("班级名称"))
    private String classesName;
    @Column(label = @Label("班级总人数"))
    private Integer classNum;
    @Column(label = @Label("班主任教师名称"))
    private String classTeacher;

    public classEntity() {
    }

    public classEntity(String classId, String classesName, Integer classNum, String classTeacher){
        this.classId = classId;
        this.classesName = classesName;
        this.classNum = classNum;
        this.classTeacher = classTeacher;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassesName() {
        return classesName;
    }

    public void setClassesName(String className) {
        this.classesName = className;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(String classTeacher) {
        this.classTeacher = classTeacher;
    }
}
