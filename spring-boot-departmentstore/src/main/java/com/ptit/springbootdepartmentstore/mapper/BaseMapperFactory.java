package com.ptit.springbootdepartmentstore.mapper;

import org.springframework.stereotype.Component;

@Component
public abstract class BaseMapperFactory {

	public abstract Mapper<?, ?> Choose(Integer choose);
}
