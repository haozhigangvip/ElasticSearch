package com.hzg.service;

import java.util.List;

import com.hzg.entity.Company_ELK;


public interface CompanyService {
    public List<Company_ELK> findByComIDandName(String key,Integer page,Integer pageNumic);

}
