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

    public Appointment addAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsBySessionId(int sessionId){
        return appointmentRepository.findAllAppointmentsBySessionId(sessionId);
    }

    public List<Appointment> getAppointmentByReferenceNo(int Reference_No) {
        return appointmentRepository.findAppointmentByReferenceNo(Reference_No);
    }
    
    public Appointment updateAppointmentStatus(int session_id,int appointmentId, String appointmentStatus) {
        Appointment appointment = appointmentRepository.findByAppointment_Id(session_id,appointmentId);
        appointment.setStatus(appointmentStatus);
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAllAppointmentsAvailableOnDate(String date,int patient_id) {
        return appointmentRepository.findAllAppointmentsAvailableOnDate(date, patient_id);
    }

    public List<Appointment> findAllUpcomingAppointments(String date, int patient_id) {
        return appointmentRepository.findAllUpcomingAppointments(date, patient_id);
    }

    public List<Appointment> findAllPastAppointments(String date, int patient_id) {
        return appointmentRepository.findAllPastAppointments(date, patient_id);
    }

    public int findCountOfAllPatientsBySessionId(int Session_Id) {
        return appointmentRepository.findCountOfAllPatientsBySessionId(Session_Id);
    }

}
