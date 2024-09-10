package com.java1234.service;

import java.util.List;

import com.java1234.entity.PageBean;
import com.java1234.entity.Aircraft;

/**
 * �ͻ�Service�ӿ�
 * @author Administrator
 *
 */
public interface AircraftService {

	
	/**
	 * ����ͻ�
	 * @param aircraft
	 */
	public void saveAircraft(Aircraft aircraft);
	
	/**
	 * ��ҳ������ѯ�ͻ���Ϣ
	 * @param s_aircraft
	 * @param pageBean
	 * @return
	 */
	public List<Aircraft> findAircraftList(Aircraft s_aircraft,PageBean pageBean);
	
	/**
	 * ��ȡ�ͻ�����
	 * @param s_aircraft
	 * @return
	 */
	public Long getAircraftCount(Aircraft s_aircraft);
	
	/**
	 * ɾ���ͻ�
	 * @param aircraft
	 */
	public void delete(Aircraft aircraft);
	
	/**
	 * ͨ��ID��ȡ�ͻ�ʵ��
	 * @param id
	 * @return
	 */
	public Aircraft getAircraftById(int id);
}
