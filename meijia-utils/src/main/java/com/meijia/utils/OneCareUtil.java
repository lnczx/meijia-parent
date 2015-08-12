package com.meijia.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 有个管家的常用方法
 *
 */
public class OneCareUtil {

	/**
	 * 判断支付是否正确
	 * @param trade_status 支付状态  
	 * 									  支付宝客户端成功状态为: TRADE_FINISHED 或者为 TRADE_SUCCESS
	 *                                    支付宝网页版成功状态为: success
	 *                                    微信支付成功的状态为: SUCCESS
	 */
	public static boolean isPaySuccess(String tradeStatus) {

		if (StringUtil.isEmpty(tradeStatus)) {
			return false;
		}

		switch (tradeStatus) {
			case "TRADE_FINISHED":
			case "TRADE_SUCCESS":
			case "success":
			case "SUCCESS":
				return true;
		}
		return false;
	}

	public static String getOrderStausName(Short status) {
		String statusName = "";
		switch (status) {
			case 0:
				statusName = "已取消";
				break;
			case 1:
				statusName = "待支付";
				break;
			case 2:
				statusName = "已支付";
				break;
			case 3:
				statusName = "待服务";
				break;
			case 4:
				statusName = "即将服务";
				break;
			case 5:
				statusName = "待评价";
				break;
			case 6:
				statusName = "已完成";
				break;
			case 7:
				statusName = "已关闭";
				break;
			default:
				statusName = "";
		}
		return statusName;
	}
	public static String getStatusName(Short status) {
		String statusName = "";
		switch (status) {
		case 0:
			statusName = "无效";
			break;
		case 1:
			statusName = "有效";
			break;
		default:
			statusName = "";
		}
		return statusName;
	}
	public static String getSendStatusName(Short sendStatus) {
		String sendStatusName = "";
		switch (sendStatus) {
		case 0:
			sendStatusName = "未发送";
			break;
		case 1:
			sendStatusName = "已发送";
			break;
		default:
			sendStatusName = "";
		}
		return sendStatusName;
	}
	public static String getIsRead(Short isRead) {
		String isReadName = "";
		switch (isRead) {
		case 0:
			isReadName = "未读";
			break;
		case 1:
			isReadName = "已读";
			break;
		default:
			isReadName = "";
		}
		return isReadName;
	}

	public static String getRangTypeName(Short rangType) {
		String rangTypeName = "";
		switch (rangType) {
			case 0:
				rangTypeName = "通用";
				break;
			case 1:
				rangTypeName = "唯一";
				break;
			default:
				rangTypeName = "";
		}
		return rangTypeName;
	}
	public static String getCouponTypeName(Short couponType) {
		String couponTypeName = "";
		switch (couponType) {
		case 0:
			couponTypeName = "订单支付 ";
			break;
		case 1:
			couponTypeName = "充值卡充值 ";
			break;
		case 2:
			couponTypeName = "活动相关";
			break;
		default:
			couponTypeName = "";
		}
		return couponTypeName;
	}
	public static String getSexTypeName(Short sexType) {
		String sexTypeName = "";
		switch (sexType) {
		case 0:
			sexTypeName = "男 ";
			break;
		case 1:
			sexTypeName = "女";
			break;
		default:
			sexTypeName = "";
		}
		return sexTypeName;
	}
	public static String getIsUsedName(Short isUsed) {
		String isUsedName = "";
		switch (isUsed) {
		case 0:
			isUsedName = "未使用 ";
			break;
		case 1:
			isUsedName = "已使用";
			break;
		default:
			isUsedName = "";
		}
		return isUsedName;
	}
	public static List<String> getOrderStatus(){
		List<String> list = new ArrayList<String>();
		list.add(0, "已取消");
		list.add(1, "待支付");
		list.add(2, "已支付");
		list.add(3, "待服务");
		list.add(4, "即将服务");
		list.add(5, "待评价");
		list.add(6, "已完成");
		list.add(7, "已关闭");
		return list;

	}
	public static List<String> getSpiderPartnerStatus(){
		List<String> list = new ArrayList<String>();
		list.add(0, "已收集");
		list.add(1, "考察中");
		list.add(2, "已考察");
		list.add(3, "待确认");
		list.add(4, "已认证");
		return list;
		
	}
	public static List<String> getPartnerCompanySize(){
		List<String> list = new ArrayList<String>();
		list.add(0, "未知");
		list.add(1, "1~10人");
		list.add(2, "11~20人");
		list.add(3, "20~50人");
		list.add(4, "50~100人");
		list.add(5, "100以上");
		return list;
		
	}
	public static List<String> getPartnerIsCooperate(){
		List<String> list = new ArrayList<String>();
		list.add(0, "未合作");
		list.add(1, "洽谈中");
		list.add(2, "已合作");
		list.add(3, "优先合作");
		list.add(4, "结束合作");
		list.add(5, "黑名单");
		return list;
		
	}
	public static List<String> getSpiderPartnerServiceType(){
		List<String> list = new ArrayList<String>();
		list.add(0, "工商注册");
		list.add(1, "法律咨询");
		list.add(2, "财务会计");
		list.add(3, "商标专利");
		list.add(4, "投资担保");
		list.add(5, "网站建设与推广");
		list.add(6, "翻译/速记");
		list.add(7, "配套办公");
		return list;
		
	}

