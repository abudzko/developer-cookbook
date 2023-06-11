CREATE SCHEMA zoo;
CREATE TABLE zoo."event" (
	id varchar PRIMARY KEY,
	name varchar NULL,
	"timestamp" int8 NULL
);