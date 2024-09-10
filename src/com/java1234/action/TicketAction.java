package com.java1234.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.java1234.entity.Aircraft;
import com.java1234.entity.Flight;
import com.java1234.entity.PageBean;
import com.java1234.entity.TicketOrder;
import com.java1234.entity.User;
import com.java1234.service.FlightService;
import com.java1234.service.TicketService;
import com.java1234.util.DateUtil;
import com.java1234.util.ResponseUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ��ƱAction��
 * @author Administrator
 *
 */
public class TicketAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HttpServletRequest request;
	
	@Resource
	private FlightService flightService;
	
	@Resource
	private TicketService ticketService;
	
	private Flight flight;
	private Integer flightId; // ������
	private String mainPage;
	
	private TicketOrder ticketOrder;
	private String price_type;
	
	private Integer id; // ��Ʊ����ID
	private Integer newFlightId; // ��ǩ�ĺ���
	
	private List<TicketOrder> ticketOrderList;
	
	private TicketOrder s_ticketOrder;
	
	private String page;
	private String rows;
	
	private String ids;
	
	
	
	public TicketOrder getS_ticketOrder() {
		return s_ticketOrder;
	}

	public void setS_ticketOrder(TicketOrder s_ticketOrder) {
		this.s_ticketOrder = s_ticketOrder;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	
	

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	
	public TicketOrder getTicketOrder() {
		return ticketOrder;
	}

	public void setTicketOrder(TicketOrder ticketOrder) {
		this.ticketOrder = ticketOrder;
	}
	
	

	public String getPrice_type() {
		return price_type;
	}

	public void setPrice_type(String price_type) {
		this.price_type = price_type;
	}
	
	

	public List<TicketOrder> getTicketOrderList() {
		return ticketOrderList;
	}

	public void setTicketOrderList(List<TicketOrder> ticketOrderList) {
		this.ticketOrderList = ticketOrderList;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public Integer getNewFlightId() {
		return newFlightId;
	}

	public void setNewFlightId(Integer newFlightId) {
		this.newFlightId = newFlightId;
	}

	/**
	 * ��Ʊ����Ԥ����
	 * @return
	 * @throws Exception
	 */
	public String preTicketOrder()throws Exception{
		flight=flightService.getFlightById(flightId);
		mainPage="ticket/ticketOrder.jsp";
		return SUCCESS;
	}
	
	/**
	 * �����Ʊ����
	 * @return
	 * @throws Exception
	 */
	public String saveTicketOrder()throws Exception{
		String orderNo="NO"+DateUtil.getCurrentDateStr(); // ���ɶ����� 
		ticketOrder.setOrderNo(orderNo);
		ticketOrder.setOrderTime(new Date());
		String strs[]=price_type.split("-");
		if(strs[1].equals("1")){
			ticketOrder.setSpaceType("���ò�");
		}else{
			ticketOrder.setSpaceType("ͷ�Ȳ�");
		}
		ticketOrder.setPrice(Integer.parseInt(strs[0]));
		ticketOrder.setTotalPrice(ticketOrder.getNum()*ticketOrder.getPrice());
		ticketService.saveTicket(ticketOrder);
		mainPage="ticket/orderSuccess.jsp";
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String myTicketOrderList()throws Exception{
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		TicketOrder s_ticketOrder=new TicketOrder();
		s_ticketOrder.setUser(currentUser);
		ticketOrderList=ticketService.findTicketOrderList(s_ticketOrder, null);
		mainPage="ticket/myTicketOrderList.jsp";
		return "userCenter";
	}
	
	/**
	 * ��Ʊ
	 * @return
	 * @throws Exception
	 */
	public String refund()throws Exception{
		ticketService.deleteTicketOrder(id);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ��Ʊ��ǩ
	 * @return
	 * @throws Exception
	 */
	public String meal()throws Exception{
		HttpSession session=request.getSession();
		User currentUser=(User) session.getAttribute("currentUser");
		ticketService.saveMeal(id, newFlightId, currentUser);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ��ҳ������ѯ����
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<TicketOrder> ticketOrderList=ticketService.findTicketOrderList(s_ticketOrder, pageBean);
		long total=ticketService.getTicketOrderCount(s_ticketOrder);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd HH:mm:ss"));
		jsonConfig.registerJsonValueProcessor(User.class,new ObjectJsonValueProcessor(new String[]{"id","userName"}, User.class));
		jsonConfig.registerJsonValueProcessor(Flight.class,new ObjectJsonValueProcessor(new String[]{"id","name"}, Flight.class));
		JSONArray rows=JSONArray.fromObject(ticketOrderList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ɾ������
	 * @return
	 * @throws Exception
	 */
	public String deleteTicketOrder()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		boolean flag=true;
		for(int i=0;i<idsStr.length;i++){
				TicketOrder ticketOrder=ticketService.getTicketOrderById(Integer.parseInt(idsStr[i]));
				ticketService.delete(ticketOrder);
		}
		result.put("success", flag);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	} 


	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}

}
