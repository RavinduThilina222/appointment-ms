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

    public void updateAppointmentStatus(int Appointment_Id, int Status){
        appointmentRepository.updateAppointmentStatus(Appointment_Id, Status);
    }

    @SuppressWarnings("null")
    public Appointment updateAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(int Appointment_Id){
        appointmentRepository.deleteById(Appointment_Id);
    }
}
