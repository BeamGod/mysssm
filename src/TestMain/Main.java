package TestMain;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Test.TestMethod;

public class Main {
	
    
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
    	Class<?> clzz = Class.forName("Test.TestMethod");
    	clzz.getMethods();
    	Object[] objects = new Object[4];
    	TestMethod tsMethod = new TestMethod();
    	for(Method method : clzz.getMethods()){
    		
    		System.out.println(">>>>>>>method.getParameterCount()" + method.getParameterCount());
        	objects[0] = "1";
        	objects[1] = "2";
        	objects[2] = "3";
        	objects[3] = Integer.parseInt("1");
    		System.out.println("method name " + method.getName());
    		if("setString1".equals(method.getName()))
    		method.invoke(clzz.newInstance(),objects);
    	}
    	
    	
    }
	
	 

}
