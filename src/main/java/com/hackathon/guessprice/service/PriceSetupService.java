package com.hackathon.guessprice.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.guessprice.dao.PriceSetupDao;
import com.hackathon.guessprice.dao.ProductDao;
import com.hackathon.guessprice.dao.UserDao;
import com.hackathon.guessprice.entity.Pricesetup;
import com.hackathon.guessprice.entity.Product;
import com.hackathon.guessprice.entity.User;
import com.hackathon.guessprice.model.HotProductItem;
import com.hackathon.guessprice.model.MessageDto;
import com.hackathon.guessprice.model.ProductPriceRangeItem;
import com.hackathon.guessprice.model.SetPriceForm;

@Service
public class PriceSetupService {
	
	@Autowired
	private PriceSetupDao priceSetupDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductDao productDao;
	
	public List<ProductPriceRangeItem> getPriceRangePercentByProduct(int productId){
		List<ProductPriceRangeItem> result = new ArrayList<>();
		List<Object[]> list = priceSetupDao.getPriceRangePercentByProduct(productId);
		String startRange,endRange = "";
		double percent = 0.0;
		if(list!=null && !list.isEmpty()){
			Object[] oArr = list.get(0);
			for(int i=0;i<oArr.length;i++){
				ProductPriceRangeItem item = new ProductPriceRangeItem();
				percent = ((BigDecimal) oArr[i]).doubleValue();
				
				item.setStartRange(i+6);
				item.setEndRange(i+7);
				item.setPercent(percent);
				result.add(item);
			}
		}
		return result;
	}
	
	public List<HotProductItem> getHotProducts(){
		List<HotProductItem> result = new ArrayList<>();
		List<Object[]> hotProducts = priceSetupDao.getHotProducts();
		if(hotProducts != null && !hotProducts.isEmpty()){
			for (Object[] p : hotProducts) {
				BigInteger count = (BigInteger) p[0];
				int productId = (int) p[1];
				String productName = (String) p[2];
				String productLine = (String) p[3];
				
				HotProductItem item = new HotProductItem();
				item.setCount(count);
				item.setProductId(productId);
				item.setProductLine(productLine);
				item.setProductName(productName);
				result.add(item);
			}
		}
		return result;
	}
	
	/*public MessageDto setPrice(SetPriceForm dto) {
		int userId = dto.getUserId();
		int productId = dto.getProductId();
		double price = dto.getPrice();
		
		
		
		
		MessageDto msgDto = new MessageDto();
		msgDto.setMsg("set price successfully");
		msgDto.setSuccess(true);
		
		return msgDto;
	}*/

	@Transactional
	public void setPrice(int userId, int productId, double price) {
		User u = userDao.finUserById(userId);
		Product p = productDao.findProductById(productId);
		Pricesetup ps = new Pricesetup();
		ps.setProduct(p);
		ps.setSetPrice(BigDecimal.valueOf(price));
		ps.setUser(u);
		
		priceSetupDao.save(ps);
	}
}
