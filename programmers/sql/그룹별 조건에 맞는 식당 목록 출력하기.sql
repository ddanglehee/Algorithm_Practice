with CTE as (
    select member_id, count(*) as count
from rest_review
group by member_id
order by count desc
    )

select A.member_name, B.review_text, DATE_FORMAT(B.review_date, '%Y-%m-%d')
from member_profile A join (
    select R.member_id, R.review_text, R.review_date
    from CTE W join rest_review R on W.member_id = R.member_id
    where W.count = (
        select count
        from CTE
        order by count desc
        limit 1
)
    ) B on A.member_id = B.member_id
order by B.review_date, B.review_text
