package com.iconectiv.dataload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iconectiv.dataload.model.USPSSSDataZIPA;

@Repository
public interface USPSDataRepository extends JpaRepository<USPSSSDataZIPA, Integer> {

}
