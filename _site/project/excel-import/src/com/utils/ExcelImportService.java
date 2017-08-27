package com.utils;

import java.util.List;
import java.util.Map;

import com.vo.Column;
import com.vo.ExcelImportTemplate;


public class ExcelImportService{
	/**
	 * 
	 * @param list excel collections
	 * @param excelImportTemplate  xml emplate
	 */
	public String ExcelImport(List<Map> list, ExcelImportTemplate excelImportTemplate) {
		for(Map<String,String> map :list){
			String sql = getSQL(excelImportTemplate,map);
			try {
				//执行sql
//				jdbcTemplateServiceI.update(sql);
			} catch (Exception e) {
				System.out.println("表:" + excelImportTemplate.getTablename()+ ",导入excel数据错误！" + e.toString());
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @param cm
	 * @param excelImportTemplate
	 * @param map
	 * @return
	 */
	private static String getSQL(ExcelImportTemplate excelImportTemplate,Map<String,String> map) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO " + excelImportTemplate.getTablename() + " ");
		StringBuilder skey = new StringBuilder();
		StringBuilder svalue = new StringBuilder();
		List<Column> columns  = excelImportTemplate.getColumns();
		Column column = null;
		for(Map.Entry<String,String> entry:map.entrySet()){
			System.out.println("key="+entry.getKey()+",value="+entry.getValue());
			skey.append(entry.getKey()+",");
			boolean isDecimal = false;
			String value = entry.getValue().trim();
			//转码   验证
			for(int i =0;i<columns.size();i++){
				column = columns.get(i);
				/**
				//Redis 转码
				if (entry.getKey().equals(column.getFieldname()) && column.getTranscoding() != null 
						&& column.getTranscoding().trim().length() != 0) {
					value = getKeyByValue(cm, column.getTranscoding().trim(), value);
				}
				//DECIMAL 
				//列名称与key相等并且数据类型为DECIMAL,此时要确定不能为空,为空时要添加默认值0  
				if(entry.getKey().equals(column.getFieldname()) && column.getFiledtype().equals("DECIMAL")){
					if(entry.getValue().length()==0){
						svalue.append(0+",");
						isDecimal = true;
						break;
					}
				}
				*/
			}
			if(!isDecimal){
				svalue.append(value+",");
			}
		}
		sb.append(removeLastSymbol(skey.toString())+" values "+removeLastSymbol(svalue.toString(),skey.toString().split(",").length));

		System.out.println("sql.xls====" + sb.toString());
		return sb.toString();
	}
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String removeLastSymbol(String str){
		String[] array = str.split(",");
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<array.length;i++){
			if (i == 0) {
				sb.append("(");
			}
			if (i == array.length-1) {
				sb.append(array[i]);
				sb.append(")");
			} else {
				sb.append(array[i] + ",");
			}
		}
		System.out.println("=========sqlKey="+sb.toString());
		return sb.toString();
	}
	/**
	 * 
	 * @param str
	 * @param length
	 * @return
	 */
	public static String removeLastSymbol(String str,int length){
		String[] arrayStr = new String[length];
		 String[] strtemp = str.split(",");
		 //如果value的字符串短,表示后面有空值,为了保持inser语句values前后数量保持一致,在此插入空串 " "
		if(length!=strtemp.length){
			for(int i=length-1;i>=strtemp.length;i--){
				arrayStr[i] = " ";
			}
		}
		//数组复制
		for(int i=0;i<strtemp.length;i++){
			arrayStr[i] = strtemp[i];
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arrayStr.length;i++){
			if (i == 0) {
				sb.append("(");
			}
			if (i == arrayStr.length-1) {
				sb.append("'"+arrayStr[i]+"'");
				sb.append(")");
			} else {
				sb.append("'"+arrayStr[i] + "',");
			}
		}
		System.out.println("=========sqlValue="+sb.toString());
		return sb.toString();
	}

	/**
	 * 使用Redis服务转码
	 * 
	 * @param cm
	 *            缓存redis
	 * @param transcoding
	 *            xml预存的码值
	 * @param value
	 *            excel表中的值
	 * @return
	 */
	/**
	public static String getKeyByValue(CacheManager cm, String transcoding,String value) {
		if(transcoding.trim() !=null){
			Map<String, String> map = cm.get(transcoding.trim());
			if (map != null) {
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (value.equals(entry.getValue())) {
						System.out.println("码值：key=" + entry.getKey() + ",value="+ entry.getValue());
						return entry.getKey();
					}
				}
			}
		}else{
			return "";
		}
		return "";
	}
	*/

}
 