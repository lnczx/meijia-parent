package com.simi.service.dict;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.simi.po.model.dict.DictServiceTypes;

public interface ServiceTypeService {

	List<DictServiceTypes> getServiceTypes();

	int insert(DictServiceTypes record);

    int insertSelective(DictServiceTypes record);

    DictServiceTypes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DictServiceTypes record);

    int deleteByPrimaryKey(Long id);

	DictServiceTypes getServiceTypesByPk(Long id);

	PageInfo searchVoListPage( int pageNo, int pageSize);

	DictServiceTypes initServiceType();

	DictServiceTypes selectByName(String name);

	DictServiceTypes selectByNameAndOtherId(String name, Long id);
}
