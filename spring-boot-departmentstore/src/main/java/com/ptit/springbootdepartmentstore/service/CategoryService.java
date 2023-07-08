package com.ptit.springbootdepartmentstore.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.ptit.springbootdepartmentstore.dto.CategoryDTO;
import com.ptit.springbootdepartmentstore.entity.Category;
import com.ptit.springbootdepartmentstore.mapper.BaseMapperFactory;
import com.ptit.springbootdepartmentstore.mapper.ConstantMapper;
import com.ptit.springbootdepartmentstore.mapper.Mapper;
import com.ptit.springbootdepartmentstore.mapper.MapperFactory;
import com.ptit.springbootdepartmentstore.mapper.component.CategoryMapper;
import com.ptit.springbootdepartmentstore.repository.CategoryRepository;

@Service
@Lazy
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

//	@Autowired
//	private CategoryMapper categoryMapper;
	
    private BaseMapperFactory mapperFactory = new MapperFactory();

	private Mapper categoryMapper = mapperFactory.Choose(ConstantMapper.CATEGORY);

	public CategoryDTO getCategory(int id) {
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Category not found in get Category"));
		return (CategoryDTO) categoryMapper.toDTO(category);
	}

	public List<CategoryDTO> getCategoryList() {
		return categoryMapper.toListDTO(categoryRepository.findAll());
	}

	@Transactional(rollbackOn = Exception.class)
	public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
		Category category = (Category) categoryMapper.toEntity(categoryDTO);
		categoryRepository.save(category);
		return categoryDTO;
	}

	@Transactional(rollbackOn = Exception.class)
	public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
		Category category = categoryRepository.findById(categoryDTO.getId())
				.orElseThrow(() -> new EntityNotFoundException("Category not found in update Category"));
		category.setName(categoryDTO.getName());
		categoryRepository.save(category);
		return categoryDTO;
	}
}