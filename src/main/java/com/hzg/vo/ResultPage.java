package com.hzg.vo;

import java.util.List;

public class ResultPage {
private List content;
private Long totalElements;
private Integer page;
private Integer totalPages;
private Long numberOfElements;
public List getContent() {
	return content;
}
public void setContent(List content) {
	this.content = content;
}
public Long getTotalElements() {
	return totalElements;
}
public void setTotalElements(long l) {
	this.totalElements = l;
}
public Integer getPage() {
	return page;
}
public void setPage(Integer page) {
	this.page = page;
}
public Integer getTotalPages() {
	return totalPages;
}
public void setTotalPages(Integer totalPages) {
	this.totalPages = totalPages;
}
public Long getNumberOfElements() {
	return numberOfElements;
}
public void setNumberOfElements(long l) {
	this.numberOfElements = l;
}
@Override
public String toString() {
	return "resultPage [content=" + content + ", totalElements=" + totalElements + ", page=" + page + ", totalPages="
			+ totalPages + ", numberOfElements=" + numberOfElements + "]";
}

}
