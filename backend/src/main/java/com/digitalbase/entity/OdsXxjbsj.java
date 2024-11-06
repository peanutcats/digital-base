package com.digitalbase.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ods_xxjbsj")
public class OdsXxjbsj {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "field_no")
    private String fieldNo;
    
    @Column(name = "field_name")
    private String fieldName;
    
    @Column(name = "chinese_name")
    private String chineseName;
    
    @Column(name = "type")
    private String type;
    
    @Column(name = "length")
    private String length;
    
    @Column(name = "`constraint`")
    private String constraint;
    
    @Column(name = "value_space")
    private String valueSpace;
    
    @Column(name = "example")
    private String example;
    
    @Column(name = "reference_no")
    private String referenceNo;
} 