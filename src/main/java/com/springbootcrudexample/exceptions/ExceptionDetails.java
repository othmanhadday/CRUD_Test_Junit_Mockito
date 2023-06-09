package com.springbootcrudexample.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionDetails {

    private String msg;
    private Date date = new Date();
    private String type;
}
