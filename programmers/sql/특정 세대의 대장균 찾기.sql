with recursive CTE as (
    select ID, PARENT_ID, 1 as GRADE
    from ECOLI_DATA
    where PARENT_ID is NULL
    union all
    select E.ID, E.PARENT_ID, C.GRADE + 1 as GRADE
    from CTE as C join ECOLI_DATA as E on C.ID = E.PARENT_ID
)

select ID
from CTE
where GRADE = 3
order by ID