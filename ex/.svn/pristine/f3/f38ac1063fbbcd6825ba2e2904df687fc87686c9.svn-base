package demo.common.utils;
/**
 * 防止SQL注入的工具类
 * @author Administrator
 *
 */
public class SQLFilterUtils {
	/**
	 * 校验是否被sql注入，如果 返回true被sql注入了，返回false没有被sql注入
	 * @param str
	 * @return
	 */
    public static boolean sqlValidate(String str) {
        str = str.toLowerCase();//统一转为小写
        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|,|like'|and|exec|execute|insert|create|drop|" +
                "table|from|grant|use|group_concat|column_name|" +
                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";//过滤掉的sql关键字，可以手动添加
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 过滤SQL语句,防止注入
     * @param sSql
     * @return 0 - 没有注入, 1 - 有注入
     */
    public static String filterSql(String sSql){
        if (sSql == null || "".equals(sSql)){
            return sSql;
        }
        sSql = sSql.toLowerCase().trim();
        sSql = sSql.replace("exec", "");
        sSql = sSql.replace("delete", "");
        sSql = sSql.replace("master", "");
        sSql = sSql.replace("truncate", "");
        sSql = sSql.replace("declare", "");
        sSql = sSql.replace("create", "");
        sSql = sSql.replace("select", "");
        sSql = sSql.replace("from", "");
        sSql = sSql.replace("where", "");
        sSql = sSql.replace("update", "");
        sSql = sSql.replace("xp_", "no");
        return sSql;
    }
  
}
