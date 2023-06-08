package com.student.util.annnotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @version 1.0
 * @Author 瑾瑜风禾
 * @Date 2023/5/21 19:30
 * @注释
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    //表示类的属性的显示器
    Label label();

    //Nullable表示是否允许属性值为空
    boolean Nullable() default false;

    //是否为主键
    boolean isKey() default false;

    //MaxLength 表示文本属性的最大长度
    int MaxLength() default 20;
    int MinLength() default 0;

    int MaxValue() default 99;
    int MinValue() default 0;
}
