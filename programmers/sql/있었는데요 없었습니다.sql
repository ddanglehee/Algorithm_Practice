select i.ANIMAL_ID, i.NAME
from ANIMAL_INS as i join ANIMAL_OUTS as o using(ANIMAL_ID)
where i.DATETIME > o.DATETIME
order by i.DATETIME