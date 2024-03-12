package com.example.appointmentms.Controller;

import com.example.appointmentms.Data.Appointment;
import com.example.appointmentms.Service.AppointmemtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppointmentController {
    @Autowired
    private AppointmemtService appointmentService;

    @PostMapping(path = "/appointments")
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }

    @GetMapping(path = "/appointments", params = "session_id")
    public List<Appointment> getAllAppointmentsbySessionId(@RequestParam("session_id") int sessionId) {
        return appointmentService.getAllAppointmentsbySessionId(sessionId);
    }
    
    @GetMapping(path = "/appointments", params = {"session_id", "appointment_id"})
    public List<Appointment> getAllAppointmentbySessionIdAndAppointmentId(@RequestParam("session_id") int sessionId, @RequestParam("appointment_id") int appointmentId) {
        return appointmentService.getAllAppointmentbySessionId(sessionId, appointmentId);
    }

    @PutMapping(path = "/appointments", params = {"session_id", "session_status"})
    public void updateAppointmentStatus(@RequestParam("session_id") int sessionId, @RequestParam("session_status") int sessionStatus) {
        appointmentService.updateAppointmentStatus(sessionId, sessionStatus);
    }

    @PutMapping(path = "/appointments")
    public Appointment updateAppointment(@RequestBody Appointment appointment){
        return appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping(path = "/appointments")
    public void deleteAppointment(@RequestParam("appointmentId") int appointmentId){
        appointmentService.deleteAppointment(appointmentId);
    }  
}
