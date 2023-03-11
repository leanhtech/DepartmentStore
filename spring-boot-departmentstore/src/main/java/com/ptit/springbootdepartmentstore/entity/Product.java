package com.ptit.springbootdepartmentstore.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Product {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id", nullable = false)
    private Integer id;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="product_name")
    private String productName;

    @Column(name="status")
    private String status;
    
    @Column(name = "price")
	private Double price;
    
    @Column(name = "specificaion")
    private String specification;
    
    @Column(name = "calculation_unit")
    private Integer calculationUnit;
    
    @Column(name = "discount")
    private Integer discount;
    
    @Column(name = "sold")
    private Integer sold;
    
    @Column(name = "quantity")
    private Integer quantity;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="brand_id", nullable=false)
    private Brand brand;

    @JsonIgnore
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<Image> imageList;
}
