package org.kenshin.desensitizationtest.config.desensitization;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.lang.reflect.Field;

public class ValueDesensitizeFilter implements ValueFilter {
    @Override
    public Object process(Object object, String name, Object value) {
        System.out.println(value+"-");
        if (null == value || !(value instanceof String) || ((String) value).length() == 0) {
            return value;
        }
        try {
            Field field = object.getClass().getDeclaredField(name);
            Desensitization desensitization;
            if (String.class != field.getType() || (desensitization = field.getAnnotation(Desensitization.class)) == null) {
                return value;
            }
            String valueStr = (String) value;
            SensitiveTypeEnum type = desensitization.type();
            switch (type) {
                case ID_CARD:
                    return DesensitizationUtils.hiddenIdCard(valueStr);
                case PHONE:
                    return DesensitizationUtils.hiddenPhone(valueStr);
                case PLATE_NUM:
                    return DesensitizationUtils.hiddenPlateNum(valueStr);
                default:
            }
        } catch (NoSuchFieldException e) {
            return value;
        }
        return value;
    }
}
