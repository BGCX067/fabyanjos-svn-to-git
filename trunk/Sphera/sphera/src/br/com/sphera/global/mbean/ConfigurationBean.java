package br.com.sphera.global.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

public class ConfigurationBean {

	private List<String> acceptedFilesTypeList;
	
	public ConfigurationBean() {
		acceptedFilesTypeList = new ArrayList<String>();
	}
	
	public String getAcceptedFiles() {
		StringBuffer sb = new StringBuffer();
		int size = acceptedFilesTypeList.size();
		int i = 0;
		for (String fName : acceptedFilesTypeList) {
			sb.append(fName);
			i++;
			if(i!=size){
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	

	public List<String> getAcceptedFilesTypeList() {
		return acceptedFilesTypeList;
	}

	public void setAcceptedFilesTypeList(List<String> acceptedFilesTypeList) {
		this.acceptedFilesTypeList = acceptedFilesTypeList;
	}
	@PostConstruct
	public void criaTiposPermitidos(){
		acceptedFilesTypeList.add("jpg");
		acceptedFilesTypeList.add("gif");
		acceptedFilesTypeList.add("png");
		acceptedFilesTypeList.add("bmp");
		acceptedFilesTypeList.add("pdf");
		acceptedFilesTypeList.add("doc");
		acceptedFilesTypeList.add("xls");
		acceptedFilesTypeList.add("ppt");
		acceptedFilesTypeList.add("pps");
		acceptedFilesTypeList.add("sxi");
		acceptedFilesTypeList.add("sql");
		acceptedFilesTypeList.add("zip");
		acceptedFilesTypeList.add("rar");
		acceptedFilesTypeList.add("odb");
		acceptedFilesTypeList.add("htm");
		acceptedFilesTypeList.add("html");
	}
}
