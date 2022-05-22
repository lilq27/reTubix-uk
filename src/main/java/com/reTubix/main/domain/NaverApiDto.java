package com.reTubix.main.domain;

public class NaverApiDto {

	/* 배우*/
	private String actor;
	
	/* 감독*/
	private String director;
	
	/* 이미지*/
	private String image;
	
	/* 링크*/
	private String link;
	
	/* 개봉일*/
	private String pubDate;
	
	/* 부제*/
	private String subtitle;
	
	/* 제목*/
	private String title;
	
	/* 평점*/
	private String userRating;

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserRating() {
		return userRating;
	}

	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}
	
}
