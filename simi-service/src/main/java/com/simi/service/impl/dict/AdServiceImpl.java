package com.simi.service.impl.dict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simi.service.dict.AdService;
import com.simi.po.dao.dict.DictAdMapper;
import com.simi.po.model.dict.DictAd;
import com.meijia.utils.TimeStampUtil;

@Service
public class AdServiceImpl implements AdService {

	@Autowired
	private DictAdMapper adMapper;

	/*
	 * 获取表dict_ad的数据，通过serviceType
	 * 
	 * @param
	 * 
	 * @return List<DictServiceTypes>
	 */

	@Override
	public PageInfo searchVoListPage(int pageNo, int pageSize) {

		PageHelper.startPage(pageNo, pageSize);
		List<DictAd> list = adMapper.selectByListPage();

		PageInfo result = new PageInfo(list);
		return result;
	}

	@Override
	public DictAd selectByPrimaryKey(Long id) {
		return adMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<DictAd> selectByAdType(Long adType) {
		return adMapper.selectByAdType(adType);
	}

	@Override
	public int insertSelective(DictAd record) {
		return adMapper.insert(record);
	}

	@Override
	public DictAd initAd() {
		DictAd record = new DictAd();
		record.setId(0L);
		record.setNo((short) 0);
		record.setImgUrl("");
		record.setGotoUrl("");
		record.setAdType(0L);
		record.setAddTime(TimeStampUtil.getNow() / 1000);
		record.setUpdateTime(0L);
		record.setEnable((short) 0);

		return record;
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return adMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(DictAd record) {
		return adMapper.updateByPrimaryKeySelective(record);
	}

}
