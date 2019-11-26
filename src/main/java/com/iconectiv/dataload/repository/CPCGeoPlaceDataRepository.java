package com.iconectiv.dataload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iconectiv.dataload.model.CPCGeoPlaceData;

@Repository
public interface CPCGeoPlaceDataRepository extends JpaRepository<CPCGeoPlaceData, Integer>{

}
