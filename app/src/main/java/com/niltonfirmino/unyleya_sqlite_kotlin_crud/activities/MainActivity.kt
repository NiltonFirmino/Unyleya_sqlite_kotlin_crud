package com.niltonfirmino.unyleya_sqlite_kotlin_crud.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.R
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.common.Util
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.database.Employee
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.database.MyDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }
    private fun init(){
        val empId = intent.getIntExtra(Util.EMP_ID_KEY,0)
        if(empId>0){
            text_employee_number.isEnabled = false
            textView_title.text = "Atualizar Empregado"
            button_save_employee.text = "Atualizar"
            var myDb = Room.databaseBuilder(this, MyDatabase::class.java, Util.DB_NAME)
                .allowMainThreadQueries()
                .build()
            var emp = myDb.myDao().getEmployeeById(empId)
            text_employee_number.setText(emp.id.toString())
            text_employee_email.setText(emp.email)
            text_employee_name.setText(emp.name)
        }
        button_save_employee.setOnClickListener {
            var emp_id = text_employee_number.text.toString().toInt()
            var emp_name = text_employee_name.text.toString()
            var emp_email = text_employee_email.text.toString()
            var obj_emp = Employee(emp_id,emp_name,emp_email)
            var myDb = Room.databaseBuilder(this,MyDatabase::class.java,Util.DB_NAME)
                .allowMainThreadQueries()
                .build()
            val intent = Intent(this,EmployeeListActivity::class.java)
            if(empId>0)
            {
                myDb.myDao().updateEmployee(obj_emp)
                Toast.makeText(this,"Empregado $emp_name foi atualizado.",Toast.LENGTH_LONG).show()

            } else {
                myDb.myDao().addEmployee(obj_emp)
                Toast.makeText(this,"Empregado $emp_name foi adicionado.",Toast.LENGTH_LONG).show()
            }
            startActivity(intent)
            img_back.setOnClickListener {
                startActivity(intent)
            }

        }


    }
}