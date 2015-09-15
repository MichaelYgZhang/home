package com.ist.common.util;

import httl.Engine;
import httl.Template;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TextTemplateUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(TextTemplateUtil.class);
	private static Engine engine;
	private static Properties config = new Properties();
	static {
		config.put("compiler", "httl.spi.compilers.JavassistCompiler");
		//config.put("json.codec", "com.ist.bmp.tools.JacksonCodec");
		//config.put("json.codec", "com.ist.bmp.tools.GsonCodec");
		config.put("cache.capacity", "1000");
		config.put("output.encoding", "UTF-8");
		config.put("input.encoding", "UTF-8"); 
		config.put("localized", "true");
		config.put("locale", "zh_CN");
		config.put("reloadable", "true");
		config.put("message.basename", "messages");
       
		
		engine = Engine.getEngine(config);
	}

	/**
	 * 根据classpath模板获取其内部包含的变量信息
	 * 
	 * @param vmPath
	 * @return
	 */
	public static Map<String, Class<?>> getClassPathVars(String vmPath) {
		Template t = null;
		try {
			t = getTemplate(vmPath);
			return t.getVariables();
		} catch (ParseException e) {
			logger.error("不能正确解析模板:" + vmPath, e);
		} catch (Exception e) {
			logger.error("IO异常:" + vmPath, e);
		}
		return null;
	}

	/**
	 * 根据string模板获取其内部包含的变量信息
	 * 
	 * @param vmPath
	 * @return
	 */
	public static Map<String, Class<?>> getStringVars(String source) {
		Template t = null;
		try {
			t = engine.parseTemplate(source, "UTF-8");
			return t.getVariables();
		} catch (ParseException e) {
			logger.error("模板语法异常" + source, e);
		} catch (Exception e) {
			logger.error("IO异常:" + source, e);
		}
		return null;
	}

	/**
	 * 根据vm模板的classpath进行freemarker模板合并返回
	 * 
	 * @param vmPath
	 *            freemarker模板的类路径
	 * @return 模板处理后的数据字符串
	 */
	public static String mergingByClassPath(String vmPath) {
		return mergingByClassPath(vmPath, EasyMap.EMPTY_MAP);
	}

	/**
	 * 根据vm模板的classpath进行freemarker模板合并返回
	 * 
	 * @param vmPath
	 *            freemarker模板的类路径
	 * @param model
	 *            数据模型
	 * @return 模板处理后的数据字符串
	 */
	public static String mergingByClassPath(String vmPath, Object model) {
		return mergingByClassPath(vmPath,null,model);
	}
	
	public static String mergingByClassPath(String vmPath,Locale locale, Object model) {
		Template t = null;
		try {
			if(locale == null){
				t = getTemplate(vmPath); 
			}else{
				t = getTemplate(vmPath,locale);
			}
			
		} catch (ParseException e) {
			logger.error("不能正确解析模板:" + vmPath, e);
			return "";
		} catch (Exception e) {
			logger.error("IO异常:" + vmPath, e);
			return "";
		}
		java.io.CharArrayWriter cw = new java.io.CharArrayWriter();
		java.io.PrintWriter pw = new java.io.PrintWriter(cw, true);
		try {
			t.render(model, pw);
		} catch (ParseException e) {
			logger.error("模板语法异常:" + vmPath, e);
		} catch (Exception e) {
			logger.error("IO异常:" + vmPath, e);
		}
		pw.close();
		return cw.toString();
	}

	private static Template getTemplate(String vmPath) throws IOException,
			ParseException {
		 return getTemplate(vmPath,null);
	}
	
	private static Template getTemplate(String vmPath, Locale locale)
			throws IOException, ParseException {
		Template t  = null;
		if(locale==null){
			t = engine.getTemplate(vmPath, "UTF-8");
		}else{
	    	t = engine.getTemplate(vmPath, locale, "UTF-8");
		}
		return t;
	}

	public static Template getTemplateByString(String source) {
		try {
			return engine.parseTemplate(source, "UTF-8");
		} catch (ParseException e) {
			logger.error("模板语法异常", e);
		}
		return null;
	}

	public static String render(Template t, Object model) {
		java.io.CharArrayWriter cw = new java.io.CharArrayWriter();
		java.io.PrintWriter pw = new java.io.PrintWriter(cw, true);
		try {
			t.render(model, pw);
		} catch (ParseException e) {
			logger.error("模板语法异常", e);
		} catch (IOException e) {
			logger.error("IO异常", e);
		}
		pw.close();
		return cw.toString();
	}

	public static String mergingByString(String source, Object model) {
		Template t = null;
		try {
			t = engine.parseTemplate(source, "UTF-8");
		} catch (ParseException e) {
			logger.error("模板语法异常", e);
		}
		java.io.CharArrayWriter cw = new java.io.CharArrayWriter();
		java.io.PrintWriter pw = new java.io.PrintWriter(cw, true);
		try {
			t.render(model, pw);
		} catch (ParseException e) {
			logger.error("模板语法异常", e);
		} catch (IOException e) {
			logger.error("IO异常", e);
		}
		pw.close();
		return cw.toString();
	}

	public static void mergingByClassPath(String vmPath, Object model,
			String fileName, String encoding) throws Exception {
		Template t = null;
		try {
			t = getTemplate(vmPath);
		} catch (ParseException e) {
			logger.error("模板语法异常:" + vmPath, e);
			throw e;
		} catch (Exception e) {
			logger.error("IO异常:" + vmPath, e);
			throw e;
		}
		outputToFile(model, fileName, t, encoding);
	}

	public static void mergingByString(String source, Object model,
			String fileName) throws Exception {
		mergingByString(source, model, fileName, "UTF-8");
	}

	public static void mergingByString(String source, Object model,
			String fileName, String encoding) throws Exception {
		Template t = null;
		try {
			t = engine.parseTemplate(source, encoding);
		} catch (ParseException e) {
			logger.error("模板语法异常", e);
			throw e;
		}
		outputToFile(model, fileName, t, encoding);
	}

	private static void outputToFile(Object model, String fileName, Template t,
			String encoding) throws ParseException, IOException,
			FileNotFoundException {
		OutputStreamWriter os = null;
		try {
			os = new OutputStreamWriter(new FileOutputStream(fileName),
					encoding);
			try {
				t.render(model, os);
			} catch (ParseException e) {
				logger.error("模板语法异常", e);
				throw e;
			} catch (IOException e) {
				logger.error("IO异常", e);
				throw e;
			}
		} catch (FileNotFoundException e) {
			logger.error("IO异常 toFile:[" + fileName + "]", e);
			throw e;
		} finally {
			if (null != os) {
				os.close();
			}
		}
	}

	public static void main(String[] args) {
		
	}
}
