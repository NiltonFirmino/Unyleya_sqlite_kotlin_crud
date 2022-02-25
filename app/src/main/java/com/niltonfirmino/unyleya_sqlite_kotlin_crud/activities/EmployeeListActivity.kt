package com.niltonfirmino.unyleya_sqlite_kotlin_crud.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.R
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.adapters.EmployeeListAdapter
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.common.Util
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.database.Employee
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.database.MyDatabase
import kotlinx.android.synthetic.main.activity_employee_list.*

class EmployeeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        init()
    }
    private fun init(){
        var myDb = Room.databaseBuilder(this, MyDatabase::class.java, Util.DB_NAME)
            .allowMainThreadQueries()
            .build()
        var empList = myDb.myDao().getAllEmployee()
        var adapter = EmployeeListAdapter(this)
        adapter.setDataSource(ArrayList(empList))
        rv_employee_list.adapter = adapter
        rv_employee_list.layoutManager =GridLayoutManager(this,1)
        iv_add_user.setOnClickListener {
            var intent = Intent(this,MainActivity::class.java)
            intent.putExtra(Util.EMP_ID_KEY,0)
            startActivity(intent)
        }
        rv_employee_list.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL))
    }
}