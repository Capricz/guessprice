package com.hackathon.guessprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hackathon.guessprice.model.HotProductItem;
import com.hackathon.guessprice.model.ProductPriceRangeItem;
import com.hackathon.guessprice.model.SetPriceForm;
import com.hackathon.guessprice.model.UserDto;
import com.hackathon.guessprice.service.PriceSetupService;

@Controller
//@RequestMapping("/pricesetup")
@SessionAttributes("currUser")
public class PriceSetupController {

	@Autowired
	private PriceSetupService priceSetupService;
	
	@RequestMapping(value="/pricesetup/{productId}",method=RequestMethod.GET)
	@ResponseBody
	public List<ProductPriceRangeItem> getPriceRangePercentByProduct(@PathVariable String productId){
		return priceSetupService.getPriceRangePercentByProduct(Integer.valueOf(productId));
	}
	
	@RequestMapping(value="/hotProducts",method=RequestMethod.GET)
	@ResponseBody
	public List<HotProductItem> getHotProducts(){
		return priceSetupService.getHotProducts();
	}
	
	@RequestMapping("/setPrice")
//	@ResponseBody
	public String setPrice(@RequestBody SetPriceForm setPriceForm,@ModelAttribute("currUser") UserDto userDto){
//		return productService.setPrice(dto);
		if(userDto != null && userDto.getUserId() != 0){
			int userId = userDto.getUserId();
			int productId = setPriceForm.getProductId();
			double price = setPriceForm.getPrice();
			priceSetupService.setPrice(userId,productId,price);
		} 
		return "thanks";
	}
}
