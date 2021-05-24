package com.example.taskmaster;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmaster.Models.Task;

import java.util.List;

public class TaskAdpater extends RecyclerView.Adapter<TaskAdpater.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, body, state;
        public ViewHolder (View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.titleTask);
            body = itemView.findViewById(R.id.bodyTask);
            state = itemView.findViewById(R.id.statusTask);
        }
    }

    private Context context;
    private List<Task> tasks;


    public TaskAdpater(Context context,  List<Task> tasksList){
        this.context =context;
        this.tasks = tasksList;
    }

    public TaskAdpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.task,parent,false);
        View view = LayoutInflater.from(context).inflate(R.layout.task, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskAdpater.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.title.setText(task.title);
        holder.body.setText(task.body);
        String status = task.state.name() ;
        holder.state.setText(status);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent k = new Intent(context, TaskDetailActivity.class);
                TextView title = (TextView) v.findViewById(R.id.titleTask);
                TextView description = (TextView) v.findViewById(R.id.bodyTask);
                k.putExtra("title",title.getText().toString());
                k.putExtra("description",description.getText().toString());
                context.startActivity(k);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