	public static String getOrderFromName(Short status) {
		String statusName = "";
		switch (status) {
			case 0:
				statusName = "App";
				break;
			case 1:
				statusName = "微网站";
				break;
			case 2:
				statusName = "管理平台";
				break;
			case 999:
				statusName = "所用来源";
				break;
			default:
				statusName = "";
		}
		return statusName;
	}

	public static List<String> getOrderFrom(){
		List<String> list = new ArrayList<String>();
		list.add(0,"APP");
		list.add(1,"微网站");
		list.add(2,"管理平台");
		return list;
	}
	public static List<String> getMsgSendGroupName(){
		List<String> list = new ArrayList<String>();
		list.add(0,"全部用户");
		list.add(1,"已注册未下单用户");
		list.add(2,"下过单用户");
		return list;
	}

	public static List<String> getPayType(){
		List<String> list = new ArrayList<String>();
		list.add(0,"无");
		list.add(1,"月结");
		list.add(2,"按次结算");
		list.add(3,"预付");
		return list;
	}

	public static String getPayTypeName(Short payType) {
		String payTypeName = "";
		switch (payType) {
			case 0:
				payTypeName = "余额支付";
				break;
			case 1:
				payTypeName = "支付宝支付";
				break;
			case 2:
				payTypeName = "微信支付";
				break;
			case 3:
				payTypeName = "智慧支付";
				break;
			case 4:
				payTypeName = "上门刷卡";
				break;
			case 5:
				payTypeName ="优惠券兑换";
				break;
			default:
				payTypeName = "";
		}
		return payTypeName;
	}

	public static String getserviceTypeName(Short seviceType) {
		String serviceTypeName = "";
		switch (seviceType) {
			case 0:
				serviceTypeName = "全部";
				break;
			case 1:
				serviceTypeName = "做饭";
				break;
			case 2:
				serviceTypeName = "洗衣";
				break;
			case 3:
				serviceTypeName = "家电清洗";
				break;
			case 4:
				serviceTypeName = "保洁";
				break;
			case 5:
				serviceTypeName = "擦玻璃";
				break;
			case 6:
				serviceTypeName = "管道疏通";
				break;
			case 7:
				serviceTypeName = "新居开荒";
				break;
			default:
				serviceTypeName = "";
		}
		return serviceTypeName;
	}

