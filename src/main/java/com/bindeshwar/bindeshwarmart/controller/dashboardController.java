package com.bindeshwar.bindeshwarmart.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bindeshwar.bindeshwarmart.beans.Organisation;
import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.Orders;
import com.bindeshwar.bindeshwarmart.ecommerce.service.OrderService;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.UsersService;

@Controller
@RequestMapping("/Dashboard")
public class dashboardController {
	@Autowired
	OrganisationService service;
	
	@Autowired
	OrderService orderService;

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UsersService usersService;
	
	@GetMapping("/showDashboard")
	public String showFormDashboard(Model model) {
		
		int totalOrderReceived = 0;
		int totalOrderDelivered = 0;
		int totalAdmin = 0;
		int totalCustomer = 0;
		List<Orders> orders = orderService.getAllOrders();
		
		for (Orders ord : orders) {
			System.out.println(ord.getStatus());
	          if(ord.getStatus()!=null && ord.getStatus().equalsIgnoreCase("received")) {
	        	  totalOrderReceived++;
	        	  System.out.println(totalOrderReceived);
	          }else {
	        	  totalOrderDelivered++;
	          }
	        }
		
		List<Users> users = (List<Users>) usersService.getAllUsers();
		for (Users u : users) {
			
			Long user = (long) u.getUsercategory().getId();
	          if(user == 2) {
	        	  totalCustomer++;
	        	
	          }else {
	        	  totalAdmin++;
	          }
	        }
		model.addAttribute("totalCustomer", totalCustomer);
		model.addAttribute("totalAdmin", totalAdmin);
		model.addAttribute("totalOrderReceived", totalOrderReceived);
		model.addAttribute("totalOrderDelivered", totalOrderDelivered);
		model.addAttribute("organisations", service.getAllOrganisation());
		return "home";
	}
	
	
	//image displaying my image controller
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Organisation> organisation)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		organisation = service.getImageById(id);
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(organisation.get().getImage());
		response.getOutputStream().close();
	}
	
}
