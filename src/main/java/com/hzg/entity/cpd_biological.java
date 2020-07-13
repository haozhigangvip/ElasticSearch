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
@Document(indexName="tsweb",type="cpd_biological") 
public class cpd_biological {
    @Id
    @Field(type=FieldType.Integer,store=true)
    private Integer id;
    @Field(type=FieldType.text,store=true)
    private String cpdid;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String description;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String vitro;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String vivo;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String name;
    @Field(type=FieldType.text,store=true)
    private String urid;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String synonym;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpdid() {
		return cpdid;
	}
	public void setCpdid(String cpdid) {
		this.cpdid = cpdid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVitro() {
		return vitro;
	}
	public void setVitro(String vitro) {
		this.vitro = vitro;
	}
	public String getVivo() {
		return vivo;
	}
	public void setVivo(String vivo) {
		this.vivo = vivo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrid() {
		return urid;
	}
	public void setUrid(String urid) {
		this.urid = urid;
	}
	public String getSynonym() {
		return synonym;
	}
	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}
	@Override
	public String toString() {
		return "cpd_biological [id=" + id + ", cpdid=" + cpdid + ", description=" + description + ", vitro=" + vitro
				+ ", vivo=" + vivo + ", name=" + name + ", urid=" + urid + ", synonym=" + synonym + "]";
	}


    



}
