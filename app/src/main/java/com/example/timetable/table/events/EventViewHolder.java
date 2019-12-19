package com.example.timetable.table.events;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timetable.R;

public class EventViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private TextView time;
    private TextView comment;

    EventViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.textView9);
        time = itemView.findViewById(R.id.textView10);
        comment = itemView.findViewById(R.id.textView11);
    }

    public void bind(EventItem item) {
        name.setText(item.name);
        time.setText(item.time);
        comment.setText(item.comment);
    }
}
