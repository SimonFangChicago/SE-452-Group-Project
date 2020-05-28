package com.se452.group5.MediHelp.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.se452.group5.MediHelp.entity.AppUser;
import com.se452.group5.MediHelp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class RegisterController {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private UserService userService;
 
    @Autowired
    private org.springframework.jdbc.core.JdbcTemplate jdbcTemplate;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserService userService) {
      
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
      this.userService = userService;
    }

	// Return registration form template
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, AppUser user){
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid AppUser user, BindingResult bindingResult, HttpServletRequest request) {
				
		// Lookup user in database by e-mail
		AppUser userExists = userService.findByUsername(user.getEncrytedPassword());
		
		System.out.println(userExists);
		
		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}
			
		if (bindingResult.hasErrors()) { 
			modelAndView.setViewName("register");		
		} else { 
		      
            user.setEnabled(true);
            user.setEncrytedPassword(bCryptPasswordEncoder.encode(user.getEncrytedPassword()));

            userService.saveUser(user);
            //jdbcTemplate.update("INSERT INTO App_User(user_name, ENCRYTED_PASSWORD,ENABLED) VALUES (?,?,?)","test",
            //bCryptPasswordEncoder.encode("123"),1);
				
			modelAndView.setViewName("login");
		}
			
		return modelAndView;
	}
}