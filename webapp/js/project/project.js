/*
 * 2017.05.18
 * 김민지
 * 프로젝트 관련 js 파일
*/
let project = {
		
		//내가 등록한 프로젝트 전체 리스트 보기 (관리자만)
		myPjoectList : (mycheck) => {
			if(mycheck.checked){// 내가등록한 프로젝트에 체크 하였을때 내가 등록한 프로젝트 보여주기
				console.log(document.getElementById("user_id").value)
			}else{
				
			}
		},
		//목록으로 돌아가기
		projectList : () => {
			location.href = "/projectList"
		},
		//직무별 사람 수 정하기
		departCount : (depart) => {
			if(depart.checked){// 직무별로 프로젝트 단위로 사람수 지정하기
				document.getElementById(depart.value + "_count").style.display = "inline-table";
			}else{
				document.getElementById(depart.value + "_count").style.display = "none";
			}
		},
		//프로젝트 상세보기
		projectDetail : (projectId) =>{
			location.href = "/project/"+projectId;
		},
		//프로젝트 삭제하기
		projectDelete : (projectId) =>{
			location.href = "/project/"+projectId;
		}
		
}