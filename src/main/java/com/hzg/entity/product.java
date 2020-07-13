package com.hzg.entity;



import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.sql.Timestamp;


/**
 * 1.实体类和表的映射关系
 * @Eitity  声明实体类
 * @Table   表明实体类和表的映射关旭
 * 2.类中属性和表中字段的映射关系
 * @Id  主键
 * @GeneratedValue  主键的生成策略
 * @Column  实体类中属性和表中字段的映射关系
 * @co
 * @UpdateTimestamp 自动更新日期时间
 *
 */
@Document(indexName="tsweb",type="product") 
public class product {
    @Id
    @Field(type=FieldType.Integer,store=true)
    private Integer id;
    @Field(type=FieldType.text,store=true)
    private String tid;
    @Field(type=FieldType.text,store=true)
    private String category;
    @Field(type=FieldType.text,store=true)
    private String urid;
    @Field(type=FieldType.text,store=true)
    private String engname;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String cnname;
    @Field(type=FieldType.Boolean,store=true)
    private boolean onshelfus;
    @Field(type=FieldType.Boolean,store=true)
    private String onshelfcn;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUrid() {
		return urid;
	}
	public void setUrid(String urid) {
		this.urid = urid;
	}
	public String getEngname() {
		return engname;
	}
	public void setEngname(String engname) {
		this.engname = engname;
	}
	public String getCnname() {
		return cnname;
	}
	public void setCnname(String cnname) {
		this.cnname = cnname;
	}
	public boolean isOnshelfus() {
		return onshelfus;
	}
	public void setOnshelfus(boolean onshelfus) {
		this.onshelfus = onshelfus;
	}
	public String getOnshelfcn() {
		return onshelfcn;
	}
	public void setOnshelfcn(String onshelfcn) {
		this.onshelfcn = onshelfcn;
	}
	@Override
	public String toString() {
		return "product [id=" + id + ", tid=" + tid + ", category=" + category + ", urid=" + urid + ", engname="
				+ engname + ", cnname=" + cnname + ", onshelfus=" + onshelfus + ", onshelfcn=" + onshelfcn + "]";
	}

    


}
