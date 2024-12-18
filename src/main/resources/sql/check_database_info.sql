-- SEQUENCE 조회
SELECT 
    sequence_name,
    last_number,
    increment_by,
    min_value,
    max_value,
    cache_size,
    cycle_flag
FROM 
    user_sequences;
    
-- 제약조건 조회 (전체)
SELECT constraint_name, constraint_type, search_condition, r_constraint_name
FROM user_constraints
WHERE table_name = 'USERINFO';

-- 제약조건 조회 (외래키)
SELECT
    ac1.constraint_name AS fk_name,
    ac1.table_name AS child_table,
    ac1.column_name AS child_column,
    ac2.table_name AS parent_table,
    ac2.column_name AS parent_column
FROM
    all_cons_columns ac1
    JOIN all_constraints c1 ON ac1.constraint_name = c1.constraint_name
    JOIN all_constraints c2 ON c1.r_constraint_name = c2.constraint_name
    JOIN all_cons_columns ac2 ON c2.constraint_name = ac2.constraint_name
WHERE
    c1.constraint_type = 'R' -- 외래키만 조회
    AND ac2.table_name = 'USERINFO'; -- 부모 테이블 이름 지정
