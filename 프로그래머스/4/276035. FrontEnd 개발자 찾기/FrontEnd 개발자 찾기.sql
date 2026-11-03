select distinct d.id as ID, d.email as EMAIL, d.first_name as FIRST_NAME, d.last_name as LAST_NAME
from (
    select code
    from skillcodes
    where category = 'Front End'
) as fs join developers as d on fs.code & d.skill_code >= 1
order by ID