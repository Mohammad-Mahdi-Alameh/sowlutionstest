package com.example.wired.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mazica.models.data.Task
import com.example.wired.R
import com.example.wired.interfece.ItemClickListener
import com.squareup.picasso.Picasso


class TaskListAdapter(val tasks: MutableList<Task>?,private val itemClickListener: ItemClickListener): RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    var onTaskClick : ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.task_view,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
       return tasks?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bindView(tasks?.get(position) ?: null)
    }
inner class ViewHolder(itemView : View):RecyclerView.ViewHolder(itemView){
        private var projectName : TextView = itemView.findViewById(R.id.projectName)
        private var taskName : TextView = itemView.findViewById(R.id.taskName)
        private var time : TextView = itemView.findViewById(R.id.time)
        private var taskNotes : ImageView = itemView.findViewById(R.id.withNotes)
        private var taskInitializer : LinearLayout = itemView.findViewById(R.id.trigger)

        fun bindView(task : Task?){
            projectName.text = task?.project?.name
            taskName.text= task?.category?.name
            if(task?.notes == ""){
                taskNotes.visibility=View.GONE
            }
            val estHours = task?.estHours
            val estMin = task?.estMins
            val timeString = String.format("%02d:%02d", estHours, estMin)
            time.text = timeString
            taskInitializer.setOnClickListener {
                onClick(it)
                if (task != null) {
                    onTaskClick?.invoke(task.id)
                }
            }
        }
    private fun onClick(v: View) {
        val position = adapterPosition
        if (position != RecyclerView.NO_POSITION) {
            itemClickListener.onItemClick(v, position)
        }
    }
}
}
