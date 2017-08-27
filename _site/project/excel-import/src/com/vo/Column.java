package com.vo;

public class Column {
	/**
	 * 字段名
	 */
	private String fieldname;
	/**
	 * 字段类型
	 */
	private String filedtype;
	/**
	 * 字段对应excel列(0,1,2,3...)
	 */
	private int excelcol;
	/**
	 * 是否转码？ 码值?
	 */
	private String transcoding;
	
	
	public String getFieldname() {
		return fieldname;
	}
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	public String getFiledtype() {
		return filedtype;
	}
	public void setFiledtype(String filedtype) {
		this.filedtype = filedtype;
	}
	public int getExcelcol() {
		return excelcol;
	}
	public void setExcelcol(int excelcol) {
		this.excelcol = excelcol;
	}
	public String getTranscoding() {
		return transcoding;
	}
	public void setTranscoding(String transcoding) {
		this.transcoding = transcoding;
	}
	
	
}
