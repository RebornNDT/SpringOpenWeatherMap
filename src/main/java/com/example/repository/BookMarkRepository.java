package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.entities.BookMark;



public interface BookMarkRepository extends CrudRepository<BookMark, Long> {
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Query("SELECT coalesce(max(b.id), 0) FROM BOOK_MARK b")
//    Long getMaxId();
}
