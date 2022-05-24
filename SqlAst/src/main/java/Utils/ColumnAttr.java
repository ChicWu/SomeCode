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


    public static int getEncrypt(int status){
        int rst = 0;
        rst = (status & (1 << ColumnAttr.ENCRYPT_BIT)) != 0 && (status & (1 << ColumnAttr.ENCRYPT_RESULT_BIT)) == 0? 1:0;
        return rst;
    }

    public static boolean isPrimaryKey(int status){
        return (status & (1 << ColumnAttr.PRIMARY_KEY_BIT)) != 0;
    }

    public static OtlEnum.DATA_TYPE getDataType(int status){
        int rst = 0;
        rst = status >> ColumnAttr.DATA_TYPE_BIT_1;
        rst = rst & 0b11111;
        return OtlEnum.DATA_TYPE.values()[rst];
    }

    public static int setDataType(int status,OtlEnum.DATA_TYPE type){
        int rst = status;
        rst = ((type.ordinal() & 0b00001) != 0)?(rst |1 << DATA_TYPE_BIT_1):rst & ~(1 <<DATA_TYPE_BIT_1);
        rst = ((type.ordinal() & 0b00010) != 0)?(rst |1 << DATA_TYPE_BIT_2):rst & ~(1 <<DATA_TYPE_BIT_2);
        rst = ((type.ordinal() & 0b00100) != 0)?(rst |1 << DATA_TYPE_BIT_3):rst & ~(1 <<DATA_TYPE_BIT_3);
        rst = ((type.ordinal() & 0b01000) != 0)?(rst |1 << DATA_TYPE_BIT_4):rst & ~(1 <<DATA_TYPE_BIT_4);
        rst = ((type.ordinal() & 0b10000) != 0)?(rst |1 << DATA_TYPE_BIT_5):rst & ~(1 <<DATA_TYPE_BIT_5);
        return rst;
    }

    public static int getDesensitive(int status){
        int rst;
        rst = (status & (1 << ColumnAttr.DATA_DESENSITION_OFFLINE_BIT)) != 0? 1:0;
        return rst;
    }

    public static OtlEnum.OPERATION_TYPE getOperationType(int status){
        int rst;
        rst = status >> ColumnAttr.OPERATION_TYPE_BIT_1;
        rst = rst & 0b1111;
        return OtlEnum.OPERATION_TYPE.values()[rst];
    }

    public static boolean isBlank(int status){
        boolean rst;
        rst = ((status & (1 << ColumnAttr.INIT_BIT)) == 0);
        return rst;
    }




    public static void main(String[] args) throws Exception
    {
        int status = 64;

        System.out.println(getDataType(status));
        System.out.println(getOperationType(status));
        System.out.println(isPrimaryKey(status));

    }
}
