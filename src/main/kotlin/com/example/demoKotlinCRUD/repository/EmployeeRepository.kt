package com.example.demoKotlinCRUD.repository

import com.example.demoKotlinCRUD.model.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/*interface EmployeeRepository : CrudRepository<Employee, Long>
{
    abstract fun findOne(id: Long): Employee

}*/

@Repository
interface EmployeeRepository : JpaRepository<Employee, Long>