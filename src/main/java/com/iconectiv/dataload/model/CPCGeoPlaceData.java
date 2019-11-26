package com.iconectiv.dataload.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CPC_GEOPLACE")
public class CPCGeoPlaceData extends BaseGeoPlaceData {

	public CPCGeoPlaceData() {
		super();
	}

}
