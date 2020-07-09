function getParameterValue(){
	var name = "name="+$("#name").val();
	var kor = "kor="+$("#kor").val();
	var eng="eng="+$("#eng").val();
	var math = "math="+$("#math").val();
	
	return "?"+name+"&"+kor+"&"+eng+"&"+math;
}

function load(){
	var url = "CalScore" +getParameterValue();
	
	httpRequest = new XMLHttpRequest();			//서버와 통신
	httpRequest.onreadystatechange=callback;	//서버로 보낸 요청에 대한 응답을 받았을때
												//어떤 동작을 할 것인지
	httpRequest.open("GET",url, true);
	httpRequest.send();
	
}
function callback(){
	alert("readyState: " + httpRequest.readyState);
	
	if(httpRequest.readyState == 4){		//통신이 완료 됐을 떄
		alert("status: "+ httpRequest.status);
		
		
		if(httpRequest.status == 200){
			var obj = JSON.parse(httpRequest.responseText);
			$("#result").html(obj.name="의 총점은" + obj.sum+"이고, 평균은"+obj.avg+"입니다");
			
		}else{
			alert("데이터를 계싼할 수 없습니다.");
		}
		
	
		
	}
	
}

$(function(){
	$("#process").click(function(){
		load();
		
	});
	
});

/*
 * 1:loading - 로드 중
 * 2:loaded - 로드 됨
 * 3:interactive - 통신됨
 * 4:complete - 통신완료
 *
 * -status
 * 200: 성공
 * 400: not found
 * 500: internal server error
 * 
 */

