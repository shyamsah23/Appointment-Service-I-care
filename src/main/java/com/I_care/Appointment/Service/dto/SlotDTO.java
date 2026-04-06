package com.I_care.Appointment.Service.dto;

import java.time.LocalTime;

public class SlotDTO {
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean booked;

    public SlotDTO(LocalTime startTime, LocalTime endTime, boolean booked) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.booked = booked;
    }

    public SlotDTO() {

    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}