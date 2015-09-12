package com.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;

import com.vo.Column;
import com.vo.ExcelImportTemplate;

public class ParseXMLUtils {


	/**
	 * 解析xml文件，返回Template对象集合
	 * 
	 * @param xmlPath
	 * @return
	 * @throws Exception
	 */
	public static List<ExcelImportTemplate> parseXML(String xmlPath){
		if(xmlPath.trim().length() == 0){
			System.out.println("==================xmlPath路径无效！！");
			return null;
		}
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		List<ExcelImportTemplate> templateList = new ArrayList<ExcelImportTemplate>();
		try{
			DocumentBuilder db = dbf.newDocumentBuilder();
			org.w3c.dom.Document document = db.parse(new File(xmlPath));
			DOMReader domReader = new DOMReader();
			Document d = domReader.read(document);
			Element root = d.getRootElement();
			System.out.println("第一层根元素:" + root.getName());
			List<?> templates = root.elements();
			ExcelImportTemplate tempVO = null;
			Column columnVO = null;
			List<Column> columnsList = null;
			for (Iterator<?> iterTemplates = templates.iterator(); iterTemplates.hasNext();) {
				Element templatesEle = (Element) iterTemplates.next();
				System.out.println("第二层根元素:" + templatesEle.getName());
				for (Iterator<?> template = templatesEle.elements().iterator(); template.hasNext();) {
					tempVO = new ExcelImportTemplate();
					Element templateEle = (Element) template.next();
					System.out.println("第三层=" + templateEle.getName()+",id="+templateEle.attribute("id").getText());
					tempVO.setId(templateEle.attribute("id").getText());
					tempVO.setTitle(templateEle.attribute("title").getText());
					for (Iterator<?> element = templateEle.elements().iterator(); element.hasNext();) {
						Element e = (Element) element.next();
						if (e.getName().equals("sheet")) {
							tempVO.setSheet(Integer.parseInt(e.getText().trim()));
						} else if (e.getName().equals("row")) {
							tempVO.setRow(Integer.parseInt(e.getText().trim()));
						} else if (e.getName().equals("tablename")) {
							tempVO.setTablename(e.getText());
						}
						columnsList = new ArrayList<Column>();
						for (Iterator<?> columns = e.elements().iterator(); columns
								.hasNext();) {
							columnVO = new Column();
							Element column = (Element) columns.next();
							columnVO.setFieldname(column.attribute("fieldname")
									.getText());
							columnVO.setFiledtype(column.attribute("filedtype")
									.getText());
							columnVO.setExcelcol(Integer.parseInt(column.attribute(
									"excelcol").getText()));
							if (column.attribute("transcoding") != null) {
								columnVO.setTranscoding(column.attribute(
										"transcoding").getText());
							}
							System.out.println("fieldname="+columnVO.getFieldname()+",filedtype="+
									columnVO.getFiledtype()+",excelcol="+columnVO.getExcelcol()
									+",transcoding="+columnVO.getTranscoding());
							
							columnsList.add(columnVO);
						}
						tempVO.setColumns(columnsList);
					}
					templateList.add(tempVO);
				}
			}
		}catch(Exception e) {
			System.out.println("====================遍历excel导入的xml文件失败！"+e);
		}
		System.out.println("==="+templateList.size());
		return templateList;
	}

	/**
	 * 根据template的id，获取Template对象
	 * @param templateList
	 * @param id
	 * @return
	 */
	public static ExcelImportTemplate getTemplateById(List<ExcelImportTemplate> templateList,String id){
		ExcelImportTemplate tempVO = null;
		if(id !=null && templateList.size() > 0){
			for(ExcelImportTemplate template : templateList){
				if(id.equals(template.getId())){
					return template;
				}
			}
		}
		return tempVO;
	}

}
