package com.wpmtec.buildersBoard.entity.data;

import com.sun.istack.Nullable;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "project")
@NamedQueries({
        @NamedQuery(name = "Project.getAll", query = "Select p from Project p"),
        @NamedQuery(name = "Project.getForId", query = "Select p from Project p where p.id =:id")
})
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "order_number")
    private Long orderNumber;

    @NotNull
    @Column(name = "project_description")
    private String projectDescription;

    @NotNull
    @Column(name = "start")
    private Date start;

    @NotNull
    @Column(name = "end")
    private Date end;

    @Nullable
    @Column(name = "reminder")
    private boolean reminder;

    @Nullable
    @Column(name = "start_reminder")
    private int startReminder;

    @Nullable
    @Column(name = "end_reminder")
    private int endReminder;

    @ManyToOne
    @JoinColumn(name = "responsible_person")
    private User responsiblePerson;
}
