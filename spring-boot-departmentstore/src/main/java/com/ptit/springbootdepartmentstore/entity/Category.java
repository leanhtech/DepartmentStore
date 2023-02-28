package com.ptit.springbootdepartmentstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Category {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="category_id", nullable = false)
    private Integer id;

    @Column(name="category_name")
    private String categoryName;
    
    @Column(name="status")
    private String status;
    
    @Column(name = "image")
    private String image;
    
    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

}
