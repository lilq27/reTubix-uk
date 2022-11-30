<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<script>
$(function() {
	/**
	var movieName = $(".movieTitle").data("title");
	MovieAPI(movieName);
	function MovieAPI(movieName) {
		$.ajax({
			url : "${cp}/naverAPI/movieList?movieName=" + movieName,
			type : "get",
			dataType : "json",
			contentType: "application/json; charset=UTF-8",
			success : function(data) {
				$.each(data, function(key, value) {
					
				});
				
			}, error : function(data) {
			}
		});
	}
	*/
	
	init();
	
	function init() {
		findImg("tredingMovie", 1);
		findImg("tredingTV", 2, true);
// 		findImg("latestMovie", 3);
// 		findImg("latestTV", 4);
	}
	
	function findImg(url, idKey, isLast) {
		var imageURL = "https://image.tmdb.org/t/p/w200";
		$.ajax({
			url: "${cp}/" + url,
			type: "get",
			success: function(data) {
				console.log(data)
				$("#SearchReTurn").empty();
				$.each(data.results, function(key, value) {
					if(value.poster_path != null) {
						var image = imageURL + value.poster_path;
						$("#MovieTitle_" + idKey).append(
								"<li class='col-6 col-lg-2 animate-in-down'>" +
								"<a href='user/showMovie?id="+ value.id + "'>" +
								"<img src='" + image + "'class='center-block img-fluid my-3' height='230px' width='158px'>" +
								"</a></li>"
						);
					}
				});
				console.log(isLast);
				if(isLast) {
					console.log(idKey);
					lightSlider();
				}
			}
		});
	}
	
	function lightSlider() {
		$(".content-slider").lightSlider({
        	item:9,
	        loop:true,
	        keyPress: true,
	        easing: 'cubic-bezier(0.25, 0, 0.25, 1)',
	        responsive : [
	        	{
	                breakpoint:1845,
	                settings: {
	                    item:10,
                  	}
	            },
	            {
	                breakpoint:1760,
	                settings: {
	                    item:8,
                  	}
	            },
	            {
	                breakpoint:1565,
	                settings: {
                    	item:7,
                 	}
	            },
	            {
	                breakpoint:1410,
	                settings: {
	                    item:6,
                  	}
	            },
	            {
	                breakpoint:1350, 
	                settings: {
	                    item:5,
                  	}
	            },
	            {
	                breakpoint:1085,
	                settings: {
	                    item:4,
                  	}
	            },
	            {
	                breakpoint:930,
	                settings: {
	                    item:3,
                 	}
	            },
	            {
	                breakpoint:780,
	                settings: {
	                    item:2,
                  	}
	            },
	            {
	                breakpoint:650,
	                settings: {
	                    item:1,
                  	}
	            }
	        ]
    	});  
  	}
});
</script>