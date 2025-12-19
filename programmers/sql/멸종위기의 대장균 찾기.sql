with recursive GEN_INFO as (
    select ID, PARENT_ID, 1 as GENERATION
    from ECOLI_DATA
    where PARENT_ID is NULL

    union all

    select E.ID, E.PARENT_ID, GENERATION + 1
    from GEN_INFO G join ECOLI_DATA E on G.ID = E.PARENT_ID
)

select count(ID) as COUNT, GENERATION
from GEN_INFO
where ID not in (
    select distinct PARENT_ID
    from GEN_INFO
    where PARENT_ID is not null
    )
group by GENERATION
order by GENERATION
