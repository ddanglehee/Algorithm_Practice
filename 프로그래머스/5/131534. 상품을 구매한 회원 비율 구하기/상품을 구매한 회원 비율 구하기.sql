with count_2021 as (
    SELECT count(*) as total
    from user_info
    where year(joined) = 2021
)

select year(os.sales_date) as YEAR, month(os.sales_date) as MONTH, count(distinct ui.user_id) as PURCHASED_USERS, round(count(distinct ui.user_id) / total, 1) as PURCHASED_RATIO
from user_info as ui join online_sale as os on ui.user_id = os.user_id, count_2021
where year(ui.joined) = 2021
group by year(os.sales_date), month(os.sales_date)
order by YEAR, MONTH


