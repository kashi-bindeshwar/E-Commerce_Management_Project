package com.bindeshwar.bindeshwarmart.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bindeshwar.bindeshwarmart.beans.FinancialYear;
import com.bindeshwar.bindeshwarmart.repository.FinancialYearRepository;
import com.bindeshwar.bindeshwarmart.service.FinancialYearService;


@Controller
@RequestMapping("/sine")
public class financialYController {
	@Autowired
	FinancialYearService service;
	
	@Autowired
	FinancialYearService serviceUpdate;
	
	@Autowired
	FinancialYearRepository repository;
	
	
	
	//show sinepolo
	@GetMapping("/sine")
	public String showsine(Model model) {
		FinancialYear finance = new FinancialYear();
		model.addAttribute("finance", finance);
		model.addAttribute("finances",service.getAllOrganisation());
		return "/html/masterSetup/sinepolo";
	}
	
	
	@GetMapping("/last")
    public ResponseEntity<FinancialYear> getLastRecord() {
        FinancialYear lastRecord = service.getLastRecord();
        if (lastRecord != null) {
            return ResponseEntity.ok(lastRecord);
            
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
	
	@PostMapping("/addFinance")
	public String addDesignation(@ModelAttribute FinancialYear financialYear) {
		
		FinancialYear lastRecord = repository.findLastRecord();
	
		if(lastRecord == null) {
			service.saveOrganisation(financialYear);
			return "redirect:sine";
		}else {
			lastRecord.setIsCurrent("No");
			serviceUpdate.UpdateOrganisation(lastRecord);
			service.saveOrganisation(financialYear);
			return "redirect:sine";
		}
		
	}
}
