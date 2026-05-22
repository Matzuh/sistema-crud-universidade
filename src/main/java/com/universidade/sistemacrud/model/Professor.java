package com.universidade.sistemacrud.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_professor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Professor extends Person {

    private Double salary;
}