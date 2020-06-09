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
@Document(indexName="tsweb",type="page_block") 
public class page_block {
    @Id
    @Field(type=FieldType.Integer,store=true)
    private Integer id;
    @Field(type=FieldType.text,store=true)
    private String entity_id;
    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String content;
    @Field(type=FieldType.text,store=true)
    private String urid;
    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String engname;
    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String cnname;
    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String engdsp;
    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String cndsp;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getEngdsp() {
		return engdsp;
	}
	public void setEngdsp(String engdsp) {
		this.engdsp = engdsp;
	}
	public String getCndsp() {
		return cndsp;
	}
	public void setCndsp(String cndsp) {
		this.cndsp = cndsp;
	}
	@Override
	public String toString() {
		return "page_block [id=" + id + ", entity_id=" + entity_id + ", content=" + content + ", urid=" + urid
				+ ", engname=" + engname + ", cnname=" + cnname + ", engdsp=" + engdsp + ", cndsp=" + cndsp + "]";
	}

    	



}
