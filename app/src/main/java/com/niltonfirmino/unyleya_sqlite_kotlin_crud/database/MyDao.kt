package com.niltonfirmino.unyleya_sqlite_kotlin_crud.database

import androidx.room.*

@Dao
interface MyDao {
    @Insert
    fun addEmployee(emp:Employee)

    @Update
    fun updateEmployee(emp:Employee)

    @Delete
    fun deleteEmployee(emp:Employee)

    @Query("select * from emp_table")
    fun getAllEmployee():List<Employee>

    @Query("select * from emp_table where id=:id")
    fun getEmployeeById(id:Int):Employee
}