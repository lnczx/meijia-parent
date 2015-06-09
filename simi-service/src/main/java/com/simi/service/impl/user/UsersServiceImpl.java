package com.simi.service.impl.user;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.simi.common.Constants;
import com.simi.service.admin.AdminAccountService;
import com.simi.service.dict.CouponService;
import com.simi.service.order.OrderSeniorService;
import com.simi.service.user.UserCouponService;
import com.simi.service.user.UserRefSeniorService;
import com.simi.service.user.UsersService;
import com.simi.vo.AppResultData;
import com.simi.vo.user.UserSearchVo;
import com.simi.vo.user.UserViewVo;
import com.simi.po.dao.user.UserRef3rdMapper;
import com.simi.po.dao.user.UserRefSeniorMapper;
import com.simi.po.dao.user.UsersMapper;
import com.simi.po.model.admin.AdminAccount;
import com.simi.po.model.dict.DictCoupons;
import com.simi.po.model.user.UserCoupons;
import com.simi.po.model.user.UserRef3rd;
import com.simi.po.model.user.UserRefSenior;
import com.simi.po.model.user.Users;
import com.meijia.utils.DateUtil;
import com.meijia.utils.MobileUtil;
import com.meijia.utils.TimeStampUtil;
import com.meijia.utils.huanxin.EasemobIMUsers;


