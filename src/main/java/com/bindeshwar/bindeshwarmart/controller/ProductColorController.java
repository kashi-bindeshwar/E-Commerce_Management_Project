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
import com.bindeshwar.bindeshwarmart.beans.ProductColor;
import com.bindeshwar.bindeshwarmart.repository.ProductColorRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductColorService;


@Controller
@RequestMapping("/ProductColor")
public class ProductColorController {
	@Autowired
	ProductColorService service;
	
	@Autowired
	ProductColorRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdColor")
	public String showFormProductType(Model model) {
		ProductColor productColor = new ProductColor();
		model.addAttribute("productColor", productColor);
		model.addAttribute("productColors", service.getAllProductColors());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Color";
	}
	
	@PostMapping("/addColor")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductColor productColor,BindingResult result) throws Exception {
	if(result.hasErrors()) {
		model.addAttribute("organisations", orgservice.getAllOrganisation());	
		model.addAttribute("productColors", service.getAllProductColors());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Color";
		}else {
			service.saveProductBrand(productColor);
			return "redirect:showorProdColor";
		}
	}
	
	@GetMapping("/UpdateProdColor")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		ProductColor productColor = repository.findById(id).get();
		  model.addAttribute(productColor);
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		  model.addAttribute("productColors", service.getAllProductColors());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-Color";
	}
	@GetMapping("/deleteProdColor")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdColor(id);
			return "redirect:showorProdColor";
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
