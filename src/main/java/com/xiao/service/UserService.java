package com.xiao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiao.dao.LoginLogDao;
import com.xiao.dao.UserDao;
import com.xiao.domain.LoginLog;
import com.xiao.domain.User;
import com.xiao.exception.UserExistException;

/**
 * 对用户进行管理
 * 
 * @author XIAO
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;

	/**
	 * 注册一个新的用户
	 * 
	 * @param user
	 * @throws UserExistException
	 */
	public void register(User user) throws UserExistException {
		User u = this.getUserByUserName(user.getUserName());
		// 如果用户名已经存在
		if (u != null) {
			throw new UserExistException("用户名已经存在");
		} else {
			user.setCredit(100);// 初始100积分
			user.setUserType(1);// 普通用户
			userDao.save(user);
		}
	}

	/**
	 * 根据用户名查询用户对象
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserByUserName(String userName) {
		return userDao.getUserByUserName(userName);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param user
	 */
	public void updateUser(User user) {
		userDao.update(user);
	}

	/**
	 * 根据用户id获得User对象
	 * 
	 * @param id
	 */
	public User getUserById(int id) {
		return userDao.get(id);
	}

	/**
	 * 锁定一个用户，被锁定的用户不能登录
	 * 
	 * @param user
	 */
	public void lockUser(User user) {
		user.setLocked(User.USER_LOCK);// 设置锁定状态
		userDao.update(user);
	}

	/**
	 * 解锁一个用户
	 * 
	 * @param user
	 */
	public void unlockUser(User user) {
		user.setLocked(User.USER_UNLOCK);
		userDao.update(user);
	}

	/**
	 * 根据用户名为模糊查询条件，查询出所有前缀匹配的User对象
	 * 
	 * @param userName
	 * @return
	 */
	public List<User> queryUserByUserName(String userName) {
		return userDao.queryUserByUserName(userName);
	}

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	public List<User> getAllUser() {
		return userDao.loadAll();
	}

	/**
	 * 登录成功
	 */
	public void loginSuccess(User user) {
		user.setCredit(5 + user.getCredit());// 登录成功之后加5积分
		LoginLog loginLog = new LoginLog();
		loginLog.setUser(user);
		loginLog.setIp(user.getLastIp());
		loginLog.setLoginDate(new Date());
		userDao.update(user);
		loginLogDao.save(loginLog);
	}
}
