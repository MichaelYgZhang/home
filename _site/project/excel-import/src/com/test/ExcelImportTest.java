package com.test;

import java.util.List;
import java.util.Map;

import com.utils.ExcelImportService;
import com.utils.ExcelImportUtils;
import com.utils.ParseXMLUtils;
import com.vo.ExcelImportTemplate;

public class ExcelImportTest {
	public static void main(String[] args) throws Exception {
		ExcelImportService  excelImportService = new ExcelImportService();
		String xmlPath = "student.xml";
		List listTemp = ParseXMLUtils.parseXML(xmlPath);
		ExcelImportTemplate excelImportTemplate = ParseXMLUtils.getTemplateById(listTemp, "1");
		String excelPath = "student.xls";
		List<Map> list = ExcelImportUtils.readExcel(excelImportTemplate, excelPath);
		for(Map<String,String> map :list){
			for(Map.Entry<String, String> entry: map.entrySet()){
				System.out.println("key="+entry.getKey()+",value="+entry.getValue());
			}
		}
		excelImportService.ExcelImport(list, excelImportTemplate);
	}
}
