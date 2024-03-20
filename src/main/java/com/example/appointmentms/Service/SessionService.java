package com.example.appointmentms.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointmentms.Data.Session;
import com.example.appointmentms.Data.SessionRepository;

@Service
public class SessionService {

    @Autowired
    private SessionRepository sessionRepository;
    //method for getting all sessions
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
   
    @SuppressWarnings("null")
    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }
    public List<Session> findSessionsByDoctorId(int doctorId) {
        return sessionRepository.findByDoctorId(doctorId);
    }
    
    
    @SuppressWarnings("null")
    public Session updateSession(Session session) {
        return sessionRepository.save(session);
    }

    public void deleteSession(int id) {
        sessionRepository.deleteById(id);
    }

    public void updatSessionStatus(int id, String status) {
        sessionRepository.updateStatus(id, status);
    }

}
