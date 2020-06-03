package com.example.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.CrawlData;
import com.example.repository.CrawlDataRepository;

@RestController
public class SaveHtmlRest {
	@Autowired
	CrawlDataRepository repo;
	@PostMapping("/insert-html")
	public String saveHtml(@RequestParam("time") String time, @RequestParam("html") String html) {
		CrawlData crawlData = new CrawlData();
		crawlData.setTime(time);
		crawlData.setHtml(html);
		this.repo.save(crawlData);
		return "lưu trang thành công!";
	}
}
