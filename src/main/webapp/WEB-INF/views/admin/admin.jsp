<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<jsp:include page="../top.jsp"></jsp:include>
<body>
	<ul>
		<li class="topNavSearch">
			<form action="${pageContext.request.contextPath}/search" method='get'>
				<input id="Searchbar1" type="text" name="findKeyWord" placeholder="영화/드라마">
				<input id="Searchbar2" type="text" name="findKeyWord" placeholder="제목">
				<input id="Searchbar3" type="text" name="findKeyWord" placeholder="년도">
				<input type="hidden" value="${findType}">	
				<button><i class="fa fa-search"></i></button>
			</form>
		</li>
	</ul>
	<div id="SearchReTurn"></div>
</body>

<script>
$(function() {
	var apiKey = "44a1e09be53f66de396d8c69acba58c6";
	var whichGenre = "multi";
	var title = null;
	var year = null;
	var language = "한국어";
	var releaseYear = null;
	var imageURL = "https://image.tmdb.org/t/p/w200";
	
	$('#Searchbar1').keyup(function() {
		whichGenre = $("#Searchbar1").val();
		
		switch(whichGenre) {
		case "영화":
			whichGenre = "movie";
			releaseYear = "primary_release_year";
			break;
		case "드라마":
			whichGenre = "tv";
			releaseYear = "first_air_date_year";
			break;
		}
		ajaxUrl();
	});
	
	$('#Searchbar2').keyup(function() {
		title = $("#Searchbar2").val();
		ajaxUrl();
	});
	
	$('#Searchbar3').keyup(function() {
		year = $("#Searchbar3").val();
		ajaxUrl();
	});
	
	switch(language) {
	case "한국어":
		language = "ko";
		break;
	case "영어":
		language = "en-US";
		break;
	}
	
	function ajaxUrl() {
		var url = "${cp}/adminSearchInfo?whichGenre="+ whichGenre +"&apiKey=" + apiKey + "&language=" + language + "&title=" + encodeURI(title) + "&releaseYear=" + releaseYear + "&year=" + year;
		$.ajax({
			url: url,
			type: "get",
			success: function(data) {
			$("#SearchReTurn").empty();
				$.each(data.results, function(key, value) {
					console.log(value);
					console.log(url)
					if(value.poster_path != null) {
						var image = imageURL + value.poster_path;
						$("#SearchReTurn").append("<img src='" + image + "'>");
					}
				});
			}
		});
	}
});
//http://localhost:9090/retubix/searchInfo?whichGenre=movie&apiKey=44a1e09be53f66de396d8c69acba58c6&language=ko&title=%EC%8B%A0%EC%84%B8%EA%B3%84&releaseYear=primary_release_year&year=2013
//&append_to_response=credits
		
</script>