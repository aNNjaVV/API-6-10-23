package com.cibertec.QuickSale.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cibertec.QuickSale.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IEventRepo extends JpaRepository<Event, Integer>{

    @Query("SELECT v FROM Event v WHERE v.title = :title")
    Event findEventByName (@Param("title") String title);

    @Query("SELECT v FROM Event  v WHERE v.category.idCategory = :idCategory")
    Event filterEventByCategory(@Param("idCategory") Integer idCategory);

}
