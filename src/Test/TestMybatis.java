package Test;

import com.myssm.config.mymybatis.sqlsession.MyConfiguration;
import com.myssm.config.mymybatis.sqlsession.MyExcutor;
import com.myssm.config.mymybatis.sqlsession.MySqlsession;
import com.myssm.core.mapper.CompanyMapper;
import com.myssm.core.mapper.UserMapper;
import com.myssm.core.pojo.Company;
import com.myssm.core.pojo.User;



public class TestMybatis {
	
    public static void main(String[] args) {  
    	MyConfiguration myConfiguration =new MyConfiguration("mybatisconfig/config.xml","resources/CompanyMapper.xml");
        MySqlsession sqlsession=new MySqlsession(new MyExcutor(myConfiguration),myConfiguration);  
        CompanyMapper mapper = sqlsession.getMapper(CompanyMapper.class);  
        if(mapper == null ){
        	System.out.println(" null1 ");
        }
        Company company = mapper.getCompanyById("1");  
        System.out.println(company);
        
        //
        
        MyConfiguration myConfiguration2 =new MyConfiguration("mybatisconfig/config.xml","resources/UserMapper.xml");
        MySqlsession sqlsession2=new MySqlsession(new MyExcutor(myConfiguration2),myConfiguration2);  
        UserMapper mapper2 = sqlsession2.getMapper(UserMapper.class);  
        if(mapper2 == null ){
        	System.out.println(" null2 ");
        }
        User user = mapper2.getUserById("11");  
        System.out.println(user);
    } 

}
