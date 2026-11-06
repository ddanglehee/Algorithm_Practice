select count(*) as fish_count, ni.fish_name
from fish_info as i join fish_name_info as ni on i.fish_type = ni.fish_type
group by ni.fish_type
order by fish_count desc