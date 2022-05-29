
let index = {
	init: function(){
		$("#btn-save").on("click",() =>{ // this 바인딩
			this.save();
		});
	},	

	save:function(){
		let data = {
			username: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		console.log(data);
		
		//ajax 통신을 이용해서 parameter를 json으로 변경하여 request
		$.ajax({
			type:"POST",
			url: "/blog/api/member",
			data: JSON.stringify(data), //http body data
			contentType:"application/json; charset=utf-8", //request body dataType
			dataType:"json" //response dataType
		}).done(function(res){
			alert("회원가입이 완료되었습니다.");
			console.log(res);
			location.href="/blog";
		}).fail(function(err){
			alert(JSON.stringify(err))
		});	
	}
}

index.init();