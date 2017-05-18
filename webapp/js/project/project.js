/*
 * 2017.05.18
 * 김민지
 * 프로젝트 관련 js 파일
*/
var project = {
		
		//내가 등록한 프로젝트 전체 리스트 보기 (관리자만)
		myPjoectList : function(mycheck){
			if(mycheck.checked){// 내가등록한 프로젝트에 체크 하였을때 내가 등록한 프로젝트 보여주기
				console.log(document.getElementById("user_id").value)
			}else{
				
			}
		},
		//목록으로 돌아가기
		projectList : function(){
			location.href = "/projectList"
		}
		
}