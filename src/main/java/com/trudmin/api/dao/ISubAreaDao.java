package com.trudmin.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trudmin.api.model.SubArea;

@Repository
public interface ISubAreaDao extends JpaRepository<SubArea, Long>{
    
    // public SubArea findAllBysubAreaId(int subAreaId);
}
