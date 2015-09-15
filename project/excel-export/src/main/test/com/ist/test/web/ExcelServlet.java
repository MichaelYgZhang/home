package com.ist.test.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ist.common.excel.ExcelTemplateServer;
import com.ist.test.jdbc.ConnData;

/**
 * Servlet implementation class ExcelServlet
 */
public class ExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExcelTemplateServer excelTemplateServer = new ExcelTemplateServer();
		String sql = "select * from t00_user";
		Connection conn = null;
		try {
			conn = ConnData.getConnection();
			List userList = ConnData.executeQuery(conn, sql);
			Map<Object,Object> beans = new HashMap<Object,Object>();
			beans.put("userList", userList);
			beans.put("username", "系统管理员");
			//jxl
			String templatePath = this.getServletConfig().getServletContext().getRealPath("/")+"\\WEB-INF\\template\\";
			//vel httl
			String targetPath = "D:\\activities-workspace\\excelApp\\WebContent\\WEB-INF\\temp\\";
			
			ExcelTemplateServer excel = new ExcelTemplateServer();
			String type = request.getParameter("type");
			if(null != type && "jxl".equals(type)){
				String xlstTemplateFile = templatePath + "xlstTemplate.xls";
				//下载文件名
				String targetFileName = "jxlTest.xls";
				excel.exportDataByXLST(xlstTemplateFile, beans, targetFileName, response);
			}else if(null != type && "vel".equals(type)){
				String veltemplateName = "velTemplate.htm";
				String targetFileName = "velTest.xls";
				excel.exportDataByVelocity(veltemplateName, beans, targetPath, targetFileName, response);
			}else if(null != type && "httl".equals(type)){
				//注意此处templatePath，正确的方式是加上classes路径的模板名称
				String veltemplateName = "com/ist/httl/excel/httlTemplate.htm";
				String targetFileName = "httlTest.xls";
				excel.exportDataByHttl(veltemplateName, beans, targetPath, targetFileName, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
