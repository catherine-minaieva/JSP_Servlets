package ua.goit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "projects")
public class Project implements Serializable, BaseEntity<Long> {

    private static final long serialVersionUID = 7573244728918659871L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "base_technology")
    private String baseTechnology;

    @Column(name = "creation_date")
    private String creationDate;

    @Column(name = "cost")
    private Long cost;

    @Override
    public Long getID() {
        return id;
    }
}
