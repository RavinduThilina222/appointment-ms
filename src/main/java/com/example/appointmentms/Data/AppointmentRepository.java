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

    @Query("SELECT a FROM Appointment a WHERE a.Session_Id = :session_id AND a.Appointment_Id = :appointmentId")
    Appointment findByAppointment_Id(int session_id,int appointmentId);

    //write a method for find count of  all patient_id's by session id and status is not cancelled.
    @Query("select count(a.Patient_Id) from Appointment a where a.Session_Id = :session_id and a.Status != 'Cancelled'")
    int findCountOfAllPatientIdsBySessionId(@Param("session_id")int Session_Id);

    // Method to find all appointments available today
    @Query("SELECT a FROM Appointment a INNER JOIN Session s ON a.Session_Id = s.Session_Id WHERE s.Session_Date = :date")
    List<Appointment> findAllAppointmentsAvailableOnDate(@Param("date") String date);
}
