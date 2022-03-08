package com.auditing.spike.fleetadmin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;

@NamedNativeQuery(
        name = "user_history_query",
        query ="SELECT id, tech, lag(tech) over (order by rev) as previous_tech, name, lag(name) over (order by rev) as previous_name, rev, revtype FROM user_aud where id=:id",
        resultSetMapping = "user_history_dto"
)
@SqlResultSetMapping(
        name = "user_history_dto",
        classes = @ConstructorResult(
                targetClass = AuditEntity.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "tech", type = String.class),
                        @ColumnResult(name = "previous_tech", type = String.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "previous_name", type = String.class),
                        @ColumnResult(name = "rev", type = Integer.class),
                        @ColumnResult(name = "revtype", type = Integer.class)
                }
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Audited
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "tech")
    private String tech;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    @Column(name = "created_date")
    private Date createdDate;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;
}
