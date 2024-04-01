package com.example.appointmentms.Controller;

import com.example.appointmentms.Data.Appointment;
import com.example.appointmentms.Service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping(path = "/appointments")
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }

    @GetMapping(path = "/appointments/sessions/{id}")
    public List<Appointment> getAllAppointmentsBySessionId(@PathVariable int id){
        return appointmentService.getAllAppointmentsBySessionId(id);
    }
    
    @GetMapping(path = "/appointments", params = {"reference_no"})
    public List<Appointment> getAppointmentByReferenceNo(@RequestParam int reference_no) {
        return appointmentService.getAppointmentByReferenceNo(reference_no);
    }

    @PatchMapping(path = "appointments/{appointment_id}")
    public Appointment updateAppointmentStatus(@PathVariable int appointment_id, @RequestBody Map<String,String> requestBody) {
        int session_id = Integer.parseInt(requestBody.get("session_id"));
        String status = requestBody.get("status");
        return appointmentService.updateAppointmentStatus(session_id,appointment_id, status);
    }

    @PutMapping(path = "/appointments")
    public Appointment updateAppointment(@RequestBody Appointment appointment){
        return appointmentService.updateAppointment(appointment);
    }

    @GetMapping(value = "/appointments/today",params = {"patient_id"})
    public List<Appointment> getAppointmentsAvailableTodayByPatientId(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();
        
        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllAppointmentsAvailableOnDate(formattedDate,patient_id);
    }

    @GetMapping(value = "/appointments",params = {"date","patient_id"})
    public List<Appointment> getAvailableAppointmentsByDateAndPatientId(@RequestParam String date,@RequestParam int patient_id) {
        // Get date
        LocalDate localDate = LocalDate.parse(date);

        // Format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);

        // Call repository method with date
        return appointmentService.findAllAppointmentsAvailableOnDate(formattedDate,patient_id);
    }

    @GetMapping(value = "/appointments/history",params = {"patient_id"})
    public List<Appointment> getAppointmentsHistoryByPatientId(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllPastAppointments(formattedDate,patient_id);
    }

    @GetMapping(value = "/appointments/upcoming",params = {"patient_id"})
    public List<Appointment> getUpcomingAppointmentsByPatientId(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllUpcomingAppointments(formattedDate,patient_id);
    }

    @GetMapping("/appointments/patients-count")
    public int findCountOfAllPatientsBySessionId(@RequestParam int session_id) {
        return appointmentService.findCountOfAllPatientsBySessionId(session_id);
    }

}
