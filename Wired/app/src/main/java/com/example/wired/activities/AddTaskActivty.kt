package com.example.wired.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import com.example.wired.api.team.TeamRestApiService
import com.example.wired.R
import com.example.wired.adapters.CustomSpinnerAdapter
import com.example.wired.api.ApiMainHeadersProvider
import com.example.wired.api.tasks.TasksRestApiService
import com.example.wired.models.data.Project
import com.example.wired.models.data.TaskCategories
import com.example.wired.models.request.tasks.AddTaskRequest
import com.example.wired.utils.Loader

val teamApiService = TeamRestApiService()
val tasksApiService = TasksRestApiService()

class AddTaskActivty : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var projectSpinner:Spinner
    private lateinit var taskSpinner:Spinner
    private lateinit var notesEditText:EditText
    private lateinit var btns:LinearLayout
    var selectedProject:String =""
    var selectedTask:String =""
    private lateinit var cancelButton:Button
    private lateinit var addButton:Button
    private lateinit var projectIds:List<Int>
    private lateinit var projects:List<Project>
    private lateinit var taskCategories: List<MutableList<TaskCategories>>
    private lateinit var selectedProjectCategories:MutableList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task_activty)
        btns = findViewById(R.id.btnsContainer)
        projectSpinner = findViewById(R.id.projects)
        taskSpinner = findViewById(R.id.tasks)
        cancelButton=findViewById(R.id.cancel)
        cancelButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        addButton=findViewById(R.id.addTask)
        notesEditText=findViewById(R.id.notes)
        addButton.isEnabled=false
        projectSpinner.onItemSelectedListener = this
        taskSpinner.onItemSelectedListener = this
        taskSpinner.visibility = View.GONE
        notesEditText.visibility = View.GONE
        Loader.show(this)
        val authenticatedHeaders = ApiMainHeadersProvider.getPublicHeaders()
        teamApiService.getTeamProjects(authenticatedHeaders) { response ->
            if (response != null) {
                if (response.success) {
                    projects = response.projects
                    if (projects.size != 0) {
                        Loader.hide()
                        val projectNames = projects.map { it.name }.toMutableList()
                        projectNames.add(0,"Select a project...")
                        projectIds = projects.map { it.id }
                        taskCategories = projects.map { it.tasks }
//                        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, projectNames)
                        val adapter = CustomSpinnerAdapter(this, projectNames)
                        projectSpinner.adapter = adapter

                    }
                }
            }
        }
        addButton.setOnClickListener{
            val task = AddTaskRequest(
                project = selectedProject,
                category = selectedTask,
                totalMinutes = 14,
                totalHours = 10,
                date = "15/02/2022 09:00",
                limit = 5,
                offset = 0,
                notes = notesEditText.text.toString()
            )
            tasksApiService.addTask(authenticatedHeaders,task) { response ->
                if (response != null) {
                    if (response.success) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }


    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when(parent?.id){
            R.id.projects->{
                selectedProject = parent?.getItemAtPosition(position) as String
                if(selectedProject !="Select a project..."){
                    taskSpinner.visibility = View.VISIBLE
                }

                    if(position >0){
                    val tasks = taskCategories[position]
                        selectedProjectCategories = tasks.map { it.name }.toMutableList()
                        selectedProjectCategories .add(0,"Select a task...")

//                        taskSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, selectedProjectCategories)
                        taskSpinner.adapter = CustomSpinnerAdapter(this, selectedProjectCategories)

                    }
            }
            R.id.tasks ->{
                selectedTask = parent?.getItemAtPosition(position) as String
                if(selectedTask !="Select a task..."){
                addButton.isEnabled=true
                notesEditText.visibility=View.VISIBLE
                }
            }
        }

        // Perform other actions based on the selected item
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}