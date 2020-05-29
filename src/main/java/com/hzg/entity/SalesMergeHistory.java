package com.hzg.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity     //声明实体类
@Table(name="SalesMergeHistory")
public class SalesMergeHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "AutoID" )
	private int autoID;
	@Column(name = "comID", columnDefinition = "VARCHAR(50)")
	private String comID;
	@Column(name = "oldsalesname", columnDefinition = "VARCHAR(50)")
	private String oldSalesName;
	@Column(name = "newsalesname", columnDefinition = "VARCHAR(50)")
	private String newSalesName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "creatime")
	private Timestamp  creatime;

	public int getAutoID() {
		return autoID;
	}

	public void setAutoID(int autoID) {
		this.autoID = autoID;
	}

	public String getComID() {
		return comID;
	}

	public void setComID(String comID) {
		this.comID = comID;
	}

	public String getOldSalesName() {
		return oldSalesName;
	}

	public void setOldSalesName(String oldSalesName) {
		this.oldSalesName = oldSalesName;
	}

	public String getNewSalesName() {
		return newSalesName;
	}

	public void setNewSalesName(String newSalesName) {
		this.newSalesName = newSalesName;
	}

	public Timestamp getCreatime() {
		return creatime;
	}

	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}

	@Override
	public String toString() {
		return "SalesMergeHistory [autoID=" + autoID + ", comID=" + comID + ", oldSalesName=" + oldSalesName
				+ ", newSalesName=" + newSalesName + ", creatime=" + creatime + "]";
	}
	
	
	
	
	
	
	
	
}
