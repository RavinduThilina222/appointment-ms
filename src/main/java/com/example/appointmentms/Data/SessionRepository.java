package com.example.appointmentms.Data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    // Method to find all sessions by doctor id
    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId")
    List<Session> findAllSessionsByDoctorId(@Param("doctorId") int doctorId);

    // Method to find all sessions available today or on a specific date by doctor id
    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId AND s.Session_Date = :date")
    List<Session> findAllSessionsAvailableOnDateByDoctorId(@Param("doctorId") int doctorId, @Param("date") String date);

    // Method to find all sessions available in the future by doctor id
    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId AND s.Session_Date > :date")
    List<Session> findAllUpcomingSessionsByDoctorId(@Param("doctorId") int doctorId, @Param("date") String date);

    // Method to find all sessions available in the past by doctor id
    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId AND s.Session_Date < :date")
    List<Session> findAllPastSessionsByDoctorId(@Param("doctorId") int doctorId, @Param("date") String date);
}
