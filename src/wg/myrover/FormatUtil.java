package wg.myrover;



import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
* <p>Title:FormatUtil </p>
* <p>Description: 格式化字符串类</p>
* @author yxz
* @date Jul 24, 2017
 */
public class FormatUtil {

    
    /**
     * 对字串进行处理，防止空字串产生错误
     * @param String 
     * @return String 格式化后的字串
     * */
    public static String formatNullString(String str) {
        if (str == null || str.trim().equals("")) {
            str = "";
        }
        return str;
    }



    /**
     * 如果字符串为空，设成默认值
     * @param String  字符串
     * @param String  默认字符串
     * @return String 格式化后的字串
     * */
    public static String formatNullString(String str, String defaultStr) {
        if (str == null || str.trim().equals("")) {
            str = defaultStr;
        }
        return str;
    }    

    

    /**
     * 判断是否是数字
     * @param String  字符串
     * @return boolean 
     * */
    public static boolean isNumeric(String str){
    	if(str != null && !"".equals(str) && !str.startsWith("0")){
    		for(int i=str.length();--i>=0;){
    			int chr=str.charAt(i);
    			if(chr<48||chr>57)
    				return false;
    		}
    		return true;
    	}else
    		return false;
    }

    

    /**
     * 将字符串中的多个空格，替换成一个
     * @param str
     * @return
     */
    public static String replaceKg(String str){
    	 Pattern p = Pattern.compile("\\s+");
    	 Matcher m = p.matcher(str);
    	 return  m.replaceAll(" ");
    }
    
    
    /**
     * m转为g
     * @param str
     * @return
     */
    public static double mToG(String str){
    	double result = 0;
    	double mod = 1024;
    	if(str.contains("M")){
    		int f = Integer.valueOf(str.replace("M", ""));
    		result = f/mod;
    	}else if(str.contains("K")){
    		int f = Integer.valueOf(str.replace("K", ""));
    		result = (f/mod)/mod;
    	}else if(str.contains("T")){
    		double f = Double.valueOf(str.replace("T", ""));
    		result = f*1024;
    	}else if(str.contains("G")){
    		result = Double.valueOf(str.replace("G", ""));
    	}
    	return formatDouble(result,2);
    }
    
    
    /**
 	 * 格式化double数据，截取小数点后数字
 	 * @param str
 	 * @param num
 	 * @return
 	 */
 	public static double formatDouble(double str,int num){
		java.math.BigDecimal b = new java.math.BigDecimal(str);  
		double myNum3 = b.setScale(num,java.math.BigDecimal.ROUND_HALF_UP).doubleValue();  
		return myNum3; 
 	}
 	
 	
}
