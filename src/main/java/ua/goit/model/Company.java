package ua.goit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "companies")
public class Company implements BaseEntity<Long>, Serializable {
    private static final long serialVersionUID = -3989228596940809304L;


    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "head_office")
    private String headOffice;

    @Override
    public Long getID() {
        return id;
    }
}
