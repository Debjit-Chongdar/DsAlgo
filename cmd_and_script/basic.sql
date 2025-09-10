-- row_number()  input={1,2,2,3} output => {1,2,3,4}    for same value {2,2} but different row number {2,3}
-- rank()        input={1,2,2,3} output => {1,2,2,4}    for same value {2,2} skip next rank {3}
-- dense_rank()  input={1,2,2,3} output => {1,2,2,3}    for same value {2,2} same rank, then next

-- top 2 amount from each region
WITH cte AS (SELECT region, row_number() OVER (PARTITION BY region ORDER BY amount DESC) AS rn)
SELECT region FROM cte WHERE rn < 3;

-- top 2 total amount belongs to which region
WITH CTE AS (SELECT region, SUM(amount) AS total_amount FROM order_table GROUP BY region),
     CTE1 AS (SELECT region, total_amount, row_number() OVER (ORDER BY total_amount DESC) AS rn FROM CTE)
     SELECT region, total_amount FROM CTE1 WHERE rn < 3;

-- add value type column based on order amount
SELECT order_id, user_name,
CASE
    WHEN amount > 40000 THEN 'high_value_item'
    WHEN amount < 10000 THEN 'low_value_item'
    ELSE 'medium_value_item'
END AS value_type
FROM order_table;

-- order by city and if city is null then by country
SELECT CustomerName, City, Country
FROM Customers
ORDER BY
(CASE
    WHEN City IS NULL THEN Country
    ELSE City
END);