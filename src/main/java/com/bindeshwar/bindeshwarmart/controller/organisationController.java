package com.bindeshwar.bindeshwarmart.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bindeshwar.bindeshwarmart.beans.Organisation;
import com.bindeshwar.bindeshwarmart.repository.organisationRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;


@Controller
@RequestMapping("/Organization")
public class organisationController {
	@Autowired
	OrganisationService service;
	
	@Autowired
	organisationRepository repository;
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/showOrganization")
	public String showFormOrganisation(Model model) {
		List<Organisation> organi = repository.findAll();
		Organisation organisation = new Organisation();
		if(organi.isEmpty()) {
			model.addAttribute("organisation", organisation);
			return "/html/masterSetup/masterSetup-organisation-Add2";
		}else {
			model.addAttribute("organisations", service.getAllOrganisation());
			return "/html/masterSetup/masterSetup-organisation2";
		}
	}
	
	@GetMapping("/viewOrganization")
	public String showFormAddOrganisationAdd(Model model) {
		model.addAttribute("organisations", service.getAllOrganisation());
		return "/html/masterSetup/masterSetup-organisation-view2";
	}
	
	@PostMapping("/addOrg")
	public String addEmployee(@ModelAttribute Organisation organisation) throws Exception {
			 
		byte[] logo= organisation.getTempimage().getBytes();
		organisation.setImage(logo);
		
		service.saveOrganisation(organisation);
		return "redirect:showOrganization";
	}
	
	@GetMapping("/UpdateOrg")
	public String UpdateOrganisation(Model model, @RequestParam("id") Long id) {
			    Organisation organisation = repository.findById(id).get();
				model.addAttribute(organisation);
				model.addAttribute("organisations", service.getAllOrganisation());
				return "/html/masterSetup/masterSetup-organisation-Add2";
	}
	
	//image displaying my image controller
	@GetMapping("/image/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<Organisation> organisation)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		organisation = service.getImageById(id);
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(organisation.get().getImage());
		response.getOutputStream().close();
	}
	
	
	// this is for neeraj displaying image
	@RequestMapping("/getImageFromOrganisationId")
	private @ResponseBody byte[] getImageFromOrganisationId(Model model, @RequestParam Long id)
			throws MalformedURLException,IOException {
		System.out.println("Hi");
		Organisation organisation = service.getImageFormOrganisationId(id);
		if(organisation.getImage() != null) {
			System.out.println("In if");
			return organisation.getImage();
		}else {
			System.out.println("In else");
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("assets/img/avatar.jpg").getFile());
			return Files.readAllBytes(file.toPath());
		}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
