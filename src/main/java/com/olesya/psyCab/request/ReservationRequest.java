package com.olesya.psyCab.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
public class ReservationRequest {

    private Long id;

    private String specialist;

    private Date filterDate;
}
