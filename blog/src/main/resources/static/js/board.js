
let index = {
	init: function() {
		ck = CKEDITOR.replace('ckEditor',{
			filebrowserUploadUrl: '/board/ckUpload'
		});
		CKEDITOR.config.height = 400;


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
			this.replySaveDto();
		});


	},

	save: function() {
		let data = {
			title: $("#title").val(),
			content: CKEDITOR.instances.content.getData(),
			category: $("#category").val(),
		}

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {
			if (res.status === 500) {
				alert("글 작성이 실패하였습니다. \n" + res.data);
			}
			else {
				alert("글 작성이 완료되었습니다.");
				location.href = "/board/boardList";
			}

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
			if (res.status === 500) {
				alert("글 삭제가 실패하였습니다. \n" + res.data);
			}
			else {
				alert("글 삭제가 완료되었습니다.");
				location.href = "/board/boardList/";
			}


		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},

	update: function() {
		let id = $("#id").val();

		let data = {
			title: $("#title").val(),
			content: CKEDITOR.instances.content.getData(),
		}
		$.ajax({
			type: "PUT",
			url: "/api/board/" + id,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {
			if (res.status === 500) {
				alert("글 수정이 실패하였습니다. \n" + res.data);
			}
			else {
				alert("글 수정이 완료되었습니다.");
				location.href = "/board/boardList/";
			}
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},


	replySave: function() {
		let data = {
			content: $("#reply-content").val()
		}

		let boardId = $("#boardId").val();

		$.ajax({
			type: "POST",
			url: `/api/board/${boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {
			if (res.status === 500) {
				alert("댓글 작성이 실패하였습니다. \n" + res.data);
			}
			else {
				alert("댓글 작성이 완료되었습니다.");
				location.href = `/board/boardDetail/${boardId}`;
			}
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},

	replySaveDto: function() {
		let data = {
			content: $("#reply-content").val(),
			boardId: $("#boardId").val(),
			memberId: $("#memberId").val(),

		}

		$.ajax({
			type: "POST",
			url: `/api/board/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {

			if (res.status === 500) {
				alert("댓글 작성이 실패하였습니다. \n" + res.data);
			}
			else {
				alert("댓글 작성이 완료되었습니다.");
				location.href = `/board/boardDetail/${data.boardId}`;
			}
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},

	replyDelete: function(boardId, replyId) {
		$.ajax({
			type: "DELETE",
			url: `/api/board/${boardId}/reply/${replyId}`,
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {
			if (res.status === 500) {
				alert("댓글 삭제가 실패하였습니다. \n" + res.data);
			}
			else {
				alert("댓글 삭제가 완료되었습니다.");
				location.href = `/board/boardDetail/${boardId}`;
			}
		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	},


}

index.init();
