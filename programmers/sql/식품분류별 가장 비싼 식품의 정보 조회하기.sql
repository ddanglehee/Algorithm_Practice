select A.CATEGORY, PRICE as MAX_PRICE, PRODUCT_NAME
from FOOD_PRODUCT as A join (
    select CATEGORY, max(PRICE) as MAX_PRICE
    from FOOD_PRODUCT
    where CATEGORY in ('과자', '국', '김치', '식용유')
    group by CATEGORY
) as B on A.CATEGORY = B.CATEGORY and A.PRICE = B.MAX_PRICE
order by MAX_PRICE desc