package com.iconectiv.dataload.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ZIPD_GEOPLACE")
public class USPSGPDataZIPD extends BaseGeoPlaceData {

	public USPSGPDataZIPD() {
		super();
	}

}
