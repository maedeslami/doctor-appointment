package com.blubank.doctorappointment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Entity
@Table(name = "OPENTIMES")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class OpenTime extends BaseEntity implements Serializable {
    @Column(name = "open_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "startTime", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "endTime", nullable = false)
    private LocalDateTime endTime;

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "doctor_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
//    private Doctor doctor;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    @OneToMany(mappedBy = "openTime")
    private List<OpenTimeSlot> openTimeSlotList;
}
