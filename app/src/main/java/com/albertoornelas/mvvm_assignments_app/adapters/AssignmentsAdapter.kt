package com.albertoornelas.mvvm_assignments_app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albertoornelas.mvvm_assignments_app.R
import com.albertoornelas.mvvm_assignments_app.models.Assignment
import com.albertoornelas.mvvm_assignments_app.viewmodel.AssignmentViewModel

class AssignmentsAdapter (
    val viewModel: AssignmentViewModel,
    val assignmentList: ArrayList<Assignment>,
    val context: Context) : RecyclerView.Adapter<AssignmentsAdapter.ViewHolder>() {
        inner class ViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {
            private val txtAssignment: TextView = view.findViewById(R.id.txtAssignment)
            private val txtProfessor: TextView = view.findViewById(R.id.txtProfessor)
            private val txtDate: TextView = view.findViewById(R.id.txtDate)
            private val btnDelete: Button = view.findViewById(R.id.btnDelete)

            fun bind (assignment: Assignment) {
                txtAssignment.text = assignment.assignmentName
                txtProfessor.text = assignment.professor
                txtDate.text = assignment.date

                btnDelete.setOnClickListener() {
                    viewModel.delete(assignment)
                    notifyItemRemoved(assignmentList.indexOf(assignment))
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var root = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(assignmentList[position])
    }

    override fun getItemCount(): Int {
        return assignmentList.size
    }
}