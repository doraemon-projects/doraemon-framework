package org.doraemon.framework.mybatis.enums;

import org.doraemon.framework.base.IEnumProvider;

import java.util.Arrays;

/**
 * created with IntelliJ IDEA.
 * description: 描述
 * author:      fengwenping
 * date:        2019/7/14 21:40
 */
public enum DatabaseType implements IEnumProvider {
    ORACLE("Oracle", "Oracle"),

    MYSQL("MySQL", "MySQL"),

    SQL_SERVER("SQLServer", "Microsoft SQLServer");

    String code;
    String desc;

    DatabaseType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static DatabaseType fromString(String code) {
        final DatabaseType databaseType = Arrays.stream(DatabaseType.values()).filter(dataType -> dataType.code.equalsIgnoreCase(code)).findFirst().get();
        if (databaseType == null) {
            throw new IllegalArgumentException("database type must be 'Oracle', 'MySQL' or 'SQLServer'.");
        }
        return databaseType;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.desc;
    }
}
