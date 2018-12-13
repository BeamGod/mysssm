package com.myssm.config.mymybatis.config;

public class MybatisConfig {
	
	String configXmlLocation = "";
	
	String mapperPackagePath = "";

	public String getConfigXmlLocation(String configXmlLocation) {
		System.out.println("configXmlLocation :" + configXmlLocation);
		return this.configXmlLocation = this.getClass().getClassLoader().getResource(configXmlLocation).getPath();
	}


	public String getMapperPackagePath() {
		return mapperPackagePath;
	}

	public void setMapperPackagePath(String mapperPackagePath) {
		this.mapperPackagePath = mapperPackagePath;
	}
	
	
	

}
