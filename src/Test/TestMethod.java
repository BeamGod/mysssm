package Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TestMethod {
	
	public String string1 = "11111";
	public String string2 = "22222";
	public String string3 = "33333";
	public Integer integer1 = 111;

	
	public void setString1(String string1,String string2,String string3,Integer integer1) {
		System.out.println("进入 setString1");
		this.string1 = string1;
		this.string2 = string2;
		this.string3 = string3;
		this.integer1 = integer1;
		System.out.println("this.string1 :" + this.string1);
		System.out.println("this.string2 :" + this.string2);
		System.out.println("this.string3 :" + this.string3);
		System.out.println("this.integer1 :" + this.integer1);
	}
	
	

}
