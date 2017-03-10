-- 회원
CREATE TABLE USER (
	user_id     VARCHAR(50)   NOT NULL, -- 회원아이디
	user_name   VARCHAR(40)   NOT NULL, -- 이름
	password    VARCHAR(2000) NOT NULL, -- 비밀번호
	email       VARCHAR(50)   NULL,     -- 이메일
	depart_code VARCHAR(20)   NULL,     -- 직무코드
	reg_date    DATE          NULL,     -- 등록일
	reg_id      VARCHAR(20)   NULL,     -- 등록아이디
	mod_date    DATE          NULL,     -- 수정일
	mod_id      VARCHAR(20)   NULL,     -- 수정아이디
	enabled     INTEGER(1)    NOT NULL  -- 시큐리티 설정값
);


-- 권한
CREATE TABLE AUTHORITY (
	auth_code VARCHAR(20)  NOT NULL, -- 권한코드
	auth_name VARCHAR(200) NULL      -- 권한이름
);

-- 회원권한
CREATE TABLE USERAUTHORITY (
	user_id   VARCHAR(50) NOT NULL, -- 회원아이디
	auth_code VARCHAR(20) NOT NULL  -- 권한코드
);


-- 직무테이블
CREATE TABLE DEPARTMENT (
	depart_code VARCHAR(20)  NOT NULL, -- 직무코드
	depart_name VARCHAR(200) NOT NULL  -- 직무이름
);


-- 활동내역
CREATE TABLE ACTHISTORY (
	history_id NUMERIC      NOT NULL, -- 활동시퀀스
	user_id    VARCHAR(50)  NOT NULL, -- 회원아이디
	act_code   VARCHAR(20)  NOT NULL, -- 활동코드
	act_url    VARCHAR(500) NOT NULL, -- 활동URL
	reg_date   DATE         NULL      -- 등록일
);

-- 프로젝트테이블
CREATE TABLE PROJECT (
	project_id         VARCHAR(20)   NOT NULL, -- 프로젝트아이디
	project_nm         VARCHAR(200)  NULL,     -- 프로젝트명
	project_start_date DATE          NULL,     -- 시작날짜
	project_end_date   DATE          NULL,     -- 끝날짜
	usercount          NUMERIC       NULL,     -- 인원
	project_detail     VARCHAR(2000) NULL,     -- 상세설명
	pm_id              VARCHAR(50)   NULL,     -- 회원아이디(PM)
	state_code         VARCHAR(20)   NULL,     -- 상태코드(프로젝트)
	reg_date           DATE          NULL,     -- 등록일
	mod_date           DATE          NULL,     -- 수정일
	mod_id             VARCHAR()     NULL      -- 수정아이디
);

-- 프로젝트참여자
CREATE TABLE PROJECTMEMBER (
	project_id    VARCHAR(20)  NOT NULL, -- 프로젝트아이디
	user_id       VARCHAR(50)  NOT NULL, -- 회원아이디(참여)
	state_code    VARCHAR(20)  NULL,     -- 상태코드(참여)
	depart_detail VARCHAR(500) NULL,     -- 상세직무
	reg_date      DATE         NULL,     -- 등록일
	reg_id        VARCHAR(20)  NULL,     -- 등록아이디
	mod_date      DATE         NULL,     -- 수정일
	mod_id        VARCHAR(20)  NULL      -- 수정아이디
);


-- 직무그룹테이블
CREATE TABLE DEPARTGROUP (
	depart_group_code VARCHAR(20)  NOT NULL, -- 직무그룹코드
	depart_group_name VARCHAR(200) NULL      -- 직무그룹명
);


-- 직무그룹매핑
CREATE TABLE DEPARTGROUPMAPPING (
	depart_group_code VARCHAR(20) NOT NULL, -- 직무그룹코드
	depart_code       VARCHAR(20) NOT NULL  -- 직무코드
);

-- 일감
CREATE TABLE ISSUE (
	issue_id         NUMERIC       NOT NULL, -- 일감아이디
	project_id       VARCHAR(20)   NOT NULL, -- 프로젝트아이디
	issue_name       VARCHAR(200)  NULL,     -- 일감이름
	issue_detail     VARCHAR(2000) NULL,     -- 일감상세정보
	issue_start_date DATE          NULL,     -- 시작날짜
	issue_end_date   DATE          NULL,     -- 종료날짜
	state_code       VARCHAR(20)   NULL,     -- 상태코드(일감)
	reg_dt           DATE          NULL,     -- 등록일
	reg_id           VARCHAR(20)   NULL,     -- 등록아이디
	mod_dt           DATE          NULL,     -- 수정일
	mod_id           VARCHAR(20)   NULL      -- 수정아이디
);

-- 활동코드
CREATE TABLE ACT (
	act_code VARCHAR(20)  NOT NULL, -- 활동코드
	act_name VARCHAR(500) NULL      -- 활동내용
);

-- 상태테이블
CREATE TABLE STATE (
	state_code VARCHAR(20)  NOT NULL, -- 상태코드
	state_name VARCHAR(200) NULL      -- 상태명
);

-- 포지션테이블
CREATE TABLE PROJECTDEPARTMENTE (
	project_id  VARCHAR(20) NOT NULL, -- 프로젝트아이디
	depart_code VARCHAR(20) NOT NULL  -- 직무코드
);
