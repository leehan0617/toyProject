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
			
			let rootValue = document.getElementById("rootValue").value;
			const csrfToken = document.querySelector("meta[name=csrf-token]").getAttribute("content");
			console.log(csrfToken);
			
			let params = {"project_id":project_id};
			
			let esc = encodeURIComponent
			let query = Object.keys(params)
			             .map(k => esc(k) + '=' + esc(params[k]))
			             .join('&');

			 let url = rootValue+"/issue/projectMember?" + query
		   
			fetch(url).then(function(response) {
			    if (!response.ok) {
				      throw Error(response.statusText);
				    }
				    return response.json();
				  }).then((data) => {
				    console.log(data);
				  }).catch((e) => {
				    console.log(e);
		  });
		}
	
}

