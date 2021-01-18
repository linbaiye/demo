package com.example.demo.controller.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class QueryLoanRespDTO {

    private String outstandingAmount;

    private String state;

    private String tipMessage;

    private LocalDate dueDate;

}
