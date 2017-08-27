package com.ist.common.excel;

public class Sheet {
	/** sheet名称 **/
	private String name = "sheet1";
	/** 报表标题占用列数 **/
	private int titleWidth = 8;
	/** 报表标题开始行 **/
	private int titleStart = 1;
	/** 报表标题结束行 **/
	private int titleEnd = 1;
	/** 报表表头占用列数  **/
	private int headWidth;
	/** 报表表头开始行 **/
	private int headStart = 2;
	/** 报表表头结束行 **/
	private int headEnd = 2;
	/** 报表数据占用列数 **/
	private int bodyWidth;
	/** 报表数据开始行 **/
	private int bodyStart = 3;
	/** 报表数据结束行 **/
	private int bodyEnd = 3;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTitleWidth() {
		return titleWidth;
	}
	public void setTitleWidth(int titleWidth) {
		this.titleWidth = titleWidth;
	}
	public int getTitleStart() {
		return titleStart;
	}
	public void setTitleStart(int titleStart) {
		this.titleStart = titleStart;
	}
	public int getTitleEnd() {
		return titleEnd;
	}
	public void setTitleEnd(int titleEnd) {
		this.titleEnd = titleEnd;
	}
	public int getHeadWidth() {
		return headWidth;
	}
	public void setHeadWidth(int headWidth) {
		this.headWidth = headWidth;
	}
	public int getHeadStart() {
		return headStart;
	}
	public void setHeadStart(int headStart) {
		this.headStart = headStart;
	}
	public int getHeadEnd() {
		return headEnd;
	}
	public void setHeadEnd(int headEnd) {
		this.headEnd = headEnd;
	}
	public int getBodyWidth() {
		return bodyWidth;
	}
	public void setBodyWidth(int bodyWidth) {
		this.bodyWidth = bodyWidth;
	}
	public int getBodyStart() {
		return bodyStart;
	}
	public void setBodyStart(int bodyStart) {
		this.bodyStart = bodyStart;
	}
	public int getBodyEnd() {
		return bodyEnd;
	}
	public void setBodyEnd(int bodyEnd) {
		this.bodyEnd = bodyEnd;
	}

}
