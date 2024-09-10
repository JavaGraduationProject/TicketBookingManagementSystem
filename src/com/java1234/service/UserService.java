package com.java1234.service;

import java.util.List;

import com.java1234.entity.PageBean;
import com.java1234.entity.User;

/**
 * �ÿ�Service�ӿ�
 * @author Administrator
 *
 */
public interface UserService {

	/**
	 * ��¼��֤
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * �����û�
	 * @param user
	 */
	public void saveUser(User user);
	
	/**
	 * �ж��û����Ƿ����
	 * @param userName
	 * @return
	 */
	public boolean existUserWithUserName(String userName);
	
	/**
	 * ��ҳ������ѯ�û���Ϣ
	 * @param s_user
	 * @param pageBean
	 * @return
	 */
	public List<User> findUserList(User s_user,PageBean pageBean);
	
	/**
	 * ��ȡ�û�����
	 * @param s_user
	 * @return
	 */
	public Long getUserCount(User s_user);
	
	/**
	 * ɾ���û�
	 * @param user
	 */
	public void delete(User user);
	
	/**
	 * ͨ��ID��ȡ�û�ʵ��
	 * @param id
	 * @return
	 */
	public User getUserById(int id);
}
