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
import com.bindeshwar.bindeshwarmart.beans.ProductPackaging;
import com.bindeshwar.bindeshwarmart.repository.ProductPackagingRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductPackagingService;


@Controller
@RequestMapping("/ProductPackaging")
public class ProductPackagingController {
	@Autowired
	ProductPackagingService service;
	
	@Autowired
	ProductPackagingRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdPackaging")
	public String showFormProductType(Model model) {
		ProductPackaging productPackaging = new ProductPackaging();
		model.addAttribute("productPackaging", productPackaging);
		model.addAttribute("productpackagings", service.getAllProductPackages());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Packaging2";
	}
	
	@PostMapping("/addPackage")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductPackaging productpackaging,BindingResult result) throws Exception {
	if(result.hasErrors()) {	
		model.addAttribute("productpackagings", service.getAllProductPackages());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Packaging2";
		}else {
			service.saveProductPackage(productpackaging);
			return "redirect:showorProdPackaging";
		}
	}
	
	@GetMapping("/UpdateProdPackage")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		ProductPackaging productpackaging = repository.findById(id).get();
		  model.addAttribute(productpackaging);
		  model.addAttribute("productpackagings", service.getAllProductPackages());
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-Packaging2";
	}
	@GetMapping("/deleteProdPackage")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdPackage(id);
			return "redirect:showorProdPackaging";
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
