package com.hackathon.guessprice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hackathon.guessprice.model.ProductDto;
import com.hackathon.guessprice.model.UserDto;
import com.hackathon.guessprice.service.PriceSetupService;
import com.hackathon.guessprice.service.ProductService;

@Controller
//@RequestMapping("/getGuessPriceFormData")
@SessionAttributes("currUser")
public class GuessPriceFromController {
	
	@Autowired
	private PriceSetupService priceSetupService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/getGuessPriceFormData/{productId}",method = RequestMethod.GET)
	public String getGuessPriceFormData(@ModelAttribute("currUser") UserDto userDto,@PathVariable String productId, ModelMap model) {
//		model.addAttribute("message", "Spring 3 MVC Hello World");
		if(userDto!=null){
			int userId = userDto.getUserId();
			int productID = Integer.valueOf(productId);
			System.out.println("userId = "+userId+", productId = "+productID);
			boolean isGuessed = priceSetupService.hasGuessPriceForProduct(userId,productId);
			if(isGuessed){
				return "sayhello";
			}
			ProductDto productDto = productService.findProductById(productId);
			model.addAttribute("productDto",productDto);
		} 
		return "guessPriceForm";
	}
}
