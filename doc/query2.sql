SELECT * FROM pricesetup;

#SELECT productId,count(*) FROM pricesetup ps group by ps.productId;

#SELECT productId,COUNT(*) FROM pricesetup PS  GROUP BY PS.productId ORDER BY PS.setPrice;

#SELECT userId,COUNT(*) FROM pricesetup ps GROUP BY ps.userId;

/*
SELECT A.COUNT,P.productId,P.productName,P.productLine FROM(
	SELECT productId,COUNT(*) AS COUNT
	FROM pricesetup PS 
	GROUP BY PS.productId 
) A
INNER JOIN PRODUCT P ON A.productId = P.productId
LIMIT 0,3;
*/

/*
SELECT SUM(PSCOUNT.UCOUNT) 
FROM PRODUCT P		
LEFT JOIN (			
		SELECT PRODUCTID,COUNT(1) AS UCOUNT 
		FROM PRICESETUP PS 
		GROUP BY productId		
) PSCOUNT ON P.PRODUCTID = PSCOUNT.PRODUCTID;
*/
#GROUP BY P.productLine 

#SELECT * FROM PRODUCT;

#SELECT * FROM PRODUCT GROUP BY productLine;

#SELECT * FROM USER U;

#SELECT * FROM USER U ORDER BY U.userId;

#SELECT region,COUNT(*) FROM USER GROUP BY region;



