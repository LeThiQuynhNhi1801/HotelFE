package com.example.hotel_booking.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel_booking.ManagePointActivity;
import com.example.hotel_booking.MyVoucherActivity;
import com.example.hotel_booking.R;
import com.example.hotel_booking.dto.response.VoucherResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {
    private List<VoucherResponse> mListVoucher;
    private Context mContext;

    public VoucherAdapter(Context context, List<VoucherResponse> mListVoucher) {
        this.mContext = context;
        this.mListVoucher = mListVoucher;
    }


    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.voucher_layout,parent,false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        VoucherResponse voucher = mListVoucher.get(position);
        holder.textView.setText(voucher.getPriceVoucher()+"Đ");
        holder.nameVoucher.setText("Voucher " +voucher.getNameVoucher()+"Đ");
        holder.textView2.setText(voucher.getPointVoucher());
        holder.change.setText("Đổi");
        holder.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                getLastPoint(1, new ManagePointActivity.VolleyCallback() {
                    @Override
                    public void onSucess(Integer total) {
                        if(total<voucher.getPointVoucher()){
                            builder.setTitle("! Không thể đổi do không đủ điểm");
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Xử lý khi người dùng click vào nút "Cancel"
                                    dialog.dismiss(); // Đóng hộp thoại
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                        else{
                            builder.setTitle("Xác nhận đổi điểm lấy voucher");
                            builder.setMessage("Bạn chắc chắn muốn đổi 100 điểm lấy voucher 50k ?");

                            // Thiết lập nút "OK" và xử lý sự kiện khi người dùng click vào
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    exchange(1,voucher.getIdVoucher());
                                    builder.setTitle("Đổi thành công, xem tại Quà của tôi.");

                                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(mContext, MyVoucherActivity.class);

                                            // Đặt cờ để xác định rằng hoạt động mới là hoạt động mới và không phải là một phần của ngăn xếp hoạt động trước đó
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                            // Khởi chạy hoạt động mới
                                            mContext.startActivity(intent);
                                        }
                                    });

                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                }
                            });

                            // Thiết lập nút "Cancel" và xử lý sự kiện khi người dùng click vào
                            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Xử lý khi người dùng click vào nút "Cancel"
                                    dialog.dismiss(); // Đóng hộp thoại
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListVoucher.size();
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private TextView nameVoucher;
        private Button change;

        private TextView textView2;
        public VoucherViewHolder(View view) {
            super(view);
            this.textView2 = view.findViewById(R.id.textPoint);
            this.textView = view.findViewById(R.id.textVoucher);
            this.nameVoucher = view.findViewById(R.id.textvoucher);
            this.change = view.findViewById(R.id.buttonchange);
        }
    }

    public void   exchange(Integer idUser,Integer idVoucher){
        int a =0;
        RequestQueue requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        JSONObject requestBody = new JSONObject();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PATCH, "https://protective-toes-production.up.railway.app/apiv1/exchangevoucher/"+idUser+"/"+idVoucher, requestBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        // Thêm yêu cầu vào hàng đợi
        requestQueue.add(jsonObjectRequest);

    }
    public Integer getLastPoint(Integer id, final ManagePointActivity.VolleyCallback callback) {
        final Integer[] point = new Integer[1];
        RequestQueue requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        String url = "https://protective-toes-production.up.railway.app/apiv1/point/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            point[0] = response.getInt("total");
                            // Trả về đối tượng User thông qua callback
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi
                    }
                });
        requestQueue.add(jsonObjectRequest);
        return point[0];
    }
    public interface VolleyCallback {
        void onSucess(Integer total);
    }

}