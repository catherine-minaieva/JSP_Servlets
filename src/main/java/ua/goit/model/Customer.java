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
@Entity(name = "customers")
public class Customer implements Serializable, BaseEntity<Long> {

    private static final long serialVersionUID = 3544299261792003113L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "head_office")
    private String headOffice;
}
