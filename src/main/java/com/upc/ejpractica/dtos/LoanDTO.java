package com.upc.ejpractica.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanDTO {
    private Long id;
    private String codeStudent;
    private LocalDate loanDate;
    private LocalDate devolutionDate;
    private boolean bookLoan;
    private BookDTO book;
}
