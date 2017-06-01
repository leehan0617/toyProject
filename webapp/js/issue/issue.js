/**
 * 성명 : 송하람
 * 설명 : issue 관련 js파일
 * 
 */


let issue = {
		addIssuePopUp : function () {
			let popup = document.getElementById("issuePopup");
			popup.style.display = 'block';
		}
	
}

const upload = (formData) => {

	  let headers = new Headers({
	    "x-requested-with": "XMLHttpRequest",
	    "accept": "application/json"
	  });

	  fetch("/photoajax", {
	    method: "POST",
	    mode: "same-origin",
	    credentials: "same-origin",
	    body: formData,
	    headers: headers
	  }).then(function(response) {
	    if (!response.ok) {
	      throw Error(response.statusText);
	    }
	    return response.json();
	  }).then((data) => {
	    updateForm(data["name"], data["url"]);
	  }).catch((e) => {
	    console.log(e);
	  });
	}