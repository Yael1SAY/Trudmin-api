package com.trudmin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trudmin.api.dao.ISubAreaDao;
import com.trudmin.api.model.SubArea;

@Service
public class SubAreaService {

    @Autowired
    ISubAreaDao subAreaDao;

    public SubArea findById(long subAreaId){
        return subAreaDao.findById(subAreaId).get();
    }

}
