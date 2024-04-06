package com.example.appointmentms.Service;

import com.example.appointmentms.Data.Appointment;
import com.example.appointmentms.Data.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointmentsBySessionId(int sessionId){
        return appointmentRepository.findAllAppointmentsBySessionId(sessionId);
    }

    public List<Appointment> getAppointmentByReferenceNo(int Reference_No) {
        return appointmentRepository.findAppointmentByReferenceNo(Reference_No);
    }

    public List<Appointment> getAppointmentBySessionIdAndAppointmentId(int Session_Id,int Appointment_Id) {
        return appointmentRepository.findAppointmentBySessionIdAndAppointmentId(Session_Id,Appointment_Id);
    }

    public List<Appointment> findAllAppointmentsAvailableOnDateByPatientIdAndDate(String date,int patient_id) {
        return appointmentRepository.findAllAppointmentsAvailableOnDateByPatientIdAndDate(date, patient_id);
    }

    public List<Appointment> findAllUpcomingAppointmentsByPatientIdAndDate(String date, int patient_id) {
        return appointmentRepository.findAllUpcomingAppointmentsByPatientIdAndDate(date, patient_id);
    }

    public List<Appointment> findAllPastAppointmentsByPatientIdAndDate(String date, int patient_id) {
        return appointmentRepository.findAllPastAppointmentsByPatientIdAndDate(date, patient_id);
    }

    public int findCountOfAllPatientsBySessionId(int Session_Id) {
        return appointmentRepository.findCountOfAllPatientsBySessionId(Session_Id);
    }

    public int findMaxAppointmentNoBySessionId(int session_id) {
        return appointmentRepository.findMaxAppointmentNoBySessionId(session_id);
    }

    public Appointment addAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointmentStatusByAppointmentId(int session_id,int appointmentId,String appointmentStatus) {
        Appointment appointment = appointmentRepository.findByAppointment_Id(session_id,appointmentId);
        appointment.setStatus(appointmentStatus);
        return appointmentRepository.save(appointment);
    }

}
