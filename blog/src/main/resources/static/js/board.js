
let index = {
	init: function() {
		$("#btn-save").on("click", () => { // this 바인딩
			this.save();
		});

		$("#btn-delete").on("click", () => { // this 바인딩
			this.deleteById();
		});
		$("#btn-update").on("click", () => { // this 바인딩
			this.update();
		});

		$("#btn-reply-save").on("click", () => {
			this.replySave();
		})
	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {
			alert("글 작성이 완료되었습니다.");
			location.href = "/board/boardList";
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},


	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json",
			contentType: "application/json; charset=utf-8"
		}).done(function(res) {
			alert("삭제가 완료되었습니다.");
			location.href = "/board/boardList/";
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},

	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: $("#content").val(),
		}
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {
			alert("글 수정이 완료되었습니다.");
			location.href = "/board/boardList/";
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},

	
	replySave: function() {
		let data = {
			content: $("#reply-content").val()
		}
		
		let boardId =  $("#boardId").val();

		$.ajax({
			type: "POST",
			url: `/api/board/${boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {
			alert("댓글 작성이 완료되었습니다.");
			location.href = `/board/boardDetail/${boardId}`;
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},


}

index.init();