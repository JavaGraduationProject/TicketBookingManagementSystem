package com.java1234.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.java1234.dao.BaseDAO;
import com.java1234.entity.Flight;
import com.java1234.entity.PageBean;
import com.java1234.entity.TicketOrder;
import com.java1234.entity.User;
import com.java1234.service.TicketService;
import com.java1234.util.DateUtil;
import com.java1234.util.StringUtil;

/**
 * ��ƱServiceʵ����
 * @author Administrator
 *
 */
@Service("ticketService")
public class TicketServiceImpl implements TicketService{

	@Resource
	private BaseDAO<TicketOrder> baseDAO;
	
	@Resource
	private BaseDAO<Flight> baseDAO2;
	
	public void saveTicket(TicketOrder ticketOrder) {
		Flight flight=baseDAO2.get(Flight.class, ticketOrder.getFlight().getId());
		if("���ò�".equals(ticketOrder.getSpaceType())){
			flight.setEcTicketRemain(flight.getEcTicketRemain()-ticketOrder.getNum());
		}else if("ͷ�Ȳ�".equals(ticketOrder.getSpaceType())){
			flight.setFcTicketRemain(flight.getFcTicketRemain()-ticketOrder.getNum());
		}
		baseDAO2.save(flight);
		baseDAO.save(ticketOrder);
	}

	public List<TicketOrder> findTicketOrderList(TicketOrder s_ticketOrder,
			PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TicketOrder");
		if(s_ticketOrder!=null){
			if(s_ticketOrder.getUser().getId()!=null){
				hql.append(" and user.id="+s_ticketOrder.getUser().getId());
			}
			if(s_ticketOrder.getUser()!=null && StringUtil.isNotEmpty(s_ticketOrder.getUser().getUserName())){
				hql.append(" and user.userName like '%"+s_ticketOrder.getUser().getUserName()+"%'");
			}
			if(StringUtil.isNotEmpty(s_ticketOrder.getOrderNo())){
				hql.append(" and orderNo like '%"+s_ticketOrder.getOrderNo()+"%'");
			}
		}
		hql.append(" order by orderTime desc");
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param);
		}
	}

	public Long getTicketOrderCount(TicketOrder s_ticketOrder) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from TicketOrder");
		if(s_ticketOrder!=null){
			if(s_ticketOrder.getUser().getId()!=null){
				hql.append(" and user.id="+s_ticketOrder.getUser().getId());
			}
			if(s_ticketOrder.getUser()!=null && StringUtil.isNotEmpty(s_ticketOrder.getUser().getUserName())){
				hql.append(" and user.userName like '%"+s_ticketOrder.getUser().getUserName()+"%'");
			}
			if(StringUtil.isNotEmpty(s_ticketOrder.getOrderNo())){
				hql.append(" and orderNo like '%"+s_ticketOrder.getOrderNo()+"%'");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	public void deleteTicketOrder(Integer id) {
		TicketOrder ticketOrder=baseDAO.get(TicketOrder.class, id);
		baseDAO.delete(ticketOrder);
		Flight flight=baseDAO2.get(Flight.class, ticketOrder.getFlight().getId());
		if("���ò�".equals(ticketOrder.getSpaceType())){
			flight.setEcTicketRemain(flight.getEcTicketRemain()+ticketOrder.getNum());
		}else{
			flight.setFcTicketRemain(flight.getFcTicketRemain()+ticketOrder.getNum());
		}
		baseDAO2.save(flight);
	}

	public void saveMeal(Integer id, Integer newFlightId,User currentUser) throws Exception {
		Flight newFlight=baseDAO2.get(Flight.class, newFlightId); // �µĺ���
		TicketOrder ticketOrder=baseDAO.get(TicketOrder.class, id); // ԭ�ȵĻ�Ʊ����ʵ��
		Flight oldFlight=ticketOrder.getFlight(); // ԭ�ȵĺ���
		TicketOrder newTicketOrder=new TicketOrder();
		newTicketOrder.setOrderNo("NO"+DateUtil.getCurrentDateStr());
		newTicketOrder.setOrderTime(new Date());
		newTicketOrder.setNum(ticketOrder.getNum());
		newTicketOrder.setSpaceType(ticketOrder.getSpaceType());
		if("���ò�".equals(ticketOrder.getSpaceType())){
			newTicketOrder.setPrice(newFlight.getEcPrice());
			newTicketOrder.setTotalPrice(newFlight.getEcPrice()*newTicketOrder.getNum());
			oldFlight.setEcTicketRemain(oldFlight.getEcTicketRemain()+newTicketOrder.getNum()); // ԭ�ȵĺ���Ҫ���ϻ�Ʊ
			newFlight.setEcTicketRemain(newFlight.getEcTicketRemain()-newTicketOrder.getNum()); // �µĺ���Ҫ������Ʊ
		}else{
			newTicketOrder.setPrice(newFlight.getFcPrice());
			newTicketOrder.setTotalPrice(newFlight.getFcPrice()*newTicketOrder.getNum());
			oldFlight.setFcTicketRemain(oldFlight.getFcTicketRemain()+newTicketOrder.getNum()); // ԭ�ȵĺ���Ҫ���ϻ�Ʊ
			newFlight.setFcTicketRemain(newFlight.getFcTicketRemain()-newTicketOrder.getNum()); // �µĺ���Ҫ������Ʊ
		}
		newTicketOrder.setFlight(newFlight);
		newTicketOrder.setUser(currentUser);

		baseDAO.save(newTicketOrder); // �����µĶ���
		baseDAO2.save(oldFlight); // ����ԭ�ȵĺ�����Ϣ
		baseDAO2.save(newFlight); // �����µĺ�����Ϣ
		
		baseDAO.delete(ticketOrder); // ɾ��ԭ�ȵĶ���
		
	}

	public boolean existTicketOrderWithUserId(Integer userId) {
		String hql="from TicketOrder where user.id="+userId;
		if(baseDAO.find(hql).size()>0){
			return true;
		}else{
			return false;
		}
	}

	public TicketOrder getTicketOrderById(int id) {
		return baseDAO.get(TicketOrder.class, id);
	}

	public void delete(TicketOrder ticketOrder) {
		baseDAO.delete(ticketOrder);
	}
	

}
