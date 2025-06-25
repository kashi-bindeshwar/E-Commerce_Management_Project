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
import com.bindeshwar.bindeshwarmart.beans.ProductCategories;
import com.bindeshwar.bindeshwarmart.beans.ProductSubCategory;
import com.bindeshwar.bindeshwarmart.repository.ProductCategoriesRepository;
import com.bindeshwar.bindeshwarmart.repository.ProductSubCategoyRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductCategoryService;
import com.bindeshwar.bindeshwarmart.service.ProductSubCategoryService;


@Controller
@RequestMapping("/ProductCategory")
public class ProductCategoryController {
	@Autowired
	ProductCategoryService service;
	
	@Autowired
	ProductCategoriesRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	//this is for category
	@GetMapping("/showorProdCategory")
	public String showFormOrganisation(Model model) {
		ProductCategories productCategories = new ProductCategories();
		model.addAttribute("productCategories", productCategories);
		model.addAttribute("prodcategories", service.getAllProductCategories());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Category2";
	}
	@PostMapping("/addCategory")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductCategories prodcategory,BindingResult result) throws Exception {
		if(result.hasErrors()) {
			
			model.addAttribute("prodcategories", service.getAllProductCategories());
			model.addAttribute("organisations", orgservice.getAllOrganisation());
			return "/html/masterSetup/masterSetup-Goods-And-Service-Category2";
		}else {
			service.saveProductCategories(prodcategory);
			return "redirect:showorProdCategory";
		}
	}
	
	@GetMapping("/UpdateProdCat")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
	      ProductCategories productCategories = repository.findById(id).get();
		  model.addAttribute(productCategories);
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		  model.addAttribute("prodcategories", service.getAllProductCategories());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-Category2";
	}
	@GetMapping("/deleteProdCat")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdCategory(id);
			return "redirect:showorProdCategory";
	}

	//this is for Sub category
	
	@Autowired
	ProductSubCategoryService subservice;
	
	@Autowired
	ProductSubCategoyRepository subrepository;
	
	@Autowired
	ProductCategoryService prodcat_service;
	
	@GetMapping("/showorProdSubCategory")
	public String showProdSubCategory(Model model) {
		ProductSubCategory productSubCategory = new ProductSubCategory();
		model.addAttribute("productSubCategory", productSubCategory);
		model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		model.addAttribute("prodcategories", prodcat_service.getAllProductCategories());
		return "/html/masterSetup/masterSetup-Goods-And-Service-SubCategory";
	}
	@PostMapping("/addSubCategory")
	public String addProdSubCat(Model model,@Valid @ModelAttribute ProductSubCategory productSubCategory,BindingResult result) throws Exception {
		if(result.hasErrors()) {
			model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
			model.addAttribute("organisations", orgservice.getAllOrganisation());
			model.addAttribute("prodcategories", prodcat_service.getAllProductCategories());
			return "/html/masterSetup/masterSetup-Goods-And-Service-SubCategory";
		}else {
			subservice.saveProductCategories(productSubCategory);
			return "redirect:showorProdSubCategory";
		}
	}
	
	
	
	@GetMapping("/UpdateProdSubCat")
	public String UpdateProdSubCat(Model model, @RequestParam("id") Long id) {
		ProductSubCategory productSubCategory = subrepository.findById(id).get();
		  model.addAttribute(productSubCategory);
		  model.addAttribute("prodcategories", service.getAllProductCategories());
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		  model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-SubCategory";
	}
	@GetMapping("/deleteProdSubCat")
	public String deleteProdSubCat(@RequestParam("id") Long id) {
			subservice.deleteProdCategory(id);
			return "redirect:showorProdSubCategory";
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
