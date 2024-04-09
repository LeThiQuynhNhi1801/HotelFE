package com.example.hotel_booking.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_booking.MainActivity;
import com.example.hotel_booking.R;
import com.example.hotel_booking.dto.response.MyVoucherResponse;

import java.util.List;

public class MyVoucherAdapter extends RecyclerView.Adapter<MyVoucherAdapter.VoucherViewHoder> {
    private List<MyVoucherResponse> mListVoucher;
    private Context mContext;

    public MyVoucherAdapter(Context mContext,List<MyVoucherResponse> mListVoucher) {
        this.mContext = mContext;
        this.mListVoucher = mListVoucher;
    }

    @NonNull
    @Override
    public VoucherViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_layout,parent,false);
        return new VoucherViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHoder holder, int position) {
        MyVoucherResponse voucher = mListVoucher.get(position);
        holder.textView.setText(voucher.getPrice()+"Đ");
        holder.nameVoucher.setText("Voucher "+voucher.getNameVoucher()+"Đ");
        holder.textView2.setText(voucher.getPoint());
        if(voucher.getStatus()==0){
            holder.change.setText("Dùng");
            holder.change.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MainActivity.class);

                    // Đặt cờ để xác định rằng hoạt động mới là hoạt động mới và không phải là một phần của ngăn xếp hoạt động trước đó
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                    // Khởi chạy hoạt động mới
                    mContext.startActivity(intent);
                }
            });
        }else{
            holder.change.setText("Dùng");
            holder.change.setBackgroundColor(Color.parseColor("#CCCCCC")); // Màu xám nhạt
        }
    }

    @Override
    public int getItemCount() {
        return mListVoucher.size();
    }

    public class VoucherViewHoder extends RecyclerView.ViewHolder{
        private TextView textView;
        private TextView nameVoucher;
        private Button change;

        private TextView textView2;
        public VoucherViewHoder(@NonNull View view) {
            super(view);
            this.textView2 = view.findViewById(R.id.textPoint);
            this.textView = view.findViewById(R.id.textVoucher);
            this.nameVoucher = view.findViewById(R.id.textvoucher);
            this.change = view.findViewById(R.id.buttonchange);
        }
    }
}
