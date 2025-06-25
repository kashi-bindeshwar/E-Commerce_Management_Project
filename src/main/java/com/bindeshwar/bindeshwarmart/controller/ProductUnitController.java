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
import com.bindeshwar.bindeshwarmart.beans.ProductUnits;
import com.bindeshwar.bindeshwarmart.repository.ProductUnitRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductUnitService;

@Controller
@RequestMapping("/ProductUnits")
public class ProductUnitController {
	@Autowired
	ProductUnitService service;
	
	@Autowired
	ProductUnitRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showorProdUnit")
	public String showFormProductType(Model model) {
		ProductUnits productUnits = new ProductUnits();
		model.addAttribute("productUnits", productUnits);
		model.addAttribute("productUnitss", service.getAllProductUnit());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Units2";
	}
	
	@PostMapping("/addUnit")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductUnits productUnits,BindingResult result) throws Exception {
	if(result.hasErrors()) {
			
		model.addAttribute("productUnitss", service.getAllProductUnit());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "/html/masterSetup/masterSetup-Goods-And-Service-Units2";
		}else {
			service.saveProductUnit(productUnits);
			return "redirect:showorProdUnit";
		}
	}
	
	@GetMapping("/UpdateProdUnit")
	public String UpdateProdCat(Model model, @RequestParam("id") Long id) {
		ProductUnits productUnits = repository.findById(id).get();
		  model.addAttribute(productUnits);
		  model.addAttribute("organisations", orgservice.getAllOrganisation());
		  model.addAttribute("productUnitss", service.getAllProductUnit());
		  return "/html/masterSetup/masterSetup-Goods-And-Service-Units2";
	}
	@GetMapping("/deleteProdUnit")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProdUnit(id);
			return "redirect:showorProdUnit";
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
