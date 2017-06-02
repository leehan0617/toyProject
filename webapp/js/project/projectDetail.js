window.onload = function () {
	let movie_name = eval(document.getElementById("projectDep"));
	console.log(movie_name)
// 	alert("who"+ movie_name);
	let checkbox = document.getElementsByName("departcodeList");
	for (let i=0; i<checkbox.length; i++) {
	    
		for (let j=0; j<movie_name.result.length; j++) {
			if(checkbox[i].value == movie_name.result[j].depart_code){
				document.getElementById(checkbox[i].value).checked = true;
				document.getElementById(checkbox[i].value+"_count").style.display = "inline-table";
				document.getElementById(checkbox[i].value+"_select").options[movie_name.result[j].usercount-1].selected = true;
			}
		}
	 }
	
}