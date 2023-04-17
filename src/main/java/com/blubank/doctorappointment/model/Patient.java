package com.blubank.doctorappointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "patients")
public class Patient extends BaseEntity implements Serializable {

    @Column
    private String name;

    @Column
    private String cellNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "open_time_slot_id", referencedColumnName = "id")
    @JsonIgnore
    private OpenTimeSlot openTimeSlot;
}
