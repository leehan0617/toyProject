-- 회원 기본키
CREATE UNIQUE INDEX PK_USER
	ON USER ( -- 회원
		user_id ASC -- 회원아이디
	);

-- 회원
ALTER TABLE USER
	ADD
		CONSTRAINT PK_USER -- 회원 기본키
		PRIMARY KEY (
			user_id -- 회원아이디
		);

-- 권한 기본키
CREATE UNIQUE INDEX PK_AUTHORITY
	ON AUTHORITY ( -- 권한
		auth_code ASC -- 권한코드
	);

-- 권한
ALTER TABLE AUTHORITY
	ADD
		CONSTRAINT PK_AUTHORITY -- 권한 기본키
		PRIMARY KEY (
			auth_code -- 권한코드
		);

-- 회원권한 기본키
CREATE UNIQUE INDEX PK_USERAUTHORITY
	ON USERAUTHORITY ( -- 회원권한
		user_id   ASC, -- 회원아이디
		auth_code ASC  -- 권한코드
	);

-- 회원권한
ALTER TABLE USERAUTHORITY
	ADD
		CONSTRAINT PK_USERAUTHORITY -- 회원권한 기본키
		PRIMARY KEY (
			user_id,   -- 회원아이디
			auth_code  -- 권한코드
		);

-- 직무테이블 기본키
CREATE UNIQUE INDEX PK_DEPARTMENT
	ON DEPARTMENT ( -- 직무테이블
		depart_code ASC -- 직무코드
	);

-- 직무테이블
ALTER TABLE DEPARTMENT
	ADD
		CONSTRAINT PK_DEPARTMENT -- 직무테이블 기본키
		PRIMARY KEY (
			depart_code -- 직무코드
		);

-- 활동내역 기본키
CREATE UNIQUE INDEX PK_ACTHISTORY
	ON ACTHISTORY ( -- 활동내역
		history_id ASC, -- 활동시퀀스
		user_id    ASC, -- 회원아이디
		act_code   ASC  -- 활동코드
	);

-- 활동내역
ALTER TABLE ACTHISTORY
	ADD
		CONSTRAINT PK_ACTHISTORY -- 활동내역 기본키
		PRIMARY KEY (
			history_id, -- 활동시퀀스
			user_id,    -- 회원아이디
			act_code    -- 활동코드
		);

-- 프로젝트테이블 기본키
CREATE UNIQUE INDEX PK_PROJECT
	ON PROJECT ( -- 프로젝트테이블
		project_id ASC -- 프로젝트아이디
	);

-- 프로젝트테이블
ALTER TABLE PROJECT
	ADD
		CONSTRAINT PK_PROJECT -- 프로젝트테이블 기본키
		PRIMARY KEY (
			project_id -- 프로젝트아이디
		);

-- 프로젝트참여자 기본키
CREATE UNIQUE INDEX PK_PROJECTMEMBER
	ON PROJECTMEMBER ( -- 프로젝트참여자
		project_id ASC, -- 프로젝트아이디
		user_id    ASC  -- 회원아이디(참여)
	);

-- 프로젝트참여자
ALTER TABLE PROJECTMEMBER
	ADD
		CONSTRAINT PK_PROJECTMEMBER -- 프로젝트참여자 기본키
		PRIMARY KEY (
			project_id, -- 프로젝트아이디
			user_id     -- 회원아이디(참여)
		);

-- 직무그룹테이블 기본키
CREATE UNIQUE INDEX PK_DEPARTGROUP
	ON DEPARTGROUP ( -- 직무그룹테이블
		depart_group_code ASC -- 직무그룹코드
	);

-- 직무그룹테이블
ALTER TABLE DEPARTGROUP
	ADD
		CONSTRAINT PK_DEPARTGROUP -- 직무그룹테이블 기본키
		PRIMARY KEY (
			depart_group_code -- 직무그룹코드
		);

-- 직무그룹매핑 기본키
CREATE UNIQUE INDEX PK_DEPARTGROUPMAPPING
	ON DEPARTGROUPMAPPING ( -- 직무그룹매핑
		depart_group_code ASC, -- 직무그룹코드
		depart_code       ASC  -- 직무코드
	);

