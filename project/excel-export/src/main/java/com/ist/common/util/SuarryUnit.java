package com.ist.common.util;

import java.io.Serializable;

//合并汇总字段信息
public class SuarryUnit implements Serializable
{
	private static final long serialVersionUID = 5344945508659059590L;
	
	private int column = 0;
	private int beginrow = 0;
	private int endrow = 0;
	
	public SuarryUnit(int column,int beginrow,int endrow)
	{
		this.column = column;
		this.beginrow = beginrow;
		this.endrow = endrow;
	}
	
	//考虑模板时多行的情况，多行循环；
	public void  handleSuarryUnit(int n)
	{
		//1次合并多少行计算公式,例如有的数据本身就是站2行，那么合并的时候就需要*2，每次合并都是合并2行，下面是通过
		//寻找规律得出来的计算的公式。
		//		公式： n*begin , 
		//		      n*(end + 1) - 1
		beginrow = n * beginrow;
		endrow = n*(endrow + 1) - 1;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getBeginrow() {
		return beginrow;
	}

	public void setBeginrow(int beginrow) {
		this.beginrow = beginrow;
	}

	public int getEndrow() {
		return endrow;
	}

	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}
	
	
}