	public static String getPartnerName(String partnerId) {
		String partnerName = "";
		switch (partnerId) {
			case "0":
				partnerName = "自由派工";
				break;
			case "1":
				partnerName = "嘉佣坊";
				break;
			default:
				partnerName = "";
		}
		return partnerName;
	}
	public static String getUserTypeName(Short userTypeId) {
		String userTypeName = "";
		switch (userTypeId) {
		case 0:
			userTypeName = "普通用户";
			break;
		case 1:
			userTypeName = "代理商";
			break;
		default:
			userTypeName = "";
		}
		return userTypeName;
	}
	public static String getOrderTypeName(Short orderTypeId) {
		String orderTypeName = "";
		switch (orderTypeId) {
		case 0:
			orderTypeName = "订单支付";
			break;
		case 1:
			orderTypeName = "购买充值卡";
			break;
		case 2:
			orderTypeName = "购买管家卡";
			break;
		case 3:
			orderTypeName = "订单退款";
			break;
		default:
			orderTypeName = "";
		}
		return orderTypeName;
	}
	public static String getPartnerPayTypeName(Short partnerTyp) {
		String partnerPayTypeName = "";
		switch (partnerTyp) {
		case 0:
			partnerPayTypeName = "月结";
			break;
		case 1:
			partnerPayTypeName = "按次结算";
			break;
		case 2:
			partnerPayTypeName = "预付";
			break;
		default:
			partnerPayTypeName = "";
		}
		return partnerPayTypeName;
	}
	public static String getMsgSendGroup(Short sendGroupId) {
		String msgSendGroup = "";
		switch (sendGroupId) {
		case 0:
			msgSendGroup = "全部用户";
			break;
		case 1:
			msgSendGroup = "未下单用户";
			break;
		case 2:
			msgSendGroup = "已下单用户";
			break;
		default:
			msgSendGroup = "";
		}
		return msgSendGroup;
	}
	public static String getSpiderPartnerStatus(Short status) {
		String statusName = "";
		switch (status) {
		case 0:
			statusName = "已采集";
			break;
		case 1:
			statusName = "考察中";
			break;
		case 2:
			statusName = "已考察";
			break;
		case 3:
			statusName = "待认证";
			break;
		case 4:
			statusName = "已认证";
			break;
		default:
			statusName = "";
		}
		return statusName;
	}
	public static String getPartnerCompanySize(Short companySize) {
		String companySizeName = "";
		switch (companySize) {
		case 0:
			companySizeName = "未知";
			break;
		case 1:
			companySizeName = "1~10人";
			break;
		case 2:
			companySizeName = "11~20人";
			break;
		case 3:
			companySizeName = "20~50人";
			break;
		case 4:
			companySizeName = "50~100人";
			break;
		case 5:
			companySizeName = "100以上";
			break;
		default:
			companySizeName = "";
		}
		return companySizeName;
	}
	public static String getPartnerIsCooperate(Short isCooperate) {
		String isCooperateName = "";
		switch (isCooperate) {
		case 0:
			isCooperateName = "未合作";
			break;
		case 1:
			isCooperateName = "洽谈中";
			break;
		case 2:
			isCooperateName = "已合作";
			break;
		case 3:
			isCooperateName = "优先合作";
			break;
		case 4:
			isCooperateName = "结束合作";
			break;
		case 5:
			isCooperateName = "黑名单";
			break;
		default:
			isCooperateName = "";
		}
		return isCooperateName;
	}
	public static String getDegreeName(String degreeId) {
		String DegreeName = "";
		switch (degreeId) {
			case "0":
				DegreeName = "小学";
				break;
			case "1":
				DegreeName = "初中";
				break;
			case "2":
				DegreeName = "高中";
				break;
			case "3":
				DegreeName = "专科";
				break;
			case "4":
				DegreeName = "本科";
				break;
			case "5":
				DegreeName = "硕士研究生";
				break;
			case "6":
				DegreeName = "博士研究生";
				break;
			default:
				DegreeName = "";
		}
		return DegreeName;
	}
	public static List<String> getDegreeType(){
		List<String> list = new ArrayList<String>();
		list.add(0,"小学");
		list.add(1,"初中");
		list.add(2,"高中");
		list.add(3,"专科");
		list.add(4,"本科");
		list.add(5,"硕士研究生");
		list.add(6,"博士研究生");
		return list;
	}
	public static String getNationName(String nameId) {
		String NationName = "";
		switch (nameId) {
		case "0":
			NationName = "汉族";
			break;
		case "1":
			NationName = "壮族";
			break;
		case "2":
			NationName = "藏族";
			break;
		case "3":
			NationName = "满族";
			break;
		case "4":
			NationName = "蒙古族";
			break;
		case "5":
			NationName = "哈萨克族";
			break;
		case "6":
			NationName = "朝鲜族";
			break;
		case "7":
			NationName = "苗族";
			break;
		default:
			NationName = "";
		}
		return NationName;
	}
	public static List<String> getNationType(){
		List<String> list = new ArrayList<String>();
		list.add(0,"汉族");
		list.add(1,"蒙古族");
		list.add(2,"回族");
		list.add(3,"壮族");
		list.add(4,"藏族");
		list.add(5,"满族");
		list.add(6,"哈萨克族");
		list.add(7,"朝鲜族");
		list.add(8,"苗族");
		list.add(9,"其他民族");

		return list;
	}
}
