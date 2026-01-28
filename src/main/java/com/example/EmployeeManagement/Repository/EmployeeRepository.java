package com.example.EmployeeManagement.Repository;


import com.example.EmployeeManagement.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    find employee by partial name
//    List<Employee> findByNameContainingIgnoreCase(String name);

    @Query("""
    SELECT e
    FROM Employee e
    WHERE
        LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
        OR
        LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))
        OR
        LOWER(CONCAT(e.firstName, ' ', e.lastName)) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    List<Employee> searchByFullName(@Param("name") String name);
}
