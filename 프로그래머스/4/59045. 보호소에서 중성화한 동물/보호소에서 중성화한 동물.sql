select i.animal_id as ANIMAL_ID, i.animal_type as ANIMAL_TYPE, i.name as NAME
from animal_ins as i join animal_outs as o on i.animal_id = o.animal_id
where i.sex_upon_intake like 'Intact%' and (o.sex_upon_outcome like 'Spayed%' or o.sex_upon_outcome like 'Neutered%')
order by ANIMAL_ID