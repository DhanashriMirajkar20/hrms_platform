
package com.example.EmployeeManagement.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "employeeId")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    private String firstName;
    private String lastName;
    private String companyEmail;
    private LocalDate dateOfJoining;
    private String status;
    private String employeeType;
    private Long phoneNumber;
    private String currentBand;
    private double currentExperience;
    private String designation;
    private int ctc;

    @Column(columnDefinition = "BYTEA")
    private byte[] photo;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @JsonManagedReference
    @OneToOne(mappedBy = "employee" ,
              cascade = CascadeType.ALL)
    private EmployeePersonal employeePersonal;

    @JsonManagedReference
    @OneToOne(mappedBy = "employee" ,
              cascade = CascadeType.ALL)
    private Account account;

    @JsonManagedReference
    @OneToOne(mappedBy = "employee" ,
              cascade = CascadeType.ALL)
    private JobDetails jobDetails;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" ,
               cascade = CascadeType.ALL ,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private Set<EmployeeAddress> employeeAddress;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" ,
               cascade = CascadeType.ALL ,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private Set<EmployeeEducation> employeeEducations;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" ,
               cascade = CascadeType.ALL ,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private Set<EmployeeSkill> employeeSkills;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" ,
               cascade = CascadeType.ALL ,
               orphanRemoval = true,
               fetch = FetchType.LAZY)
    private Set<EmployeeBand> employeeBands;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" ,
              cascade = CascadeType.ALL ,
              orphanRemoval = true,
              fetch = FetchType.LAZY)
    private Set<EmployeeManagerHistory> employeeManagerHistories;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" ,
            cascade = CascadeType.ALL ,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<EmploymentContract> employmentContracts;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" ,
            cascade = CascadeType.ALL ,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<EmployeeEmergency> employeeEmergencies;

    @JsonManagedReference
    @OneToMany(mappedBy = "employee" ,
            cascade = CascadeType.ALL ,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Set<Experience> employeeExperiences;

//    current manager of the employee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

//    Set of employees who are reporting to this employee
    @JsonIgnore
    @OneToMany(mappedBy = "manager",
               fetch = FetchType.LAZY)
    private Set<Employee> subordinates;
}
