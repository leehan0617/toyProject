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
			let rootValue = document.getElementById("rootValue").value;
			location.href = rootValue + "/project"
		},
		//직무별 사람 수 정하기
		departCount : (depart) => {
			if(depart.checked){// 직무별로 프로젝트 단위로 사람수 지정하기
				document.getElementById(depart.value + "_count").style.display = "inline-table";
				document.getElementById(depart.value + "_select").name = "departMap["+depart.value+"]";
			}else{
				document.getElementById(depart.value + "_count").style.display = "none";
				document.getElementById(depart.value + "_select").name = "";
			}
		},
		//프로젝트 상세보기
		projectDetail : (projectId) => {
			let rootValue = document.getElementById("rootValue").value;
			location.href = rootValue+"/project/"+projectId;
		},
		//프로젝트 수정하기
		projectModify: (projectId) => {
			let rootValue = document.getElementById("rootValue").value;
			location.href = rootValue+"/project/modify/"+projectId;
		},
		//프로젝트 수정하기
		projectUpdate: (projectId) => {
            detail.updateAll(projectId).then(result => {
            	alert("성공");
            	let rootValue = document.getElementById("rootValue").value;
    			location.href = rootValue+"/project/"+projectId;
			}).catch(err => {
			  alert("오류발생")
			});
		},
		//프로젝트 삭제하기
		projectDelete : (projectId) => {
			detail.deleteCheck(projectId).then(result => {
					detail.deleteAll(projectId).then(result => {
						  alert("성공");
						  location.href = "/project";
					}).catch(err => {
					  alert("오류발생")
					});
			}).catch(err => {
			  alert("삭제할수 없습니다.")
			});
		},
		//신청하기 팝업 열기
		applyOpen : (projectId) => {
			document.getElementById("registDiv").style.display = "inline";
			document.getElementById("layer2").style.top = (window.event.clientX)+"px";
			document.getElementById("project_id").value = projectId ;//프로젝트 ID 셋팅
			document.getElementById("depart_detail").value = "";//상세직무 설명 초기화
		},
		//신청하기 팝업 닫기 
		applyClose : () => {
			document.getElementById("registDiv").style.display = "none";
			document.getElementById("project_id").value = "" ;//프로젝트 ID 지우기
			document.getElementById("depart_detail").value = "" ;//상세직무 설명 초기화
		},
		//프로젝트 신청하기
		projectApply : () => {
			detail.memberApply().then(result => {
				alert("신청완료");
				location.reload();//새로고침
			}).catch(err => {
				alert("에러발생.")
			});
		}
		

}

//프로젝트 상세보기 페이지
let detail = {
		// delete 여부 체크
		deleteCheck : (projectId) =>{
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				req.open('GET',rootValue+'/project/checkIs/'+projectId);
				req.send();
				
				req.onload = () =>{//로드 했을떄 
					 if(req.status == 200){
						 if(req.responseText == "true"){//true 일때
							 resolve(req.responseText);
						 }else{
							 reject(new Error(req.statusText));
						 }
					 }else{
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
				
			});
		},
		// delete 하기
		deleteAll : (projectId) =>{
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				req.open('GET',rootValue+'/project/delete/'+projectId);
				req.send();
				
				req.onload = () => {//로드 했을떄 
					 if(req.status == 200){
						 resolve(req.responseText);
					 }else{
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
				
			});
		},
		// update 하기
		updateAll : (projectId) =>{
			
			let elements = document.getElementById('updatefrm');//form 데이터 불러오기
			
			const csrfToken = document.querySelector("input[name=csrf-token]").value;//csrf 토큰 값
			const header = document.querySelector("input[name=_csrf_header]").value;
			
			let uri = "";
            let argcount = 0;
            for (i = 0 ; i <elements.length ;i++) {//form 데이터 - url 방식으로 변경
              if (argcount++) {
                uri += '&';
              }
              uri += encodeURIComponent(elements[i].name) + '=' + encodeURIComponent(elements[i].value);
           }
            
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				
				req.open('POST',rootValue+'/project/update/'+projectId,true);//post 방식으로 전송하기
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");//post 방식일떄 필요
				req.setRequestHeader(header, csrfToken);
				req.send(uri);
				
				req.onload = () =>{//로드 했을떄 
					 if(req.status == 200){
						 if(req.responseText == "true"){//true 일때
							 resolve(req.responseText);
						 }else{
							 reject(new Error(req.statusText));
						 }
					 }else{
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
			});
		},
		// 프로젝트 신청하기
		memberApply : () =>{
			
			let elements = document.getElementById('memberApply');//form 데이터 불러오기
			
			const csrfToken = document.querySelector("input[name=csrf-token]").value;//csrf 토큰 값
			const header = document.querySelector("input[name=_csrf_header]").value;
			
			let uri = "";
            let argcount = 0;
            for (i = 0 ; i <elements.length ;i++) {//form 데이터 - url 방식으로 변경
              if (argcount++) {
                uri += '&';
              }
              uri += encodeURIComponent(elements[i].name) + '=' + encodeURIComponent(elements[i].value);
           }
            
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				
				req.open('POST',rootValue+'/project/apply',true);//post 방식으로 전송하기
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");//post 방식일떄 필요
				req.setRequestHeader(header, csrfToken);
				req.send(uri);
				
				req.onload = () =>{//로드 했을떄 
					 if(req.status == 200){
						 if(req.responseText == "true"){//true 일때
							 resolve(req.responseText);
						 }else{
							 reject(new Error(req.statusText));
						 }
					 }else{
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
			});
		}
		
}
