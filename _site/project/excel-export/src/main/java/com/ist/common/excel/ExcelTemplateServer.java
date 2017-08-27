package com.ist.common.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.ist.common.util.TextTemplateUtil;

public class ExcelTemplateServer {
	
	public void exportDataByXLST(String templateFile,Map<Object, Object> beans, 
			String targetFileName, HttpServletResponse response) throws Exception {
		HSSFWorkbook workbook = null; 
		InputStream in;
        try {
        	if(!targetFileName.endsWith(".xls") && !targetFileName.endsWith(".xlsx")){
        		targetFileName += ".xls";
        	}
            XLSTransformer transformer = new XLSTransformer();
            in = new FileInputStream(templateFile);  
            workbook = transformer.transformXLS(in, beans);
            /** 此注释的代码不支持下载，只会在服务器上生成excel文件
            OutputStream os = new FileOutputStream(targetFile);    
            workbook.write(os);    
            os.close();
            **/  
            response.setContentType("application ns.ms-excel");  
            response.setHeader("Expires", "0");  
            response.setHeader("Cache-Control",  
            		"must-revalidate, post-check=0, pre-check=0");  
            response.setHeader("Pragma", "public");  
            response.setHeader("Content-disposition", "attachment;filename="+targetFileName);  
            workbook.write(response.getOutputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ParsePropertyException pe) {
            pe.printStackTrace();
        }
        
    }
	public void exportDataByVelocity(String templateFileName, Map<Object, Object> beans, 
            String targetFilePath, String targetFileName, HttpServletResponse response) throws Exception {
		final String VELOCITY = "velocity.properties";
		String temp = this.getClass().getClassLoader().getResource("").getPath();
		
		VelocityContext context = new VelocityContext();
		Iterator it = beans.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next().toString();
			Object value = beans.get(key);
			context.put(key, value);
		}
	
		String initStr = temp +  VELOCITY;
		//System.out.println("initStr="+initStr);
		
		 //输出流
		StringWriter writer = null;
		InputStream ins = null;
		try {
			Velocity.init(initStr);
			
	        //输出流
			writer = new StringWriter();
			Velocity.mergeTemplate(templateFileName, "UTF-8", context,
					writer);
			FileUtils.writeStringToFile(new File(targetFilePath+targetFileName), writer.toString());
			System.out.println("writer="+writer.toString());
			File outFile = new File(targetFilePath+targetFileName);
			int contentLength = (int) outFile.length();
			ins = new FileInputStream(targetFilePath+targetFileName);
			response.setContentType("application/x-msdownload");
			response.setContentLength(contentLength);
			response.setHeader("Content-disposition", "attachment;filename="+targetFileName);  
		    OutputStream out = response.getOutputStream();
		    byte[] buffer = new byte[65535];
		    int len;
		    while ((len = ins.read(buffer)) > 0){
		        out.write(buffer, 0, len);
		    }
		    
		    out.flush();
		    out.close();
		    
		} catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if (null != writer){
        		writer.close();
        	}
        	if (null != ins){
        		ins.close();
        	}
        	
        	File outFile = new File(targetFilePath+targetFileName);
        	if(outFile.exists()){
        		outFile.delete();
        	}
        	
        }
        
    
	}
	
	public void exportDataByHttl(String templateFileName, Map<Object, Object> beans, 
			String targetFilePath, String targetFileName, HttpServletResponse response) throws Exception {
		
		InputStream ins = null;
		OutputStream out = null;
		try {
			String temp = this.getClass().getClassLoader().getResource("").getPath();
			//System.out.println("temp="+temp);
			
			String templatePath = templateFileName;
			System.out.println("LOCAL="+Locale.getDefault());
			String content = TextTemplateUtil.mergingByClassPath(templatePath,Locale.getDefault(), beans);
			
			FileUtils.writeStringToFile(new File(targetFilePath+targetFileName), content);
			
			File outFile = new File(targetFilePath+targetFileName);
			int contentLength = (int) outFile.length();
			ins = new FileInputStream(targetFilePath+targetFileName);
			response.setContentType("application/x-msdownload");
			response.setContentLength(contentLength);
			response.setHeader("Content-disposition", "attachment;filename="+targetFileName);  
		    out = response.getOutputStream();
		    byte[] buffer = new byte[65535];
		    int len;
		    while ((len = ins.read(buffer)) > 0){
		        out.write(buffer, 0, len);
		    }
		    out.flush();
		    
		} catch (Exception e) {
            e.printStackTrace();
        }finally{
        	if (null != ins){
        		ins.close();
        	}
        	if (null != out){
        		out.close();
        	}
        	File outFile = new File(targetFilePath+targetFileName);
        	if(outFile.exists()){
        		outFile.delete();
        	}
        }
    
	}
	
	

}
