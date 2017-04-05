-- 권한 값에 대한 insert문
insert into authority (auth_code , auth_name, comment)
	values ('01' , 'ROLE_ADMIN' , 'admin user');	
insert into authority (auth_code , auth_name, comment)
	values ('02' , 'ROLE_USER' , 'normal_user');
	
-- 부서코드 , 부서명에 대한 insert문
insert into department (depart_code , depart_name)
	values ('dev' , 'development');
insert into department (depart_code , depart_name)
	values ('art' , 'design');
insert into department (depart_code , depart_name)
	values ('sal' , 'sales');