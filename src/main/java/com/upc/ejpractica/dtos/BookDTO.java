package com.upc.ejpractica.dtos;

import com.upc.ejpractica.entities.Loan;
import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String editorial;
}
