select CAR_TYPE, count(CAR_TYPE) AS CARS
from CAR_RENTAL_COMPANY_CAR
where OPTIONS regexp '(열선시트|통풍시트|가죽시트)'
group by CAR_TYPE
order by CAR_TYPE