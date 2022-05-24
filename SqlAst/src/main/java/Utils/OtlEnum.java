/*
 *  @author wonder
 *  @data 2018/5/28 17:12
 */
package Utils;

public class OtlEnum {

    public enum DATA_TYPE{
        NONE,
        MAPPER,							// 	普通映射
        DATE,							// 	2.日期
        MOBILE_PHONE,					// 	3.移动电话
        TELE_PHONE,						//	4.固定电话
        IDCARD,							//	5.身份证号
        NAME,							//	6.姓名
        EMAIL,							//	7.邮箱
        ADDRESS,						//	8.地址
        ORGANIZATION_CODE,				//	9.企业组织机构代码
        BUSINESS_LISENSE_CODE,			//	10.营业执照代码
        TAX_REGISTRATION_CODE,			//	11.税务登记号码
        SOCIAL_CREDIT_CODE,				//	12.统一社会信用代码
        NUM,
    }

    public enum DB_DEST_TYPE{
        REMOTE,
        FILE,
        LOCAL,
        UNKNOW,
    }

    public enum OPERATION_TYPE{
        NONE,								//
        EMPTY,								//	1.置空。将敏感字段替换为Null
        MASKING,							//	2.屏蔽。将敏感字段替换为*或者X等其它符号
        SUBSTITUTION,						//	3.替换。随机替换一个长度和类型在合法的数据
        SIMULATION,							//	4.仿真替换。替换原始数据保持原始数据的合法性，可以通过合法性检测
        VARIANCE,							//	5.数字日期变形。对数字日期进行上下随机增加或减少，保留原先统计特性
        SHUFFLING,							//	6.洗牌。将已有的数据集进行次序进行随机打乱操作
        ENCRYPT,							//	7.保留格式的加密
        CUSTOM,								//	8.用户自定义
        OTL,								//	9.OTL功能
        NUM
    }

    public enum	SERVER_TYPE{
        UNKNOW,
        MYSQL,
        SQLSERVER,
        ORACLE,
        KINGBASE
    }

    public enum	CLIENT_TYPE{
        DBATOOL,								// DBA 工具请求
        WEBSERVER,								// WEB 应用请求
        OTLSERVER_ENCRYPT,						// 安全服务器 ，在加密情况做脱敏时，使用
        OTLSERVER_DESENTIVE,					// 安全服务器 ，在加密情况做脱敏时，使用
        UNKNOW,
    }

    public static DATA_TYPE GetDataType(String type) {
        if (type != null) {
            try {
                return Enum.valueOf(DATA_TYPE.class, type.toUpperCase().trim());
            } catch (IllegalArgumentException ex) {
                int order = Integer.parseInt(type);
                return DATA_TYPE.values()[order];
            }
        }

        return DATA_TYPE.MAPPER;
    }

    public static SERVER_TYPE GetServerType(String type) {
        if (type != null) {
            try {
                return Enum.valueOf(SERVER_TYPE.class, type.toUpperCase().trim());
            } catch (IllegalArgumentException ex) {
            }
        }

        return SERVER_TYPE.MYSQL;
    }
    public static DB_DEST_TYPE GetDestType(String type) {

        if (type != null) {
            try {
                return Enum.valueOf(DB_DEST_TYPE.class, type.toUpperCase().trim());
            } catch (IllegalArgumentException ex) {
                int order = Integer.parseInt(type);
                return DB_DEST_TYPE.values()[order];
            }
        }
        return DB_DEST_TYPE.UNKNOW;
    }

    public static CLIENT_TYPE GetClientType(String type) {
        if (type != null) {
            if(type.equals("dba")){
                return CLIENT_TYPE.DBATOOL;
            }
            if(type.equals("app")){
                return CLIENT_TYPE.WEBSERVER;
            }
            try {
                return Enum.valueOf(CLIENT_TYPE.class, type.toUpperCase().trim());
            } catch (IllegalArgumentException ex) {
                int order = Integer.parseInt(type);
                return CLIENT_TYPE.values()[order];
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return CLIENT_TYPE.UNKNOW;
    }
}
