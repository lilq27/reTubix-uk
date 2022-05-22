<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% String ctx = request.getContextPath(); %>
<c:set var="cp" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<!-- CSS============================================================ -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/whole.css" />		<!-- 전체 기본 스타일 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/topNav.css" />	<!-- 상단 내비게이션 바  -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/sideNav.css" />	<!-- 좌측 내비게이션 바 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />		<!-- 메인 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/admin.css" />		<!-- 관리자 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/channel.css" />	<!-- 내 채널 -->

<!--jQuery Google CDN=============================================== -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- 아이콘 라이브러리=================================================== -->
<script src="https://kit.fontawesome.com/d9fe37202c.js"></script>

<!-- lightslider==================================================== -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/lightslider.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" ></script>


<title>reTuLix</title>
<!-- 상단바====================================================================== -->
</head>
<div id="wrap">
	<ul class="topNav">
		<li class="topNavLeft" id="menuToggle"><i class="fa fa-bars"></i></li>
		<li class="topNavLeft">
			<a href="${pageContext.request.contextPath}/main">
				<img src="${pageContext.request.contextPath}/resources/images/logo-row.png" alt="logo" class="retulix_logo">
			</a>
		</li>
		<li class="topNavLeft"><a href="${pageContext.request.contextPath}/onlyMovie">영화</a></li>
		<li class="topNavLeft"><a href="${pageContext.request.contextPath}/onlyDrama">TV프로그램</a></li>
		<li class="topNavSearch">
			<form action="${pageContext.request.contextPath}/search" method='get' >
				<input id="searchbar" type="text" name="findKeyWord" placeholder="검색어를 입력하세요">
				<input type="hidden" value="${findType }">	
				<button><i class="fa fa-search"></i></button>
			</form>
		</li>
		<li class="topNavRight"><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
		<li class="topNavRight"><a href="${pageContext.request.contextPath}/chDoor"><i class="fa fa-cog"></i></a></li>
		<li class="topNavRight"><a href="${pageContext.request.contextPath}/admin"><i class="fa fa-star"></i></a></li>
	</ul>
</div>

<!-- 좌측바====================================================================== -->
<div id="wrap">
	<!-- topNav에 가려지는 부분 -->
	<div class="topNavFloor"></div>
	<div class="sideNav">
		<!-- .sideNav============== -->
		<!-- 상단 로그인 회원 정보 -->
		<div class="sideNavInfo">
			<span> <img src="${pageContext.request.contextPath}/resources/images/noUserIcon.png" alt="회원 이미지" />
			</span>
			<h1>${loginUser.name}</h1>
			<p>일반회원</p>
		</div>
		<!-- 중앙 메뉴 -->
		<div class="sideNavMenu">
			<ul>
				<li>
					<a href="${pageContext.request.contextPath}/main">
						<i class="fa fa-home"> 홈</i>
					</a>
				</li>
				<li>
					<c:if test="${zzimListSize ne 0}">		
					<a href="${pageContext.request.contextPath}/main#zzim"> 
						<i class="fa fa-heart"> ${loginUser.name}님이 찜한 영상</i>
					</a>
					</c:if>
					<c:if test="${zzimListSize eq 0}">		
						<i class="fa fa-heart"> ${loginUser.name}님을 위한 추천</i>
					</c:if>
				</li>
				
				<li>
					<c:if test="${historyListSize ne 0}">		
					<a href="${pageContext.request.contextPath}/main#history"> 
						<i class="fa fa-reply"> 최근에 본 영상</i>
					</a>
					</c:if>
					<c:if test="${historyListSize eq 0}">		
						<i>좋아요가 많은 영상</i>
					</c:if>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/main#click"> 
					<i class="fa fa-star"> 최근 인기 영상</i>
					</a>
				</li>
			</ul>
			<hr>
			
			<!-- 구독 리스트: 첫 로드시 최대 4행 -->
			<ul class="subscribe">
				<li class="subscribeHead">구독</li>
				<c:forEach var="sub" items="${email_subs}">
					<li class="moreSub">
						<a href="${sub.email}">
						<%-- <img src="${pageContext.request.contextPath}/resources/images/${sub.icon}"></img>${sub.name}</span> --%>
					 	<c:if test="${sub.icon eq 'noicon.png'}">
							<img src="${pageContext.request.contextPath}/resources/images/noUserIcon.png"></img>
							${sub.name}
						</c:if>
						<c:if test="${sub.icon ne 'noicon.png'}">
							<img src="${pageContext.request.contextPath}/resources/images/userIcon/${sub.icon}"></img>
							${sub.name}
						</c:if>
						</a>
					</li>
				</c:forEach>
			
				<li>
					<a href="#">
						<span class="more"><i class="fa fa-plus"></i>더보기</span>
					</a>
				</li>
			</ul>

			<hr>
			<div class="sideNavFoot">
				<ul>
					<li>
						<a href="${pageContext.request.contextPath}/noticeMain">
							<i class="fa fa-exclamation-circle"> 공지사항</i>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/chDoor">
							<i class="fa fa-cog"> 내 채널</i>
						</a>
					</li>
					<li>
						<a href="${pageContext.request.contextPath}/logout"> 
							<i class="fa fa-times"> 로그아웃</i>
						</a>
					</li>
				</ul>
				<hr>
				<button type="button">
					<i class="fab fa-product-hunt"> 포인트 충전하기</i>
				</button>
			</div>
		</div>
	</div><!-- .sideNav -->
</div><!-- #wrap -->
<div class="main" id="main"></div>