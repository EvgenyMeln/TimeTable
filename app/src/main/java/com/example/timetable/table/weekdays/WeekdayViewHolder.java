package com.example.timetable.table.weekdays;

import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WeekdayViewHolder extends RecyclerView.ViewHolder {
    public WeekdayViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(Weekdays day) {
        ((Button) itemView).setText(day.getShortName());
    }
}