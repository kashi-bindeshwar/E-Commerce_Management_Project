package com.bindeshwar.bindeshwarmart.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bindeshwar.bindeshwarmart.application.config.Ecryption;
import com.bindeshwar.bindeshwarmart.beans.Organisation;
import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.beans.UsersCategory;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.Orders;
import com.bindeshwar.bindeshwarmart.ecommerce.service.OrderService;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.UsersService;
import com.bindeshwar.bindeshwarmart.service.userCategoryService;


@Controller
@RequestMapping("/admin")
public class loginController {
	@Autowired
	OrganisationService orgservice;
	
	@Autowired
	UsersService service;
	@Autowired
	userCategoryService usercatService;
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	private HttpSession session;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/loginForm")
	public String showFormLogin(Model model) {
		model.addAttribute("users",new Users());
		return "/html/login/admin-login";
	}
		
	@GetMapping("/showAdminDashboard")
	public String showDashboard(Model model) {
		
		Users users = (Users) session.getAttribute("users");
		if (users==null) 
			return "redirect:loginForm";
		
		int totalOrderReceived = 0;
		int totalOrderDelivered = 0;
		int totalAdmin = 0;
		int totalCustomer = 0;
		List<Orders> orders = orderService.getAllOrders();
		
		for (Orders ord : orders) {
	          if(ord.getStatus()!=null && ord.getStatus().equalsIgnoreCase("received")) {
	        	  totalOrderReceived++;
	          }else {
	        	  totalOrderDelivered++;
	          }
	        }
		
		List<Users> users1 = (List<Users>) usersService.getAllUsers();
		for (Users u : users1) {
			
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
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		model.addAttribute("users", users);
		System.out.println(users.getId()+" "+users.getUsername()  + " "+users.getEmail());
		return "home";
	}
	
//	@PostMapping("/checkuser")
//	public String checkuser(@ModelAttribute Users users,Model model,RedirectAttributes redirectAttributes,
//	BindingResult result,HttpServletRequest request,@RequestParam("username") String username) {
	
	
	@PostMapping("/checkuser")
	public String checkuser(@ModelAttribute Users users,Model model,RedirectAttributes redirectAttributes, 
			BindingResult result,HttpServletRequest request) {	
		
		System.out.println("email "+users.getEmail());
		System.out.println("email "+users.getPassword());
		
		if(users.getEmail().equals("bindeshwar@gmail.com") && users.getPassword().equals("bindeshwar@12")) {
			HttpSession session = request.getSession();
	        session.setAttribute("username", users.getUsername());
	        session.setAttribute("users", users);
			return "redirect:showAdminDashboard";
		}else {
			System.out.println("invalid");
			redirectAttributes.addFlashAttribute("message", "Login or password incorect !");
			return "redirect:loginForm";
		}
			
	}
	
	
	
	
	
	
//	@PostMapping("/checkuser")
//	public String checkuser(@ModelAttribute Users users,Model model,RedirectAttributes redirectAttributes, 
//			BindingResult result,HttpServletRequest request) {	
//		System.out.println("email "+users.getUsername());
//		System.out.println("email "+users.getPassword());
//		
//		UsersCategory usercat =  usercatService.getUserById((long) 1);
//		users.setUsercategory(usercat);
//		
//		//encrypting my password
//		 String originalData = users.getPassword();
//	     byte[] originalBytes = originalData.getBytes();
//
//	     // Encoding
//	     String encodedData = Ecryption.encode(originalBytes);
//      
//	     users.setPassword(encodedData);
//		
//	    Users user= service.getUserByEmailPasswordAndCate(users);
////		Users user= service.getUserByUsernamePasswordAndCate(users);
//		
//		System.out.println("user comes : "+user);
//		
//		if(user==null)
//		{
//			System.out.println("invalid");
//			redirectAttributes.addFlashAttribute("message", "Login or password incorect !");
//			return "redirect:loginForm";
//		}
//		else
//		{
//			HttpSession session = request.getSession();
//	        session.setAttribute("username", users.getUsername());
//	        session.setAttribute("users", user);
//			return "redirect:showAdminDashboard";
//		}
//	}
	
	 @GetMapping("/logout")
	    public String logout(HttpServletRequest request, HttpServletResponse response) {
	        HttpSession session = request.getSession();
	        session.invalidate();
	        // Set cache-control header to prevent caching of the page
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	        response.setHeader("Expires", "0"); // Proxies
	        
	        return "redirect:loginForm";
	    }
	
	//image displaying my image controller
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Organisation> organisation)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		organisation = orgservice.getImageById(id);
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(organisation.get().getImage());
		response.getOutputStream().close();
	}
}
