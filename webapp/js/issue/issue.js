/**
 * 성명 : 송하람
 * 설명 : issue 관련 js파일
 * 
 */


let issue = {
		// 이슈 생성 팝업 뜨게하기
		addIssuePopUp :(project_id) => {
			let popup = document.getElementById("issuePopup");
			popup.style.display = 'block';
			
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
				
				alert("성공");
				  
				let now_state = document.getElementById("now_state");
				
				if (now_state == "issue-wait") {
					document.getElementById("issueState").val("이슈시작");
				}
				else if (now_state == "issue-start" || now_state == "issue-restart"){
					document.getElementById("issueState").val("이슈종료");
				}
				else if (now_state == "issue-end"){
					document.getElementById("issueState").val("재시작");
				}
			}).catch(err => {
				alert(err)
			});
		},
		issueChange:()=>{
			let nowState = document.getElementById("now_state").value;
			let issue_id = document.getElementById("issue_id").value;
			const csrfToken = document.querySelector("input[name=csrf_token]").value;
			const header = document.querySelector("input[name=_csrf_header]").value;
			let sendState = "";
			if (nowState == "issue-wait") {
				sendState = "issue-start";
			}
			else if (nowState == "issue-start" || now_state == "issue-restart"){
				sendState = "issue-end";
			}
			else if (nowState == "issue-end"){
				sendState = "issue-restart";
			}
			
			let sendData = {"issue_id":issue_id, "sendState":sendState};
			console.log(JSON.stringify(sendData));

			return new Promise((resolve, reject) => {
				const csrfToken = document.querySelector("input[name=csrf_token]").value;
				const header = document.querySelector("input[name=_csrf_header]").value;
				
				let rootValue = document.getElementById("rootValue").value;
				let req = new XMLHttpRequest();
				let DataToSend = rootValue+"/issue/changeIssue";
				
				req.open('POST',DataToSend,true);
				req.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
				req.setRequestHeader(header, csrfToken);
				req.send(JSON.stringify(sendData));
				
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

let detailIssue = {
		selectIssueDetail : (issue_id, issue_name, issue_detail, state_name, start, end, reg_id, state_code) => {
			console.log("들어옴" + issue_id);
			let popup = document.getElementById("issueDetailPopup");
			popup.style.display = 'block';
			
			document.getElementById('issueState').innerHTML=state_name;
			document.getElementById('issueInfo').innerHTML=issue_detail;
			document.getElementById('issueRegId').innerHTML=reg_id;
			document.getElementById('issueStartDate').innerHTML=start;
			document.getElementById('issueEndDate').innerHTML=end;
			document.getElementById('issue_id').value = issue_id;
			document.getElementById('now_state').value = state_code;
		}
}

