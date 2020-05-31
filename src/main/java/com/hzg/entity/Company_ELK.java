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
@Document(indexName="tsbio",type="company") 
public class Company_ELK {



    @Id
    @Field(type=FieldType.Long,store=true)
    private Integer autoid;

    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String comid;
   

    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String companyname;
    @Field(type=FieldType.text,store=true)
    private String comptype;
    @Field(type=FieldType.text,store=true)
    private String comadrid;
    @Field(type=FieldType.text,store=true)
    private String phone;
    @Field(type=FieldType.text,store=true)
    private String abbre;
    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String note;
    @Field(type=FieldType.text,store=true)
    private Timestamp creatime;
    @Field(type=FieldType.text,store=true,analyzer="ik_smart")
    private String csalesman;
    @Field(type=FieldType.Integer,store=true)
    private Integer delTag;
	public Integer getAutoid() {
		return autoid;
	}
	public void setAutoid(Integer autoid) {
		this.autoid = autoid;
	}
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getComptype() {
		return comptype;
	}
	public void setComptype(String comptype) {
		this.comptype = comptype;
	}
	public String getComadrid() {
		return comadrid;
	}
	public void setComadrid(String comadrid) {
		this.comadrid = comadrid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAbbre() {
		return abbre;
	}
	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Timestamp getCreatime() {
		return creatime;
	}
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	public String getCsalesman() {
		return csalesman;
	}
	public void setCsalesman(String csalesman) {
		this.csalesman = csalesman;
	}
	public Integer getDelTag() {
		return delTag;
	}
	public void setDelTag(Integer delTag) {
		this.delTag = delTag;
	}
	@Override
	public String toString() {
		return "Company_ELK [autoid=" + autoid + ", comid=" + comid + ", companyname=" + companyname + ", comptype="
				+ comptype + ", comadrid=" + comadrid + ", phone=" + phone + ", abbre=" + abbre + ", note=" + note
				+ ", creatime=" + creatime + ", csalesman=" + csalesman + ", delTag=" + delTag + "]";
	}
  


}
