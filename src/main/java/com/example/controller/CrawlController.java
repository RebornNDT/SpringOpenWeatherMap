package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.entities.BookMark;
import com.example.entities.CrawlData;
import com.example.modelobj.RowForecast;
import com.example.repository.CrawlDataRepository;

@Controller
public class CrawlController {
	@Autowired
	CrawlDataRepository repo;
	@GetMapping("/crawler")
	public String craw(Model model, @RequestParam(name = "lat",defaultValue = "40.7146") String lat, @RequestParam(name = "lon",defaultValue = "-74.0071") String lon) throws IOException {
		Connection conn = Jsoup.connect("https://forecast.weather.gov/MapClick.php?lat="+lat+"&lon="+lon);
		Document doc = conn.get();
		Elements es = doc.select(".panel-heading .panel-title");
		if(es.hasText()) {
			model.addAttribute("diadiem",es.get(0).text());
			Elements locations = doc.select(".panel-heading .smallTxt");
			model.addAttribute("locationHtml", locations.html());
			Elements tempInforsF = doc.select("#current_conditions-summary .myforecast-current-lrg");
			model.addAttribute("nhietDoF", tempInforsF.get(0).text());
			Elements tempInforsC = doc.select("#current_conditions-summary .myforecast-current-sm");
			model.addAttribute("nhietDoC", tempInforsC.get(0).text());
			Elements tableCurrents = doc.select("#current_conditions_detail tbody");
			model.addAttribute("tableCurrentHtml", tableCurrents.get(0).html());
			Elements tdsTag = doc.select("#current_conditions_detail tbody tr td");
			model.addAttribute("doamhientai",tdsTag.get(1).text());
			model.addAttribute("tocdogiohientai",tdsTag.get(3).text());
			model.addAttribute("apsuathientai",tdsTag.get(5).text());
			model.addAttribute("diemsuonghientai",tdsTag.get(7).text());
			model.addAttribute("tamnhinxahientai",tdsTag.get(9).text());
			if(tdsTag.size()>12) {
				model.addAttribute("chisonhiet", tdsTag.get(11).text());
				model.addAttribute("lancapnhatcuoi",tdsTag.get(13).text());
			}else {
				model.addAttribute("chisonhiet", "Không có dữ liệu");
				model.addAttribute("lancapnhatcuoi",tdsTag.get(11).text());
			}
			Elements forecastSevenCards = doc.select("#seven-day-forecast-body #seven-day-forecast-container #seven-day-forecast-list");
			model.addAttribute("cardSevenForecast",forecastSevenCards.get(0).html());
			List<RowForecast> Details = new ArrayList<RowForecast>();
			Elements rowForecasts = doc.select("#detailed-forecast #detailed-forecast-body .row-forecast");
			for (Element element : rowForecasts) {
				Details.add(new RowForecast(element.select(".forecast-label").text(), element.select(".forecast-text").text()));
			}
			model.addAttribute("detailsRow", Details);
			model.addAttribute("vido", Double.valueOf(lat));
			model.addAttribute("kinhdo", Double.valueOf(lon));
			return "crawl";
		}else {
			return "crawlerror";
		}
	}
	@GetMapping("/bookmark-crawl")
	public String listBookmark(Model model) {
		Iterable<CrawlData> crawls = this.repo.findAll();
		List<CrawlData> temp = new ArrayList<CrawlData>();
		for (CrawlData crawlData : crawls) {
			temp.add(crawlData);
		}
		model.addAttribute("items",temp);
		return "bookmark-crawl";
	}
	@GetMapping("/bookmark-crawl/{id}")
	@ResponseBody
	public String detail(@PathVariable("id") String id) {
		Iterable<CrawlData> temps = this.repo.findAll();
		CrawlData cr = new CrawlData();
		for (CrawlData crawlData : temps) {
			if(crawlData.getId() == Long.valueOf(id)) {
				cr = crawlData;
				break;
			}
		}
		String contentHtml = "<!DOCTYPE html>" + cr.getHtml();
		return contentHtml;
	}
}
