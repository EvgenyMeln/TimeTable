package com.example.timetable.table.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timetable.R;
import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<EventItem> eventItems = new ArrayList<>();
    private EventClickListener listener;

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_event, parent, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {
        holder.bind(eventItems.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClickedItem(eventItems.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventItems.size();
    }

    public void setItems(List<EventItem> eventItems) {
        this.eventItems = new ArrayList<>(eventItems);
        notifyDataSetChanged();
    }

    public void setlistener(EventClickListener listener){
        this.listener = listener;
    }

    public void addItem(EventItem item) {
        eventItems.add(item);
    }
}