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
import com.bindeshwar.bindeshwarmart.beans.ProductTaxPercentage;
import com.bindeshwar.bindeshwarmart.repository.ProductTaxPercentageRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductTaxPercentageService;
import com.bindeshwar.bindeshwarmart.service.ProductTaxService;

@Controller
@RequestMapping("/ProductTaxPercentage")
public class ProductTaxPercentageController {
	@Autowired
	ProductTaxPercentageService service;
	
	@Autowired
	ProductTaxPercentageRepository repository;
	
	@Autowired
	ProductTaxService tax_service;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdTaxPercentage")
	public String showFormProductType(Model model) {
		ProductTaxPercentage productTaxPercentage = new ProductTaxPercentage();
		model.addAttribute("productTaxPercentage", productTaxPercentage);
		model.addAttribute("productTaxPercentages", service.getAllProductTaxp());
		model.addAttribute("productTaxs", tax_service.getAllProductTax());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Tax-Percentage";
	}
	
	@PostMapping("/addTaxPercentage")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductTaxPercentage productTaxPercentage,BindingResult result) throws Exception {
	if(result.hasErrors()) {
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		model.addAttribute("productTaxPercentages", service.getAllProductTaxp());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Tax-Percentage";
		}else {
			service.saveProductTax(productTaxPercentage);
			return "redirect:showorProdTaxPercentage";
		}
	}
	
	@GetMapping("/UpdateProdTaxPercentage")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		ProductTaxPercentage productTaxPercentage = repository.findById(id).get();
		  model.addAttribute(productTaxPercentage);
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		  model.addAttribute("productTaxPercentages", service.getAllProductTaxp());
		  model.addAttribute("productTaxs", tax_service.getAllProductTax());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-Tax-Percentage";
	}
	@GetMapping("/deleteProdTaxPercentage")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdTaxp(id);
			return "redirect:showorProdTaxPercentage";
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
