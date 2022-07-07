
let index = {
	init: function() {
		$("#btn-search").on("click", () => { // this 바인딩
			this.search();
		});
	},

	save: function() {
		username = $("#username").val(),

		$.ajax({
			type: "POST",
			url: "/api/riot-api/" + username,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json" //response dataType
		}).done(function(res) {
			if (res.status === 403) {
				alert("API Key가 만료 되었습니다. 더미 데이터로 조회합니다.");
				location.href = "/toy/riot-api/dummy-data";
			}
			else if (res.status === 500) {
				alert("전적 검색에 실패하였습니다. \n" + res.data);
			}
			else {
				alert("글 작성이 완료되었습니다.");
			}

		}).fail(function(err) {
			alert(JSON.stringify(err))
		});
	}
}

index.init();