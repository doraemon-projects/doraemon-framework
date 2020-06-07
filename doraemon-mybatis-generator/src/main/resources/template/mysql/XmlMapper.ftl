<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${model.packageName}.dao.${model.name}Dao">
    <resultMap id="BaseResultMap" type="${model.packageName}.vo.${model.name}">
        <#list model.fields as field>
        <result property="${StringHelper.underLine2Camel(field.columnName)!}" column="${field.columnName}" javaType="${field.javaType}" jdbcType="${field.jdbcType}"/>
        </#list>
    </resultMap>
</mapper>