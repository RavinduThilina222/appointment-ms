package com.example.appointmentms.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.appointmentms.Data.Session;
import com.example.appointmentms.Data.SessionRepository;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    public Session getSessionById(int id) {
        return sessionRepository.findById(id).orElse(null);
    }
    public List<Session> findSessionsByDoctorId(int doctorId) {
        return sessionRepository.findAllSessionsByDoctorId(doctorId);
    }

    public List<Session> findAllSessionsAvailableOnDateByDoctorIdAndDate(int doctorId, String date) {
        return sessionRepository.findAllSessionsAvailableOnDateByDoctorIdAndDate(doctorId, date);
    }

    public List<Session> findAllUpcomingSessionsByDoctorIdAndDate(int doctorId, String date) {
        return sessionRepository.findAllUpcomingSessionsByDoctorIdAndDate(doctorId, date);
    }

    public List<Session> findAllPastSessionsByDoctorIdAndDate(int doctorId, String date) {
        return sessionRepository.findAllPastSessionsByDoctorIdAndDate(doctorId, date);
    }

    public Session addSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session updateSession(Session session) {
        return sessionRepository.save(session);
    }

    public Session updateSessionStatus(int id, String status) {
        Session session = sessionRepository.findById(id).orElse(null);
        if (session == null) {
            return null;
        }
        session.setSession_Status(status);
        sessionRepository.save(session);
        return session;
    }

}
