package com.example.appointmentms.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointmentms.Data.Session;
import com.example.appointmentms.Service.*;

@RestController
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping(path = "/sessions")
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    @PostMapping(path = "/sessions")
    public Session addSession(@RequestBody Session session) {
        return sessionService.addSession(session);
    }

    //URL : http://localhost:8083/appointment-ms/sessions?doctor_Id=2
    @GetMapping(path = "/sessions", params = "doctor_Id")
    public List<Session> findSessionsByDoctorId(@RequestParam("doctor_Id") int doctorId) {
        return sessionService.findSessionsByDoctorId(doctorId);
    }

    @PutMapping(path = "/sessions", params = {"id","status"})
    public void updatSessionStatus(@RequestParam("id") int id, @RequestParam("status") String status) {
        sessionService.updatSessionStatus(id, status);
    }

    @PutMapping(path = "/sessions")
    public Session updateSession(@RequestBody Session session) {
        return sessionService.updateSession(session);
    }

    @DeleteMapping(path = "/sessions/{id}")  
    public void deleteSession(int id) {
        sessionService.deleteSession(id);
    }

}
