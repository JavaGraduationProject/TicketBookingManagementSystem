package com.java1234.service;

import com.java1234.entity.Manager;

/**
 * ����ԱService�ӿ�
 * @author Administrator
 *
 */
public interface ManagerService {

	/**
	 * ����Ա��¼��֤
	 * @param manager
	 * @return
	 */
	public Manager login(Manager manager);
	
	/**
	 * ͨ��ID��ȡ����Աʵ��
	 * @param id
	 * @return
	 */
	public Manager getManagerById(int id);
	
	/**
	 * �������Ա
	 * @param manager
	 */
	public void saveManager(Manager manager);
	
	
}
