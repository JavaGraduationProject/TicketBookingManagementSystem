package com.java1234.service;

import java.util.List;

import com.java1234.entity.Flight;
import com.java1234.entity.PageBean;
import com.java1234.entity.User;

/**
 * ����Service�ӿ�
 * @author Administrator
 *
 */
public interface FlightService {

	/**
	 * ��ҳ��ѯ������Ϣ
	 * @param s_flight
	 * @param pageBean
	 * @return
	 */
	public List<Flight> findFlightList(Flight s_flight,PageBean pageBean);
	
	/**
	 * ��ȡ��������
	 * @param s_flight
	 * @return
	 */
	public Long getFlightCount(Flight s_flight);
	
	/**
	 * ͨ��ID���Һ�����Ϣ
	 * @param id
	 * @return
	 */
	public Flight getFlightById(int id);
	
	/**
	 * ���溽��
	 * @param flight
	 */
	public void saveFlight(Flight flight);
	
	/**
	 * ɾ������
	 * @param user
	 */
	public void delete(Flight flight);
}
