package com.ptit.springbootdepartmentstore.mapper;

import org.springframework.stereotype.Component;

@Component
public interface BaseMapperFactory{

	public Mapper <?, ?> Choose(Integer choose);
}
