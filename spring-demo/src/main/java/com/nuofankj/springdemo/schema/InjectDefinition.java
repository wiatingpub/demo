package com.nuofankj.springdemo.schema;

import com.nuofankj.springdemo.anno.Resource;
import com.nuofankj.springdemo.anno.ResourceInject;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

public class InjectDefinition {

    // 被注入的Field
    private Field field;
    // 注入配置
    private ResourceInject resourceInject;
    // 注入类型
    private InjectType type;

    public InjectDefinition(Field field) {

        if (field == null) {
            throw new IllegalArgumentException("被注入属性域不能为null");
        }
        if (!field.isAnnotationPresent(ResourceInject.class)) {
            throw new IllegalArgumentException("被注入属性域" + field.getName() + "的注释配置缺失");
        }
        field.setAccessible(true);
        this.resourceInject = field.getAnnotation(ResourceInject.class);
        if (StringUtils.isEmpty(this.resourceInject.value())) {
            this.type = InjectType.CLASS;
        } else {
            this.type = InjectType.NAME;
        }
    }

    public Field getField() {
        return field;
    }

    public InjectType getType() {
        return type;
    }
}
