package com.example.appointmentms.Data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionRepository extends JpaRepository<Session, Integer> {

    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId")
    List<Session> findByDoctorId(@Param("doctorId") int doctorId);

    @Query("UPDATE Session s SET s.Session_Status = :status WHERE s.Session_Id = :id")
    void updateStatus(@Param("id") int id, @Param("status") String status);

}
