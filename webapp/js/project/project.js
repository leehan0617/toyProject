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
		infoDetailOpenapplyOpen : (projectId) => {
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
		},
		//프로젝트 멤버 리스트 가져오기 
		memberList : (projectId) => {
			let table = document.getElementById("memberTable");
			let tabletr = document.getElementsByName("cloneTr");
			
			for(i = tabletr.length-1 ; i >= 0 ; i--){// table tr 모두 제거
				tabletr[i].parentNode.removeChild(tabletr[i]);
			}
			
			detail.getmemberList(projectId).then(result => {

				let memberList = JSON.parse(result);
				let tr = document.getElementById("memberTr")
				
				document.getElementById("project_id").value = projectId;//프로젝트 id 셋팅 

				for(i = 0 ; i <memberList.length ; i++){
					let clone = tr.cloneNode(true);
					let td = clone.getElementsByTagName("td");
					
					clone.style.display = "table-row";
					clone.id = "cloneTr";
					clone.setAttribute("name","cloneTr");
					
					td[0].innerHTML = memberList[i].user_name;//신청한 사람
					
					if(memberList[i].state_code == "accept"){//이미 승인한 사람일 경우 
						clone.querySelector("input[id=acceptBtn]").disabled = true;
					}
					if(memberList[i].state_code == "reject"){//이미 거절 당한 사람일 경우 
						clone.querySelector("input[id=rejectBtn]").disabled = true;
					}
					
					clone.querySelector("input[id=user_id]").value = memberList[i].user_id;//신청한 사람 ID
					
					table.appendChild(clone);
				}
				
			}).catch(err => {
				alert("에러발생.")
			});
		},
		//사람 정보 상세보기
		infoDetailOpen : (my) => {
			let user_id = my.parentNode.querySelector("input[id=user_id]").value;//선택한 user_id
			let project_id = document.querySelector("input[id=project_id]").value;//선택한 프로젝트 id

			detail.memberDetail(project_id,user_id).then(result => {//사람 정보 가져오기
				let memberInfo = JSON.parse(result);
				
				document.querySelector("td[id=name]").innerHTML = memberInfo.user_name;
				document.querySelector("td[id=depart]").innerHTML = memberInfo.depart_name;
				document.querySelector("td[id=depart_detail]").innerHTML = memberInfo.depart_detail;
				document.getElementById("infoDetailDiv").style.display = "inline";
				document.getElementById("layer2").style.top = (window.event.clientX)+"px";
				
			}).catch(err => {
				alert("에러발생.")
			});
		},
		//사람 정보 팝업 닫기 
		infoDetailClose : () => {
			document.getElementById("infoDetailDiv").style.display = "none";
			document.querySelector("td[id=name]").innerHTML = "";
			document.querySelector("td[id=depart]").innerHTML = "";
			document.querySelector("td[id=depart_detail]").innerHTML = "";
		},
		//사람에 대하여 승인 절차 (accept:승인,reject:거절)
		apprMember: (approve,my) => {
			detail.updatememberState(approve,my).then(result => {
				alert("완료");
				let project_id = document.querySelector("input[id=project_id]").value;//선택한 프로젝트 id
				project.memberList(project_id);//새로고침 하기
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
		},
		// 프로젝트 멤버 가져오기
		getmemberList : (projectId) =>{
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				req.open('GET',rootValue+'/project/member/'+projectId);
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
		// member 정보 상세 조회
		memberDetail : (projectId,userId) =>{
			
			let params = {"project_id":projectId,"user_id":userId};
			
			const csrfToken = document.querySelector("input[name=csrf-token]").value;//csrf 토큰 값
			const header = document.querySelector("input[name=_csrf_header]").value;
			
			let esc = encodeURIComponent
			let query = Object.keys(params)
			             .map(k => esc(k) + '=' + esc(params[k]))
			             .join('&');
			
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				req.open('POST',rootValue+'/project/memberInfo');
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");//post 방식일떄 필요
				req.setRequestHeader(header, csrfToken);
				req.send(query);
				
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
		// 프로젝트 멤버 승인하기 
		updatememberState : (approve,my) =>{
			
			let userId = my.parentNode.querySelector("input[id=user_id]").value;//선택한 user_id
			let projectId = document.querySelector("input[id=project_id]").value;//선택한 프로젝트 id
			
			let params = {"project_id":projectId,"user_id":userId,"state_code":approve};
			
			const csrfToken = document.querySelector("input[name=csrf-token]").value;//csrf 토큰 값
			const header = document.querySelector("input[name=_csrf_header]").value;
			
			let esc = encodeURIComponent
			let query = Object.keys(params)
			             .map(k => esc(k) + '=' + esc(params[k]))
			             .join('&');
            
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				
				req.open('POST',rootValue+'/project/approve',true);//post 방식으로 전송하기
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");//post 방식일떄 필요
				req.setRequestHeader(header, csrfToken);
				req.send(query);
				
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
}
