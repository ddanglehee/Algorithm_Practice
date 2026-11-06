select round(avg(a.len), 2) as AVERAGE_LENGTH
from (select ifnull(length, 10) as len from fish_info) as a

