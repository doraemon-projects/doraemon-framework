package org.doraemon.framework.base;

import java.util.Objects;

public class Test {

    static <T extends EnumTypeTest, K> T fromValue(Class<T> enumType, K value) {
        final T[] enumConstants = enumType.getEnumConstants();
        for (T t : enumConstants) {
            if (Objects.equals(t.getKey(), value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("no enum value " + value + " of " + enumType.getCanonicalName());
    }

    public enum  InfoType implements EnumTypeTest{
        Test(EnumTypeTest.create("1","2")),
        Test1(EnumTypeTest.create("3","4"))
        ;

        private EnumTypeTest infoType;

        InfoType(EnumTypeTest enumTypeTest) {
            this.infoType =  enumTypeTest;
        }
        @Override
        public EnumTypeTest getDelegate() {
            return infoType;
        }
    }

    public static void main(String[] args) {
        InfoType infoType = Test.fromValue(InfoType.class, "1");
        System.out.println(infoType.getKey()+"==="+infoType.getVal());
    }
}
