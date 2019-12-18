package com.example.timetable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private List<EventItem>  eventItems;

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_event, parent, false);
        EventViewHolder pvh = new EventViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        holder.name.setText(eventItems.get(position).name);
        holder.time.setText(eventItems.get(position).time);
        holder.comment.setText(eventItems.get(position).comment);
    }

    @Override
    public int getItemCount() {
        return eventItems.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView time;
        TextView comment;
        EventViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv_event);
            name = itemView.findViewById(R.id.textView9);
            time = itemView.findViewById(R.id.textView10);
            comment = itemView.findViewById(R.id.textView11);
        }
    }

    EventAdapter(List<EventItem> eventItems){
        this.eventItems = eventItems;
    }
}