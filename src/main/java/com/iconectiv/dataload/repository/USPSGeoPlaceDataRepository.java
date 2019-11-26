package com.iconectiv.dataload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iconectiv.dataload.model.USPSGPDataZIPA;

@Repository
public interface USPSGeoPlaceDataRepository extends JpaRepository<USPSGPDataZIPA, Integer>{

}
