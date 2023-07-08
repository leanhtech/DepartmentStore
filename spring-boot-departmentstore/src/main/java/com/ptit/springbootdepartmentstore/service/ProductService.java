package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.ProductDTO;
import com.ptit.springbootdepartmentstore.entity.Brand;
import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.entity.Product;
import com.ptit.springbootdepartmentstore.mapper.BaseMapperFactory;
import com.ptit.springbootdepartmentstore.mapper.ConstantMapper;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.mapper.MapperFactory;
import com.ptit.springbootdepartmentstore.mapper.component.ProductMapper;
import com.ptit.springbootdepartmentstore.repository.BrandRepository;
import com.ptit.springbootdepartmentstore.repository.CategoryRepository;
import com.ptit.springbootdepartmentstore.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BrandRepository brandRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
//	@Autowired
//	private ProductMapper productMapper;
	
    private BaseMapperFactory mapperFactory = new MapperFactory();

	private Mapper productMapper = mapperFactory.Choose(ConstantMapper.PRODUCT);

	public List<ProductDTO> getAllProducts() {
		return productMapper.toListDTO(productRepository.findAll());
	}

	public ProductDTO getProduct(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		return (ProductDTO) productMapper.toDTO(product);
	}
	
	public List<ProductDTO> getListRecommendedProduct() {
		List<Product> list = productRepository.findFirst8ByOrderBySold();
		return productMapper.toListDTO(list);
	}

	@Transactional(rollbackOn = Exception.class)
	public ProductDTO createProduct(ProductDTO productDTO) {
//		productDTO.setId(null);
//		if (productDTO.getImage() == "") {
//			productDTO.setImage("https://w7.pngwing.com/pngs/470/493/png-transparent-new-product-development-sales-industry-business-new-product-text-label-service.png");
//		}
		
		Product product = (Product) productMapper.toEntity(productDTO);
		Product savedProduct = productRepository.save(product);
		return (ProductDTO) productMapper.toDTO(savedProduct);
	}

	@Transactional(rollbackOn = Exception.class)
	public ProductDTO updateProduct(ProductDTO productDTO) {
		Product productOld = productRepository.findById(productDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		
		Product product = (Product) productMapper.toEntity(productDTO);
		if(productDTO.getImageBase64() == null)
			product.setImageByte(productOld.getImageByte());
		Product savedProduct = productRepository.save(product);
		return (ProductDTO) productMapper.toDTO(savedProduct);
	}

	@Transactional(rollbackOn = Exception.class)
	public void deleteProduct(int id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Product not found"));
		productRepository.delete(product);
	}


	public List<ProductDTO> getProductListInBrand(int id) {
		Brand brand = brandRepository.findById(id)
						.orElseThrow(() -> new EntityNotFoundException("Brand not found"));
		return productMapper.toListDTO(brand.getProductList());
	}

	public List<ProductDTO> getProductListInCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
								.orElseThrow(() -> new EntityNotFoundException("Category not found in get list Product by Category"));
		return productMapper.toListDTO(category.getProductList());
	}
}