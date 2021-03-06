-- 회원
CREATE TABLE user (
	user_id     VARCHAR(50)   NOT NULL, -- 회원아이디
	user_name   VARCHAR(40)   NOT NULL, -- 이름
	password    VARCHAR(2000) NOT NULL, -- 비밀번호
	email       VARCHAR(50)   NULL,     -- 이메일
	depart_code VARCHAR(20)   NULL,     -- 직무코드
	reg_date    DATE          NULL,     -- 등록일
	mod_date    DATE          NULL,     -- 수정일
	enabled     INTEGER(1)    NOT NULL DEFAULT 1,  -- 계정사용가능여부
	account_non_expired     INTEGER(1)    NOT NULL DEFAULT 1, -- 사용자계정만료여부
	account_non_locked      INTEGER(1)    NOT NULL DEFAULT 1, -- 자격증명만료여부
	credentials_non_expired INTEGER(1)    NOT NULL DEFAULT 1 -- 잠겨있는지 여부
);

-- 권한
CREATE TABLE authority (
	auth_code VARCHAR(20)  NOT NULL, -- 권한코드
	auth_name VARCHAR(200) NULL   ,   -- 권한이름
	comment   VARCHAR(200) NULL      -- 설명
);

-- 회원권한
CREATE TABLE userauthority (
	user_id   VARCHAR(50) NOT NULL, -- 회원아이디
	auth_code VARCHAR(20) NOT NULL  -- 권한코드
);


-- 직무테이블
CREATE TABLE department (
	depart_code VARCHAR(20)  NOT NULL, -- 직무코드
	depart_name VARCHAR(200) NOT NULL  -- 직무이름
);


-- 활동내역
CREATE TABLE acthistory (
	history_id NUMERIC      NOT NULL, -- 활동시퀀스
	user_id    VARCHAR(50)  NOT NULL, -- 회원아이디
	act_code   VARCHAR(20)  NOT NULL, -- 활동코드
	act_url    VARCHAR(500) NOT NULL, -- 활동URL
	reg_date   DATE         NULL      -- 등록일
);

-- 프로젝트테이블
CREATE TABLE project (
	project_id         VARCHAR(20)   NOT NULL, -- 프로젝트아이디
	project_name         VARCHAR(200)  NULL,     -- 프로젝트명
	project_detail     VARCHAR(2000) NULL,     -- 상세설명
	manager_id              VARCHAR(50)   NULL,     -- 회원아이디(PM)
	reg_date           DATE          NULL,     -- 등록일
	mod_date           DATE          NULL,     -- 수정일
	mod_id             VARCHAR(50)     NULL,      -- 수정아이디
	his_date           DATETIME          NULL,     -- history 키
	project_his_date           DATETIME          NULL     -- history 키
);

-- 프로젝트참여자
CREATE TABLE projectmember (
	project_id    VARCHAR(20)  NOT NULL, -- 프로젝트아이디
	user_id       VARCHAR(50)  NOT NULL, -- 회원아이디(참여)
	state_code    VARCHAR(20)  NULL,     -- 상태코드(참여)
	depart_detail VARCHAR(500) NULL,     -- 상세직무
	reg_date      DATE         NULL,     -- 등록일
	reg_id        VARCHAR(50)  NULL,     -- 등록아이디
	mod_date      DATE         NULL,     -- 수정일
	mod_id        VARCHAR(50)  NULL      -- 수정아이디
);


-- 직무그룹테이블
CREATE TABLE departgroup (
	depart_group_code VARCHAR(20)  NOT NULL, -- 직무그룹코드
	depart_group_name VARCHAR(200) NULL      -- 직무그룹명
);


-- 직무그룹매핑
CREATE TABLE departgroupmapping (
	depart_group_code VARCHAR(20) NOT NULL, -- 직무그룹코드
	depart_code       VARCHAR(20) NOT NULL  -- 직무코드
);

-- 일감
CREATE TABLE issue (
	issue_id         NUMERIC       NOT NULL, -- 일감아이디
	project_id       VARCHAR(20)   NOT NULL, -- 프로젝트아이디
	issue_name       VARCHAR(200)  NULL,     -- 일감이름
	issue_detail     VARCHAR(2000) NULL,     -- 일감상세정보
	reg_date           DATE          NULL,     -- 등록일
	reg_id           VARCHAR(50)   NULL,     -- 등록아이디
	mod_date           DATE          NULL,     -- 수정일
	mod_id           VARCHAR(50)   NULL ,     -- 수정아이디
	his_date         DATETIME      NULL     -- history 키
);

-- 활동코드
CREATE TABLE act (
	act_code VARCHAR(20)  NOT NULL, -- 활동코드
	act_name VARCHAR(500) NULL      -- 활동내용
);

-- 상태테이블
CREATE TABLE state (
	state_code VARCHAR(20)  NOT NULL, -- 상태코드
	type       VARCHAR(20)  NOT NULL, -- 유형
	state_name VARCHAR(200) NULL      -- 상태명
);


-- 포지션테이블
CREATE TABLE projectdepartment (
	project_id  VARCHAR(20) NOT NULL, -- 프로젝트아이디
	depart_code VARCHAR(20) NOT NULL,  -- 직무코드
	usercount   NUMERIC  NULL,     -- 인원
);

-- 프로젝트 기간
CREATE TABLE projecthistory (
	project_id VARCHAR(20) NULL, -- 프로젝트아이디
	start_date DATETIME        NULL, -- 시작날짜
	end_date   DATETIME        NULL, -- 끝날짜
	state_code VARCHAR(20) NULL, -- 상태코드
	type      VARCHAR(20) NULL, -- 유형
	reg_date   DATETIME        NULL, -- 등록일
	reg_id     VARCHAR(20) NULL  -- 등록아이디
);

-- 이슈 기간
CREATE TABLE issuehistory (
	issue_id   NUMERIC     NULL, -- 일감아이디
	project_id VARCHAR(20) NULL, -- 프로젝트아이디
	start_date DATETIME        NULL, -- 시작날짜
	end_date   DATETIME        NULL, -- 끝날짜
	state_code VARCHAR(20) NULL, -- 상태코드
	type       VARCHAR(20) NULL, -- 유형
	reg_date   DATETIME        NULL, -- 등록일
	reg_id     VARCHAR(20) NULL  -- 등록아이디
);

-- 프로젝트모집기간
CREATE TABLE projectrecurithistory (
	project_id VARCHAR(20) NULL, -- 프로젝트아이디
	start_date DATETIME        NULL, -- 시작날짜
	end_date   DATETIME        NULL, -- 끝날짜
	state_code VARCHAR(20) NULL, -- 상태코드
	type      VARCHAR(20) NULL, -- 유형
	reg_date   DATETIME        NULL, -- 등록일
	reg_id     VARCHAR(20) NULL  -- 등록아이디
);
-- 이슈 멤버
CREATE TABLE issuemember (
	project_id VARCHAR(20) NOT NULL, -- 프로젝트아이디
	issue_id   NUMERIC     NOT NULL, -- 일감아이디
	user_id   VARCHAR(50) NOT NULL  -- 회원아이디
);