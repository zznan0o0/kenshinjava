package org.kenshin.desensitizationtest.config.desensitization;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
public @interface Desensitization {
    //脱敏类型
    SensitiveTypeEnum type();
}
