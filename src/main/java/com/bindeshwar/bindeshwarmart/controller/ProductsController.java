package com.bindeshwar.bindeshwarmart.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import com.bindeshwar.bindeshwarmart.beans.Organisation;
import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.beans.ProductImages;
import com.bindeshwar.bindeshwarmart.repository.ProductAddRepository;
import com.bindeshwar.bindeshwarmart.repository.ProductCategoriesRepository;
import com.bindeshwar.bindeshwarmart.repository.ProductImageRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.ProductAddService;
import com.bindeshwar.bindeshwarmart.service.ProductBrandService;
import com.bindeshwar.bindeshwarmart.service.ProductCategoryService;
import com.bindeshwar.bindeshwarmart.service.ProductColorService;
import com.bindeshwar.bindeshwarmart.service.ProductPackagingService;
import com.bindeshwar.bindeshwarmart.service.ProductSubCategoryService;
import com.bindeshwar.bindeshwarmart.service.ProductTypeService;
import com.bindeshwar.bindeshwarmart.service.ProductUnitService;


@Controller
@RequestMapping("/Products")
public class ProductsController {
	
	@Autowired
	ProductAddService service;
	@Autowired
	ProductAddRepository repository;
	
	@Autowired
	OrganisationService orgservice;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductCategoryService prodcat_service;
	@Autowired
	ProductCategoriesRepository prodcat_repos;
	
	@Autowired
	ProductColorService prodcolorservice;
	@Autowired
	ProductSubCategoryService subservice;
	
	@Autowired
	ProductBrandService brandservice;
	@Autowired
	ProductTypeService typeservice;
	@Autowired
	ProductUnitService produnitservice;
	
	@Autowired
	ProductPackagingService prodpackaginservice;
	@Autowired
	ProductImageRepository prodImagerepository;
	
	@GetMapping("/showproductsAdd")
	public String showFormProduct(Model model) {
		ProductAdd productAdd = new ProductAdd();
		model.addAttribute("productAdd", productAdd);
		model.addAttribute("productAdds",service.getAllProducts());
		model.addAttribute("productColors", prodcolorservice.getAllProductColors());
		model.addAttribute("prodcategories", prodcat_service.getAllProductCategories());
		model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
		model.addAttribute("productTypes", typeservice.getAllProductTypes());
		model.addAttribute("productUnitss", produnitservice.getAllProductUnit());
		model.addAttribute("productpackagings", prodpackaginservice.getAllProductPackages());
		model.addAttribute("productBrands", brandservice.getAllProductBrand());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "html/products/products-Add";
	}
	
	
	@GetMapping("/showproductManage")
	public String showproductManage(Model model) {
		model.addAttribute("productAdds",service.getAllProducts());
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		return "html/products/products-Manage";
		//return "html/products/data";
	}
	
	@GetMapping("/viewProduct")
	public String viewproduct(Model model, @RequestParam("id") Long id) {
			    ProductAdd productAdd = repository.findById(id).get();
				model.addAttribute(productAdd);
				 // Add the doc_list associated with the ProductAdd object to the model
				List<ProductImages> docList = productAdd.getDoc_list();
				
				 // Convert the bytes to Base64-encoded strings for display in the view
			    List<String> imageList = new ArrayList<>();
			    for (ProductImages image : docList) {
			    	 if (image.getDoc() != null) {
			    		 byte[] imageData = image.getDoc();
			    		 String base64Image = Base64.getEncoder().encodeToString(imageData);
			    		
			    		 imageList.add(base64Image);
			    	 }
			    }
				
			    model.addAttribute("imageList", imageList);
		
			    
				model.addAttribute("productAdds",service.getAllProducts());
				model.addAttribute("productColors", prodcolorservice.getAllProductColors());
				model.addAttribute("prodcategories", prodcat_service.getAllProductCategories());
				model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
				model.addAttribute("productTypes", typeservice.getAllProductTypes());
				model.addAttribute("productUnitss", produnitservice.getAllProductUnit());
				model.addAttribute("productpackagings", prodpackaginservice.getAllProductPackages());
				model.addAttribute("productBrands", brandservice.getAllProductBrand());
				model.addAttribute("organisations", orgservice.getAllOrganisation());
				return "html/products/products-view";
	}
	
	@GetMapping("/UpdateProduct")
	public String UpdateOrganisation(Model model, @RequestParam("id") Long id) {
			    ProductAdd productAdd = repository.findById(id).get();
				model.addAttribute(productAdd);
				model.addAttribute("productAdds",service.getAllProducts());
				model.addAttribute("productColors", prodcolorservice.getAllProductColors());
				model.addAttribute("prodcategories", prodcat_service.getAllProductCategories());
				model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
				model.addAttribute("productTypes", typeservice.getAllProductTypes());
				model.addAttribute("productUnitss", produnitservice.getAllProductUnit());
				model.addAttribute("productpackagings", prodpackaginservice.getAllProductPackages());
				model.addAttribute("productBrands", brandservice.getAllProductBrand());
				model.addAttribute("organisations", orgservice.getAllOrganisation());
				return "html/products/products-Add";
	}
	
	
	@GetMapping("/deleteProd")
	public String deleteProdCat(@RequestParam("id") Long id) {
			service.deleteProd(id);
			return "redirect:showproductManage";
	}
	@PostMapping("/addProduct")
	public String addProdCat(Model model,@Valid @ModelAttribute ProductAdd productAdd,BindingResult result,@RequestParam("image") MultipartFile[] files,@RequestParam("doucumentName") String[] docName) throws Exception {
			if(result.hasErrors()) {	
				model.addAttribute("productAdds",service.getAllProducts());
				model.addAttribute("prodcategories", prodcat_service.getAllProductCategories());
				return "html/products/products-Add";
			}else {
				 // Save the Productdoc object to the persistence
				 LocalDateTime currentDateTime = LocalDateTime.now();
			     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			     DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
			     String formattedDate = currentDateTime.format(formatter);
			     String formatedTime = currentDateTime.format(timeFormatter);
			     System.out.println("Current date: " + formattedDate);
			     System.out.println("Current date: " + formatedTime);
				
				
			    service.saveProduct(productAdd);
				
				 // Saving the uploaded images to the persistence
				int i = 0;
				if(files.length > 0) {
					for (MultipartFile file : files) {	
						if(!file.isEmpty()) {
				        	ProductImages productImage = new ProductImages();
					        
				        	productImage.setDocName(docName[i]);
				        	
					        productImage.setDocType(file.getContentType());
					        productImage.setDoc(file.getBytes());
					        i++;
					        productImage.setProductdoc(productAdd);
					        prodImagerepository.save(productImage);
				        }
				    	
				    }
				}
				
				
			return "redirect:showproductsAdd?message=Product added successfully !!!!";
			}
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
