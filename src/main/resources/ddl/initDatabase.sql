-- db 설치하고 local에서 해야할 작업들 (maria db 기준)
-- 1. 데이터 베이스 생성
create database toyProject;
-- 2. user 생성 (root로 해도되나 보안 issue)
-- osstem이라는 유저를 만들고 비밀번호도 osstem으로 세팅
create user 'osstem'@'localhost' identified by 'osstem';
-- 3. osstem에게 toyProject 데이터베이스에 모든 권한 부여
-- @ 뒤에 localhost로 작성하였기 때문에 로컬에서만 사용가능
-- 원격 접속도 오픈할려면 'localhost' -> '%' 로 수정
grant all privileges on toyProject.* to osstem@'localhost';

-- 4. 권한 새로고침
flush privileges;

-- console 접속시
-- mysql -u osstem -p 한뒤에 비밀번호 입력(osstem) 
