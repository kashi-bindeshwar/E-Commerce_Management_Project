package com.bindeshwar.bindeshwarmart.ecommerce.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.beans.ProductImages;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishList;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishListItems;
import com.bindeshwar.bindeshwarmart.ecommerce.service.WishListItemService;
import com.bindeshwar.bindeshwarmart.service.ProductAddService;
import com.bindeshwar.bindeshwarmart.service.ProductsImageService;

@Controller
@RequestMapping("/wishList")
public class WishListController {
	@Autowired
	WishListItemService wishListItemService;

	@Autowired
	ProductAddService productService;
	
	@Autowired
	ProductsImageService prodImgService;
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<?> addItemToCart(@RequestParam("productId") Long productId, HttpSession session) {
	    // Getting session attribute
	    Long userWishListNo = (Long) session.getAttribute("userWishList");

	    // Check if userWishListNo is null
	    if (userWishListNo == null) {
	        return ResponseEntity.badRequest().body("No active wishlist found for the user.");
	    }

	    System.out.println("userWishListNo : " + userWishListNo);

	    // Setting product Id
	    ProductAdd product = new ProductAdd();
	    product.setId(productId);

	    WishList wishList = new WishList();
	    wishList.setId(userWishListNo);

	    WishListItems wishListItems = new WishListItems();
	    wishListItems.setWishList(wishList);
	    wishListItems.setProduct(product);

	    try {
//	    	List<WishListItems> totalItems = wishListItemService.getTotalItems(wishList);
	    	List<WishListItems> totalItems = wishListItemService.findByWishListIdAndProduct(wishList , product);
	    	int itemCount = totalItems.size();
	    	
			if (itemCount < 1) {
				wishListItemService.addItem(wishListItems);
			}
	    	
	        return ResponseEntity.ok(itemCount);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding item to wishlist.");
	    }
	}

	
	
	@GetMapping("/updateWishListSize")
	@ResponseBody
	public ResponseEntity<Integer> updateCartSize(HttpSession session) {
		// Getting session
		Long userWishListNo = (Long) session.getAttribute("userWishList");
		int itemCount = 0;
		
		if (userWishListNo!=null && userWishListNo > 0) {
			WishList wishList = new WishList();
			wishList.setId(userWishListNo);
			
			List<WishListItems> totalItems = wishListItemService.getTotalItems(wishList);
			itemCount = totalItems.size();
		}
		return ResponseEntity.ok(itemCount);
	}
	
	@GetMapping("/remove")
	public String removeItemFromCart(@RequestParam("id") Long ItemId) {
		wishListItemService.deleteItem(ItemId);
		return "redirect:/ecom/viewWishList";
	}
	
	
	//image displaying my image controller
	@GetMapping("/prodimage3/display/{id}")
	@ResponseBody
	void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ProductImages> productImages)
			throws ServletException, IOException {
		log.info("Id :: " + id);
		productImages = prodImgService.GetProductImageByProductIdAndDocName(id, "todisplay");
		
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(productImages.get().getDoc());
		response.getOutputStream().close();
	}	
	
}
