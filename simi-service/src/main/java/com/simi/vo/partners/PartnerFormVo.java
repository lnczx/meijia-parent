package com.simi.vo.partners;

import java.util.ArrayList;
import java.util.List;

import com.meijia.utils.common.extension.ArrayHelper;
import com.simi.po.model.partners.PartnerLinkMan;
import com.simi.po.model.partners.PartnerServiceType;
import com.simi.po.model.partners.Partners;
public class PartnerFormVo extends Partners {

	List<PartnerLinkMan> linkMan;
	
	String cbdIds;

	private  List<PartnerServiceType> childList = new ArrayList<PartnerServiceType>() ;

	private  Long[]  serviceTypeIds;

	private Long partnerTypeId;
		
	private  Long[]  partnerTypeIds;
	
	
	public String getPartnerTypeIdsString(){
		return ArrayHelper.LongtoString(partnerTypeIds, ",");
	}
	public Long getPartnerTypeId() {
		return partnerTypeId;
	}

	public void setPartnerTypeId(Long partnerTypeId) {
		this.partnerTypeId = partnerTypeId;
	}

	public Long[] getPartnerTypeIds() {
		return partnerTypeIds;
	}

	public void setPartnerTypeIds(Long[] partnerTypeIds) {
		this.partnerTypeIds = partnerTypeIds;
	}

	public Long[] getServiceTypeIds() {
		return serviceTypeIds;
	}

	public void setServiceTypeIds(Long[] serviceTypeIds) {
		this.serviceTypeIds = serviceTypeIds;
	}

	public List<PartnerServiceType> getChildList() {
		return childList;
	}

	public void setChildList(List<PartnerServiceType> childList) {
		this.childList = childList;
	}

	public String getCbdIds() {
		return cbdIds;
	}

	public void setCbdIds(String cbdIds) {
		this.cbdIds = cbdIds;
	}

	public List<PartnerLinkMan> getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(List<PartnerLinkMan> linkMan) {
		this.linkMan = linkMan;
	}

	List<PartnerServiceTypeVo>  serviceTypes;

	public List<PartnerServiceTypeVo> getServiceTypes() {
		return serviceTypes;
	}

	public void setServiceTypes(List<PartnerServiceTypeVo> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

}