package com.example.appointmentms.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.appointmentms.Data.Session;
import com.example.appointmentms.Service.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(path = "/sessions")
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @GetMapping(path = "/sessions/{id}")
    public Session getSessionById(@PathVariable int id) {
        return sessionService.getSessionById(id);
    }

    //URL : http://localhost:8083/appointment-ms/sessions?doctor_Id=2
    @GetMapping(path = "/sessions", params = "doctor_Id")
    public List<Session> findSessionsByDoctorId(@RequestParam int doctorId) {
        return sessionService.findSessionsByDoctorId(doctorId);
    }

    @GetMapping(path = "/sessions", params = {"doctor_Id","date"})
    public List<Session> findAllSessionsAvailableOnDateByDoctorIdAndDate(@RequestParam int doctorId,@RequestParam String date) {
        // Get date
        LocalDate localDate = LocalDate.parse(date);

        // Format date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = localDate.format(formatter);

        // Call repository method with today's date
        return sessionService.findAllSessionsAvailableOnDateByDoctorIdAndDate(doctorId, formattedDate);
    }

    @GetMapping(path = "/sessions/today", params = {"doctor_Id"})
    public List<Session> findAllSessionsAvailableOnTodayByDoctorId(@RequestParam int doctorId) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return sessionService.findAllSessionsAvailableOnDateByDoctorIdAndDate(doctorId, formattedDate);
    }

    @GetMapping(path = "/sessions/upcoming", params = {"doctor_Id"})
    public List<Session> findAllUpcomingSessionsByDoctorId(@RequestParam int doctorId) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return sessionService.findAllUpcomingSessionsByDoctorIdAndDate(doctorId, formattedDate);
    }

    @GetMapping(path = "/sessions/history", params = {"doctor_Id"})
    public List<Session> findAllPastSessionsByDoctorId(@RequestParam int doctorId) {
        // Get today's date
        LocalDate today = LocalDate.now();

        // Format today's date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);

        // Call repository method with today's date
        return sessionService.findAllPastSessionsByDoctorIdAndDate(doctorId, formattedDate);
    }

    @PostMapping(path = "/sessions")
    public Session addSession(@RequestBody Session session) {
        return sessionService.addSession(session);
    }

    @PutMapping(path = "/sessions")
    public Session updateSessionById(@RequestBody Session session) {
        return sessionService.updateSession(session);
    }

    @PatchMapping(path = "/sessions/{id}")
    public Session updateSessionStatusById(@PathVariable int id, @RequestBody Map<String,String> requestBody) {
        String status = requestBody.get("status");
        return sessionService.updateSessionStatusById(id, status);
    }

}
