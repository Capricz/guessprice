package com.hackathon.guessprice.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDao extends BaseDao {
	
	public List<Object[]> getProductLinePercent() {
		String sql = "	SELECT P.productLine,PSCOUNT.UCOUNT FROM PRODUCT P	"
					+ "	LEFT JOIN (	"
					+ "		SELECT PRODUCTID,COUNT(1) AS UCOUNT FROM PRICESETUP PS GROUP BY productId	"
					+ "	) PSCOUNT ON P.PRODUCTID = PSCOUNT.PRODUCTID	"
					+ "	GROUP BY P.productLine ";
		Query query = this.getEntityManager().createNativeQuery(sql);
		return query.getResultList();
	}

	public int getProductCount() {
		String jpql = " select count(*) from Product ";
		return queryCount(jpql);
	}
}
