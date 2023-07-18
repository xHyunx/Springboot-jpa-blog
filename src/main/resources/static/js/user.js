let index = {
	init : function() {
		$("#btn-save").on("click", ()=>{ // 화살표 함수 사용이유는 this를 바인딩하기 위해서임
			this.save();
		});
	},	
	
	save : function() {
		// alert("user의 save함수 호출됨");
		let data = {
			username : $("#username").val(),
			password : $("#password").val(),
			email : $("#email").val()
		}
		
		// ajax는 기본적으로 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		$.ajax({
			type: "POST",
			url:"/blog/api/user",
			data: JSON.stringify(data),
			contentType : "application/json; charset=utf-8",
			dataType : "json"
		}).done(function(resp){ // 요청을 서버로부터 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 오브젝트
			alert("회원가입이 완료되었습니다.");
			location.href = "/blog";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
}

index.init();