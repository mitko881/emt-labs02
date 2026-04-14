CREATE MATERIALIZED VIEW accommodation_stats_mv AS
SELECT
    category,
    COUNT(*) AS total_accommodations,
    SUM(num_rooms) AS total_rooms,
    AVG(num_rooms) AS avg_rooms
FROM accommodations
GROUP BY category;