package com.example.wired.activities

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mazica.models.data.Task
import com.example.wired.R
import com.example.wired.adapters.TaskListAdapter
import com.example.wired.api.ApiMainHeadersProvider
import com.example.wired.api.tasks.TasksRestApiService
import com.example.wired.interfece.ItemClickListener
import com.example.wired.models.response.tasks.GetTasksResponse
import com.example.wired.utils.Loader
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() , ItemClickListener {
    val tasksApiService = TasksRestApiService()
    private lateinit var timerHandler: Handler
    private lateinit var timerRunnable: Runnable
    private lateinit var timerTextView: TextView
    private lateinit var secTextView: TextView
    private lateinit var mainCardPlayer: LinearLayout
    private lateinit var noteLayout: LinearLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var addTask: Button
    private lateinit var addNote: Button
    private lateinit var note: EditText
    private lateinit var endAll: Button
    private lateinit var tasksList: RecyclerView
    private lateinit var fetchedTasks: MutableList<Task>
    private lateinit var tasksIds: MutableList<Int>
    private lateinit var animator: ValueAnimator
    private var secondsPassed: Long = 0
    private var prevPosition: Int = -1
    private lateinit var prevView :View
    private var isNoteVisible = false
    private var pause: Int = 0
    private var play: Int = 0
    private var textColor: Int = 0
    private var playToggle: Boolean = false
    private val authenticatedHeaders = ApiMainHeadersProvider.getPublicHeaders()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textColor = ContextCompat.getColor(this, R.color.textColor)
        pause = resources.getIdentifier("@drawable/pause", null, packageName)
        play = resources.getIdentifier("@drawable/play", null, packageName)
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        noteLayout = findViewById(R.id.noteLayout)
        addNote = findViewById(R.id.addNote)
        note = findViewById(R.id.note)
        endAll = findViewById(R.id.endAll)
        timerTextView = findViewById(R.id.timer)
        secTextView = findViewById(R.id.sec)
        mainCardPlayer = findViewById(R.id.stop)
        mainCardPlayer.visibility=View.GONE
        addNote.setOnClickListener {
            toggleIsNoteVisisble()
        }
        //Setting up timer
        timerHandler = Handler()
        timerRunnable = object : Runnable {
            override fun run() {
                secondsPassed += 1000
                val hours = TimeUnit.MILLISECONDS.toHours(secondsPassed)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(secondsPassed) % 60
                val seconds = TimeUnit.MILLISECONDS.toSeconds(secondsPassed) % 60

                val timeString = String.format("%02d:%02d", hours, minutes)
                timerTextView.text = timeString
                secTextView.text = String.format("%02d", seconds)
                timerHandler.postDelayed(this, 1000)
            }
        }
        //setting up the animation of the timer when paused
        val colorWhite = Color.WHITE
        val colorGray = Color.GRAY

        animator = ValueAnimator.ofObject(ArgbEvaluator(), colorWhite, colorGray)
        animator.duration = 700
        animator.repeatCount = ValueAnimator.INFINITE
        animator.repeatMode = ValueAnimator.REVERSE

        animator.addUpdateListener { valueAnimator ->
            val color = valueAnimator.animatedValue as Int
            timerTextView.setTextColor(color)
        }


        addTask = findViewById(R.id.addTask)
        addTask.setOnClickListener {
            val intent = Intent(this, AddTaskActivty::class.java)
            startActivity(intent)
        }
        tasksList=findViewById(R.id.tasksRecyclerView)
        Loader.show(this)
        tasksApiService.getTasks(authenticatedHeaders) { response ->
            Loader.hide()
            if (response != null) {
                if (response.success) {
                    fetchedTasks = response.data.tasks
                    if (fetchedTasks.size != 0) {
                        val taskAdapter = TaskListAdapter(fetchedTasks,this)
                        tasksIds = fetchedTasks.map { it.id }.toMutableList()
                        tasksList.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = taskAdapter
                        }
                    }
                }
            }
        }
        endAll.setOnClickListener{
            for (number in tasksIds) {
                tasksApiService.endTask(authenticatedHeaders,number) { response ->
                    if (response != null) {
                        if (!response.success) {
                            Toast.makeText(this, "Ending all tasks failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            recreate()
        }
    }

    private fun toggleIsNoteVisisble() {
       isNoteVisible = !isNoteVisible
        if (isNoteVisible) {
            noteLayout.visibility = View.VISIBLE
        } else {
            noteLayout.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer(0)
    }


    private fun resetTimer(){
        timerHandler.removeCallbacks(timerRunnable)
        secTextView.text = "00"
    }
    private fun stopTimer(selectedTaskId:Int) {
        if(selectedTaskId == 0){
            timerHandler.removeCallbacks(timerRunnable)
            return
        }
//        tasksApiService.stopTask(authenticatedHeaders,selectedTaskId) { response ->
//            if (response != null) {
//                if (response.success) {
                    timerHandler.removeCallbacks(timerRunnable)
//                }
//            }
//        }

    }
    private fun startTimer(selectedTaskId:Int) {
        if(selectedTaskId == 0){
            timerHandler.removeCallbacks(timerRunnable)
            return
        }
//        tasksApiService.startTask(authenticatedHeaders,selectedTaskId) { response ->
//            if (response != null) {
//                if (response.success) {
        timerHandler.postDelayed(timerRunnable, 1000)
//                }
//            }
//        }

    }


    override fun onItemClick(v: View, position: Int) {
        val layoutManager = tasksList.layoutManager
        var view = layoutManager?.findViewByPosition(position)
        val selectedTask = fetchedTasks[position]
        note.setText(selectedTask.notes)
        val selectedTaskId = selectedTask.id
        if(prevPosition == position){
            if(playToggle){
                stopTimer(selectedTaskId)
                mainCardPlayer.visibility=View.VISIBLE
                view?.findViewById<ImageView>(R.id.player)?.setImageResource(play)
                animator.start()
                playToggle = !playToggle
                prevView = view!!
                return
            }else{
                startTimer(selectedTaskId)
                mainCardPlayer.visibility=View.GONE
                view?.findViewById<ImageView>(R.id.player)?.setImageResource(pause)
                animator.cancel()
                timerTextView.setTextColor(textColor)
                playToggle = !playToggle
                prevView = view!!
                return
            }
        }else if(prevPosition != -1){
            changeThemeToUnSelected(prevView)
            loadRemainingTime(view!!)
            startTimer(selectedTaskId)
            changeThemeToSelected(view!!)
            resetTimer()
            startTimer(selectedTaskId)
            prevPosition = position
            playToggle = !playToggle
            prevView = view!!
            return
        }
        loadRemainingTime(view!!)
        startTimer(selectedTaskId)
        changeThemeToSelected(view!!)
        playToggle =! playToggle
        prevPosition = position
        prevView = view!!
    }

    private fun changeThemeToSelected(view: View) {
        animator.cancel()
        timerTextView.setTextColor(textColor)
        mainCardPlayer.visibility=View.GONE
        view?.findViewById<ImageView>(R.id.player)?.setImageResource(pause)
        view?.findViewById<TextView>(R.id.time)?.visibility=View.GONE
        view?.findViewById<TextView>(R.id.projectName)?.setTypeface(null, Typeface.BOLD)
        view?.findViewById<TextView>(R.id.projectName)?.setTextColor(Color.BLACK)
        view?.findViewById<TextView>(R.id.taskName)?.setTypeface(null, Typeface.BOLD)
        view?.findViewById<TextView>(R.id.taskName)?.setTextColor(Color.BLACK)
    }

    private fun changeThemeToUnSelected(view: View) {
        view?.findViewById<ImageView>(R.id.player)?.setImageResource(play)
        val time = view?.findViewById<TextView>(R.id.time)
        val project = view?.findViewById<TextView>(R.id.projectName)
        val task = view?.findViewById<TextView>(R.id.taskName)
        time?.visibility=View.VISIBLE
        time?.text = timerTextView.text
        project?.setTypeface(null, Typeface.NORMAL)
        project?.setTextColor(textColor)
        task?.setTypeface(null, Typeface.NORMAL)
        task?.setTextColor(textColor)
        view?.findViewById<ImageView>(R.id.player)?.setImageResource(play)
    }

    private fun loadRemainingTime(view: View) {
        val time = view?.findViewById<TextView>(R.id.time)?.text
        val timeSplitted = time?.split(":")
        val startHour = timeSplitted?.get(0)
        val startMinute = timeSplitted?.get(1)
        secondsPassed = TimeUnit.HOURS.toMillis(startHour!!.toLong()) +
        TimeUnit.MINUTES.toMillis(startMinute!!.toLong())
        timerTextView.text = time
    }
}