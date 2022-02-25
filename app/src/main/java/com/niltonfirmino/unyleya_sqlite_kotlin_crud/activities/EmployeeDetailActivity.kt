package com.niltonfirmino.unyleya_sqlite_kotlin_crud.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.R
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.common.Util
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.database.MyDatabase
import kotlinx.android.synthetic.main.activity_employee_detail.*

class EmployeeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_detail)
        init()
    }
    private fun init(){
        var empId = intent.getIntExtra(Util.EMP_ID_KEY,0)
        var myDb = Room.databaseBuilder(this, MyDatabase::class.java, Util.DB_NAME)
            .allowMainThreadQueries()
            .build()
        var emp = myDb.myDao().getEmployeeById(empId)
        txt_employee_email.text = emp.email
        txt_employee_name.text =emp.name
        txt_employee_number.text = emp.id.toString()
        btn_delete_employee.setOnClickListener {
            myDb.myDao().deleteEmployee(emp)
            Toast.makeText(this,"Employee has been deleted successfully",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,EmployeeListActivity::class.java))
        }
        btn_update_employee.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            intent.putExtra(Util.EMP_ID_KEY,empId)
            startActivity(intent)
        }
        img_back.setOnClickListener {
            startActivity(Intent(this,EmployeeListActivity::class.java))
        }

    }
}