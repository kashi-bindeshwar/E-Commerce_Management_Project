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
import com.bindeshwar.bindeshwarmart.beans.ProductBrand;
import com.bindeshwar.bindeshwarmart.repository.ProductBrandRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductBrandService;


@Controller
@RequestMapping("/ProductBrand")
public class ProductBrandController {
	@Autowired
	ProductBrandService service;
	
	@Autowired
	ProductBrandRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdBrand")
	public String showFormProductType(Model model) {
		ProductBrand productBrand = new ProductBrand();
		model.addAttribute("productBrand", productBrand);
		model.addAttribute("productBrands", service.getAllProductBrand());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Brand";
	}
	
	@PostMapping("/addBrand")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductBrand productBrand,BindingResult result) throws Exception {
	if(result.hasErrors()) {	
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		model.addAttribute("productBrands", service.getAllProductBrand());
	
		return "/html/masterSetup/masterSetup-Goods-And-Service-Brand";
		}else {
			service.saveProductBrand(productBrand);
			return "redirect:showorProdBrand";
		}
	}
	
	@GetMapping("/UpdateProdBrand")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		ProductBrand productBrand = repository.findById(id).get();
		  model.addAttribute(productBrand);
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		  model.addAttribute("productBrands", service.getAllProductBrand());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-Brand";
	}
	@GetMapping("/deleteProdBrand")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdBrand(id);
			return "redirect:showorProdBrand";
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
