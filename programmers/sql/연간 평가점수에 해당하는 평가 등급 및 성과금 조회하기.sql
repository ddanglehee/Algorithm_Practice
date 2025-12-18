with NEW_GRADE as (
    select EMP_NO, SUM(SCORE) / 2 as AVG_SCORE
    from HR_GRADE
    group by EMP_NO
)

select B.EMP_NO, B.EMP_NAME, case when A.AVG_SCORE >= 96 then 'S'
                                  when A.AVG_SCORE >= 90 then 'A'
                                  when A.AVG_SCORE >= 80 then 'B'
                                  else 'C' end as GRADE,
       case when A.AVG_SCORE >= 96 then B.SAL * 0.2
            when A.AVG_SCORE >= 90 then B.SAL * 0.15
            when A.AVG_SCORE >= 80 then B.SAL * 0.1
            else 0 end as BONUS
from NEW_GRADE as A join HR_EMPLOYEES as B on A.EMP_NO = B.EMP_NO
order by B.EMP_NO