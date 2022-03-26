package com.albertoornelas.mvvm_assignments_app.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.albertoornelas.mvvm_assignments_app.models.Assignment

class AssignmentViewModel : ViewModel(){
    var assignmentsList = MutableLiveData<ArrayList<Assignment>>()
    var newAssignmentList = arrayListOf<Assignment>()

    fun add(assignment: Assignment){
        print("---" + assignment.assignmentName)
        newAssignmentList.add(assignment)
    }

    fun delete(assignment: Assignment) {
        newAssignmentList.remove(assignment)
        assignmentsList.value = newAssignmentList
    }
}