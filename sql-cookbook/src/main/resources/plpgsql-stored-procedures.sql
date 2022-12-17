create procedure insert_data(c integer)
language plpgsql
as $$
	declare
		x int;
	begin
		FOR x IN 1..c loop
		INSERT INTO users(id, qualifier) VALUES (x, x+80000000);
    END LOOP;
	end;
$$

create procedure delete_data(c integer)
language plpgsql
as $$
	declare
		x int;
	begin
		FOR x IN 1..c loop
		delete FROM users where id = cast(x as varchar);
    END LOOP;
	end;
$$

call insert_data(50000);
call delete_data(50000);

DROP PROCEDURE IF EXISTS insert_data;
DROP PROCEDURE IF EXISTS delete_data;
