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

    @GetMapping(path = "/appointments/sessions/{id}")
    public List<Appointment> getAllAppointmentsBySessionId(@PathVariable int id){
        return appointmentService.getAllAppointmentsBySessionId(id);
    }
    
    @GetMapping(path = "/appointments", params = {"reference_no"})
    public List<Appointment> getAppointmentByReferenceNo(@RequestParam int reference_no) {
        return appointmentService.getAppointmentByReferenceNo(reference_no);
    }

    @GetMapping(path = "/appointments", params = {"Session_Id","Appointment_Id"})
    public List<Appointment> getAppointmentBySessionIdAndAppointmentId(@RequestParam int Session_Id,@RequestParam int Appointment_Id) {
        return appointmentService.getAppointmentBySessionIdAndAppointmentId(Session_Id,Appointment_Id);
    }

    @GetMapping(value = "/appointments/today",params = {"patient_id"})
    public List<Appointment> getAppointmentsAvailableTodayByPatientIdAndDate(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();
        
        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllAppointmentsAvailableOnDateByPatientIdAndDate(formattedDate,patient_id);
    }

    @GetMapping(value = "/appointments",params = {"date","patient_id"})
    public List<Appointment> getAvailableAppointmentsByDateAndPatientIdAndDate(@RequestParam String date,@RequestParam int patient_id) {
        // Get date
        LocalDate localDate = LocalDate.parse(date);

        // Format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);

        // Call repository method with date
        return appointmentService.findAllAppointmentsAvailableOnDateByPatientIdAndDate(formattedDate,patient_id);
    }

    @GetMapping(value = "/appointments/history",params = {"patient_id"})
    public List<Appointment> getAppointmentsHistoryByPatientIdAndDate(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllPastAppointmentsByPatientIdAndDate(formattedDate,patient_id);
    }

    @GetMapping(value = "/appointments/upcoming",params = {"patient_id"})
    public List<Appointment> getUpcomingAppointmentsByPatientIdAndDate(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllUpcomingAppointmentsByPatientIdAndDate(formattedDate,patient_id);
    }

    @GetMapping("/appointments/patients-count")
    public int findCountOfAllPatientsBySessionId(@RequestParam int session_id) {
        return appointmentService.findCountOfAllPatientsBySessionId(session_id);
    }

    @PostMapping(path = "/appointments")
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping(path = "/appointments")
    public Appointment updateAppointment(@RequestBody Appointment appointment){
        return appointmentService.updateAppointment(appointment);
    }

    @PatchMapping(path = "appointments/{appointment_id}")
    public Appointment updateAppointmentStatus(@PathVariable int appointment_id, @RequestBody Map<String,String> requestBody) {
        int session_id = Integer.parseInt(requestBody.get("session_id"));
        String status = requestBody.get("status");
        return appointmentService.updateAppointmentStatus(session_id,appointment_id, status);
    }

}
