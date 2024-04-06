package com.example.appointmentms.Controller;

import com.example.appointmentms.Data.Appointment;
import com.example.appointmentms.Service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "/appointments/sessions/{session_id}")
    public List<Appointment> getAllAppointmentsBySessionId(@PathVariable int session_id){
        return appointmentService.getAllAppointmentsBySessionId(session_id);
    }
    
    @GetMapping(path = "/appointments", params = {"reference_no"})
    public List<Appointment> getAppointmentByReferenceNo(@RequestParam int reference_no) {
        return appointmentService.getAppointmentByReferenceNo(reference_no);
    }

    @GetMapping(path = "/appointments", params = {"session_id","appointment_id"})
    public List<Appointment> getAppointmentBySessionIdAndAppointmentId(@RequestParam int session_id,@RequestParam int appointment_id) {
        return appointmentService.getAppointmentBySessionIdAndAppointmentId(session_id,appointment_id);
    }

    @GetMapping(path = "/appointments/today", params = {"patient_id"})
    public List<Appointment> getAppointmentsAvailableOnTodayByPatientId(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();
        
        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllAppointmentsAvailableOnDateByPatientIdAndDate(formattedDate,patient_id);
    }

    @GetMapping(path = "/appointments",params = {"date","patient_id"})
    public List<Appointment> getAvailableAppointmentsOnDateByPatientIdAndDate(@RequestParam String date,@RequestParam int patient_id) {
        // Get date
        LocalDate localDate = LocalDate.parse(date);

        // Format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);

        // Call repository method with date
        return appointmentService.findAllAppointmentsAvailableOnDateByPatientIdAndDate(formattedDate,patient_id);
    }

    @GetMapping(path = "/appointments/history",params = {"patient_id"})
    public List<Appointment> getAppointmentsHistoryByPatientId(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllPastAppointmentsByPatientIdAndDate(formattedDate,patient_id);
    }

    @GetMapping(path = "/appointments/upcoming",params = {"patient_id"})
    public List<Appointment> getUpcomingAppointmentsByPatientId(@RequestParam int patient_id) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return appointmentService.findAllUpcomingAppointmentsByPatientIdAndDate(formattedDate,patient_id);
    }

    @GetMapping(path = "/appointments/patients-count", params = {"session_id"})
    public int findCountOfAllPatientsBySessionId(@RequestParam int session_id) {
        return appointmentService.findCountOfAllPatientsBySessionId(session_id);
    }

    @GetMapping(path = "/appointments/max-appointment-no", params = {"session_id"})
    public int findMaxAppointmentNoBySessionId(@RequestParam int session_id) {
        return appointmentService.findMaxAppointmentNoBySessionId(session_id);
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
    public Appointment updateAppointmentStatusByAppointmentId(@PathVariable int appointment_id, @RequestBody Map<String,String> requestBody) {
        int session_id = Integer.parseInt(requestBody.get("session_Id"));
        String status = requestBody.get("status");
        return appointmentService.updateAppointmentStatusByAppointmentId(session_id, appointment_id, status);
    }

}
