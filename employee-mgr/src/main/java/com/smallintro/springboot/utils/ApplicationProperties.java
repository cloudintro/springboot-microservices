package com.smallintro.springboot.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="springboot")
//@PropertySource("classpath:application.properties")
public class ApplicationProperties {

	private String fileExportPath;
	
	private String fileExportName;

	private String fileDateFormat;

	public String getFileExportPath() {
		return fileExportPath;
	}

	public void setFileExportPath(String fileExportPath) {
		this.fileExportPath = fileExportPath;
	}

	public String getFileExportName() {
		return fileExportName;
	}

	public void setFileExportName(String fileExportName) {
		this.fileExportName = fileExportName;
	}

	public String getFileDateFormat() {
		return fileDateFormat;
	}

	public void setFileDateFormat(String fileDateFormat) {
		this.fileDateFormat = fileDateFormat;
	}

	@Override
	public String toString() {
		return "ProjectProperties [fileExportPath=" + fileExportPath + ", fileExportName=" + fileExportName
				+ ", fileDateFormat=" + fileDateFormat + "]";
	}
	

}
