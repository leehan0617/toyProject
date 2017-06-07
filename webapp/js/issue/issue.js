/**
 * 성명 : 송하람
 * 설명 : issue 관련 js파일
 * 
 */


let issue = {
		// 이슈 생성 팝업 뜨게하기 -> 내 프로젝트에 참여한 사람 가져온다.
		addIssuePopUp : function (project_id) {
			console.log(project_id);
			let popup = document.getElementById("issuePopup");
			popup.style.display = 'block';
			
			let root = document.getElementById("rootValue").value;
			document.getElementById("addIssueForm").action = root+"/issue/addIssue/" + project_id;
			
			
		}
	
}

