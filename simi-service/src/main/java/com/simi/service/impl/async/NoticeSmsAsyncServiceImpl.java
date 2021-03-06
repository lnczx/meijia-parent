package com.simi.service.impl.async;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import com.meijia.utils.MathBigDecimalUtil;
import com.meijia.utils.SmsUtil;
import com.meijia.utils.StringUtil;
import com.meijia.utils.TimeStampUtil;
import com.simi.common.Constants;
import com.simi.po.model.admin.AdminAccount;
import com.simi.po.model.order.OrderCards;
import com.simi.po.model.order.OrderPrices;
import com.simi.po.model.order.OrderSenior;
import com.simi.po.model.order.Orders;
import com.simi.po.model.partners.PartnerServiceType;
import com.simi.po.model.user.Users;
import com.simi.service.admin.AdminAccountService;
import com.simi.service.async.NoticeSmsAsyncService;
import com.simi.service.order.OrderCardsService;
import com.simi.service.order.OrderPricesService;
import com.simi.service.order.OrderSeniorService;
import com.simi.service.order.OrdersService;
import com.simi.service.partners.PartnerServiceTypeService;
import com.simi.service.user.UsersService;

@Service
public class NoticeSmsAsyncServiceImpl implements NoticeSmsAsyncService {

	@Autowired
	public UsersService usersService;
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderSeniorService orderSeniorService;
	
	@Autowired
	private OrderPricesService orderPricesService;
	
	@Autowired
	PartnerServiceTypeService partnerServiceTypeService;
	
	@Autowired
	private AdminAccountService adminAccountService;	
	
	@Autowired
	private OrderCardsService orderCardsService;
	
	//订单支付成功通知服务商
	@Async
	@Override
	public Future<Boolean> noticeOrderPartner(Long orderId) {
		
		Orders order = ordersService.selectByPrimaryKey(orderId);
		if (order == null) return new AsyncResult<Boolean>(true);
		
		if (order.getPartnerUserId().equals(0L)) return new AsyncResult<Boolean>(true);
		Users partnerUser = usersService.selectByPrimaryKey(order.getPartnerUserId());
		if (partnerUser == null) return new AsyncResult<Boolean>(true);
		
		String partnerUserMobile = partnerUser.getMobile();
		if (StringUtil.isEmpty(partnerUserMobile)) return new AsyncResult<Boolean>(true);
		
		OrderPrices orderPrice = orderPricesService.selectByOrderId(orderId);
		Users u = usersService.selectByPrimaryKey(order.getUserId());
		
		PartnerServiceType serviceType = partnerServiceTypeService.selectByPrimaryKey(orderPrice.getServicePriceId());
		
		BigDecimal orderPay = orderPrice.getOrderPay();
		String orderPayStr = orderPay.toString();
		String servicePriceName = serviceType.getName();
		String userName = u.getName();
		String userMobile = order.getMobile();
		
		//通知相关服务商
		String[] partnerContent = new String[] { orderPayStr, servicePriceName, userName, userMobile , " " };
		SmsUtil.SendSms(partnerUserMobile, "48147", partnerContent);
		
		return new AsyncResult<Boolean>(true);
	}

	//订单支付成功通知运营人员
	@Async
	@Override
	public Future<Boolean> noticeOrderOper(Long orderId) {
		
		Orders order = ordersService.selectByPrimaryKey(orderId);
		if (order == null) return new AsyncResult<Boolean>(true);
		
		OrderPrices orderPrice = orderPricesService.selectByOrderId(orderId);
		Users u = usersService.selectByPrimaryKey(order.getUserId());
		
		PartnerServiceType serviceType = partnerServiceTypeService.selectByPrimaryKey(orderPrice.getServicePriceId());
		
		BigDecimal orderPay = orderPrice.getOrderPay();
		String orderPayStr = orderPay.toString();
		String servicePriceName = serviceType.getName();
		String userName = u.getName();
		String userMobile = order.getMobile();
		
		String[] partnerContent = new String[] { orderPayStr, servicePriceName, userName, userMobile , " " };
		
		Long roleId = 3L;
		List<AdminAccount> adminAccounts = adminAccountService.selectByRoleId(roleId);
		for (AdminAccount item : adminAccounts) {
			if (!StringUtil.isEmpty(item.getMobile())) {
				SmsUtil.SendSms(item.getMobile(), "48147", partnerContent);
			}
		}
		return new AsyncResult<Boolean>(true);
	}
	
	
	//订单支付成功通知用户
	@Async
	@Override
	public Future<Boolean> noticeOrderUser(Long orderId) {
		Orders order = ordersService.selectByPrimaryKey(orderId);
		if (order == null) return new AsyncResult<Boolean>(true);
		
		OrderPrices orderPrice = orderPricesService.selectByOrderId(orderId);
		
		Users u = usersService.selectByPrimaryKey(order.getUserId());
		
		String userName = u.getName();
		String userMobile = order.getMobile();
		
		PartnerServiceType serviceType = partnerServiceTypeService.selectByPrimaryKey(orderPrice.getServicePriceId());
		String servicePriceName = serviceType.getName();
		
		String addTimeStr = TimeStampUtil.timeStampToDateStr(order.getAddTime() * 1000 , "yyyy-MM-dd HH:mm");
		String[] userContent = new String[] { userName, addTimeStr, servicePriceName };
		SmsUtil.SendSms(userMobile, "48132", userContent);
		
		return new AsyncResult<Boolean>(true);
	}
	
