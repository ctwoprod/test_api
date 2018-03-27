package org.rf.test.helper;

import org.apache.commons.lang3.StringUtils;

public final class PhoneUtils {
    private static final String INDONESIA_PREFIX = "+62";
    private static final String PLUS = "+";
    private static final String LOCAL_PREFIX = "0";

    public static String toInternationalFormat(String phoneNo) {
        if(StringUtils.isNotBlank(phoneNo)) {
            if (phoneNo.startsWith(LOCAL_PREFIX)) {
                return INDONESIA_PREFIX + StringUtils.substring(phoneNo, 1);
            }
            if (!StringUtils.startsWith(phoneNo, PLUS)) {
                return PLUS + phoneNo;
            }
        }
        return phoneNo;
    }

    public static String removePlusInPrefix(String phoneNo) {
        if(StringUtils.startsWith(phoneNo, PLUS)){
            return StringUtils.substring(phoneNo, 1);
        }
        return phoneNo;
    }
}
