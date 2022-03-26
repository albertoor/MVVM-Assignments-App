package com.albertoornelas.mvvm_assignments_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.albertoornelas.mvvm_assignments_app.adapters.AssignmentsAdapter
import com.albertoornelas.mvvm_assignments_app.models.Assignment
import com.albertoornelas.mvvm_assignments_app.viewmodel.AssignmentViewModel

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var assignmentViewModel: AssignmentViewModel
    private lateinit var rcvAssignments: RecyclerView
    private lateinit var btnAdd : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvAssignments = findViewById(R.id.rcvAssignments)
        assignmentViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory())[AssignmentViewModel::class.java]
        
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener(){
            addAssignment()
        }
        initAdapter()
    }

    private fun initAdapter() {
        rcvAssignments.layoutManager = viewManager
        observerAssignmentList()
    }

    private fun addAssignment() {
        var txtAssignment = findViewById<EditText>(R.id.edtAssignment)
        var txtProfessor = findViewById<EditText>(R.id.edtProfessor)
        var txtDate = findViewById<EditText>(R.id.edtDate)
        var assignment = txtAssignment.text.toString()
        var professor = txtProfessor.text.toString()
        var date = txtDate.text.toString()

        if (assignment.isNullOrBlank() && professor.isNullOrBlank() && date.isNullOrBlank()){
            Toast.makeText(this, "Todos los campos son requeridos",
                Toast.LENGTH_LONG).show()
        }else{
            var newAssignment = Assignment(assignment, professor, date)
            assignmentViewModel.add(newAssignment)

            Toast.makeText(this, "Cantidad: " + assignmentViewModel.newAssignmentList.size.toString(),
                Toast.LENGTH_LONG).show()

            txtAssignment.text.clear()
            txtProfessor.text.clear()
            txtDate.text.clear()
            rcvAssignments.adapter?.notifyDataSetChanged()
        }
    }

    private fun observerAssignmentList (){
        assignmentViewModel.assignmentsList.observe(
            this,
            {
                rcvAssignments.adapter = AssignmentsAdapter(assignmentViewModel, it, this)
            }
        )
    }
}