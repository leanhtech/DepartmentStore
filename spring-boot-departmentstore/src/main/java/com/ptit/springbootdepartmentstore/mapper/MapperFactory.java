package com.ptit.springbootdepartmentstore.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ptit.springbootdepartmentstore.dto.AddressDTO;
import com.ptit.springbootdepartmentstore.entity.Address;

@Component
public class MapperFactory extends BaseMapperFactory{
	
	@Autowired
	private AddressMapper addressMapper;

	@Override
	public Mapper<?, ?> Choose(Integer choose) {
		
		switch (choose)
        {
            case Constant.ADDRESS:
            	Mapper<Address, AddressDTO> mapper = new AddressMapper();
            	return mapper;
//            	
//            case Constant.BRAND:
//            	mapper = new BrandMapper();
//                break;
//            case Constant.CART:
//            	mapper = new CartMapper();
//                break;
//            case Constant.CATEGORY:
//            	mapper = new CategoryMapper();
//                break;
//            case Constant.IMAGE:
//            	mapper = new ImageMapper();
//                break;
//            case Constant.ORDER:
//            	mapper = new OrderMapper();
//                break;
//            case Constant.PERMISSION:
//            	mapper = new PermissionMapper();
//                break;
//            case Constant.PRODUCT:
//            	mapper = new AddressMapper();
//                break;
//            case Constant.USER:
//            	mapper = new UserMapper();
//                break;
//            case Constant.USERMOBILE:
//            	mapper = new UserMapper();
//                break;
            
            default: throw new IllegalArgumentException("No such Mapper");
        }
		
	}

}