-- 직무그룹매핑
ALTER TABLE DEPARTGROUPMAPPING
	ADD
		CONSTRAINT PK_DEPARTGROUPMAPPING -- 직무그룹매핑 기본키
		PRIMARY KEY (
			depart_group_code, -- 직무그룹코드
			depart_code        -- 직무코드
		);

-- 일감 기본키
CREATE UNIQUE INDEX PK_ISSUE
	ON ISSUE ( -- 일감
		issue_id   ASC, -- 일감아이디
		project_id ASC  -- 프로젝트아이디
	);

-- 일감
ALTER TABLE ISSUE
	ADD
		CONSTRAINT PK_ISSUE -- 일감 기본키
		PRIMARY KEY (
			issue_id,   -- 일감아이디
			project_id  -- 프로젝트아이디
		);

-- 활동코드 기본키
CREATE UNIQUE INDEX PK_ACT
	ON ACT ( -- 활동코드
		act_code ASC -- 활동코드
	);

-- 활동코드
ALTER TABLE ACT
	ADD
		CONSTRAINT PK_ACT -- 활동코드 기본키
		PRIMARY KEY (
			act_code -- 활동코드
		);

-- 상태테이블 기본키
CREATE UNIQUE INDEX PK_STATE
	ON STATE ( -- 상태테이블
		state_code ASC -- 상태코드
	);

-- 상태테이블
ALTER TABLE STATE
	ADD
		CONSTRAINT PK_STATE -- 상태테이블 기본키
		PRIMARY KEY (
			state_code -- 상태코드
		);

-- 포지션테이블 기본키
CREATE UNIQUE INDEX PK_PROJECTDEPARTMENTE
	ON PROJECTDEPARTMENTE ( -- 포지션테이블
		project_id  ASC, -- 프로젝트아이디
		depart_code ASC  -- 직무코드
	);

-- 포지션테이블
ALTER TABLE PROJECTDEPARTMENTE
	ADD
		CONSTRAINT PK_PROJECTDEPARTMENTE -- 포지션테이블 기본키
		PRIMARY KEY (
			project_id,  -- 프로젝트아이디
			depart_code  -- 직무코드
		);

-- 회원
ALTER TABLE USER
	ADD
		CONSTRAINT FK_DEPARTMENT_TO_USER -- 직무테이블 -> 회원
		FOREIGN KEY (
			depart_code -- 직무코드
		)
		REFERENCES DEPARTMENT ( -- 직무테이블
			depart_code -- 직무코드
		);

-- 회원권한
ALTER TABLE USERAUTHORITY
	ADD
		CONSTRAINT FK_USER_TO_USERAUTHORITY -- 회원 -> 회원권한
		FOREIGN KEY (
			user_id -- 회원아이디
		)
		REFERENCES USER ( -- 회원
			user_id -- 회원아이디
		);

-- 회원권한
ALTER TABLE USERAUTHORITY
	ADD
		CONSTRAINT FK_AUTHORITY_TO_USERAUTHORITY -- 권한 -> 회원권한
		FOREIGN KEY (
			auth_code -- 권한코드
		)
		REFERENCES AUTHORITY ( -- 권한
			auth_code -- 권한코드
		);

-- 활동내역
ALTER TABLE ACTHISTORY
	ADD
		CONSTRAINT FK_USER_TO_ACTHISTORY -- 회원 -> 활동내역
		FOREIGN KEY (
			user_id -- 회원아이디
		)
		REFERENCES USER ( -- 회원
			user_id -- 회원아이디
		);

-- 활동내역
ALTER TABLE ACTHISTORY
	ADD
		CONSTRAINT FK_ACT_TO_ACTHISTORY -- 활동코드 -> 활동내역
		FOREIGN KEY (
			act_code -- 활동코드
		)
		REFERENCES ACT ( -- 활동코드
			act_code -- 활동코드
		);

-- 프로젝트테이블
ALTER TABLE PROJECT
	ADD
		CONSTRAINT FK_USER_TO_PROJECT -- 회원 -> 프로젝트테이블
		FOREIGN KEY (
			manager_id -- 회원아이디(PM)
		)
		REFERENCES USER ( -- 회원
			user_id -- 회원아이디
		);

