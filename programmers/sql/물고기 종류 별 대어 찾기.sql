select I.ID, N.FISH_NAME, I.LENGTH
from FISH_INFO as I,
     (select max(LENGTH) as LENGTH, FISH_TYPE
      from FISH_INFO
      group by FISH_TYPE) as M
         join FISH_NAME_INFO as N on M.FISH_TYPE = N.FISH_TYPE
where I.FISH_TYPE = M.FISH_TYPE and I.LENGTH = M.LENGTH
order by I.ID