package com.myssm.config.mymybatis.sqlsession;

import javax.management.Query;

public interface Excutor {
	public <T> T query(String statement,Object parameter,Class clzz);

}
