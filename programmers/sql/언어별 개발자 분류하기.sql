with new_skillcodes as (
    select A.NAME, B.CATEGORY, (A.CODE + B.CODE) as CODE
    from (select * from skillcodes where name = 'Python') A
             cross join (select * from skillcodes where category = 'Front End') B
    union all (select * from skillcodes where name = 'C#' or category = 'Front End')
),
     grades as (
         select case when B.name = 'Python' and B.category = 'Front End' then 'A'
                     when B.name = 'C#' then 'B'
                     when B.category = 'Front End' then 'C' end as GRADE, A.ID, A.EMAIL
         from developers A join new_skillcodes B on (A.skill_code & B.code) = B.code
     )

select GRADE, ID, EMAIL
from (
         select *, ROW_NUMBER() over(partition by id order by grade) as rn
         from grades
         where GRADE is not null
     ) t
where rn = 1
order by GRADE, ID