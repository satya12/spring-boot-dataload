package com.iconectiv.dataload.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseStreetSegData {

	@Id
	@Column(name = "RECNUM")
	private int recNum;
	
	@Column(name = "SNROOT")
	private String snRoot;
	
	@Column(name = "SNTYPE")
	private String snType;
	
	@Column(name = "SNPREFIX")
	private String snPrefix;
	
	@Column(name = "SNSUFFIX")
	private String snSuffix;

	@Column(name = "ADDR_STARTL")
	private String addrStartl = "0";
	
	@Column(name = "ADDR_STARTR")
	private String addrStartr = "0";
	
	@Column(name = "ADDR_ENDL")
	private String addrEndl = "0";
	
	@Column(name = "ADDR_ENDR")
	private String addrEndr = "0";
	
	@Column(name = "CLASSCODE")
	private String classCode = "X";

	@Column(name = "STARTLAT")
	private String startLat = "0";
	
	@Column(name = "STARTLON")
	private String startLon = "0";
	
	@Column(name = "ENDLAT")
	private String endLat = "0";
	
	@Column(name = "ENDLON")
	private String endLon = "0";
	
	@Column(name = "POSTAL")
	private String postal;	
	
	@Column(name = "SPABB")
	private String stateAbb;
	
	@Column(name = "GPKEY")
	private int gpKey;
	
	@Column(name = "PRIMARYIND")
	private String primaryInd = "P";
	
	@Column(name = "QFLAG")
	private String qFlag = "x";
	
	@Column(name = "LRB")
	private String lrb = "B";
	
	@Column(name = "ORDERFLAG")
	private String orderflag = "0";
	
	@Column(name = "LASTUPDATE")
	private String lastUpdate = "0";

	@Transient
	private int stateNo;
	
	@Transient
	private String stateName;
	
	@Transient
	private String stateType;
	
	@Transient
	private int countyNo;
	
	@Transient
	private String countyName;
	
	@Transient
	private String countyType;
	
	@Transient
	private int mcdNo;
	
	@Transient
	private String mcdName;
	
	@Transient
	private String mcdType = "city";
	
	@Transient
	private int townNo;
	
	@Transient
	private String townName;
	
	@Transient
	private String townType = "city";
	
	@Transient
	private String uk1;

	@Transient
	private String uk2;
	
	public int getRecNum() {
		return recNum;
	}
	public void setRecNum(int recNum) {
		this.recNum = recNum;
	}
	public String getSnRoot() {
		return snRoot;
	}
	public void setSnRoot(String snRoot) {
		this.snRoot = snRoot;
	}
	public String getSnType() {
		return snType;
	}
	public void setSnType(String snType) {
		this.snType = snType;
	}
	public String getSnPrefix() {
		return snPrefix;
	}
	public void setSnPrefix(String snPrefix) {
		this.snPrefix = snPrefix;
	}
	public String getSnSuffix() {
		return snSuffix;
	}
	public void setSnSuffix(String snSuffix) {
		this.snSuffix = snSuffix;
	}
	public String getAddrStartl() {
		return addrStartl;
	}
	public void setAddrStartl(String addrStartl) {
		this.addrStartl = addrStartl;
	}
	public String getAddrStartr() {
		return addrStartr;
	}
	public void setAddrStartr(String addrStartr) {
		this.addrStartr = addrStartr;
	}
	public String getAddrEndl() {
		return addrEndl;
	}
	public void setAddrEndl(String addrEndl) {
		this.addrEndl = addrEndl;
	}
	public String getAddrEndr() {
		return addrEndr;
	}
	public void setAddrEndr(String addrEndr) {
		this.addrEndr = addrEndr;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getStartLat() {
		return startLat;
	}
	public void setStartLat(String startLat) {
		this.startLat = startLat;
	}
	public String getStartLon() {
		return startLon;
	}
	public void setStartLon(String startLon) {
		this.startLon = startLon;
	}
	public String getEndLat() {
		return endLat;
	}
	public void setEndLat(String endLat) {
		this.endLat = endLat;
	}
	public String getEndLon() {
		return endLon;
	}
	public void setEndLon(String endLon) {
		this.endLon = endLon;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getPrimaryInd() {
		return primaryInd;
	}
	public void setPrimaryInd(String primaryInd) {
		this.primaryInd = primaryInd;
	}
	public String getqFlag() {
		return qFlag;
	}
	public void setqFlag(String qFlag) {
		this.qFlag = qFlag;
	}
	public String getLrb() {
		return lrb;
	}
	public void setLrb(String lrb) {
		this.lrb = lrb;
	}
	public String getOrderflag() {
		return orderflag;
	}
	public void setOrderflag(String orderflag) {
		this.orderflag = orderflag;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
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
	public String getUk1() {
		return uk1;
	}
	public void setUk1(String uk1) {
		this.uk1 = uk1;
	}
	public String getUk2() {
		return uk2;
	}
	public void setUk2(String uk2) {
		this.uk2 = uk2;
	}

	@Override
	public String toString() {
		return "CPCStreetSegData [recNum=" + recNum + ", snRoot=" + snRoot + ", snType=" + snType + ", snPrefix=" + snPrefix
				+ ", snSuffix=" + snSuffix + ", addrStartl=" + addrStartl + ", addrEndl=" + addrEndl + ", postal="
				+ postal + ", primaryInd=" + primaryInd + ", gpKey=" + gpKey + ", stateAbb=" + stateAbb + ", mcdName="
				+ mcdName + ", townName=" + townName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addrEndl == null) ? 0 : addrEndl.hashCode());
		result = prime * result + ((addrEndr == null) ? 0 : addrEndr.hashCode());
		result = prime * result + ((addrStartl == null) ? 0 : addrStartl.hashCode());
		result = prime * result + ((addrStartr == null) ? 0 : addrStartr.hashCode());
		result = prime * result + ((mcdName == null) ? 0 : mcdName.hashCode());
		result = prime * result + ((postal == null) ? 0 : postal.hashCode());
		result = prime * result + ((snPrefix == null) ? 0 : snPrefix.hashCode());
		result = prime * result + ((snRoot == null) ? 0 : snRoot.hashCode());
		result = prime * result + ((snSuffix == null) ? 0 : snSuffix.hashCode());
		result = prime * result + ((snType == null) ? 0 : snType.hashCode());
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
		BaseStreetSegData other = (BaseStreetSegData) obj;
		if (addrEndl == null) {
			if (other.addrEndl != null)
				return false;
		} else if (!addrEndl.equals(other.addrEndl))
			return false;
		if (addrEndr == null) {
			if (other.addrEndr != null)
				return false;
		} else if (!addrEndr.equals(other.addrEndr))
			return false;
		if (addrStartl == null) {
			if (other.addrStartl != null)
				return false;
		} else if (!addrStartl.equals(other.addrStartl))
			return false;
		if (addrStartr == null) {
			if (other.addrStartr != null)
				return false;
		} else if (!addrStartr.equals(other.addrStartr))
			return false;
		if (mcdName == null) {
			if (other.mcdName != null)
				return false;
		} else if (!mcdName.equals(other.mcdName))
			return false;
		if (postal == null) {
			if (other.postal != null)
				return false;
		} else if (!postal.equals(other.postal))
			return false;
		if (snPrefix == null) {
			if (other.snPrefix != null)
				return false;
		} else if (!snPrefix.equals(other.snPrefix))
			return false;
		if (snRoot == null) {
			if (other.snRoot != null)
				return false;
		} else if (!snRoot.equals(other.snRoot))
			return false;
		if (snSuffix == null) {
			if (other.snSuffix != null)
				return false;
		} else if (!snSuffix.equals(other.snSuffix))
			return false;
		if (snType == null) {
			if (other.snType != null)
				return false;
		} else if (!snType.equals(other.snType))
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
