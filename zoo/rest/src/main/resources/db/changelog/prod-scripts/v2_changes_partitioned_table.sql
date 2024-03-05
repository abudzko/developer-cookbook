CREATE TABLE zoo.user_action (
	id varchar NOT NULL,
	user_id varchar NULL,
	user_action varchar NULL,
	action_value int8 NOT NULL
) PARTITION BY RANGE (action_value);

CREATE TABLE zoo.user_action_1_100 PARTITION OF zoo.user_action FOR VALUES FROM (1) TO (100);
CREATE TABLE zoo.user_action_101_200 PARTITION OF zoo.user_action FOR VALUES FROM (101) TO (200);
