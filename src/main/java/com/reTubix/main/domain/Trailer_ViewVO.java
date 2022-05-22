package com.reTubix.main.domain;

import lombok.Data;

@Data
public class Trailer_ViewVO {
	
	private String idx;
	
	private String api_idx;
	
	private String url;
	
	private String title;
	
	private int good;
	
	private int click;
	
	private int zzim;
	
	private String email;

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getApi_idx() {
		return api_idx;
	}

	public void setApi_idx(String api_idx) {
		this.api_idx = api_idx;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	public int getZzim() {
		return zzim;
	}

	public void setZzim(int zzim) {
		this.zzim = zzim;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
