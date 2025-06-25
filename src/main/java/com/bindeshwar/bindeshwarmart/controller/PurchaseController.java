package com.bindeshwar.bindeshwarmart.controller;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.bindeshwar.bindeshwarmart.beans.Organisation;
import com.bindeshwar.bindeshwarmart.beans.Purchase;
import com.bindeshwar.bindeshwarmart.repository.PurchaseAddRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.PayementModeService;
import com.bindeshwar.bindeshwarmart.service.PurchaseAddService;
import com.bindeshwar.bindeshwarmart.service.VendorService;

@Controller
@RequestMapping("/Purchase")
public class PurchaseController {
	@Autowired
	PurchaseAddService service;
	@Autowired
	PurchaseAddRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	@Autowired
	VendorService vendorservice;
	@Autowired
	PayementModeService payementModeservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorPurchaseAdd")
	public String showFormPurchase(Model model) {
		Purchase purchase = new Purchase();  
		model.addAttribute("purchase", purchase);
		model.addAttribute("purchases", service.getAllPurchase());
		model.addAttribute("payementModes", payementModeservice.getAllProductPMode());
		model.addAttribute("vendors",vendorservice.getAllVendors());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/purchase/purchaseAdd";
	}
	
	@GetMapping("/showpurchaseManage")
	public String showproductManage(Model model) {
		model.addAttribute("purchases", service.getAllPurchase());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "html/purchase/purchase-Manage";
	}
	
	@GetMapping("/viewPurchase")
	public String viewproduct(Model model, @RequestParam("id") Long id) {
			    Purchase purchase = repository.findById(id).get();
				model.addAttribute(purchase);
				return "html/purchase/purchase-view";
	}
	
	@PostMapping("/addPurchase")
	public String addProdCat(Model model, @Valid @ModelAttribute Purchase purchase,BindingResult result) throws Exception {
	if(result.hasErrors()) {	
		model.addAttribute("purchases", service.getAllPurchase());
		return "/html/purchase/purchaseAdd";
		}else {
			service.savePurchase(purchase);
			return "redirect:showorPurchaseAdd?message=Purchase added successfully !!!!";
		}
	}
	
	@GetMapping("/UpdatePurchase")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
			Purchase purchase = repository.findById(id).get();
		  model.addAttribute(purchase);
		  model.addAttribute("purchases", service.getAllPurchase());
			model.addAttribute("payementModes", payementModeservice.getAllProductPMode());
			model.addAttribute("vendors",vendorservice.getAllVendors());
		  return "/html/purchase/purchaseAdd";
	}
	@GetMapping("/deletePurchase")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deletePurchase(id);
			return "redirect:showpurchaseManage";
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
