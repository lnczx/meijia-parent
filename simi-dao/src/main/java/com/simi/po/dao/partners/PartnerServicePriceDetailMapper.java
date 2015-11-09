package com.simi.po.dao.partners;

import com.simi.po.model.partners.PartnerServicePriceDetail;

public interface PartnerServicePriceDetailMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(PartnerServicePriceDetail record);

    Long insertSelective(PartnerServicePriceDetail record);

    PartnerServicePriceDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PartnerServicePriceDetail record);

    int updateByPrimaryKey(PartnerServicePriceDetail record);
}