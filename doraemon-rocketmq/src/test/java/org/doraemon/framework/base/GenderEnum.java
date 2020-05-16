package org.doraemon.framework.base;

/**
 * Created with IntelliJ IDEA.
 * Description: 描述
 * Author:      fengwenping
 * Date:        2020/1/6 0:46
 */
public enum GenderEnum implements IEnumProvider<Integer> {

    MALE(1, "男", "男性"),
    FEMALE(2, "女", "女性");

    private final Integer code;
    private final String name;
    private final String desc;

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
