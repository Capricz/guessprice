package com.hackathon.guessprice.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class PriceSetupDao extends BaseDao {
	
	public List<Object[]> getPriceRangePercentByProduct(int productId){
		String sql = " SELECT ROUND(SUM(CASE WHEN a.setPrice > b.marketPrice * 0.7  and  a.setPrice <= b.marketPrice * 0.8 THEN 1 ELSE 0 END)/COUNT(1) * 100 , 2)  as '7-8 discount',"+
					 "	ROUND(SUM(CASE WHEN a.setPrice > b.marketPrice * 0.8  and  a.setPrice <= b.marketPrice * 0.9 THEN 1 ELSE 0 END)/COUNT(1) * 100 , 2)  as '8-9 discount', " +
					 "	ROUND(SUM(CASE WHEN a.setPrice > b.marketPrice * 0.9  and  a.setPrice <= b.marketPrice THEN 1 ELSE 0 END)/COUNT(1) * 100 , 2)  as '9-10 discount',	"	+
					 "	ROUND(SUM(CASE WHEN a.setPrice > b.marketPrice and  a.setPrice <= b.marketPrice * 1.1 THEN 1 ELSE 0 END)/COUNT(1) * 100 , 2)  as '10-11 discount',	"	+
					 "	ROUND(SUM(CASE WHEN a.setPrice > b.marketPrice * 1.1  and  a.setPrice <= b.marketPrice * 1.2 THEN 1 ELSE 0 END)/COUNT(1) * 100 , 2)  as '11-12 discount',	"	+
					 "	ROUND(SUM(CASE WHEN a.setPrice > b.marketPrice * 1.2  and  a.setPrice <= b.marketPrice * 1.3 THEN 1 ELSE 0 END)/COUNT(1) * 100 , 2)  as '12-13 discount'	"	+
					 "	from pricesetup a	"	+
					 "	inner join product b on a.productId = b.productId	"	+
					 "	where a.productId = :productId GROUP BY a.productId";
		Query query = this.getEntityManager().createNativeQuery(sql);
		query.setParameter("productId", productId);
		return query.getResultList();
	}
	
	public List<Object[]> getHotProducts(){
		String sql = " SELECT A.COUNT,P.productId,P.productName,P.productLine FROM(	"	+
					 "	SELECT productId,COUNT(*) AS COUNT	"	+
					 "	FROM pricesetup PS	"	+ 	
					 "	GROUP BY PS.productId	"	+ 
					 "	) A	"	+
					 "	INNER JOIN PRODUCT P ON A.productId = P.productId	"	+
					 "	LIMIT 0,3 ";
		return this.getEntityManager().createNativeQuery(sql).getResultList();
	}
}
