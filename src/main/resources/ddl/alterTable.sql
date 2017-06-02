-- 회원 기본키
CREATE UNIQUE INDEX pk_user
	ON user ( -- 회원
		user_id ASC -- 회원아이디
	);

-- 회원
ALTER TABLE user
	ADD
		CONSTRAINT pk_user -- 회원 기본키
		PRIMARY KEY (
			user_id -- 회원아이디
		);

-- 권한 기본키
CREATE UNIQUE INDEX pk_authority
	ON authority ( -- 권한
		auth_code ASC -- 권한코드
	);

-- 권한
ALTER TABLE authority
	ADD
		CONSTRAINT pk_authority -- 권한 기본키
		PRIMARY KEY (
			auth_code -- 권한코드
		);

-- 회원권한 기본키
CREATE UNIQUE INDEX pk_userauthority
	ON userauthority ( -- 회원권한
		user_id   ASC, -- 회원아이디
		auth_code ASC  -- 권한코드
	);

-- 회원권한
ALTER TABLE userauthority
	ADD
		CONSTRAINT pk_userauthority -- 회원권한 기본키
		PRIMARY KEY (
			user_id,   -- 회원아이디
			auth_code  -- 권한코드
		);

-- 직무테이블 기본키
CREATE UNIQUE INDEX pk_department
	ON department ( -- 직무테이블
		depart_code ASC -- 직무코드
	);

-- 직무테이블
ALTER TABLE department
	ADD
		CONSTRAINT pk_department -- 직무테이블 기본키
		PRIMARY KEY (
			depart_code -- 직무코드
		);

-- 활동내역 기본키
CREATE UNIQUE INDEX pk_acthistory
	ON acthistory ( -- 활동내역
		history_id ASC, -- 활동시퀀스
		user_id    ASC, -- 회원아이디
		act_code   ASC  -- 활동코드
	);

-- 활동내역
ALTER TABLE acthistory
	ADD
		CONSTRAINT pk_acthistory -- 활동내역 기본키
		PRIMARY KEY (
			history_id, -- 활동시퀀스
			user_id,    -- 회원아이디
			act_code    -- 활동코드
		);

-- 프로젝트테이블 기본키
CREATE UNIQUE INDEX pk_project
	ON project ( -- 프로젝트테이블
		project_id ASC -- 프로젝트아이디
	);

-- 프로젝트테이블
ALTER TABLE project
	ADD
		CONSTRAINT pk_project -- 프로젝트테이블 기본키
		PRIMARY KEY (
			project_id -- 프로젝트아이디
		);

-- 프로젝트참여자 기본키
CREATE UNIQUE INDEX pk_projectmember
	ON projectmember ( -- 프로젝트참여자
		project_id ASC, -- 프로젝트아이디
		user_id    ASC  -- 회원아이디(참여)
	);

-- 프로젝트참여자
ALTER TABLE projectmember
	ADD
		CONSTRAINT pk_projectmember -- 프로젝트참여자 기본키
		PRIMARY KEY (
			project_id, -- 프로젝트아이디
			user_id     -- 회원아이디(참여)
		);

-- 직무그룹테이블 기본키
CREATE UNIQUE INDEX pk_departgroup
	ON departgroup ( -- 직무그룹테이블
		depart_group_code ASC -- 직무그룹코드
	);

-- 직무그룹테이블
ALTER TABLE departgroup
	ADD
		CONSTRAINT pk_departgroup -- 직무그룹테이블 기본키
		PRIMARY KEY (
			depart_group_code -- 직무그룹코드
		);

-- 직무그룹매핑 기본키
CREATE UNIQUE INDEX pk_departgroupmapping
	ON departgroupmapping ( -- 직무그룹매핑
		depart_group_code ASC, -- 직무그룹코드
		depart_code       ASC  -- 직무코드
	);

-- 직무그룹매핑
ALTER TABLE departgroupmapping
	ADD
		CONSTRAINT pk_departgroupmapping -- 직무그룹매핑 기본키
		PRIMARY KEY (
			depart_group_code, -- 직무그룹코드
			depart_code        -- 직무코드
		);

-- 일감 기본키
CREATE UNIQUE INDEX pk_issue
	ON issue ( -- 일감
		issue_id   ASC, -- 일감아이디
		project_id ASC  -- 프로젝트아이디
	);

-- 일감
ALTER TABLE issue
	ADD
		CONSTRAINT pk_issue -- 일감 기본키
		PRIMARY KEY (
			issue_id,   -- 일감아이디
			project_id  -- 프로젝트아이디
		);

-- 활동코드 기본키
CREATE UNIQUE INDEX pk_act
	ON act ( -- 활동코드
		act_code ASC -- 활동코드
	);

-- 활동코드
ALTER TABLE act
	ADD
		CONSTRAINT pk_act -- 활동코드 기본키
		PRIMARY KEY (
			act_code -- 활동코드
		);

-- 상태테이블 기본키
CREATE UNIQUE INDEX pk_state
	ON state ( -- 상태테이블
		state_code ASC -- 상태코드
	);

-- 상태테이블
ALTER TABLE state
	ADD
		CONSTRAINT pk_state -- 상태테이블 기본키
		PRIMARY KEY (
			state_code -- 상태코드
		);

-- 포지션테이블 기본키
CREATE UNIQUE INDEX pk_projectdepartment
	ON projectdepartment ( -- 포지션테이블
		project_id  ASC, -- 프로젝트아이디
		depart_code ASC  -- 직무코드
	);

