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
    @Entity(name = "developers_skills")
    public class DeveloperSkill implements BaseEntity<Long> , Serializable {

        private static final long serialVersionUID = -3545558946984093089L;

        @Id
        @Column(name = "developer_id")
        private Long developerId;

        @Column(name = "skill_id")
        private Long skillId;

        @Override
        public Long getID() {
            return developerId;
        }
    }
