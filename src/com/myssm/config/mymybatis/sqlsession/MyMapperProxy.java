package com.myssm.config.mymybatis.sqlsession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

import com.myssm.config.mymybatis.config.Function;
import com.myssm.config.mymybatis.config.MapperBean;


public class MyMapperProxy implements InvocationHandler{
	
	private MySqlsession mySqlsession ;
	
	private MyConfiguration myConfiguration ;
	
	public  MyMapperProxy(MyConfiguration myConfiguration,MySqlsession mySqlsession) {
		// TODO Auto-generated constructor stub
		this.myConfiguration=myConfiguration;  
        this.mySqlsession=mySqlsession;  
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		//MapperBean readMapper = myConfiguration.readMapper("resources/UserMapper.xml");
		MapperBean readMapper = myConfiguration.readMapper();
		//是否是xml文件对应的接口
		if(!method.getDeclaringClass().getName().equals(readMapper.getInterfaceName())){
			return null;
		}
		List<Function> list = readMapper.getList();
		if(null != list || 0 != list.size()){
			for(Function function : list) {
				//看id是否和接口的方法名一样
				if(method.getName().equals(function.getFuncName())){
					return mySqlsession.selectOne(function.getSql(), String.valueOf(args[0]),function.getResultType().getClass());
				}
			}
		}
		
		return null;
	}

}