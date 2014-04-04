package com.hackathon.guessprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.guessprice.model.ProductPriceRangeItem;
import com.hackathon.guessprice.service.PriceSetupService;

@Controller
@RequestMapping("/pricesetup")
public class PriceSetupController {

	@Autowired
	private PriceSetupService priceSetupService;
	
	@RequestMapping(value="/{productId}",method=RequestMethod.GET)
	@ResponseBody
	public List<ProductPriceRangeItem> getPriceRangePercentByProduct(@PathVariable String productId){
		return priceSetupService.getPriceRangePercentByProduct(Integer.valueOf(productId));
	}
}
