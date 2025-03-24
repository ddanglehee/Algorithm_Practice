select distinct D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
from SKILLCODES as S, DEVELOPERS as D
where S.CODE & D.SKILL_CODE in (select CODE from SKILLCODES where NAME = 'Python' or NAME = 'C#')
order by D.ID