
let index = {
	init: function() {
		$("#btn-save").on("click", () => { // this 바인딩
			this.save();
		});

	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		console.log(data);

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), 
			contentType: "application/json; charset=utf-8", 
			dataType: "json" //response dataType
		}).done(function(res) {
			alert("글 작성이 완료되었습니다.");
			location.href = "/board";
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},
	
}

index.init();