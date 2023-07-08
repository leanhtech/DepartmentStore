package com.ptit.springbootdepartmentstore.mapper;

import java.util.List;
import java.util.stream.Collectors;


public interface Mapper <E, D> {
	
	public D toDTO(E entity);
	
    public E toEntity(D dto);
    
    public List<D> toListDTO(List<? extends E> listEntity);
    
    public List<E> toListEntity(List<? extends D> listDTO);

}
