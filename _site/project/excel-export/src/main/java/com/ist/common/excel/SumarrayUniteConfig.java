package com.ist.common.excel;

import java.io.Serializable;

/**
 * 动态行中，对某列进行单元格合并的设置
 * @author
 * 
 */
public class SumarrayUniteConfig implements Serializable {

	private static final long serialVersionUID = -7633244439140549602L;
	// 合并列数组
	private String[] unitecols;
	// 开始合并列号
	private int startCol = 0;
	//合并行数(模板中的数量)
	private int uniterows = 0;
	//是否需要合并单元格
	private boolean unite = false;

	public SumarrayUniteConfig() {
	}
	
	public SumarrayUniteConfig(boolean unite) {
		this.unite = unite;
	}

	public SumarrayUniteConfig(String[] unitecols, boolean unite, int startCol) {
		this.unitecols = unitecols;
		this.unite = unite;
		this.startCol = startCol;
	}

	public String[] getUnitecols() {
		return unitecols;
	}

	public void setUnitecols(String[] unitecols) {
		this.unitecols = unitecols;
	}

	public int getStartCol() {
		return startCol;
	}

	public void setStartCol(int startCol) {
		this.startCol = startCol;
	}

	public int getUniterows() {
		return uniterows;
	}

	public void setUniterows(int uniterows) {
		this.uniterows = uniterows;
	}

	public boolean isUnite() {
		return unite;
	}

	public void setUnite(boolean unite) {
		this.unite = unite;
	}

}
