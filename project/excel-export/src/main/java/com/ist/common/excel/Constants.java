package com.ist.common.excel;

public class Constants {
	
	/** 报表modulekey 前缀 */
	public static final String  SHEET_NAME = "sheet1";

	/**
	 * 默认行高
	 */
	public static final int DEFAULT_HEIGHT_INPOINT = 10;
	
	/**
	 * 默认列宽
	 */
	public static final int DEFAULT_COL_WIDTH = 2304 ;

	
	/** 公共定义信息 */
	public static final String CONFIGTITLE_START 		= "title_start";
	public static final String CONFIGTITLE_END 			= "title_end";
	public static final String CONFIGTITLE_CLASS_TR 	= "awp_title_tr";	
	
	public static final String CONFIGHEAD_START 		= "head_start";
	public static final String CONFIGHEAD_END	 		= "head_end";
	public static final String CONFIGHEAD_CLASS_TR 		= "awp_head_tr";
	
	public static final String CONFIGBODY_START 		= "body_start";
	public static final String CONFIGBODY_END 			= "body_end";
	public static final String CONFIGBODY_CLASS_TR		= "awp_body_tr";

	public static final String CONFIGTOTAL_START 		= "total_start";
	public static final String CONFIGTOTAL_END 			= "total_end";
	public static final String CONFIGTOTAL_CLASS_TR		= "awp_total_tr";	
	
	public static final String CONFIGDETAIL_START 		= "detail_start";
	public static final String CONFIGDETAIL_END   		= "detail_end";
	public static final String CONFIGDETAIL_CLASS_TR 	= "awp_detail_tr";
	
	public static final String CONFIGFOOT_START 		= "foot_start";
	public static final String CONFIGFOOT_END 			= "foot_end";
	public static final String CONFIGFOOT_CLASS_TR 		= "awp_foot_tr";
	
	public static final String $TAG$ = "$tag$";
	
	public static final String $SN$  = "$sn$";
	
	public static final String $LIST$ = "$list$";
	
	public static final String $SUMMARY$ = "$summary$"; // 合并单元格
	
	public static final String TO_NAME = "TO_NAME";//expression=COLUMNX.to_name(CODETABLE);	
	public static final String TO_NUMBER = "TO_NUMBER";//expression=COLUMNX.to_number(#.####);
	public static final String TO_PERCENT = "TO_PERCENT";//expression=COLUMNX.to_number(0.00%);
	public static final String TO_THOUSANDS = "TO_THOUSANDS";//expression=COLUMNX.to_thousands(###,##0.00);
	
	
	/** $total$sum(colname) ,$total$count() */
	public static final String $TOTAL$ = "$total$";
	
	public static final String DATA_FUNCTION_SUM = "sum"; //sum( COLUM )
	
	public static final String DATA_FUNCTION_COUNT = "COUNT()";
	
	public static final String DATA_TYPE_LIST = "list";

	/** 附加列表(如字段转码等) */
	public static final String DATA_TYPE_ADDITIONAL_LIST = "additional_list";
	
	public static final String DATA_TYPE_DYNAMIC = "dynamic";
	
	public static final String DATA_TYPE_STATIC = "static";

	
	public static final String DATA_FUNCTION_CURR_USERNAME = "curr_username";
	public static final String DATA_FUNCTION_CURR_REALNAME = "curr_realname";
	public static final String DATA_FUNCTION_CURR_DATE = "curr_date";
	public static final String DATA_FUNCTION_CURR_TIME = "curr_time";
	public static final String DATA_FUNCTION_CURR_DATETIME = "curr_datetime";
	public static final String DATA_FUNCTION_CURR_GROUPKEY = "curr_groupkey";
	

}
