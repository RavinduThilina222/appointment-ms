package com.example.appointmentms.Data;

import jakarta.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Reference_No;

    @Column(name = "session_Id")
    private int Session_Id;

    @Column(name = "appointment_Id")
    private int Appointment_Id;

    @Column(name = "patient_Id")
    private int Patient_Id;
    
    @Column(name = "status")
    private String Status;

    public int getReference_No() {
        return Reference_No;
    }

    public void setReference_No(int reference_No) {
        Reference_No = reference_No;
    }

    public int getSession_Id() {
        return Session_Id;
    }

    public void setSession_Id(int session_Id) {
        Session_Id = session_Id;
    }

    public int getAppointment_Id() {
        return Appointment_Id;
    }

    public void setAppointment_Id(int appointment_Id) {
        Appointment_Id = appointment_Id;
    }

    public int getPatient_Id() {
        return Patient_Id;
    }

    public void setPatient_Id(int patient_Id) {
        Patient_Id = patient_Id;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
