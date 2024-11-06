package com.digitalbase.service;

import com.digitalbase.entity.OdsXxjbsj;
import com.digitalbase.repository.OdsXxjbsjRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdsXxjbsjService {
    
    @Autowired
    private OdsXxjbsjRepository repository;
    
    public List<OdsXxjbsj> findAll() {
        return repository.findAll();
    }
} 