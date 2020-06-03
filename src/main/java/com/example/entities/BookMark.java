package com.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BOOK_MARK")
public class BookMark {
	@Id
	@GeneratedValue
    private Long id;
	
	@Column(name = "DiaDiem", length = 128, nullable = false)
    private String diaDiem;

	@Column(name = "ThoiGianLuu", length = 128, nullable = false)
    private String thoiGianLuu;
	
	@Column(name = "NhietDo", length = 128, nullable = false)
	private String nhietDo;
	
	@Column(name = "TocDoGio", length = 128, nullable = false)
	private String tocDoGio;
	
	@Column(name = "May", length = 128, nullable = false)
	private String May;
	
	@Column(name = "ApSuat", length = 128, nullable = false)
	private String apSuat;
	
	@Column(name = "DoAm", length = 128, nullable = false)
	private String doAm;
	
	@Column(name = "ToaDo", length = 128, nullable = false)
	private String toaDo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public String getThoiGianLuu() {
		return thoiGianLuu;
	}

	public void setThoiGianLuu(String thoiGianLuu) {
		this.thoiGianLuu = thoiGianLuu;
	}

	public String getNhietDo() {
		return nhietDo;
	}

	public void setNhietDo(String nhietDo) {
		this.nhietDo = nhietDo;
	}

	public String getTocDoGio() {
		return tocDoGio;
	}

	public void setTocDoGio(String tocDoGio) {
		this.tocDoGio = tocDoGio;
	}

	public String getMay() {
		return May;
	}

	public void setMay(String may) {
		May = may;
	}

	public String getApSuat() {
		return apSuat;
	}

	public void setApSuat(String apSuat) {
		this.apSuat = apSuat;
	}

	public String getToaDo() {
		return toaDo;
	}

	public void setToaDo(String toaDo) {
		this.toaDo = toaDo;
	}

	public String getDoAm() {
		return doAm;
	}

	public void setDoAm(String doAm) {
		this.doAm = doAm;
	}
	
}
