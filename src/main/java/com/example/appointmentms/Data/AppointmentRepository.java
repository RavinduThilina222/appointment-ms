package com.example.appointmentms.Data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    
    @Query("select a from Appointment a where a.Session_Id = :session_id")
    List<Appointment> findAllAppointmentsBySessionId(@Param("session_id")int Session_Id);

    @Query("select a from Appointment a where a.Session_Id = :session_id and a.Appointment_Id = :appointment_id")
    List<Appointment> findAllAppointmentBySessionIdAndAppointmentId(@Param("session_id")int Session_Id,@Param("appointment_id")int Appointment_Id);

    @Query("UPDATE Appointment a SET a.Status = :status WHERE a.Appointment_Id = :appointment_id")
    void updateAppointmentStatus(@Param("appointment_id") int appointmentId, @Param("status") int status);
}
