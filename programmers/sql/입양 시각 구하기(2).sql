with recursive HOUR_CTE as (
    select 0 as HOUR
union all
select HOUR + 1
from HOUR_CTE
where HOUR < 23
    )

select B.HOUR as HOUR, COUNT(DISTINCT(ANIMAL_ID)) as COUNT
from ANIMAL_OUTS A right outer join HOUR_CTE B on HOUR(A.DATETIME) = B.HOUR
group by B.HOUR
order by HOUR