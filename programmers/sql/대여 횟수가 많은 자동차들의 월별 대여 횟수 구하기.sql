select MONTH(START_DATE) as MONTH, CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where CAR_ID in (
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where START_DATE between '2022-08-01' and '2022-10-31'
    group by CAR_ID
    having COUNT(HISTORY_ID) >= 5
    ) and START_DATE between '2022-08-01' and '2022-10-31'
group by MONTH, CAR_ID
order by MONTH , CAR_ID desc