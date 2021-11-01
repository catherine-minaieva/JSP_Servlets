package ua.goit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "developers")

    public class Developer implements BaseEntity<Long>, Serializable {

        @Serial
        private static final long serialVersionUID = 1928374651928374656L;

        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "age")
        private int age;

        @Column(name = "gender")
        private String gender;

        @Column(name = "salary")
        private Double salary;
}

