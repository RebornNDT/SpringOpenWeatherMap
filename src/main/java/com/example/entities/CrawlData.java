package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "crawl_data")
public class CrawlData {
	@Id
	@GeneratedValue
    private Long id;
	
	@Column(name = "time",length = 255)
	private String time;
	
	@Column(name = "html", columnDefinition = "NVARCHAR(MAX)")
	private String html;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
	
}
