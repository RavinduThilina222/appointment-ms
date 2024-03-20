package com.example.appointmentms.Service;

import com.example.appointmentms.Data.Appointment;
import com.example.appointmentms.Data.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmemtService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @SuppressWarnings("null")
    public Appointment addAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsbySessionId(int sessionId){
        return appointmentRepository.findAllAppointmentsBySessionId(sessionId);
    }

    public List<Appointment> getAllAppointmentbySessionId(int sessionId, int appointmentId){
        return appointmentRepository.findAllAppointmentBySessionIdAndAppointmentId(sessionId, appointmentId);
    }
    
    public Appointment updateAppointmentStatus(int session_id,int appointmentId, String appointmentStatus) {
        Appointment appointment = appointmentRepository.findByAppointment_Id(session_id,appointmentId);
        appointment.setStatus(appointmentStatus);
        return appointmentRepository.save(appointment);
    }
   

    @SuppressWarnings("null")
    public Appointment updateAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(int Appointment_Id){
        appointmentRepository.deleteById(Appointment_Id);
    }

    public List<Appointment> findAllAppointmentsAvailableOnDate(String date) {
        return appointmentRepository.findAllAppointmentsAvailableOnDate(date);
    }

    public int findCountOfAllPatientIdsBySessionId(int Session_Id) {
        return appointmentRepository.findCountOfAllPatientIdsBySessionId(Session_Id);
    }

}