-- 포지션테이블
ALTER TABLE projectdepartment
	ADD
		CONSTRAINT pk_projectdepartment -- 포지션테이블 기본키
		PRIMARY KEY (
			project_id,  -- 프로젝트아이디
			depart_code  -- 직무코드
		);

-- 회원
ALTER TABLE user
	ADD
		CONSTRAINT fk_department_to_user -- 직무테이블 -> 회원
		FOREIGN KEY (
			depart_code -- 직무코드
		)
		REFERENCES department ( -- 직무테이블
			depart_code -- 직무코드
		);

-- 회원권한
ALTER TABLE userauthority
	ADD
		CONSTRAINT fk_user_to_userauthority -- 회원 -> 회원권한
		FOREIGN KEY (
			user_id -- 회원아이디
		)
		REFERENCES user ( -- 회원
			user_id -- 회원아이디
		);

-- 회원권한
ALTER TABLE userauthority
	ADD
		CONSTRAINT fk_authority_to_userauthority -- 권한 -> 회원권한
		FOREIGN KEY (
			auth_code -- 권한코드
		)
		REFERENCES authority ( -- 권한
			auth_code -- 권한코드
		);

-- 활동내역
ALTER TABLE acthistory
	ADD
		CONSTRAINT fk_user_to_acthistory -- 회원 -> 활동내역
		FOREIGN KEY (
			user_id -- 회원아이디
		)
		REFERENCES user ( -- 회원
			user_id -- 회원아이디
		);

-- 활동내역
ALTER TABLE acthistory
	ADD
		CONSTRAINT fk_act_to_acthistory -- 활동코드 -> 활동내역
		FOREIGN KEY (
			act_code -- 활동코드
		)
		REFERENCES act ( -- 활동코드
			act_code -- 활동코드
		);

-- 프로젝트테이블
ALTER TABLE project
	ADD
		CONSTRAINT fk_user_to_project -- 회원 -> 프로젝트테이블
		FOREIGN KEY (
			manager_id -- 회원아이디(PM)
		)
		REFERENCES user ( -- 회원
			user_id -- 회원아이디
		);

-- 프로젝트테이블
ALTER TABLE project
	ADD
		CONSTRAINT fk_state_to_project -- 상태테이블 -> 프로젝트테이블
		FOREIGN KEY (
			state_code -- 상태코드(프로젝트)
		)
		REFERENCES state ( -- 상태테이블
			state_code -- 상태코드
		);

-- 프로젝트참여자
ALTER TABLE projectmember
	ADD
		CONSTRAINT fk_project_to_projectmember -- 프로젝트테이블 -> 프로젝트참여자
		FOREIGN KEY (
			project_id -- 프로젝트아이디
		)
		REFERENCES project ( -- 프로젝트테이블
			project_id -- 프로젝트아이디
		);

-- 프로젝트참여자
ALTER TABLE projectmember
	ADD
		CONSTRAINT fk_user_to_projectmember -- 회원 -> 프로젝트참여자
		FOREIGN KEY (
			user_id -- 회원아이디(참여)
		)
		REFERENCES user ( -- 회원
			user_id -- 회원아이디
		);

-- 프로젝트참여자
ALTER TABLE projectmember
	ADD
		CONSTRAINT fk_state_to_projectmember -- 상태테이블 -> 프로젝트참여자
		FOREIGN KEY (
			state_code -- 상태코드(참여)
		)
		REFERENCES state ( -- 상태테이블
			state_code -- 상태코드
		);

-- 직무그룹매핑
ALTER TABLE departgroupmapping
	ADD
		CONSTRAINT fk_departgroup_to_departgroupmapping -- 직무그룹테이블 -> 직무그룹매핑
		FOREIGN KEY (
			depart_group_code -- 직무그룹코드
		)
		REFERENCES departgroup ( -- 직무그룹테이블
			depart_group_code -- 직무그룹코드
		);

-- 직무그룹매핑
ALTER TABLE departgroupmapping
	ADD
		CONSTRAINT fk_department_to_departgroupmapping -- 직무테이블 -> 직무그룹매핑
		FOREIGN KEY (
			depart_code -- 직무코드
		)
		REFERENCES department ( -- 직무테이블
			depart_code -- 직무코드
		);

-- 일감
ALTER TABLE issue
	ADD
		CONSTRAINT fk_project_to_issue -- 프로젝트테이블 -> 일감
		FOREIGN KEY (
			project_id -- 프로젝트아이디
		)
		REFERENCES project ( -- 프로젝트테이블
			project_id -- 프로젝트아이디
		);

-- 일감
ALTER TABLE issue
	ADD
		CONSTRAINT fk_state_to_issue -- 상태테이블 -> 일감
		FOREIGN KEY (
			state_code -- 상태코드(일감)
		)
		REFERENCES state ( -- 상태테이블
			state_code -- 상태코드
		);

-- 포지션테이블
ALTER TABLE projectdepartment
	ADD
		CONSTRAINT fk_project_to_projectdepartment -- 프로젝트테이블 -> 포지션테이블
		FOREIGN KEY (
			project_id -- 프로젝트아이디
		)
		REFERENCES project ( -- 프로젝트테이블
			project_id -- 프로젝트아이디
		);

-- 포지션테이블
ALTER TABLE projectdepartment
	ADD
		CONSTRAINT fk_department_to_projectdepartment -- 직무테이블 -> 포지션테이블
		FOREIGN KEY (
			depart_code -- 직무코드
		)
		REFERENCES department ( -- 직무테이블
			depart_code -- 직무코드
		);