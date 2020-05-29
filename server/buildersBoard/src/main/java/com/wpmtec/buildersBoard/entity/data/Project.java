package com.wpmtec.buildersBoard.entity.data;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "project")
@NamedQueries({
  @NamedQuery(name = "Project.getAll", query = "Select p from Project p"),
  @NamedQuery(name = "Project.getForId", query = "Select p from Project p where p.id =:id")
})
public class Project {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @NotNull
  @Column(name = "order_number")
  private Long orderNumber;

  @NotNull
  @Column(name = "project_description")
  private String projectDescription;

  @NotNull
  @Column(name= "start")
  private Date start;

  @NotNull
  @Column(name= "end")
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getOrderNumber() {
    return orderNumber;
  }

  public void setOrderNumber(Long orderNumber) {
    this.orderNumber = orderNumber;
  }

  public String getProjectDescription() {
    return projectDescription;
  }

  public void setProjectDescription(String projectDescription) {
    this.projectDescription = projectDescription;
  }

  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public boolean isReminder() {
    return reminder;
  }

  public void setReminder(boolean reminder) {
    this.reminder = reminder;
  }

  public int getStartReminder() {
    return startReminder;
  }

  public void setStartReminder(int startReminder) {
    this.startReminder = startReminder;
  }

  public int getEndReminder() {
    return endReminder;
  }

  public void setEndReminder(int endReminder) {
    this.endReminder = endReminder;
  }
}
