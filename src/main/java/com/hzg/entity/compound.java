package com.hzg.entity;



import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.test.context.TestExecutionListeners;
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
@Document(indexName="tsweb",type="compound") 
public class compound {
    @Id
    @Field(type=FieldType.Integer,store=true)
    private Integer id;
    @Field(type=FieldType.Integer,store=true)
    private Integer core;
    @Field(type=FieldType.Boolean,store=true)
    private Boolean onshelfus;
    @Field(type=FieldType.Boolean,store=true)
    private Boolean onshelfcn;
    @Field(type=FieldType.text,store=true)
    private String cpdid;
    @Field(type=FieldType.text,store=true)
    private String tID;
    @Field(type=FieldType.text,store=true)
    private String synonymseng;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String description;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String name;
    @Field(type=FieldType.text,store=true)
    private String urid;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String synonyms;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String targets;
    @Field(type=FieldType.text,store=true,analyzer="ik_max_word")
    private String targetsurid;
    @Field(type=FieldType.Integer,store=true)
    private Integer hot;
    
    
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCore() {
		return core;
	}
	public void setCore(Integer core) {
		this.core = core;
	}
	public Boolean getOnshelfus() {
		return onshelfus;
	}
	public void setOnshelfus(Boolean onshelfus) {
		this.onshelfus = onshelfus;
	}
	public Boolean getOnshelfCN() {
		return onshelfcn;
	}
	public void setOnshelfCN(Boolean onshelfCN) {
		this.onshelfcn= onshelfCN;
	}
	public String getCpdid() {
		return cpdid;
	}
	public void setCpdid(String cpdid) {
		this.cpdid = cpdid;
	}
	public String gettID() {
		return tID;
	}
	public void settID(String tID) {
		this.tID = tID;
	}
	public String getSynonymseng() {
		return synonymseng;
	}
	public void setSynonymseng(String synonymseng) {
		this.synonymseng = synonymseng;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getSynonyms() {
		return synonyms;
	}
	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}
	public String getTargets() {
		return targets;
	}
	public void setTargets(String targets) {
		this.targets = targets;
	}
	public String getTargetsurid() {
		return targetsurid;
	}
	public void setTargetsurid(String targetsurid) {
		this.targetsurid = targetsurid;
	}
	@Override
	public String toString() {
		return "compound [id=" + id + ", core=" + core + ", onshelfus=" + onshelfus + ", onshelfCN=" + onshelfcn
				+ ", cpdid=" + cpdid + ", tID=" + tID + ", synonymseng=" + synonymseng + ", description=" + description
				+ ", name=" + name + ", urid=" + urid + ", synonyms=" + synonyms + ", targets=" + targets
				+ ", targetsurid=" + targetsurid + "]";
	}




}
