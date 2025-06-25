package com.bindeshwar.bindeshwarmart.application.config;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bindeshwar.bindeshwarmart.beans.Users;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("users") // Add this annotation
	public Users getSessionUser(HttpSession session) {
		Users user = (Users) session.getAttribute("users");
		return (user != null) ? user : new Users();
	}
}