	//购买秘书订单支付成功通知运营人员
	//支付成功通知运营人员
	@Async
	@Override
	public Future<Boolean> noticeOrderSeniorOper(Long orderId) {
		OrderSenior orderSenior = orderSeniorService.selectByPrimaryKey(orderId);
		if (orderSenior == null) return new AsyncResult<Boolean>(true);
		
		Long secId = orderSenior.getSecId();
		
		// 购买秘书成功后给同事给运营人员和秘书发送信息.
		Users sec = usersService.selectByPrimaryKey(secId);
		String name = sec.getName();
		short validDay = orderSenior.getValidDay();
		BigDecimal orderPay = orderSenior.getOrderPay();

		String orderPStr = orderPay.toString();
		String validDayStr = String.valueOf(validDay);

		String[] content = new String[] { name, orderPStr, validDayStr };
		// 查出所有运营部的人员（roleId=3）
		Long roleId = 3L;
		List<AdminAccount> adminAccounts = adminAccountService.selectByRoleId(roleId);
		for (AdminAccount item : adminAccounts) {
			if (!StringUtil.isEmpty(item.getMobile())) {
				SmsUtil.SendSms(item.getMobile(), Constants.SEC_REGISTER_ID, content);
			}
		}
		
		return new AsyncResult<Boolean>(true);
	}
	
	//充值支付成功通知运营人员
	@Async
	@Override
	public Future<Boolean> noticeOrderCardUser(Long orderId) {
		OrderCards orderCards = orderCardsService.selectByPrimaryKey(orderId);
		if (orderCards == null) return new AsyncResult<Boolean>(true);
		
		Users u = usersService.selectByPrimaryKey(orderCards.getUserId());
		
		BigDecimal orderPay = orderCards.getCardPay();

		String orderPStr = orderPay.toString();
		
		String timeStr = TimeStampUtil.timeStampToDateStr(orderCards.getUpdateTime() * 1000 , "yyyy-MM-dd HH:mm");
		String[] content = new String[] { u.getMobile(), timeStr, orderPStr };
		
		Long roleId = 3L;
		List<AdminAccount> adminAccounts = adminAccountService.selectByRoleId(roleId);
		for (AdminAccount item : adminAccounts) {
			if (!StringUtil.isEmpty(item.getMobile())) {
				SmsUtil.SendSms(item.getMobile(), "70137", content);
			}
		}
		
		return new AsyncResult<Boolean>(true);
	}	
	
	//支付速通宝成功发送短信
	@Async
	@Override
	public Future<Boolean> noticeOrderCarUser(Long orderId) {
		Orders order = ordersService.selectByPrimaryKey(orderId);
		if (order == null) return new AsyncResult<Boolean>(true);
		
		Long userId = order.getUserId();
		Users u = usersService.selectByPrimaryKey(userId);
		String mobile = u.getMobile();
		if (StringUtil.isEmpty(mobile)) return new AsyncResult<Boolean>(true);
		
		OrderPrices orderPrice = orderPricesService.selectByOrderId(orderId);
		if (orderPrice == null ) return new AsyncResult<Boolean>(true);
		
		BigDecimal orderPay = orderPrice.getOrderPay();

		String orderPStr = MathBigDecimalUtil.round2(orderPay);
		
		String timeStr = TimeStampUtil.timeStampToDateStr(order.getUpdateTime() * 1000 , "MM-dd HH:mm");
		String[] content = new String[] { timeStr, orderPStr, "http://t.cn/RGBjgMp" };
		
		SmsUtil.SendSms(mobile, "72913", content);
			
		
		return new AsyncResult<Boolean>(true);
	}	
	
	// 运营人员收到新秘书注册的短信提醒
	@Override
	public Boolean userOrderAmPushSms(Users users) {
		String name = users.getName();
		String nameMobile = users.getMobile();
		Long addTime = users.getAddTime();
		String addTimeStr = TimeStampUtil.timeStampToDateStr(addTime * 1000);

		// List<AdminAccount> adminAccounts = adminAccountService.selectByAll();
		// 查出所有运营部的人员（roleId=3）
		Long roleId = 3L;
		List<AdminAccount> adminAccounts = adminAccountService.selectByRoleId(roleId);
		List<String> mobileList = new ArrayList<String>();
		for (AdminAccount item : adminAccounts) {
			if (!StringUtil.isEmpty(item.getMobile())) {
				mobileList.add(item.getMobile());
			}
		}
		String[] content = new String[] { name, nameMobile, addTimeStr };
		for (int i = 0; i < mobileList.size(); i++) {

			HashMap<String, String> sendSmsResult = SmsUtil.SendSms(mobileList.get(i), Constants.SEC_REGISTER_ID, content);
			System.out.println(sendSmsResult + "00000000000000");
		}
		return true;
	}
	
	// 秘书审核通过后给助理发送短信提醒
	@Override
	public Boolean userSecToUserPushSms(Users users) {

		String name = users.getName();
		String mobile = users.getMobile();
		String url = "";

		String[] content = new String[] { name, url };
		HashMap<String, String> sendSmsResult = SmsUtil.SendSms(mobile, Constants.SEC_REGISTER_USER_ID, content);
		System.out.println(sendSmsResult + "00000000000000");

		return true;
	}

	
}
