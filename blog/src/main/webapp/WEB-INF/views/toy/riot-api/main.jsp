<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file="../../layout/header.jsp"%>


<body class="">
	<div class="d-flex" id="wrapper">
		<%@include file="../../layout/toySideBar.jsp"%>
		<div id="page-content-wrapper">
			<%@include file="../../layout/nav.jsp"%>

			<div id="content" class="container-fluid ">
				<br />
				<div class="clearfix container-fluid">
					<div class="float-left">
						<button class="btn btn-primary" id="sidebarToggle"><></button>

					</div>
				</div>
				<br />
				<div class="ml-5 mr-5 mb-3">
					<h2>Riot-API</h2>
					라이엇 API 를 이용하여 리그오브레전드 전적정보를 검색합니다.<br /> API Key가 만료 될 시 더미데이터를 이용합니다. <br />개발 일지 - <a href="https://gomawoomi.tistory.com/55">https://gomawoomi.tistory.com/55</a>
				</div>
				<div class="ml-5 mr-5 mb-5">
					<div>
						<form action="/toy/riot-api" method="get">
							<div class="card-body row no-gutters align-items-center">
								<div class="col">
									<input name="username" class="form-control form-control-lg form-control-borderless" type="search" placeholder="소환사명을 입력하세요">
								</div>
								<div class="col-auto">
									<button type="submit" class="btn btn-lg btn-primary">&nbsp검색&nbsp</button>
								</div>
							</div>
						</form>
						<c:if test="${!empty riotInfo}">

							<div class="ml-5 mr-5 mb-5">
								<br />
								<h3>Lv ${riotInfo.summonerLevel} ${riotInfo.name}</h3>
								<c:choose>
									<c:when test="${!empty riotInfo.tier}">
										<h5>${riotInfo.tier}&nbsp${riotInfo.rank}- ${riotInfo.leaguePoints}p</h5>
									</c:when>
									<c:otherwise>
										<h5>Unranked</h5>
									</c:otherwise>
								</c:choose>
								<br />
								<h5>[최근 5 경기]</h5>
								<c:choose>
									<c:when test="${!empty riotInfo.matchs}">
										<table>
											<c:forEach var="match" items="${riotInfo.matchs}">
												<tr>
													<td>&nbsp${match.win}&nbsp</td>
													<td><c:if test="${!empty match.gameEndTimestamp}">&nbsp${fn:substring(match.gameEndTimestamp,0,10)}&nbsp</c:if></td>
													<td>&nbsp${match.gameMode}&nbsp</td>
													<td>&nbsp${match.championName}&nbsp</td>
													<td>&nbsp${match.kills}/${match.deaths}/${match.assists}&nbsp</td>
												</tr>
											</c:forEach>
										</table>
									</c:when>
									<c:otherwise>
										<h5>최근 경기가 없습니다.</h5>
									</c:otherwise>
								</c:choose>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<script src="/js/toy/riot-api.js"></script>
	</div>

</body>
</html>