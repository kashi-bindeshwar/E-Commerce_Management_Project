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
import com.bindeshwar.bindeshwarmart.beans.ProductType;
import com.bindeshwar.bindeshwarmart.repository.ProductTypeRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductTypeService;

@Controller
@RequestMapping("/ProductType")
public class ProductTypeController {

	@Autowired
	ProductTypeService service;
	
	@Autowired
	ProductTypeRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdType")
	public String showFormProductType(Model model) {
		ProductType productType = new ProductType();
		model.addAttribute("productType", productType);
		model.addAttribute("productTypes", service.getAllProductTypes());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-ProdType2";
	}
	
	@PostMapping("/addType")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductType productType,BindingResult result) throws Exception {
	if(result.hasErrors()) {
			
		model.addAttribute("productTypes", service.getAllProductTypes());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
			return "/html/masterSetup/masterSetup-Goods-And-Service-ProdType2";
		}else {
			service.saveProductType(productType);
			return "redirect:showorProdType";
		}
	}
	
	@GetMapping("/UpdateProdTYPE")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		  ProductType productType = repository.findById(id).get();
		  model.addAttribute(productType);
		  model.addAttribute("productTypes", service.getAllProductTypes());
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-ProdType2";
	}
	@GetMapping("/deleteProdType")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdType(id);
			return "redirect:showorProdType";
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