-- 프로젝트테이블
ALTER TABLE PROJECT
	ADD
		CONSTRAINT FK_STATE_TO_PROJECT -- 상태테이블 -> 프로젝트테이블
		FOREIGN KEY (
			state_code -- 상태코드(프로젝트)
		)
		REFERENCES STATE ( -- 상태테이블
			state_code -- 상태코드
		);

-- 프로젝트참여자
ALTER TABLE PROJECTMEMBER
	ADD
		CONSTRAINT FK_PROJECT_TO_PROJECTMEMBER -- 프로젝트테이블 -> 프로젝트참여자
		FOREIGN KEY (
			project_id -- 프로젝트아이디
		)
		REFERENCES PROJECT ( -- 프로젝트테이블
			project_id -- 프로젝트아이디
		);

-- 프로젝트참여자
ALTER TABLE PROJECTMEMBER
	ADD
		CONSTRAINT FK_USER_TO_PROJECTMEMBER -- 회원 -> 프로젝트참여자
		FOREIGN KEY (
			user_id -- 회원아이디(참여)
		)
		REFERENCES USER ( -- 회원
			user_id -- 회원아이디
		);

-- 프로젝트참여자
ALTER TABLE PROJECTMEMBER
	ADD
		CONSTRAINT FK_STATE_TO_PROJECTMEMBER -- 상태테이블 -> 프로젝트참여자
		FOREIGN KEY (
			state_code -- 상태코드(참여)
		)
		REFERENCES STATE ( -- 상태테이블
			state_code -- 상태코드
		);

-- 직무그룹매핑
ALTER TABLE DEPARTGROUPMAPPING
	ADD
		CONSTRAINT FK_DEPARTGROUP_TO_DEPARTGROUPMAPPING -- 직무그룹테이블 -> 직무그룹매핑
		FOREIGN KEY (
			depart_group_code -- 직무그룹코드
		)
		REFERENCES DEPARTGROUP ( -- 직무그룹테이블
			depart_group_code -- 직무그룹코드
		);

-- 직무그룹매핑
ALTER TABLE DEPARTGROUPMAPPING
	ADD
		CONSTRAINT FK_DEPARTMENT_TO_DEPARTGROUPMAPPING -- 직무테이블 -> 직무그룹매핑
		FOREIGN KEY (
			depart_code -- 직무코드
		)
		REFERENCES DEPARTMENT ( -- 직무테이블
			depart_code -- 직무코드
		);

-- 일감
ALTER TABLE ISSUE
	ADD
		CONSTRAINT FK_PROJECT_TO_ISSUE -- 프로젝트테이블 -> 일감
		FOREIGN KEY (
			project_id -- 프로젝트아이디
		)
		REFERENCES PROJECT ( -- 프로젝트테이블
			project_id -- 프로젝트아이디
		);

-- 일감
ALTER TABLE ISSUE
	ADD
		CONSTRAINT FK_STATE_TO_ISSUE -- 상태테이블 -> 일감
		FOREIGN KEY (
			state_code -- 상태코드(일감)
		)
		REFERENCES STATE ( -- 상태테이블
			state_code -- 상태코드
		);

-- 포지션테이블
ALTER TABLE PROJECTDEPARTMENTE
	ADD
		CONSTRAINT FK_PROJECT_TO_PROJECTDEPARTMENTE -- 프로젝트테이블 -> 포지션테이블
		FOREIGN KEY (
			project_id -- 프로젝트아이디
		)
		REFERENCES PROJECT ( -- 프로젝트테이블
			project_id -- 프로젝트아이디
		);

-- 포지션테이블
ALTER TABLE PROJECTDEPARTMENTE
	ADD
		CONSTRAINT FK_DEPARTMENT_TO_PROJECTDEPARTMENTE -- 직무테이블 -> 포지션테이블
		FOREIGN KEY (
			depart_code -- 직무코드
		)
		REFERENCES DEPARTMENT ( -- 직무테이블
			depart_code -- 직무코드
		);