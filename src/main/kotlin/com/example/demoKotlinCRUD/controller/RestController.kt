package com.example.demoKotlinCRUD.controller

import com.example.demoKotlinCRUD.model.Employee
import com.example.demoKotlinCRUD.repository.EmployeeRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/emp")
class RestController(private val employeeRepository: EmployeeRepository) {

    //get all employees
    @GetMapping("/employees")
    fun getAllEmployee(): List<Employee> =
        employeeRepository.findAll()

    //get employee by id
    @GetMapping("/employees/{id}")
    fun getEmployeeById(@PathVariable(value = "id") employeeId: Long): ResponseEntity<Employee> {
        return employeeRepository.findById(employeeId).map { employee ->
            ResponseEntity.ok(employee)
        }.orElse(ResponseEntity.notFound().build())
    }

    //create employee
    @PostMapping("/employees")
    fun createEmployee(@RequestBody employee: Employee): Employee=
        employeeRepository.save(employee)

    //update employee
    @PutMapping("/employees/{id}")
    fun updateEmployee(@PathVariable(value = "id")employeeId: Long,
                        @RequestBody newEmployee: Employee): ResponseEntity<Employee> {
        return employeeRepository.findById(employeeId).map { existingEmployee ->
            val updatedEmployee: Employee = existingEmployee
                .copy(id = newEmployee.id, name = newEmployee.name, age=newEmployee.age, dept = newEmployee.dept)
            ResponseEntity.ok().body(employeeRepository.save(updatedEmployee))
        }.orElse(ResponseEntity.notFound().build())
    }

    //delete employee
    @DeleteMapping("/employee/{id}")
    fun deleteEmployee(@PathVariable(value = "id")employeeId: Long): ResponseEntity<Void> {
        return employeeRepository.findById(employeeId).map { employee ->
            employeeRepository.delete(employee)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
}