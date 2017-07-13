let common = {
		goPaging:(url, currentNum, pageCount)=>{
			
			var preurl = "";
			var posturl = "";
			
			if(url.indexOf("?") != -1){
			
				preurl = url.substring(0,url.indexOf("?"))
				posturl = url.substring(url.indexOf("?"));
			
			}else{
				preurl = url;
			}
			
			location.href = preurl+ "/" + currentNum +posturl;
		}
}