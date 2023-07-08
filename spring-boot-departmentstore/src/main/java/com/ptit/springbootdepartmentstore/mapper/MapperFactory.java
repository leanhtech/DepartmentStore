package com.ptit.springbootdepartmentstore.mapper;

import com.ptit.springbootdepartmentstore.mapper.component.AddressMapper;
import com.ptit.springbootdepartmentstore.mapper.component.BrandMapper;
import com.ptit.springbootdepartmentstore.mapper.component.CartMapper;
import com.ptit.springbootdepartmentstore.mapper.component.CategoryMapper;
import com.ptit.springbootdepartmentstore.mapper.component.ImageMapper;
import com.ptit.springbootdepartmentstore.mapper.component.OrderMapper;
import com.ptit.springbootdepartmentstore.mapper.component.PermissionMapper;
import com.ptit.springbootdepartmentstore.mapper.component.UserMapper;

public class MapperFactory implements BaseMapperFactory {

	@Override
	public Mapper<?, ?> Choose(Integer choose) {
		
		switch (choose)
        {
            case ConstantMapper.ADDRESS:
            	return new AddressMapper();
            	
            case ConstantMapper.BRAND:
            	return new BrandMapper();
            	
            case ConstantMapper.CART:
            	return new CartMapper();
            	
            case ConstantMapper.CATEGORY:
            	return new CategoryMapper();

            case ConstantMapper.IMAGE:
            	return new ImageMapper();

            case ConstantMapper.ORDER:
            	return new OrderMapper();

            case ConstantMapper.PERMISSION:
            	return new PermissionMapper();

            case ConstantMapper.PRODUCT:
            	return new AddressMapper();

            case ConstantMapper.USER:
            	return new UserMapper();

            case ConstantMapper.USERMOBILE:
            	return new UserMapper();

            
            default: throw new IllegalArgumentException("No such Mapper");
        }
		
	}

}
