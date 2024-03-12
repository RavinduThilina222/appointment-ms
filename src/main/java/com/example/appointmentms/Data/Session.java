package com.example.appointmentms.Data;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Session_Id;

    @Column(name = "doctor_id")
    private int Doctor_Id;

    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name = "session_date")
    @JsonProperty("session_date")
    private String Session_Date;

    @JsonFormat(pattern = "HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @Column(name = "session_time")
    @JsonProperty("session_time")
    private String Session_Time;

    @Column(name = "no_of_patients")
    private int No_Of_Patients;

    @Column(name = "special_note")
    private String Special_Notes;

    @Column(name = "session_status")
    private String Session_Status;
    
    
    public int getSession_Id() {
        return Session_Id;
    }   
    public void setSession_Id(int session_Id) {
        Session_Id = session_Id;
    }
    public int getDoctor_Id() {
        return Doctor_Id;
    }
    public void setDoctor_Id(int doctor_Id) {
        Doctor_Id = doctor_Id;
    }
    @JsonProperty("session_date")
    public String getDate() {
        return Session_Date;
    }
    public void setDate(String date) {
        Session_Date = date;
    }
    @JsonProperty("session_time")
    public String getTime() {
        return Session_Time;
    }
    public void setTime(String time) {
        Session_Time = time;
    }
    public int getNo_Of_Patients() {
        return No_Of_Patients;
    }
    public void setNo_Of_Patients(int no_Of_Patients) {
        No_Of_Patients = no_Of_Patients;
    }
    public String getSpecial_Notes() {
        return Special_Notes;
    }
    public void setSpecial_Notes(String special_Notes) {
        Special_Notes = special_Notes;
    }
    public String getSession_Status() {
        return Session_Status;
    }
    public void setSession_Status(String session_Status) {
        Session_Status = session_Status;
    }
}
