-- 권한 값에 대한 insert문
insert into authority (auth_code , auth_name)
	values ('ROLE_USER' , 'normal user');
insert into authority (auth_code , auth_name)
	values ('ROLE_ADMIN' , 'admin user');
-- 부서코드 , 부서명에 대한 insert문
insert into department (depart_code , depart_name)
	values ('dev' , 'development');
insert into department (depart_code , depart_name)
	values ('art' , 'design');
insert into department (depart_code , depart_name)
	values ('sal' , 'sales');