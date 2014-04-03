package com.hackathon.guessprice.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.guessprice.common.Utils;
import com.hackathon.guessprice.dao.ProductDao;
import com.hackathon.guessprice.model.ProductLineItem;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao productDao;
	
	public List<ProductLineItem> getProductLineCountPercent(){
		List<ProductLineItem> result = new ArrayList<>();
		int count = productDao.getProductCount();
		if(count>0){
			List<Object[]> productLinePercentList = productDao.getProductLinePercent();
			if(productLinePercentList!=null && !productLinePercentList.isEmpty()){
				for (Object[] productLine : productLinePercentList) {
					ProductLineItem item = new ProductLineItem();
					String prodLine = (String) productLine[0];
					BigInteger plCount = (BigInteger) productLine[1];
					Double percent = Utils.calculatePercent(plCount, count) * 100;
					item.setProductLine(prodLine);
					item.setPercent(percent);
					result.add(item);
				}
			}
		}
		return result;
	}
}
