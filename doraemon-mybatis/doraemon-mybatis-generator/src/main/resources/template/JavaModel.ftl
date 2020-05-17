package ${model.packageName}.vo;


/**
* Created with IntelliJ IDEA.
* Description: ${model.comment}
* Author:      fengwenping
* Date:        ${.now}
*/
@Data
public class ${model.name} {
    <#list model.fields as field>

    /**
    * ${field.comment}
    */
    private ${StringHelper.subPrefix(field.javaType)!} ${field.name};
    </#list>
}
