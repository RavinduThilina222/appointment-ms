package com.example.appointmentms.Controller;

import com.example.appointmentms.Data.Appointment;
import com.example.appointmentms.Service.AppointmemtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    /*
    @GetMapping(path = "/appointments", params = {"session_id", "appointment_id"})
    public List<Appointment> getAllAppointmentbySessionIdAndAppointmentId(@RequestParam("session_id") int sessionId, @RequestParam("appointment_id") int appointmentId) {
        return appointmentService.getAllAppointmentbySessionId(sessionId, appointmentId);
    }*/

    @PutMapping(path = "/appointments", params = {"appointment_id", "status"})
    public Appointment updateAppointmentStatus(@RequestParam("appointment_id") int appointmentId, @RequestParam("status") String status) {
        return appointmentService.updateAppointmentStatus(appointmentId, status);
    }

@PutMapping(path = "/appointments")
public Appointment updateAppointment(@RequestBody Appointment appointment){
    return appointmentService.updateAppointment(appointment);
}

    @DeleteMapping(path = "/appointments")
    public void deleteAppointment(@RequestParam("appointmentId") int appointmentId){
        appointmentService.deleteAppointment(appointmentId);
    }  

    @GetMapping("/appointments/today")
    public List<Appointment> getAppointmentsAvailableToday() {
        // Get today's date
        LocalDate today = LocalDate.now();
        
        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllAppointmentsAvailableOnDate(formattedDate);
    }

    @GetMapping("/appointments/count") // Unique path for counting
    public int findCountOfAllPatientIdsBySessionId(@RequestParam("session_id") int sessionId) {
        return appointmentService.findCountOfAllPatientIdsBySessionId(sessionId);
    }

    

}
