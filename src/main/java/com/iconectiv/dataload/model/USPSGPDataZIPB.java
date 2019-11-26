package com.iconectiv.dataload.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ZIPB_GEOPLACE")
public class USPSGPDataZIPB extends BaseGeoPlaceData {

	public USPSGPDataZIPB() {
		super();
	}

}
