package com.universidade.sistemacrud.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student extends Person {

    private String studentNumber;
    private String photo;
}