package com.I_care.Appointment.Service.utility;

import com.I_care.Appointment.Service.exception.AppointmentException;

public class NotificationConstant {

    private NotificationConstant() throws AppointmentException {
        throw new AppointmentException("This is a Constant Class - Can't Create Object of this");
    }

    public static final String SCHEDULE_APPOINTMENT_SUBJECT = "Appointment Scheduled Successfully – I-CARE";
    public static final String APPOINTMENT_BOOKED = "APPOINTMENT_BOOKED";
    public static final String CANCEL_APPOINTMENT_SUBJECT = "Appointment Cancelled Successfully – I-CARE";
    public static final String APPOINTMENT_CANCELLED = "APPOINTMENT_CANCELLED";
    public static final String APPOINTMENT_RESCHEDULED = "APPOINTMENT_RESCHEDULED";
    public static final String APPOINTMENT_RESCHEDULED_SUBJECT = "Appointment Scheduled Successfully – I-CARE";
}
