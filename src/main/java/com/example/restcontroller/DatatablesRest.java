package com.example.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.BookMark;
import com.example.entities.Datatables;
import com.example.repository.BookMarkRepository;

import java.util.ArrayList;
import java.util.List;
@RestController
public class DatatablesRest {
	@Autowired
    private BookMarkRepository bookMarkRepository;
	@GetMapping("/data-bookmark")
	public ResponseEntity<Datatables> getDataBookmarked(){
		List<List<String>> datatables = new ArrayList<List<String>>();
		Iterable<BookMark> bookmarks = this.bookMarkRepository.findAll();
		for (BookMark bookMark : bookmarks) {
			List<String> array = new ArrayList<String>();
			array.add(bookMark.getId().toString());
			array.add(bookMark.getDiaDiem());
			array.add(bookMark.getThoiGianLuu());
			array.add(bookMark.getNhietDo());
			array.add(bookMark.getTocDoGio());
			array.add(bookMark.getMay());
			array.add(bookMark.getApSuat());
			array.add(bookMark.getDoAm());
			array.add(bookMark.getToaDo());
			datatables.add(array);
		}
		Datatables responseObj = new Datatables();
		responseObj.setData(datatables);
		return new ResponseEntity<Datatables>(responseObj,HttpStatus.OK);
	}
	@PostMapping("/insert-bookmark")
	public String insertData(@RequestParam("diadiem") String diaDiem,
			@RequestParam("thoigianluu") String thoiGianLuu, @RequestParam("nhietdo") String nhietDo,
			@RequestParam("tocdogio") String tocDoGio, @RequestParam("may") String May, @RequestParam("apsuat") String apSuat,
			@RequestParam("toado") String toaDo, @RequestParam("doam") String doAm) {
		//long id = bookMarkRepository.getMaxId() + 1;
		long id = 1;
		BookMark b = new BookMark();
		//b.setId(id);
		b.setDiaDiem(diaDiem);
		b.setThoiGianLuu(thoiGianLuu);
		b.setNhietDo(nhietDo);
		b.setTocDoGio(tocDoGio);
		b.setMay(May);
		b.setApSuat(apSuat);
		b.setDoAm(doAm);
		b.setToaDo(toaDo);
		this.bookMarkRepository.save(b);
		return "Lưu thời tiết thành công!";
	}
	@PostMapping("/delete-bookmark")
	public String deleteData(@RequestParam("id") String id) {
		this.bookMarkRepository.deleteById(Long.valueOf(id.trim()));
		return "Xóa thành công!";
	}
}
