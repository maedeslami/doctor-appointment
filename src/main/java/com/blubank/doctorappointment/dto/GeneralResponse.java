package com.blubank.doctorappointment.dto;

import com.blubank.doctorappointment.model.ResponseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GeneralResponse {

    private ResponseStatus status;

    private boolean success;

    private String message;

    public static GeneralResponse success() {
        GeneralResponse response = new GeneralResponse();
        response.setSuccess(Boolean.TRUE);
        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("عملیات با موفقیت انجام شد");
        return response;
    }
    public static GeneralResponse failure() {
        GeneralResponse response = new GeneralResponse();
        response.setSuccess(Boolean.FALSE);
        response.setStatus(ResponseStatus.FAILURE);
        response.setMessage("عملیات ناموفق است");
        return response;
    }



}
