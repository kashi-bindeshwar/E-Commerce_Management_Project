package com.bindeshwar.bindeshwarmart.ecommerce.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bindeshwar.bindeshwarmart.beans.Organisation;
import com.bindeshwar.bindeshwarmart.beans.PayementMode;
import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.beans.ProductImages;
import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.CartItems;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.OrderItems;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.Orders;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.ShoppingCart;
import com.bindeshwar.bindeshwarmart.ecommerce.repository.OrdersRepository;
import com.bindeshwar.bindeshwarmart.ecommerce.service.CartItemService;
import com.bindeshwar.bindeshwarmart.ecommerce.service.OrderItemService;
import com.bindeshwar.bindeshwarmart.ecommerce.service.OrderService;
import com.bindeshwar.bindeshwarmart.repository.UsersRepository;
import com.bindeshwar.bindeshwarmart.service.OrganisationService;
import com.bindeshwar.bindeshwarmart.service.PayementModeService;
import com.bindeshwar.bindeshwarmart.service.ProductAddService;
import com.bindeshwar.bindeshwarmart.service.ProductSubCategoryService;
import com.bindeshwar.bindeshwarmart.service.ProductsImageService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Value("${razorpay.key_id}")
	private String razorpayKey;

	@Value("${razorpay.key_secret}")
	private String razorpaySecret;

	@Autowired
	OrderService orderService;

	@Autowired
	OrganisationService orgservice;

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	CartItemService cartItemService;
	@Autowired
	ProductAddService prodservice;
	@Autowired
	ProductSubCategoryService subservice;

	@Autowired
	ProductsImageService prodImgService;

	@Autowired
	OrderItemService orderItemService;

	@Autowired
	OrganisationService service;

	@Autowired
	UsersRepository usersRepository;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	// today's date
	LocalDate today = LocalDate.now();
	DateTimeFormatter formater = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	String formattedDate = today.format(formater);

	@GetMapping("/showOrderPage")
	public String showFormOrganisation(Model model) {
		model.addAttribute("orders", new Orders());
		model.addAttribute("orderList", ordersRepository.findAllOrders());
		model.addAttribute("organisations", service.getAllOrganisation());
		return "/html/order/order";
	}

	@GetMapping("/viewOrder")
	public String viewproduct(Model model, @RequestParam("id") Long id) {

		Orders orders = ordersRepository.findById(id).get();
		model.addAttribute(orders);
		model.addAttribute("organisations", orgservice.getAllOrganisation());
		// Add Order Item
		List<OrderItems> orderItem = orders.getOrders();

		for (OrderItems item : orderItem) {

			item.getQuantity();
		}

		model.addAttribute("orderItem", orderItem);
		return "/html/order/order-view";

	}

	@GetMapping("/deliver")
	public String orderDelivery(Model model, @RequestParam("id") Long id) {
		String status = "delivered";
		orderService.UpdateOrderStatus(id, status);
		return "redirect:showOrderPage";
	}

	@Autowired
	private PayementModeService payementModeService;

	@PostMapping("/placeOder")
	public String addOrder(@Valid @ModelAttribute Orders orders, RedirectAttributes redirectAttributes,
			BindingResult result, Model model, HttpSession session) throws Exception {
		// Getting user
		Long UserId = (Long) session.getAttribute("userid");
		Users users = new Users();
		users.setId(UserId);

		// calculating order total
		Long userCartNo = (Long) session.getAttribute("usercart");
		int totalcart = 0;
		int itemCount = 0;
		if (userCartNo > 0) {
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setId(userCartNo);
			List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
			itemCount = totalItems.size();

			// Iterate over totalItems and print each item
			for (CartItems item : totalItems) {

//	            totalcart = totalcart + Integer.parseInt(item.getProduct().getCostPer()) * item.getQuantity()   ;
				totalcart = totalcart + (int) (Double.parseDouble(item.getProduct().getCostPer()) * item.getQuantity());
			}

		}

		if (itemCount == 0) {
			redirectAttributes.addFlashAttribute("message", "Your order is empty !!");
			return "redirect:/ecom/checkout";
		} else {
			if (result.hasErrors()) {
				model.addAttribute("productAdds", prodservice.getAllProducts());
				model.addAttribute("productSubCategorys", subservice.getAllProductCategories());
				return "redirect:/ecom/checkout";
			} else {

				orders.setUser(users);
				orders.setTotal(totalcart);
				orders.setStatus("received");
				orders.setDate(LocalDate.now());
				orders.setCurrency("Rs");


				// saving order
				orderService.placeOrder(orders);

				// loop for place Order Items
				ShoppingCart shoppingCart = new ShoppingCart();
				shoppingCart.setId(userCartNo);
				List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
				for (CartItems item : totalItems) {

					ProductAdd product = new ProductAdd();
					product.setId(item.getProduct().getId());

					OrderItems orderItems = new OrderItems();
					orderItems.setOrders(orders);
					orderItems.setProduct(product);
					orderItems.setQuantity(item.getQuantity());
					orderItemService.addItem(orderItems);
				}

				// loop for deleting User Shopping Cart Items
				for (CartItems item : totalItems) {
					cartItemService.deleteItem(item.getId());
				}
				redirectAttributes.addFlashAttribute("orderplacedmessage", "Your order is placed Successfully !!");
				return "redirect:/ecom/checkout";
			}
		}
	}

	// image displaying my image controller
	@GetMapping("/prodimage2/display/{id}")
	@ResponseBody
	void showImage2(@PathVariable("id") Long id, HttpServletResponse response, Optional<ProductImages> productImages)
			throws ServletException, IOException {
		log.info("Id :: " + id);
//			productImages = prodImgService.GetProductImageByProductIdAndDocName(id, "todisplay");
		productImages = prodImgService.getFirstProductImageByProductId(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(productImages.get().getDoc());
		response.getOutputStream().close();
	}

	// image displaying my image controller organisation
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

	@PostMapping("/createRazorpayOrder")
	@ResponseBody
	public Map<String, Object> createRazorpayOrder(@RequestBody Map<String, Object> request, HttpSession session) {
		Map<String, Object> response = new HashMap<>();
		try {
			int amountInRupees  = Integer.parseInt(request.get("amount").toString());
	        int amountInPaise = amountInRupees * 100; // Convert INR to Paise


			RazorpayClient razorpayClient = new RazorpayClient(razorpayKey, razorpaySecret);

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", amountInPaise );
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "txn_123456");

			Order order = razorpayClient.Orders.create(orderRequest);

			response.put("id", order.get("id"));
			response.put("amount", order.get("amount"));
			response.put("currency", order.get("currency"));

			Long userId = (Long) session.getAttribute("userid");
			Long userCartNo = (Long) session.getAttribute("usercart");

			Users user = new Users();
			user.setId(userId);

			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setId(userCartNo);

			// Calculate total from cart
			List<CartItems> totalItems = cartItemService.getTotalItems(shoppingCart);
			int totalAmount = 0;
			for (CartItems item : totalItems) {
				totalAmount += (int) (Double.parseDouble(item.getProduct().getCostPer()) * item.getQuantity());
			}

			Orders order1 = new Orders();
			order1.setUser(user);
			order1.setTotal(totalAmount);
			order1.setDate(LocalDate.now());
			order1.setStatus("received");
			order1.setCurrency("Rs");

			// Set Address Fields from Request
			order1.setAddress(request.get("address").toString());
			order1.setCity(request.get("city").toString());
			order1.setState(request.get("state").toString());
			order1.setCountry(request.get("country").toString());
			order1.setPincode(request.get("pincode").toString());
			order1.setContact(request.get("contact").toString());

			order1.setOrderReferenceId(order.get("id").toString());

			Long paymentModeId = Long.parseLong(request.get("paymentModeId").toString());
			PayementMode paymentMode = payementModeService.findById(paymentModeId);
			order1.setPaymentMode(paymentMode);
			order1.setPaymentGateway("RAZORPAY");


			orderService.placeOrder(order1);

			for (CartItems item : totalItems) {
				OrderItems orderItem = new OrderItems();
				orderItem.setOrders(order1);
				orderItem.setProduct(item.getProduct());
				orderItem.setQuantity(item.getQuantity());
				orderItemService.addItem(orderItem);
			}

			for (CartItems item : totalItems) {
				cartItemService.deleteItem(item.getId());
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "Failed to create order");
		}
		return response;
	}

	@GetMapping("/paymentSuccess")
	public String paymentSuccess(@RequestParam("razorpayPaymentId") String paymentId,
			@RequestParam("razorpayOrderId") String orderId, Model model) {
		model.addAttribute("paymentId", paymentId);
		model.addAttribute("orderId", orderId);
		return "/html/order/payment-success"; // This should be your Thymeleaf HTML page
	}
}
