package com.ptit.springbootdepartmentstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Entity
@Data
public class Image {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id", nullable=false)
    private Integer id;

    @Column(name="image_url", nullable=false)
    private String imageUrl;

    @Column(name="status")
    private String status;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="product_id", nullable=false)
    private Product product;
}
