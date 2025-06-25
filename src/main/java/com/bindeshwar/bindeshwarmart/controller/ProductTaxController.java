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
import com.bindeshwar.bindeshwarmart.beans.ProductTax;
import com.bindeshwar.bindeshwarmart.repository.ProductTaxRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductTaxService;

@Controller
@RequestMapping("/ProductTax")
public class ProductTaxController {
	@Autowired
	ProductTaxService service;
	
	@Autowired
	ProductTaxRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdTax")
	public String showFormProductType(Model model) {
		ProductTax productTax = new ProductTax();
		model.addAttribute("productTax", productTax);
		model.addAttribute("productTaxs", service.getAllProductTax());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Tax2";
	}
	
	@PostMapping("/addTax")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductTax productTax,BindingResult result) throws Exception {
	if(result.hasErrors()) {
		model.addAttribute("organisations", orgservice.getAllOrganisation());	
		model.addAttribute("productTaxs", service.getAllProductTax());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Tax2";
		}else {
			service.saveProductTax(productTax);
			return "redirect:showorProdTax";
		}
	}
	
	@GetMapping("/UpdateProdTax")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		ProductTax productTax = repository.findById(id).get();
		  model.addAttribute(productTax);
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
			model.addAttribute("productTaxs", service.getAllProductTax());
			return "/html/masterSetup/masterSetup-Goods-And-Service-Tax2";
	}
	@GetMapping("/deleteProdTax")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdTax(id);
			return "redirect:showorProdTax";
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