@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private UserCouponService userCouponService;

	@Autowired
	private OrderSeniorService orderSeniorService;

	@Autowired
	private UserRef3rdMapper userRef3rdMapper;

	@Autowired
	private UserRefSeniorMapper userRefSeniorMapper;

	@Autowired
	private AdminAccountService adminAccountService;

	@Autowired
	private CouponService couponService;

	@Autowired
	private UserRefSeniorService userRefSeniorService;

	/**
	 * 新用户注册流程
	 * 1. 注册用户
	 * 2. 赠送金额
	 */
	@Override
	public Users genUser(String mobile, Short addFrom) {
		Users u = this.getUserByMobile(mobile);
		if (u == null) {// 验证手机号是否已经注册，如果未注册，则自动注册用户，
			u = this.initUsers(mobile, addFrom);
			String provinceName = "";
			try {
				provinceName = MobileUtil.calcMobileCity(mobile);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			u.setProvinceName(provinceName);
			this.saveUser(u);


			//先看看是否已经有赠送
			UserCoupons userCoupon = userCouponService.selectByMobileCardPwd(mobile, Constants.NEW_USER_COUPON_CARD_PASSWORD);

			if (userCoupon == null) {
				//新用户注册赠送相应的优惠劵.
				DictCoupons coupon = couponService.selectByCardPasswd(Constants.NEW_USER_COUPON_CARD_PASSWORD);

				userCoupon = userCouponService.initUserCoupon();
				userCoupon.setUserId(u.getId());
				userCoupon.setMobile(u.getMobile());
				userCoupon.setCouponId(coupon.getId());
				userCoupon.setCardPasswd(coupon.getCardPasswd());
				userCoupon.setValue(coupon.getValue());
				userCoupon.setExpTime(coupon.getExpTime());

				userCouponService.insert(userCoupon);
			}

		}
		return u;
	}

	@Override
	public List<Users> selectByAll() {
		return usersMapper.selectByAll();
	}

	@Override
	public Users getUserById(Long id) {
		return usersMapper.selectByPrimaryKey(id);
	}

	@Override
	public Users getUserByMobile(String mobile) {
		return usersMapper.selectByMobile(mobile);
	}

	@Override
	public UserViewVo getUserViewByMobile(String mobile) {
		Users user  =  usersMapper.selectByMobile(mobile);

		UserViewVo userInfo = new UserViewVo();
		if (user == null) {
			return userInfo;
		}

		BeanUtils.copyProperties(user, userInfo);

		String seniorRange = "";
		HashMap<String, Date> seniorRangeResult = orderSeniorService.getSeniorRangeDate(mobile);

		if (!seniorRangeResult.isEmpty()) {
			Date startDate = seniorRangeResult.get("startDate");
			Date endDate = seniorRangeResult.get("endDate");
			seniorRange = "有效期:" + DateUtil.formatDate(startDate) + "至" + DateUtil.formatDate(endDate);
		}
		userInfo.setSeniorRange(seniorRange);


		return userInfo;
	}

	@Override
	public int saveUser(Users user) {
		return usersMapper.insert(user);
	}

	@Override
	public int updateByPrimaryKeySelective(Users user) {
		return usersMapper.updateByPrimaryKeySelective(user);
	}

	/*
	 * 初始化用户对象
	 */
	@Override
	public Users initUsers(String mobile, Short addFrom) {
		Users u =  new Users(mobile);
		u.setId(0L);
		u.setProvinceName("");
		u.setName(" ");
		u.setRestMoney(new BigDecimal(0));
		u.setUserType((short) 0);
		u.setAddFrom((short) 0);
		u.setScore(0);
		u.setAddTime(TimeStampUtil.getNow()/1000);
		u.setUpdateTime(TimeStampUtil.getNow()/1000);
		return u;
	}

	@Override
	public PageInfo searchVoListPage(UserSearchVo searchVo, int pageNo,
			int pageSize) {

		HashMap<String,Object> conditions = new HashMap<String,Object>();
		 String mobile = searchVo.getMobile();
		 Long seniorId = searchVo.getSeniorId();
		 List<Long> userIdList = new ArrayList<Long>();

		if(mobile !=null && !mobile.isEmpty()){
			conditions.put("mobile",mobile.trim());
		}
		if(seniorId!=null){
			List<UserRefSenior> list =  userRefSeniorService.selectBySeniorId(seniorId);
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				UserRefSenior userRefSenior = (UserRefSenior) iterator.next();
				userIdList.add(userRefSenior.getUserId());
			}
		}
		if(userIdList!=null  && userIdList.size()>0){
			conditions.put("userIdList",userIdList);
		}

		PageHelper.startPage(pageNo, pageSize);
		List<Users> list = usersMapper.selectByListPage(conditions);
       PageInfo result = new PageInfo(list);
		return result;
	}
	@Override
	public List<Users> searchVoByAll(UserSearchVo searchVo) {

	List<Users> list = usersMapper.searchVoByAll(searchVo);

	return list;
	}
	/**
	 * 获取用户账号详情接口
	 */
	@Override
	public UserViewVo getUserInfo(String mobile) {
		UserViewVo vo = new UserViewVo();
		Users u = usersMapper.selectByMobile(mobile);
		if (u == null) {
			return vo;
		}

		BeanUtils.copyProperties(u, vo);
		vo.setUser_id(u.getId());

		//获取用户与绑定的管家环信IM账号
		Map imRobot = this.getImRobot(u);
		vo.setImRobotUsername(imRobot.get("username").toString());
		vo.setImRobotNickname(imRobot.get("nickname").toString());
		vo.setImSeniorUsername("");
		vo.setImSeniorNickname("");

		vo.setIsSenior((short) 0);
		String seniorRange = "";
		HashMap<String, Date> seniorRangeResult = orderSeniorService.getSeniorRangeDate(mobile);

		if (!seniorRangeResult.isEmpty()) {
			Date startDate = seniorRangeResult.get("startDate");
			Date endDate = seniorRangeResult.get("endDate");
			String endDateStr = DateUtil.formatDate(endDate);
			String nowStr = DateUtil.getToday();
			if(DateUtil.compareDateStr(nowStr, endDateStr) > 0) {
				vo.setIsSenior((short) 1);
			}

			seniorRange = "有效期:" + DateUtil.formatDate(startDate) + "至" + DateUtil.formatDate(endDate);

			Map seniorIm = this.getSeniorImUsername(u);
			if (seniorIm != null && seniorIm.get("seniorImUsername") != null)
				vo.setImSeniorUsername(seniorIm.get("seniorImUsername").toString());

			if (seniorIm != null && seniorIm.get("seniorImNickname") != null)
				vo.setImSeniorNickname(seniorIm.get("seniorImNickname").toString());
		}

		vo.setSeniorRange(seniorRange);

		//用户环信IM信息
		UserRef3rd userRef3rd = this.genImUser(u);
		if (userRef3rd.getUsername().length() > 0) {
			vo.setImUsername(userRef3rd.getUsername());
			vo.setImPassword(userRef3rd.getPassword());
		}

		return vo;
	}

	/**
	 *  注册环信用户
	 */
	@Override
	public UserRef3rd genImUser(Users user) {
		UserRef3rd record = new UserRef3rd();
		Long userId = user.getId();
		UserRef3rd userRef3rd = userRef3rdMapper.selectByUserId(userId);
		if (userRef3rd !=null) {
			return userRef3rd;
		}

		//如果不存在则新增.并且存入数据库
		String username = "yggj_user_"+ user.getId().toString();
		String defaultPassword = com.meijia.utils.huanxin.comm.Constants.DEFAULT_PASSWORD;
		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username", username);
        datanode.put("password", defaultPassword);
        ObjectNode createNewIMUserSingleNode = EasemobIMUsers.createNewIMUserSingle(datanode);

        JsonNode statusCode = createNewIMUserSingleNode.get("statusCode");
		if (!statusCode.toString().equals("200"))
			return record;

		JsonNode entity = createNewIMUserSingleNode.get("entities");
		String uuid = entity.get(0).get("uuid").toString();
//		username = entity.get(0).get("username").toString();

		record.setId(0L);
		record.setUserId(userId);
		record.setRefType(Constants.IM_PROVIDE);
		record.setMobile(user.getMobile());
		record.setUsername(username);
		record.setPassword(defaultPassword);
		record.setRefPrimaryKey(uuid);
		record.setAddTime(TimeStampUtil.getNowSecond());
		userRef3rdMapper.insert(record);
        return record;
	}

	/**
	 * 查询用户与管家绑定的环信账号
	 * 1. 如果用户没有购买过管家卡，则为默认Constans.YGGJ_AMEI
	 * 2. 如果用户购买过管家卡，则为真人管家绑定的环信IM账号
	 */
	@Override
	public Map<String, String> getSeniorImUsername(Users user) {

		Map<String, String> map = new HashMap<String, String>();
		if (user == null) {
			return map;
		}
		//先找出真人管家的admin_id
		Long userId = user.getId();
		UserRefSenior userRefSenior = userRefSeniorMapper.selectByUserId(userId);
		if (userRefSenior == null) {
			return map;
		}

		Long adminId = userRefSenior.getSeniorId();
		AdminAccount adminAccount = adminAccountService.selectByPrimaryKey(adminId);

		String seniorImUsername = adminAccount.getImUsername();
		String seniorImNickname = adminAccount.getNickname();
		map.put("seniorImUsername", seniorImUsername);
		map.put("seniorImNickname", seniorImNickname);
		return map;
	}

	/**
	 * 获得环信机器人账号
	 * 后续需要根据用户的性别来进行判断，目前都统一为女性.
	 */
	@Override
	public Map<String, String> getImRobot(Users user) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("username", Constants.YGGJ_ROBOT_FEMALE_USERNAME);
		map.put("nickname", Constants.YGGJ_ROBOT_FEMALE_NICKNAME);
		return map;
	}

	@Override
	public List<Users> selectUsersHaveOrdered(List<String> mobiles) {
		return usersMapper.selectByMobiles(mobiles);
	}

	@Override
	public List<Users> selectUsersNoOrdered(List<String> mobiles) {
		return usersMapper.selectNotInMobiles(mobiles);
	}

}