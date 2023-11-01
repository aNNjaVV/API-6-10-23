package com.cibertec.QuickSale.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cibertec.QuickSale.model.DetailsEvents;

public interface IDetailsEventsRepo extends JpaRepository<DetailsEvents, Integer>{

    @Query("SELECT d FROM DetailsEvents d WHERE d.event.idEvent = :idEvent")
    DetailsEvents filterxIdEvent(@Param("idEvent") int idEvent);
}
