package com.iconectiv.dataload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iconectiv.dataload.model.CPCStreetSegData;

@Repository
public interface CPCDataRepository extends JpaRepository<CPCStreetSegData, Integer> {

}
