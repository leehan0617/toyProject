-- user 
create table user (
	user_id varchar(50) not null primary key, -- 회원 아이디
    user_name varchar(20) not null, -- 이름
    password varchar(100) not null, -- 비밀번호
    email varchar(50), -- 이메일
    memo varchar(300), -- 메모
    depart_code varchar(20), -- 직무코드
    reg_date date , -- 등록일
    reg_id varchar(50) , -- 등록아이디
    mod_date date, -- 수정일
    mod_id varchar(50) -- 수정아이디
);

-- department
create table department (
	depart_code varchar(20) not null primary key, -- 직무코드
    depart_name varchar(50) not null -- 직무이름
);