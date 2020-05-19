package com.example.modelobj;

public class RowForecast {
	private String label;
	private String detail;

	public RowForecast(String label, String detail) {
		super();
		this.label = label;
		this.detail = detail;
	}

	public RowForecast() {
		super();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
