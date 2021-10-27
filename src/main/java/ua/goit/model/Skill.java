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
@Entity(name = "skills")
public class Skill implements Serializable, BaseEntity<Long> {

    private static final long serialVersionUID = 482756703736136238L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "language")
    private String language;

    @Column(name = "level")
    private String level;

    @Override
    public Long getID() {
        return id;
    }
}
