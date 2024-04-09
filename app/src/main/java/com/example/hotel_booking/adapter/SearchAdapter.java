package com.example.hotel_booking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_booking.R;
import com.example.hotel_booking.dto.response.BranchResponse;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private List<BranchResponse> list;

    public SearchAdapter(List<BranchResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public SearchAdapter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search_layout,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.SearchViewHolder holder, int position) {
        BranchResponse branchResponse = list.get(position);
        holder.imageView.setImageResource(R.drawable.avata);
        holder.textView.setText(branchResponse.getNameHotel());
        holder.textView2.setText("phong don");
//        holder.textView3.setText(String.valueOf(room.getPricePerHour())+"-"+String.valueOf(room.getPricePerDay()));
        holder.textView4.setText(branchResponse.getCity()+", "+branchResponse.getProvince());
        holder.textView5.setText("5 sao");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        private TextView textView2;
        private TextView textView3;
        private TextView textView4;
        private TextView textView5;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.imageRoom);
            this.textView = itemView.findViewById(R.id.nameHotel);
            this.textView2 = itemView.findViewById(R.id.typeRoom);
            this.textView3 = itemView.findViewById(R.id.priceRoom);
            this.textView4 = itemView.findViewById(R.id.adrressHotel);
            this.textView5 = itemView.findViewById(R.id.review);
        }
    }
}
