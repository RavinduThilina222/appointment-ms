package com.example.appointmentms.Data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionRepository extends JpaRepository<Session, Integer> {
    // Method to find all sessions by doctor id
    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId")
    List<Session> findAllSessionsByDoctorId(@Param("doctorId") int doctorId);

    // Method to find all sessions available today or on a specific date by doctor id and date
    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId AND s.Session_Date = :date")
    List<Session> findAllSessionsAvailableOnDateByDoctorIdAndDate(@Param("doctorId") int doctorId, @Param("date") String date);

    // Method to find all sessions available in the future by doctor id and date
    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId AND s.Session_Date > :date")
    List<Session> findAllUpcomingSessionsByDoctorIdAndDate(@Param("doctorId") int doctorId, @Param("date") String date);

    // Method to find all sessions available in the past by doctor id and date
    @Query("SELECT s FROM Session s WHERE s.Doctor_Id = :doctorId AND s.Session_Date < :date")
    List<Session> findAllPastSessionsByDoctorIdAndDate(@Param("doctorId") int doctorId, @Param("date") String date);
}
