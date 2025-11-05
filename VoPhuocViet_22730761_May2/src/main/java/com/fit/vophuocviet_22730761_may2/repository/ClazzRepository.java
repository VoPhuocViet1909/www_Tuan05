package com.fit.vophuocviet_22730761_may2.repository;

import com.fit.vophuocviet_22730761_may2.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClazzRepository extends JpaRepository<Clazz, Integer> {
    @Query("select c from Clazz c " +
            "where (:fromMonth is null or " +
            "MONTH(c.startDate) >= :fromMonth) and " +
            "(:toMonth is null or " +
            "MONTH(c.startDate) <= :toMonth)")
    List<Clazz> findByMonthStartDateBetween(int fromMonth, int toMonth);

    @Query("select c from Clazz c " +
            "where (:keyword is null or " +
            "lower(c.name) like lower(concat ('%', :keyword, '%'))) or " +
            "(lower(c.teacher.name) like (lower(concat('%', :keyword, '%') )))")
    List<Clazz> findByNameClassOrTeacherName(String keyword);
}
