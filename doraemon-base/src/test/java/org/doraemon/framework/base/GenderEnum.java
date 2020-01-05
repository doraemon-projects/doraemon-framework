package org.doraemon.framework.base;

import org.doraemon.framework.util.DateUtils;
import org.doraemon.framework.util.JSON;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/6 0:46
 */
public enum GenderEnum implements EnumType<Integer> {

    MALE(1, "男", "男性"),
    FEMALE(2, "女", "女性");

    private Integer code;
    private String name;
    private String desc;

    GenderEnum(Integer code, String name, String desc) {
        this.code = code;
        this.name = name;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        return this.info();
    }

    public static void main(String[] args) {
        System.out.println(GenderEnum.FEMALE.toString());
    }
}
