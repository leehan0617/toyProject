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
	
-- 프로젝트 시퀀스 자동 생성	
alter table project modify project_id int not null auto_increment;

-- 상태코드
insert into state (state_code , state_name)
	values ('0101' , '모집중');
insert into state (state_code , state_name)
	values ('0102' , '모집완료');
insert into state (state_code , state_name)
	values ('0103' , '신청완료');