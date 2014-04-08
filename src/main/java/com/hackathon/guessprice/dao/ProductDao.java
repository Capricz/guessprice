package com.hackathon.guessprice.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hackathon.guessprice.entity.Product;

@Repository
public class ProductDao extends BaseDao {
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getProductLinePercent() {
		String sql = "	SELECT PD.productLine,COUNT(PS.productId) FROM product PD	"	+
					 "	LEFT JOIN pricesetup PS ON PD.productId = PS.productId	"	+
					 "	GROUP BY PD.productLine	";
		Query query = this.getEntityManager().createNativeQuery(sql);
		return query.getResultList();
	}

	public int getProductCount() {
		String sql = "	SELECT SUM(A.PCOUNT) " +
					  "	FROM (	"	+
					  "		SELECT COUNT(PS.productId) AS PCOUNT FROM product PD	"	+
					  "		LEFT JOIN pricesetup PS ON PD.productId = PS.productId	"	+
					  "		GROUP BY PD.productLine	"	+
					  "	) A";
		Query query = this.getEntityManager().createNativeQuery(sql);
		return ((BigDecimal)query.getResultList().get(0)).intValue();
	}

	public Product findProductById(int productId) {
		return this.getEntityManager().find(Product.class, productId);
	}
}
