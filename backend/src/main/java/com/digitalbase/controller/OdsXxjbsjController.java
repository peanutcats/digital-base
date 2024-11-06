package com.digitalbase.controller;

import com.digitalbase.entity.OdsXxjbsj;
import com.digitalbase.service.OdsXxjbsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/xxjbsj")
@CrossOrigin(origins = "http://localhost:3000")
public class OdsXxjbsjController {
    
    @Autowired
    private OdsXxjbsjService service;
    
    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        try {
            List<OdsXxjbsj> result = service.findAll();
            System.out.println("获取数据条数: " + result.size());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            e.printStackTrace(); // 打印详细错误栈
            System.err.println("获取数据失败: " + e.getMessage());
            return ResponseEntity.internalServerError().body("获取数据失败: " + e.getMessage());
        }
    }
} 