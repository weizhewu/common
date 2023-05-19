package com.waltz.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author MrWei
 * @version 1.0.0
 * @date 2023/4/25 22:45
 * @description
 */
public interface CommonConstant {

    public static final String BLANK = "";

    interface Number{
        public static final Integer INTEGER_ZERO = 0;
        public static final Integer INTEGER_ONE = 1;
    }

    interface Date{
        public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";

        public static final String YMD = "yyyy-MM-dd";
    }

    interface File{
        public static final int BUFFER_SIZE = 1024;

        public static final String IMG = "img";

        public static final List<String> IMG_TYPE_LIST = Arrays.asList("jpg","png","jpeg");
    }
}
