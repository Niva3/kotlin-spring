package com.example.demoKotlinCRUD.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var  id : Long =0,
    var  name : String="",
    var  age : Long =0,
    var  dept : String=""

)