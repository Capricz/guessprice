package com.hackathon.guessprice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.guessprice.dao.PriceSetupDao;
import com.hackathon.guessprice.model.ProductPriceRangeItem;

@Service
public class PriceSetupService {
	
	@Autowired
	private PriceSetupDao priceSetupDao;
	
	public List<ProductPriceRangeItem> getPriceRangePercentByProduct(int productId){
		List<ProductPriceRangeItem> result = new ArrayList<>();
		List<Object[]> list = priceSetupDao.getPriceRangePercentByProduct(productId);
		String startRange,endRange = "";
		double percent = 0.0;
		if(list!=null && !list.isEmpty()){
			Object[] oArr = list.get(0);
			for(int i=0;i<oArr.length;i++){
				ProductPriceRangeItem item = new ProductPriceRangeItem();
				startRange = "range"+ String.valueOf((((BigDecimal)oArr[i]).intValue()+6));
				endRange = "range"+ String.valueOf((((BigDecimal)oArr[i]).intValue()+7));
				percent = ((BigDecimal) oArr[i]).doubleValue();
				
				item.setStartRange(startRange);
				item.setEndRange(endRange);
				item.setPercent(percent);
				result.add(item);
			}
		}
		return result;
	}
}
