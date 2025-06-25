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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bindeshwar.bindeshwarmart.application.config.Ecryption;
import com.bindeshwar.bindeshwarmart.beans.Organisation;
import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.beans.UsersCategory;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.ShoppingCart;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishList;
import com.bindeshwar.bindeshwarmart.ecommerce.controller.EcommerceController;
import com.bindeshwar.bindeshwarmart.ecommerce.service.ShoppingCartService;
import com.bindeshwar.bindeshwarmart.ecommerce.service.WishListService;
import com.bindeshwar.bindeshwarmart.repository.UsersRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.UsersService;
import com.bindeshwar.bindeshwarmart.service.userCategoryService;

@Controller
@RequestMapping("/customer")
public class userController {
	
	@Autowired
	UsersService service;
	
	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	userCategoryService usercatService;
	
	@Autowired
	ShoppingCartService shoppingcartService; 
	
	@Autowired
	WishListService wishListService;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showUserRegisterPage")
	public String showFormUserRegister(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "/html/login/user_register";
	}
	
	@GetMapping("/showUses")
	public String showFormUsers(Model model) {
		model.addAttribute("userslist", service.getAllUsers());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/login/user_view";
	}
	
	
	@GetMapping("/showUserloginPage")
	public String showFormUserLogin(Model model) {
		Users users = new Users();
		model.addAttribute("users", users);
		return "/html/login/user_login";
	}
	
	@PostMapping("/checkuser")
	public String checkuser(Model model, @ModelAttribute Users users,RedirectAttributes redirectAttributes, BindingResult result,HttpServletRequest request) {
		
		UsersCategory usercat =  usercatService.getUserById((long) 2);
		users.setUsercategory(usercat);
		
		//encrypting my password
		 String originalData = users.getPassword();
	     byte[] originalBytes = originalData.getBytes();

	     // Encoding
	     String encodedData = Ecryption.encode(originalBytes);
       
	     users.setPassword(encodedData);
		
		Users user= service.getUserByEmailPasswordAndCate(users);
		
		if(user==null)
		{
			redirectAttributes.addFlashAttribute("message", "Login or password incorect !");
			return "redirect:showUserloginPage";
		}else{
			
			HttpSession session = request.getSession();
			session.setAttribute("userid", user.getId());
	        session.setAttribute("email", user.getEmail() );
	        session.setAttribute("username", user.getFullName());
	       
	        //this is for getting cart  
	        ShoppingCart userCart = shoppingcartService.getShoppingCartByUserId(user);
	        
	      //this is for getting user wishList
	        WishList userWishList = wishListService.getWishListByUserId(user);
	        
	        System.out.println("User cart: "+userCart.getId()+" "+user.getFullName());
	        
	        session.setAttribute("usercart", userCart.getId());
	        session.setAttribute("userWishList", userWishList.getId());
	      
			//return "redirect:ecom/showhomepage";
			return "redirect:/ecom/showhomepage";
		}
	}
	
	@GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        // Set cache-control header to prevent caching of the page
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setHeader("Expires", "0"); // Proxies
        return "redirect:/ecom/showhomepage";
    }
	
	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute Users userprofile, RedirectAttributes redirectAttributes) {
		
		EcommerceController.status = "profile";
		
		service.saveUser(userprofile);
		redirectAttributes.addFlashAttribute("messageupdate", "Profile Updated Successfully !");
		return "redirect:/ecom/profile";
	}
	
	
	
	@PostMapping("/changePassword")
	public String changeUserPassword(@ModelAttribute Users user, @RequestParam("oldpass") String oldpassword, HttpSession session ,RedirectAttributes redirectAttributes) throws Exception {
		Long userid = (Long) session.getAttribute("userid");
		
		
		EcommerceController.status = "change";
		//checking if password exist
		Users userss = service.getUserById(userid);
		
		String odlpass1 = userss.getPassword();
		
		//encrypting my password
		 String originalData = oldpassword;
	     byte[] originalBytes = originalData.getBytes();

       // Encoding
	    String odlpass2 = Ecryption.encode(originalBytes);
      
	    
	    //encrypting new pass
		//encrypting my password
		 String newpass = user.getPassword();
	     byte[] newpassBytes = newpass.getBytes();

      // Encoding
	    String newpassencrypted = Ecryption.encode(newpassBytes);
		
		if(!odlpass1.equals(odlpass2)){
			System.out.println("user Password "+ userss.getPassword());
			redirectAttributes.addFlashAttribute("message", "Old password incorect !");
			return "redirect:/ecom/profile";
		}else {
			
			
			userss.setPassword(newpassencrypted);
			service.saveUser(userss);
			redirectAttributes.addFlashAttribute("message6", "password changed successfully !!");
			return "redirect:/ecom/profile";
		}
	}
	
	
	@PostMapping("/addUsers")
	public String addEmployee(@ModelAttribute Users users,RedirectAttributes redirectAttributes, 
			BindingResult result,HttpServletRequest request) throws Exception {
		//UsersCategory usercategory = new UsersCategory();
		//usercategory.setId((long) 2);
		users.setUsercategory(null);
		users.setUsername(users.getFullName());
		
		//checking if email exist
		Users user = service.getUserByEmail(users);
		
		if(user != null) {
			redirectAttributes.addFlashAttribute("message", "Email in use already !");
			return "redirect:showUserRegisterPage";
		}else {
			//encrypting my password
			 String originalData = users.getPassword();
		     byte[] originalBytes = originalData.getBytes();

	        // Encoding
	        String encodedData = Ecryption.encode(originalBytes);
	        users.setPassword(encodedData);
	        
	        List<UsersCategory> byName = usercatService.findByName("customer");
	        
	        
	        Long count = service.countTotalUsers();

	        UsersCategory category = new UsersCategory();
	        if (count==0) {
	        	
	        	
	        	category.setId(1L);
	        	category.setName("admin");
	        	usercatService.save(category);
	        	users.setUsercategory(category);
			} else {
				
				if (byName.isEmpty()) {
					category.setId(2L);
		        	category.setName("customer");
		        	usercatService.save(category);
		        	users.setUsercategory(category);
				}
			}
	        
	        if (!byName.isEmpty()) 
	        	users.setUsercategory(byName.get(0));
	        
			service.saveUser(users);
			
			 //Creating user shopping cart
	        ShoppingCart ShoppingCart = new ShoppingCart();
	        ShoppingCart.setUser(users);
	        
	        //creating user WishList
	        WishList wishList = new WishList();
	        wishList.setUser(users);
	        wishListService.createUserWishList(wishList);
	        
	        shoppingcartService.createUserShoppingCart(ShoppingCart);
			
			return "redirect:showUserloginPage";
		}
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
