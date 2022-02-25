package com.niltonfirmino.unyleya_sqlite_kotlin_crud.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.R
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.activities.EmployeeDetailActivity
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.common.Util
import com.niltonfirmino.unyleya_sqlite_kotlin_crud.database.Employee
import kotlinx.android.synthetic.main.row_employee_layout.view.*

class EmployeeListAdapter(private val mContext:Context):
    RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>()
{
    private var mEmployeeList:ArrayList<Employee> = ArrayList()
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(emp:Employee){
            itemView.text_employee_name.text = emp.name
            itemView.text_emp_email.text = "${emp.id} | ${emp.email}"
            itemView.setOnClickListener {
                navigateToDetail(emp.id)
            }
        }
        private fun navigateToDetail(empId:Int){
            val intent = Intent(mContext,EmployeeDetailActivity::class.java)
            intent.putExtra(Util.EMP_ID_KEY,empId)
            mContext.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(mContext).inflate(R.layout.row_employee_layout,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mEmployeeList[position])
    }

    override fun getItemCount(): Int {
        return mEmployeeList.size
    }
    fun setDataSource(empList:ArrayList<Employee>){
        mEmployeeList = empList
        notifyDataSetChanged()
    }
}