**요약**

  개인 관련 자료 정리 및 토이 프로젝트를 구현하는 사이트입니다.

>계정 - 회원가입, 로그인 

>게시판 - 조회, 등록, 수정, 삭제

>댓글 - 조회, 등록, 삭제

>토이 프로젝트  - 채팅방, LOL 전적 검색, 웹 게임

**개발/운영 환경**

 Server는 Spring Boot 를 Clinet는 JSP를 이용하여 구현하였습니다.
 Ubuntu 기반의 인스턴스에 배포하였습니다. Database는 Oracle 기반의 Autonomous Data Warehouse를 이용하여 원격으로 사용합니다.

**Model & REST API**

 기본 조회 화면에 대해서는 viewResolver와 model을 이용하여 view와 데이터를 전달합니다.
 이외의 CURD에 대해서는 ajax를 이용하여 server측으로 REST API를 이용하여 데이터의 전달하였습니다.
 데이터 요청에 맞게 get, post, put, patch, delete 메소드를 이용하여 구현하였습니다.

**Spring Security & oAuth를 이용한 인증/인가 구현**

 기본 회원가입/로그인 이외에 카카오 oAuth를 이용하여 회원가입/로그인을 진행하고 있습니다.
 Spring Security를 이용하여 Server측으로 오는 요청을 관리 및 권한에 따른 기능을 제한 하고 있습니다.

**JPA/QueryDSL**

 ORM을 이용하여 Entity와 Database Table을 매핑하고 있습니다.
 JpaRepository 인터페이스에서 Named Query와 NativeQuery를 사용하여 쿼리를 생성하였습니다.

**ToyProject** 

**Canvas 웹게임 -** HTML Canvas를 활용하여 간단한 웹게임을 구현하였습니다.
고양이 캐릭터를 재생되는 화살을 피하는 게임으로 충돌 처리시 게임이 종료됩니다.

**RiotApi -** League Of Legend 의 계정 정보와 최근 5경기의 전적을 조회하는 기능을 구현 하였습니다. api-key가 만료 되면 dummy 데이터의 정보를 조회하고 있습니다.

**WebSocket 채팅-** WebSocket 를 활용하여 간단한 멀티 채팅을 구현하였습니다.
로그인 사용자의 경우에는 계정id로 비로그인 사용자의 경우 랜덤아이디를 생성합니다.
