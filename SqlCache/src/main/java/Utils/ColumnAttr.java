/*
 *  @author Ambo
 *  @data 2017/10/25 10:16
 */
package Utils;

public class ColumnAttr {

    // 字段属性

    /* 标识该字段是否存在bug */
    public static final int BUG_BIT = 0;
    /* 标识该字段是否加密 */
    public static final int ENCRYPT_BIT = 1;
    /* 标识该字段是否为索引 */
    public static final int DECRYPT_RESULT_BIT = 2;
    /** 标识该字段是否加密成功 **/
    public static final int ENCRYPT_RESULT_BIT = 3;
    /** 标识该字段是否已经初始化，不论是否成功。 **/
    public static final int INIT_BIT = 4;
    // 5 - 9位 标识字段的加密类型，值和CONST.DATA_TYPE 对应
    public static final int DATA_TYPE_BIT_1 = 5;
    public static final int DATA_TYPE_BIT_2 = 6;
    public static final int DATA_TYPE_BIT_3 = 7;
    public static final int DATA_TYPE_BIT_4 = 8;
    public static final int DATA_TYPE_BIT_5 = 9;

    /* 10 - 12位 标识字段处理类型 */
    public static final int DATA_DESENSITION_OFFLINE_BIT = 10;
    //  标识该字段是否为在线脱敏状态

    /* 暂未使用 */
    public static final int DATA_HANDLE_TYPE_BIT_2 = 11;
    /* 暂未使用 */
    public static final int DATA_HANDLE_TYPE_BIT_3 = 12;

    /* 14 - 15 位 标识字段脱敏方式 ，值和CONST.OPERATION_TYPE 对应 */
    public static final int OPERATION_TYPE_BIT_1 = 14;
    public static final int OPERATION_TYPE_BIT_2 = 15;
    public static final int OPERATION_TYPE_BIT_3 = 16;
    public static final int OPERATION_TYPE_BIT_4 = 17;

    public static final int PRIMARY_KEY_BIT = 18;

    public static final int PRIMARY_KEY_OFFSET = 50;


}
