package com.upc.ejpractica.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import com.upc.ejpractica.entities.Book;
@Entity(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codeStudent")
    private String codeStudent;
    @Column( name = "loanDate")
    private LocalDate loanDate;
    @Column( name = "devolutionDate")
    private LocalDate devolutionDate;
    @Column(name = "bookLoan")
    private boolean bookLoan;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = true)
    private Book book;
}
