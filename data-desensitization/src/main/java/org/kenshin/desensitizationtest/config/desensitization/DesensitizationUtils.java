package org.kenshin.desensitizationtest.config.desensitization;

import org.apache.commons.lang3.StringUtils;

public class DesensitizationUtils {

    public static String hiddenPhone(String phone) {//手机号 隐藏中间四位
        if (StringUtils.isBlank(phone)) {
            return "";
        }
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String hiddenPlateNum(String plateNum) {//车牌号 隐藏中间位
        if (StringUtils.isBlank(plateNum)) {
            return "";
        }
        return plateNum.substring(0, 3) + "*" + plateNum.substring(4, plateNum.length());
    }

    public static String hiddenIdCard(String idCard) {//身份证展示 前6位和后6位
        if (StringUtils.isBlank(idCard)) {
            return "";
        }
        return StringUtils.left(idCard, 6).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(idCard, 4), StringUtils.length(idCard), "*"), "***"));
    }

}
