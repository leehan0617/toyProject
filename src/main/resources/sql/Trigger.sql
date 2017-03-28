
DELIMITER --명령문 구획 문자를 사용하기 위해서는MYSQL명령문 구획 문자(delimiter)를 재 정의하는 것이 필요하다(구분자를 바꿔주는것)
create trigger TG_USER_AUTH
AFTER INSERT on user
for each row begin
insert into userauthority(user_id,auth_code)
values (NEW.user_id,'ROLE_USER');   
end;

 

