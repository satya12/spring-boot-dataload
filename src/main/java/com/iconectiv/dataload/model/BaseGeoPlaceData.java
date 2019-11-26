package com.iconectiv.dataload.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseGeoPlaceData {

	@Id
	@Column(name = "GPKEY")
	private int gpKey;
	
	@Column(name = "STATENO")
	private int stateNo;
	
	@Column(name = "STATEABB")
	private String stateAbb;
	
	@Column(name = "STATENAME")
	private String stateName;
	
	@Column(name = "STATETYPE")
	private String stateType;
	
	@Column(name = "COUNTYNO")
	private int countyNo;
	
	@Column(name = "COUNTYTYPE")
	private String countyType;
	
	@Column(name = "COUNTYNAME")
	private String countyName;
	
	@Column(name = "MCDNO")
	private int mcdNo;
	
	@Column(name = "MCDNAME")
	private String mcdName;
	
	@Column(name = "MCDTYPE")
	private String mcdType = "city";
	
	@Column(name = "TOWNNO")
	private int townNo;
	
	@Column(name = "TOWNNAME")
	private String townName;
	
	@Column(name = "TOWNTYPE")
	private String townType = "city";
	
	public int getGpKey() {
		return gpKey;
	}
	public void setGpKey(int gpKey) {
		this.gpKey = gpKey;
	}
	public int getStateNo() {
		return stateNo;
	}
	public void setStateNo(int stateNo) {
		this.stateNo = stateNo;
	}
	public String getStateAbb() {
		return stateAbb;
	}
	public void setStateAbb(String stateAbb) {
		this.stateAbb = stateAbb;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateType() {
		return stateType;
	}
	public void setStateType(String stateType) {
		this.stateType = stateType;
	}
	public int getCountyNo() {
		return countyNo;
	}
	public void setCountyNo(int countyNo) {
		this.countyNo = countyNo;
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	public String getCountyType() {
		return countyType;
	}
	public void setCountyType(String countyType) {
		this.countyType = countyType;
	}
	public int getMcdNo() {
		return mcdNo;
	}
	public void setMcdNo(int mcdNo) {
		this.mcdNo = mcdNo;
	}
	public String getMcdName() {
		return mcdName;
	}
	public void setMcdName(String mcdName) {
		this.mcdName = mcdName;
	}
	public String getMcdType() {
		return mcdType;
	}
	public void setMcdType(String mcdType) {
		this.mcdType = mcdType;
	}
	public int getTownNo() {
		return townNo;
	}
	public void setTownNo(int townNo) {
		this.townNo = townNo;
	}
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}
	public String getTownType() {
		return townType;
	}
	public void setTownType(String townType) {
		this.townType = townType;
	}

	@Override
	public String toString() {
		return "CPCGeoPlaceData [gpKey=" + gpKey + ", stateAbb=" + stateAbb + ", mcdName=" + mcdName + ", townName=" + townName + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mcdName == null) ? 0 : mcdName.hashCode());
		result = prime * result + ((stateAbb == null) ? 0 : stateAbb.hashCode());
		result = prime * result + ((townName == null) ? 0 : townName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseGeoPlaceData other = (BaseGeoPlaceData) obj;
		if (mcdName == null) {
			if (other.mcdName != null)
				return false;
		} else if (!mcdName.equals(other.mcdName))
			return false;
		if (stateAbb == null) {
			if (other.stateAbb != null)
				return false;
		} else if (!stateAbb.equals(other.stateAbb))
			return false;
		if (townName == null) {
			if (other.townName != null)
				return false;
		} else if (!townName.equals(other.townName))
			return false;
		return true;
	}
	
}
