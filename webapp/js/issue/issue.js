/**
 * 성명 : 송하람
 * 설명 : issue 관련 js파일
 * 
 */
		
	
document.addEventListener("DOMContentLoaded",function(){
	let selectState = document.getElementById("nowSt").value;
	console.log(selectState);
	document.getElementById("stateSearch").value=selectState;
});
let issue = {
		//이슈검색
		issueSearch:(project_id) => {
			let selectState = document.getElementById("stateSearch").options[document.getElementById("stateSearch").selectedIndex].value;
			let rootValue = document.getElementById("rootValue").value;
			
			location.href = rootValue + '/issue/search?project_id=' + project_id + '&state_code=' + selectState;
	
		},
		//이슈 수정팝업 뜨게하기
		addUpdateIssuePopup :() => {
			document.getElementById("issuePopup").style.display = 'none';
			document.getElementById("issueDetailPopup").style.display = 'none';
			document.getElementById("issueUpdatePopUp").style.display = 'block';
			
			let issue_id = document.getElementById("issue_id").value;
			
			detailIssue.selectIssueInfo(issue_id).then(result => {
				let stdate = JSON.parse(result).start_date.substring(0, 10);
				let edate = JSON.parse(result).end_date.substring(0, 10);
				
				document.getElementById('updateIssueTitle').value=JSON.parse(result).issue_name;
				document.getElementById('updateStartDate').value=stdate;
				document.getElementById('updateEndDate').value=edate;
				// 상세내용
				document.getElementById('updateDetail').value=JSON.parse(result).issue_detail;
				let checkList = document.getElementsByName('userList2');
				
				issue.issueBtnChange(JSON.parse(result).state_code);
			}).catch(err => {
				alert(err)
			});
		},
		//이슈 수정하기
		updateIssue:(project_id)=>{
			issue.issueUpdate(project_id).then(result => {
				alert("성공");
				let rootValue = document.getElementById("rootValue").value;
				location.href = rootValue + '/issue/detail/' + project_id;
			}).catch(err => {
				alert("오류발생")
			});
		},
		//이슈 수정-promise
		issueUpdate:(project_id)=>{
			let elements = document.getElementById('updateIssueForm');
			
			var uri = "";
            var argcount = 0;
            for (i = 0 ; i <elements.length ;i++) {
              if (argcount++) {
                uri += '&';
              }
              uri += encodeURIComponent(elements[i].name) + '=' + encodeURIComponent(elements[i].value);
           }
            
            uri += '&project_id=' + project_id;
            
			return new Promise((resolve, reject) => {
				const csrfToken = document.querySelector("input[name=csrf_token]").value;
				const header = document.querySelector("input[name=_csrf_header]").value;
				
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				let DataToSend = rootValue+"/issue/update";
				
				req.open('POST',DataToSend,true);
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				req.setRequestHeader(header, csrfToken);
				req.send(uri);
				
				req.onload = () =>{//로드 했을떄 
					 if(req.status == 200){
							 resolve(req.responseText);
					 }
					 else {
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
			});
		},
		// 이슈 생성 팝업 뜨게하기
		addIssuePopUp :(project_id) => {
			let popup = document.getElementById("issuePopup");
			popup.style.display = 'block';
			
			document.getElementById("issueDetailPopup").style.display = 'none';
			
			let root = document.getElementById("rootValue").value;
		},
		issueSave : (project_id) => {
			issue.saveIssue().then(result => {
				alert("성공");
				  
				let rootValue = document.getElementById("rootValue").value;
				location.href = rootValue + '/issue/detail/' + project_id;
			}).catch(err => {
				alert("오류발생")
			});
		},	
		saveIssue:() => {
			let elements = document.getElementById('addIssueForm');
			
			const csrfToken = document.querySelector("input[name=csrf_token]").value;
			const header = document.querySelector("input[name=_csrf_header]").value;
			
			var uri = "";
            var argcount = 0;
            for (i = 0 ; i <elements.length ;i++) {
              if (argcount++) {
                uri += '&';
              }
              uri += encodeURIComponent(elements[i].name) + '=' + encodeURIComponent(elements[i].value);
           }
            
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				let DataToSend = rootValue+"/issue/add";
				
				req.open('POST',DataToSend,true);
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				req.setRequestHeader(header, csrfToken);
				req.send(uri);
				
				req.onload = () =>{//로드 했을떄 
					 if(req.status == 200){
							 resolve(req.responseText);
					 }
					 else {
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
			});
		},
		closeIssue:()=> {
			let popup = document.getElementById("issuePopup");
			popup.style.display = 'none';
		},
		deleteStart:(project_id) => {
			
			issue.deleteIssue(project_id).then(result => {
				console.log(project_id);
				let rootValue = document.getElementById("rootValue").value;
				location.href = rootValue + '/issue/detail/' + project_id;
				alert("성공");
				  
				
			}).catch(err => {
				alert("오류발생")
			});
		},
		deleteIssue:(project_id) => {
			const csrfToken = document.querySelector("input[name=csrf_token]").value;
			const header = document.querySelector("input[name=_csrf_header]").value;
			
			let uri = "";
			let issue_id = document.getElementById("issue_id").value;
			
			let params = {"issue_id":issue_id, "project_id":project_id};

			let esc = encodeURIComponent
			let query = Object.keys(params)
			             .map(k => esc(k) + '=' + esc(params[k]))
			             .join('&');
            
			return new Promise((resolve, reject) => {
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				let DataToSend = rootValue+"/issue/delete";
				
				
				req.open('POST',DataToSend,true);
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				req.setRequestHeader(header, csrfToken);
				req.send(query);
				
				req.onload = () =>{//로드 했을떄 
					 if(req.status == 200){
							 resolve(req.responseText);
					 }
					 else {
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
			});
		},
		issueStateChange:()=> {
			issue.issueChange().then(result => {
				
				let issue_id = document.getElementById("issue_id").value;
				detailIssue.selectIssueDetail(issue_id);
				alert("성공");
			}).catch(err => {
				alert(err)
			});
		},
		issueChange:()=>{
			let nowState = document.getElementById("now_state").value;
			let issue_id = document.getElementById("issue_id").value;
			let sendState = "";
			if (nowState == "wait") {
				sendState = "start";
			}
			else if (nowState == "start"){
				sendState = "stop";
			}
			else if (nowState == "stop"){
				sendState = "restart";
			}
			else if (nowState == "restart"){
				sendState = "end";
			}
			
			let sendData = {"issue_id":issue_id, "state_code":sendState};
			
			let esc = encodeURIComponent
			let query = Object.keys(sendData)
			             .map(k => esc(k) + '=' + esc(sendData[k]))
			             .join('&');

			return new Promise((resolve, reject) => {
				const csrfToken = document.querySelector("input[name=csrf_token]").value;
				const header = document.querySelector("input[name=_csrf_header]").value;
				
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				let DataToSend = rootValue+"/issue/changeIssue";
				
				req.open('POST',DataToSend,true);
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				req.setRequestHeader(header, csrfToken);
				req.send(query);
				
				req.onload = () =>{//로드 했을떄 
					 if(req.status == 200){
							 resolve(req.responseText);
					 }
					 else {
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
			});
			
		},
		issueBtnChange:(state_code)=>{
			if (state_code == 'start') {
				document.getElementById('issueStateBtn').value = '이슈중지';
			}
			else if (state_code == 'wait'){
				document.getElementById('issueStateBtn').value = '이슈시작';
			}
			else if (state_code == 'stop'){
				document.getElementById('issueStateBtn').value = '이슈재시작';
			}
			else if (state_code == 'restart'){
				document.getElementById('issueStateBtn').value = '이슈종료';
			}
		}
}

let detailIssue = {
		
		//이슈 상세보기
		selectIssueDetail : (issueId) => {
			let popup = document.getElementById("issueDetailPopup");
			popup.style.display = 'block';
			
			document.getElementById("issuePopup").style.display = 'none';
			
			detailIssue.selectIssueInfo(issueId).then(result => {
				let stdate = JSON.parse(result).start_date.substring(0, 10);
				let edate = JSON.parse(result).end_date.substring(0, 10);
				
				document.getElementById('issueName').innerHTML=JSON.parse(result).issue_name;
				document.getElementById('issueState').innerHTML=JSON.parse(result).state_name;
				document.getElementById('issueInfo').innerHTML=JSON.parse(result).issue_detail;
				document.getElementById('issueRegId').innerHTML=JSON.parse(result).reg_id;
				document.getElementById('issueStartDate').innerHTML=stdate;
				document.getElementById('issueEndDate').innerHTML=edate;
				document.getElementById('issue_id').value = JSON.parse(result).issue_id;
				document.getElementById('issue_id2').value = JSON.parse(result).issue_id;
				document.getElementById('now_state').value = JSON.parse(result).state_code;
				document.getElementById('now_state2').value = JSON.parse(result).state_code;
				
				issue.issueBtnChange(JSON.parse(result).state_code);
			}).catch(err => {
				alert(err)
			});
		},
		selectIssueInfo : (issueId) => {

			return new Promise((resolve, reject) => {
				const csrfToken = document.querySelector("input[name=csrf_token]").value;
				const header = document.querySelector("input[name=_csrf_header]").value;
				
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				let DataToSend = rootValue+"/issue/getIssueInfo/" + issueId;
				
				req.open('GET',DataToSend,true);
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				req.setRequestHeader(header, csrfToken);
				req.send();
				
				req.onload = () =>{//로드 했을떄 
					 if(req.status == 200){
							 resolve(req.responseText);
					 }
					 else {
						 reject(new Error(req.statusText));
					 }
				};
					 
				req.onerror = () => {//실패했을때
					 reject(new Error(req.statusText));
				 };
			});
			
		}
}

