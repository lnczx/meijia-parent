package com.simi.common;

import java.math.BigDecimal;

public class Constants {

	public static String URL_ENCODE = "UTF-8";
	public static int PAGE_MAX_NUMBER = 10;
	/**
	 * 验证码最大值
	 */
	public static int CHECK_CODE_MAX_LENGTH = 999999;

	//'0' COMMENT '用户来源 0 = APP  1 = 微网站  2 = 管理后台'
	public static short USER_APP = 0;
	public static short USER_NET = 1;
	public static short USER_BACK = 2;

	//充值卡类型:1,无忧管家;2,快乐管家;3,超级管家
	public static long DICT_CARD_TYPE_1 = 1;
	public static long DICT_CARD_TYPE_2 = 2;
	public static long DICT_CARD_TYPE_3 = 3;

	//是否使用 0 = 未使用 1= 已使用
	public static short IS_USER_0 = 0;
	public static short IS_USER_1 = 1;

	public static short IS_USER_PROMOTION_1 = 1;
	public static short IS_USER_PROMOTION_0 = 0;

	//地址默认 默认地址 1 = 默认  0 = 不默认
	public static int ADDRESS_DEFAULT_1 = 1;
	public static int ADDRESS_DEFAULT_NOT_0 = 0;

	//0 = 已关闭 1 = 待确认 2 = 已确认 3 = 待支付 4 = 已支付
	public static short ORDER_STATUS_0_CLOSE = 0;
	public static short ORDER_STATUS_1_CONFIRM = 1;
	public static short ORDER_STATUS_2_CONFIRM_DONE = 2;
	
	public static short ORDER_STATUS_3_PAY_WAIT = 3;
	public static short ORDER_STATUS_4_PAY_DONE = 4;
	
	public static short ORDER_STATUS_9_COMPLETE = 9;
	
	//订单评价 0=好  1=一般 2=差
	public static short ORDER_RATE_GOOD = 0;
	public static short ORDER_RATE_GENERAL = 1;
	public static short ORDER_RATE_BAD = 2;

	//1 = 支付成功 2 = 退款成功
	public static short PAY_SUCCESS = 1;
	public static short BACK_SUCCESS = 2;

	//是否能取消订单. 0 = 不能取消 1 = 能取消
	public static short ORDER_CANCLE_NO = 0;
	public static short ORDER_CANCLE_YES = 1;

	public static int RATE_CORE = 10;
	public static int SHARE_CORE = 1;

	//0 = 发送失败 1 = 发送成功
	public static short SMS_SUCCESS= 0;
	public static short SMS_FAIL= 0;
	public static String SMS_SUCCESS_CODE= "000000";
	public static String SMS_STATUS_CODE= "statusCode";
	public static String SMS_STATUS_MSG= "msg";

	public static String PAY_SUCCESS_SMS_TEMPLE_ID= "9282";
	public static String GET_CODE_TEMPLE_ID= "8429";
	public static String GET_CODE_REMIND_ID= "10923";
	public static String GET_CODE_MAX_VALID= "30";//短信有效时间
	public static String NOTICE_CUSTOMER_Message_ID= "9280";
	public static String NOTICE_STAFF__Message_ID= "15284";

	public static String CANCEL_ORDER_SATUS= "cancel";

	//支付方式： 0 = 余额支付 1 = 支付宝 2 = 微信支付 3 = 智慧支付(保留,暂不开发)
	//4 = 上门刷卡（保留，暂不开发） 5 = 优惠券兑换
	public static Short PAY_TYPE_0 = 0;
	public static Short PAY_TYPE_1 = 1;
	public static Short PAY_TYPE_2 = 2;
	public static Short PAY_TYPE_3 = 3;
	public static Short PAY_TYPE_4 = 4;
	public static short PAY_TYPE_5 = 5;

	public static int SUCCESS_0 = 0;
	public static int ERROR_999 = 999;
	public static int ERROR_100 = 100;
	public static int ERROR_101 = 101;
	public static int ERROR_102 = 102;
	public static int ERROR_103 = 103;
	public static int ERROR_104 = 104;

	//0 = 未支付 1 = 已支付
	public static Short PAY_STATUS_0 = 0;
	public static Short PAY_STATUS_1 = 1;

	//服务类型0=做饭1=洗衣2=家电清洗3=保洁4=擦玻璃5=管道疏通6=新居开荒

	//客服电话号码
	public static String SERVICE_CALL = "4001691615";

	//获取积分的操作定义 0 = 订单获得积分 1 = 订单使用积分 2 = 分享获得积分 3= 评价获得积分 4=兑换使用积分
	public static Short ACTION_ORDRE = 0;
	public static Short ACTION_ORDER_USED = 1;
	public static Short ACTION_SHARE = 2;
	public static Short ACTION_ORDER_RATE = 3;
	public static Short ACTION_CONVERT_SCORE=4;

	//积分获得和使用  0 = 获取  1= 使用
	public static Short CONSUME_SCORE_GET = 0;
	public static Short CONSUME_SCORE_USED = 1;

	//是否已经返还积分 0 = 未返还 1 = 已返
	public static Short RETURN_SCORE_YES = 1;
	public static Short RETURN_SCORE_NO = 0;

	//消费类型
	public static Short ORDER_TYPE_0 = 0;  //订单支付
	public static Short ORDER_TYPE_1 = 1; //购买充值卡
	public static Short ORDER_TYPE_2 = 2; //购买管家卡
	public static Short ORDER_TYPE_3 = 3; //订单退款

	//优惠券过期时间
	public static Long COUPON_VALID_FOREVER = 0L;

	//优惠券类型 - 订单支付
	public static Short COUPON_TYPE_0 = 0;

	//优惠券类型 - 充值卡充值
	public static Short COUPON_TYPE_1 = 1;

	//优惠券类型 - 活动
	public static Short COUPON_TYPE_2 = 2;

	//IM服务提供商
	public static String IM_PROVIDE = "huanxin";

	//IM机器人账号和密码
	public static String ROBOT_FEMALE_USERNAME = "robot-amei";
	public static String ROBOT_FEMALE_NICKNAME = "阿美";
	public static String ROBOT_MALE_USERNAME = "robot-afu";
	public static String ROBOT_MALE_NICKNAME = "阿福";

	//新用户注册赠送优惠劵码
	public static String NEW_USER_COUPON_CARD_PASSWORD = "I0RBCOJL";
	//积分兑换优惠券密码
	public static String SCORE_CONVERT_COUPON_CARD_PASSWORD="CSNINL8B";

}
