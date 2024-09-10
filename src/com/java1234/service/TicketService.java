package com.java1234.service;

import java.util.List;

import com.java1234.entity.PageBean;
import com.java1234.entity.TicketOrder;
import com.java1234.entity.User;


/**
 * ��ƱService�ӿ�
 * @author Administrator
 *
 */
public interface TicketService {

	/**
	 * �����Ʊ����
	 * @param ticketOrder
	 */
	public void saveTicket(TicketOrder ticketOrder);
	
	/**
	 * ��ҳ������ѯ���ඩƱ
	 * @param s_ticketOrder
	 * @param pageBean
	 * @return
	 */
	public List<TicketOrder> findTicketOrderList(TicketOrder s_ticketOrder,PageBean pageBean);
	
	/**
	 * ������ѯ���ඩƱ��
	 * @param s_ticketOrder
	 * @return
	 */
	public Long getTicketOrderCount(TicketOrder s_ticketOrder);
	
	/**
	 * ��Ʊ
	 * @param id
	 */
	public void deleteTicketOrder(Integer id);
	
	/**
	 * ��ǩ
	 * @param id ԭ���Ļ�ƱId
	 * @param newFlightId ��ǩ���µĺ���Id
	 */
	public void saveMeal(Integer id,Integer newFlightId,User currentUser)throws Exception;
	
	/**
	 * �Ƿ����ָ���û��Ķ���
	 * @param userId
	 */
	public boolean existTicketOrderWithUserId(Integer userId);
	
	/**
	 * ͨ��ID��ȡ������Ϣ
	 * @param id
	 * @return
	 */
	public TicketOrder getTicketOrderById(int id);
	
	/**
	 * ɾ������
	 * @param ticketOrder
	 */
	public void delete(TicketOrder ticketOrder);
	
}
