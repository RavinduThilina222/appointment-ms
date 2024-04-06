package com.example.appointmentms.Data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
    //Method to find all appointments by session id
    @Query("SELECT a FROM Appointment a WHERE a.Session_Id = :session_id")
    List<Appointment> findAllAppointmentsBySessionId(@Param("session_id")int Session_Id);

    //Method to find all appointments by reference number
    @Query("SELECT a FROM Appointment a WHERE a.Reference_No = :reference_no")
    List<Appointment> findAppointmentByReferenceNo(@Param("reference_no")int Reference_No);

    //Method to find appointment by session id and appointment id
    @Query("SELECT a FROM Appointment a WHERE a.Session_Id = :session_id AND a.Appointment_Id = :appointment_id")
    List<Appointment> findAppointmentBySessionIdAndAppointmentId(@Param("session_id")int Session_Id,@Param("appointment_id")int Appointment_Id);

    //Method to find appointment by session id and appointment id
    @Query("SELECT a FROM Appointment a WHERE a.Session_Id = :session_id AND a.Appointment_Id = :appointmentId")
    Appointment findByAppointment_Id(int session_id,int appointmentId);

    //Method to find count of all patients by session id and status not cancelled
    @Query("SELECT COUNT(a.Patient_Id) FROM Appointment a WHERE a.Session_Id = :session_id AND a.Status != 'Cancelled'")
    int findCountOfAllPatientsBySessionId(@Param("session_id")int Session_Id);

    //Method to find Max appointment no of appointment table for a specific session id
    @Query("SELECT MAX(a.Appointment_Id) FROM Appointment a WHERE a.Session_Id = :session_id")
    int findMaxAppointmentNoBySessionId(int session_id);

    // Method to find all appointments available today or on a specific date by patient id and date
    @Query("SELECT a FROM Appointment a INNER JOIN Session s ON a.Session_Id = s.Session_Id WHERE s.Session_Date = :date AND a.Patient_Id = :patient_id")
    List<Appointment> findAllAppointmentsAvailableOnDateByPatientIdAndDate(@Param("date") String date, @Param("patient_id") int patient_id);

    // Method to find all appointments available in the future by patient id and date
    @Query("SELECT a FROM Appointment a INNER JOIN Session s ON a.Session_Id = s.Session_Id WHERE s.Session_Date > :date AND a.Patient_Id = :patient_id")
    List<Appointment> findAllUpcomingAppointmentsByPatientIdAndDate(@Param("date") String date, @Param("patient_id") int patient_id);

    // Method to find all appointments available in the past by patient id and date
    @Query("SELECT a FROM Appointment a INNER JOIN Session s ON a.Session_Id = s.Session_Id WHERE s.Session_Date < :date AND a.Patient_Id = :patient_id")
    List<Appointment> findAllPastAppointmentsByPatientIdAndDate(@Param("date") String date, @Param("patient_id") int patient_id);

}
