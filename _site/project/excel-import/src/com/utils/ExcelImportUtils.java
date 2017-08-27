package com.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.utils.Common;
import com.vo.Column;
import com.vo.ExcelImportTemplate;

public class ExcelImportUtils {


	/**
	 * 设置表头样式
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle setCellStyleTitle(HSSFWorkbook wb) {
		// 创建单元格样式
		HSSFCellStyle cellStyleTitle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyleTitle.setWrapText(true);

		return cellStyleTitle;
	}

	/**
	 * 设置表头以下单元格样式
	 * @param wb
	 * @return
	 */
	public static HSSFCellStyle setCellStyle(HSSFWorkbook wb) {
		HSSFCellStyle cellStyle = wb.createCellStyle();
		// 指定单元格居中对齐
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 指定单元格垂直居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 指定当单元格内容显示不下时自动换行
		cellStyle.setWrapText(true);
		return cellStyle;
	}

	/**
	 * 设置单元格字体
	 * @param wb
	 * @return
	 */
	public static HSSFFont setFont(HSSFWorkbook wb) {
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 200);
		return font;
	}
	
	/**
	 * read the Excel file
	 * 
	 * @param path
	 *            the path of the Excel file
	 * @return
	 * @throws Exception
	 */
	public static List<Map> readExcel(ExcelImportTemplate excelImportTemplate,String excelpath) throws Exception {
		if (excelpath == null || Common.EMPTY.equals(excelpath)) {
			return null;
		} else {
			String postfix = getPostfix(excelpath);
			if (!Common.EMPTY.equals(postfix)) {
				if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
					return readXls(excelImportTemplate, excelpath);
				} else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					return readXlsx(excelImportTemplate, excelpath);
				}
			} else {
				System.out.println(excelpath + Common.NOT_EXCEL_FILE);
			}
		}
		return null;
	}

	/**
	 * Read the Excel 2010
	 * @param excelImportTemplate
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static List<Map> readXlsx(ExcelImportTemplate excelImportTemplate,String path) throws Exception {
		System.out.println(Common.PROCESSING + path);
		InputStream is = new FileInputStream(path);
		// Read the Sheet
		Workbook workbook = WorkbookFactory.create(is);
		// sheet 0
		Sheet xssfSheet = workbook.getSheetAt(excelImportTemplate.getSheet());
		Iterator<Row> rowIterator = xssfSheet.iterator();
		List<Map> list = new ArrayList<Map>();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// start iterate row 1
			if (row.getRowNum() < excelImportTemplate.getRow()) {
				continue;
			}
			Iterator<Cell> cellIterator = row.cellIterator();
			List<Column> columns = excelImportTemplate.getColumns();
			Map map = new HashMap<String,String>();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				for (int i = 0; i < columns.size(); i++) {
					Column column = columns.get(i);
					if (cell.getColumnIndex() == column.getExcelcol()) {
						String value = getValue(cell);
						map.put(column.getFieldname(), value);
						break;
					}
				}
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * Read the Excel 2003-2007
	 * @param excelImportTemplate
	 * @param excelPath
	 * @return
	 * @throws Exception
	 */
	private static List<Map> readXls( ExcelImportTemplate excelImportTemplate,String excelPath) throws Exception {
		System.out.println(Common.PROCESSING + excelPath);
		InputStream is = new FileInputStream(excelPath);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		List<Map> list = new ArrayList<Map>(); 
		Map<String,String> map = null;
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(excelImportTemplate.getSheet());
		// Read the Row
		System.out.println("===getLastRowNum=="+hssfSheet.getLastRowNum());
		for (int rowNum = excelImportTemplate.getRow(); rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
			HSSFRow hssfRow = hssfSheet.getRow(rowNum);
			map = new HashMap<String,String>();
			if (hssfRow != null) {
				List<Column> columns = excelImportTemplate.getColumns();
				for (int i = 0; i < columns.size(); i++) {
					Column column = columns.get(i);
					String value = getValue(hssfRow.getCell(column.getExcelcol()));
					map.put(column.getFieldname(), value); 
				}
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 支持excel后缀为xlsx(Excel 2010+)
	 *
	 * @param xssfRow
	 * @return
	 */
	private static String getValue(Cell xssfRow) {
		if (xssfRow.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}

	/**
	 * 支持 excel后缀为 xls( Excel 2003-2007) 文件获取值
	 * 
	 * @param hssfCell
	 * @return
	 */
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
	/**
     * get postfix of the path
     * @param path
     * @return
     */
    public static String getPostfix(String path) {
        if (path == null || Common.EMPTY.equals(path.trim())) {
            return Common.EMPTY;
        }
        if (path.contains(Common.POINT)) {
            return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
        }
        return Common.EMPTY;
    }

}
