package com.simi.service.dict;

import java.util.List;

import com.simi.po.model.dict.DictCity;

public interface CityService {

	DictCity getCityById(Long id);

	List<DictCity> selectAll();

}
