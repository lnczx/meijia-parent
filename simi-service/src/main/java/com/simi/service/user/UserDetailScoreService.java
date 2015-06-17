package com.simi.service.user;

import java.util.List;

import com.simi.po.model.order.Orders;
import com.simi.po.model.user.UserDetailScore;
import com.simi.po.model.user.Users;

public interface UserDetailScoreService {


    int deleteByPrimaryKey(Long id);

    int insert(UserDetailScore record);

    int insert(Users users, UserDetailScore record);

    int insertSelective(UserDetailScore record);

    UserDetailScore selectByPrimaryKey(Long id);

    List<UserDetailScore> selectByPage(String mobile, int page);

	UserDetailScore initUserDetailScore();

}