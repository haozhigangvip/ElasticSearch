package com.hzg.vo;

public class SalesStatistics {
private String salesMan;
private Integer count;
public String getSalesMan() {
	return salesMan;
}
public void setSalesMan(String salesMan) {
	this.salesMan = salesMan;
}
public Integer getCount() {
	return count;
}
public void setCount(Integer count) {
	this.count = count;
}
@Override
public String toString() {
	return "SalesStatistics [salesMan=" + salesMan + ", count=" + count + "]";
}



}
