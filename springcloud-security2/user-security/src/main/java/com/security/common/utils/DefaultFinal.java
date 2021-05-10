package com.security.common.utils;

public class DefaultFinal {

    /**
     * 时间格式
     */
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String TIME_LOCALE_CHINA = "zh";

    public static final String TIME_ZONE = "GMT+8";

    /*******************************数据是否删除 start***********************************************/
    /**
     * 已删除
     */
    public static final Integer IS_DELETED = 1;

    /**
     * 未删除
     */
    public static final  Integer IS_NOT_DELETED = 0;
    /*******************************数据是否删除 end***********************************************/


    /*******************************医废类型常量 start***********************************************/
    /**
     * 医废类型键名
     */
    public static final String WASTE_TYPE_NAME = "medical_waste_type";

    /**
     * 感染性废物
     */
    public static final  Integer WASTE_TYPE_INFECTIOUS = 10;

    /**
     * 损伤性废物重量
     */
    public static final  Integer WASTE_TYPE_DAMAGE = 20;

    /**
     *病理性废物
     */
    public static final  Integer WASTE_TYPE_PATHOLOGY = 30;

    /**
     *化学性废物
     */
    public static final  Integer WASTE_TYPE_CHEMISTRY = 40;

    /**
     *药物性废物
     */
    public static final  Integer WASTE_TYPE_DRUG = 50;

    /**
     *其他废物
     */
    public static final  Integer WASTE_TYPE_OTHER = 60;
    /*******************************医废类型常量 end***********************************************/
}
