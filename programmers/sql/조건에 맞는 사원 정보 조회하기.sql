select B.SCORE, A.EMP_NO, A.EMP_NAME, A.POSITION, A.EMAIL
from HR_EMPLOYEES AS A
 join (select A.EMP_NO, (A.SCORE1 + B.SCORE2) AS SCORE
       from (select EMP_NO, SCORE AS SCORE1
             from HR_GRADE
             where YEAR = '2022' and HALF_YEAR = '1') AS A
                join (
                    select EMP_NO, SCORE AS SCORE2
                    from HR_GRADE
                    where YEAR = '2022' and HALF_YEAR = '2' ) AS B ON A.EMP_NO = B.EMP_NO
                    order by SCORE desc
                    limit 1
                ) AS B ON A.EMP_NO = B.EMP_NO

-- 윈도우 함수 사용
select sum(score) over(partition by A.EMP_NO) as SCORE, A.EMP_NO, EMP_NAME, POSITION, EMAIL
from HR_EMPLOYEES as A join HR_GRADE as B on A.EMP_NO = B.EMP_NO
order by SCORE desc
    limit 1