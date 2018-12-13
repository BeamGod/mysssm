package com.myssm.config.mymybatis.sqlsession;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.ResultSetMetaData;
import com.myssm.core.pojo.User;





public class MyExcutor implements Excutor{

private MyConfiguration xmlConfiguration ;//= new MyConfiguration("resources/config.xml","resources/UserMapper.xml");
	
	@Override
	public <T> T query(String sql, Object parameter,Class clzz) {
		// TODO Auto-generated method stub
		Connection connection = getConnection();
		ResultSet set = null;
		PreparedStatement pre = null;
		try {
			pre = connection.prepareStatement(sql);
			//设置参数
			pre.setString(1,parameter.toString());
			
			set = pre.executeQuery();
			System.out.println("set :" + set.toString());
			
				Object object = clzz.newInstance();
		
			//遍历结果集
			ResultSetMetaData metaData = (ResultSetMetaData) set.getMetaData();
		
			while(set.next()){
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					// resultSet数据下标从1开始
					String columnName = metaData.getColumnName(i + 1);
					int type = metaData.getColumnType(i + 1);
					if (Types.INTEGER == type) {
						// int
					} else if (Types.VARCHAR == type) {
						// String
					}
					System.out.print(columnName + "\t");
					/*u.setId(set.getString(1));
	                u.setUsername(set.getString(2)); 
	                u.setPassword(set.getString(3));*/
					
					Field[] fields = clzz.getDeclaredFields();
					for(Field field : fields){
						if(field.getName().toLowerCase().equals(columnName.replace("_", ""))){
							
							field.setAccessible(true);
							field.set(object,set.getString(i+1));
						}
					}
				}
				
			}
			
			
			return (T) object;
		} catch (SQLException e) {
			// TODO: handle exception
			 e.printStackTrace(); 
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				if(set != null){
					set.close();
				}if(pre != null){
					pre.close();
				}if(connection != null){
					connection.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();  
			}
		}
		return null;
	}
	
	

	private Connection getConnection(){
		try {
			Connection connection = xmlConfiguration.build();
			return connection;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
				
	}



	public MyExcutor(MyConfiguration xmlConfiguration) {
		super();
		// TODO Auto-generated constructor stub
		this.xmlConfiguration = xmlConfiguration;
	}
	

}
