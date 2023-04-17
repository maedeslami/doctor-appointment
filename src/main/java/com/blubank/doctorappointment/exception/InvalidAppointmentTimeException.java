package com.blubank.doctorappointment.exception;


import com.blubank.doctorappointment.model.ResponseStatus;

public class InvalidAppointmentTimeException extends Exception {

    private static final long serialVersionUID = -1205510419457531458L;

    public InvalidAppointmentTimeException(String message) {
        super(message);
    }

}
