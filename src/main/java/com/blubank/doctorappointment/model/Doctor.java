package com.blubank.doctorappointment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "Doctor")
public class Doctor extends BaseEntity implements Serializable {

    @Column(name = "date")
    private LocalDateTime date;

    @Column
    private String name;

//    @OneToMany(mappedBy = "doctor")
//    private List<OpenTime> openTimeList;
}
