
let index = {
	init: function(){
		$("#btn-save").on("click",() =>{
			this.save();
		});
	},	

	save:function(){
		let data = {
			id: $("#id").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		//console.log(data);
		
		$ajax().done().fail();	//ajax 통신을 이용해서 parameter를 json으로 변경하여 request
	}
}

index.init();