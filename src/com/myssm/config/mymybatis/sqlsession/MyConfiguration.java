package com.myssm.config.mymybatis.sqlsession;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;










import java.sql.DriverManager;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;







import com.myssm.config.mymybatis.config.Function;
import com.myssm.config.mymybatis.config.MapperBean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class MyConfiguration {
	private static ClassLoader loader = ClassLoader.getSystemClassLoader();
	
	public String xmlPath = "";
	
	public String configXmlPath = "";
	
	//读取xml的信息并处理
	public Connection build(){
		String resource = this.configXmlPath;
		try {
		//	InputStream stream = loader.getResourceAsStream(resource);
			InputStream stream = new FileInputStream(resource);
			SAXReader reader = new SAXReader();
			Document document = reader.read(stream);
			Element root = document.getRootElement();
			return evalDataSource(root);
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException("error occured while evaling xml " + resource);
		}
	}
	
	private  Connection evalDataSource(Element node) throws ClassNotFoundException {
		if(!node.getName().equals("database")){
			throw new RuntimeException("没有获取到xml的database根标签");
		}
		String driverClassName = null;
		String url = null;
		String username = null;
		String password = null;
		//遍历跟节点下的全部property标签
		for(Object item : node.elements("property")){
			Element i = (Element)item;
			String value = getValue(i);
			String name = i.attributeValue("name");
			if(name == null || value == null){
				throw new RuntimeException("database标签下的property标签的内容有错");
			}
			
			//赋值
			switch(name){
			case "url" : url = value; break;
			case "username" : username = value; break;
			case "password" : password = value; break;
			case "driverClassName" : driverClassName = value; break; 
			default : throw new RuntimeException("[database]: <property> unknown name"); 
			}
		}
		
		Class.forName(driverClassName);
		Connection connection = null;
		try {
			//建立数据库链接
			connection = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
		
	}
	
	//获取property的值，如果有value，则读取，没有设置value，则读取内容
	private String getValue(Element node){
		return node.hasContent() ? node.getText() : node.attributeValue("value");
	}
	
	@SuppressWarnings("rawtypes")
	public MapperBean readMapper(){
		String path = this.xmlPath;
		MapperBean mapper = new MapperBean();
		try {
			System.out.println("path >>>>> :" + path);
			//InputStream stream = loader.getResourceAsStream(path);
			InputStream stream = new FileInputStream(path);
			SAXReader reader = new SAXReader();
			if(stream == null) System.out.println("stream null >>>>>>>>>>>>");
			Document document = reader.read(stream);
			Element root = document.getRootElement();
			mapper.setInterfaceName(root.attributeValue("nameSpace").trim());
		    List<Function> list = new ArrayList<Function>();//用来存储方法的list
		    for(Iterator rootIter = root.elementIterator();rootIter.hasNext();) {//遍历根节点下的所有子节点
		    	Function function = new Function();  //用来存储一条方法的记录
		    	Element element = (Element) rootIter.next();
		    	String sqltype = element.getName().trim();
		    	String funcName = element.attributeValue("id").trim();
		    	String sql = element.getText().trim();
		    	String resultType = element.attributeValue("resultType").trim();
		    	function.setSqltype(sqltype);
		    	function.setFuncName(funcName);
		    	Object newInstance = null;
		    	try {
					newInstance = Class.forName(resultType).newInstance();
				} catch(InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
		    	function.setResultType(newInstance);
		    	function.setSql(sql);
		    	list.add(function);
		    	
		    }
		    mapper.setList(list);
		} catch (DocumentException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapper;
	}

	public MyConfiguration(String configXmlPath,String xmlPath) {
		super();
		// TODO Auto-generated constructor stub
		this.xmlPath = xmlPath;
		this.configXmlPath = configXmlPath;
	}
	
	

	
	
}
