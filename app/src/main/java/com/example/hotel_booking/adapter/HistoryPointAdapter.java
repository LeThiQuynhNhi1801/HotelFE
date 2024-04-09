package com.example.hotel_booking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_booking.R;
import com.example.hotel_booking.dto.response.PointResponse;

import java.util.List;

public class HistoryPointAdapter extends RecyclerView.Adapter<HistoryPointAdapter.HistoryViewHolder> {


    private List<PointResponse> list;

    public HistoryPointAdapter(List<PointResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_point_layout,parent,false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        PointResponse pointResponse = list.get(position);
        holder.textView.setText("##Bill"+pointResponse.getIdPoint());
        if(pointResponse.getStatusPoint()==0){
            holder.imageView.setImageResource(R.drawable.ic_up);
            holder.textView2.setText("+ " + pointResponse.getPoint());
        }else{
            holder.imageView.setImageResource(R.drawable.ic_down);
            holder.textView2.setText("- " + pointResponse.getPoint());
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageStatus);
            this.textView = itemView.findViewById(R.id.code);
            this.textView2 = itemView.findViewById(R.id.point);
        }
    }
}
