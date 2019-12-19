package com.example.timetable.table.weekdays;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timetable.R;
import java.util.ArrayList;
import java.util.List;

public class WeekdayAdapter extends RecyclerView.Adapter<WeekdayViewHolder> {
    private List<Weekdays> days = new ArrayList<>();
    private WeekdayClickListener listener;

    @NonNull
    @Override
    public WeekdayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weekday_item, parent, false);
        return new WeekdayViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekdayViewHolder holder, final int position) {
        holder.bind(days.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.onDayClicked(days.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public void setItems(List<Weekdays> days) {
        this.days = new ArrayList<>(days);
        notifyDataSetChanged();
    }

    public void addItem(Weekdays day) {
        days.add(day);
    }

    public void setListener(WeekdayClickListener listener) {
        this.listener = listener;
    }
}
