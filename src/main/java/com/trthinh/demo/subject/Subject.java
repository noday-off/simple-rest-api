package com.trthinh.demo.subject;

import com.trthinh.demo.enrollment.Enrollment;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subject",
    uniqueConstraints = {
        @UniqueConstraint(name = "Unique_subjectCode_constraint", columnNames = "subjectCode")
    }
)
public class Subject {

    @Id
    @SequenceGenerator(
            name = "subject_sequence",
            sequenceName = "subject_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "subject_sequence"
    )
    private Long id;

    @Column(
            name = "subjectCode",
            nullable = false
    )
    private String code;
    @Column(
            name = "subjectName",
            nullable = false
    )
    private String name;
    @Column(
            name = "credit",
            nullable = false
    )
    private Integer credit;
    @OneToMany(mappedBy = "subject")
    List<Enrollment> enrollments;

    public Subject() {
    }

    public Subject(Long id, String code, String name, Integer credit) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.credit = credit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
