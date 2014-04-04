package com.hackathon.guessprice.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hackathon.guessprice.model.MessageDto;
import com.hackathon.guessprice.model.ProductLineItem;
import com.hackathon.guessprice.model.SetPriceDto;
import com.hackathon.guessprice.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/productLineCountPercent")
	@ResponseBody
	public List<ProductLineItem> getProductLineCountPercent(){
		return productService.getProductLineCountPercent();
	}
	
	@RequestMapping("/setPrice")
	@ResponseBody
	public MessageDto setPrice(@RequestBody SetPriceDto dto){
		return productService.setPrice(dto);
	}
}
