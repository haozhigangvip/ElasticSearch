package com.hzg.vo;

import java.util.List;

import com.hzg.entity.Company;

public class tCompanyList {
private List<Company> targetCompanyList;
private List<Company> sourceCompanyList;
private List<String> salesList;
public List<Company> getTargetCompanyList() {
	return targetCompanyList;
}
public void setTargetCompanyList(List<Company> targetCompanyList) {
	this.targetCompanyList = targetCompanyList;
}
public List<Company> getSourceCompanyList() {
	return sourceCompanyList;
}
public void setSourceCompanyList(List<Company> sourceCompanyList) {
	this.sourceCompanyList = sourceCompanyList;
}
public List<String> getSalesList() {
	return salesList;
}
public void setSalesList(List<String> salesList) {
	this.salesList = salesList;
}


}
