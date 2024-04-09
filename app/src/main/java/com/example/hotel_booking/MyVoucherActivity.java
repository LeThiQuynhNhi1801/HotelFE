package com.example.hotel_booking;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel_booking.dto.response.MyVoucherResponse;
import com.example.hotel_booking.adapter.MyVoucherAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyVoucherActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_myvoucher);
        recyclerView = findViewById(R.id.list_myvoucher);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        requestQueue = Volley.newRequestQueue(this);

        getListVoucher(1, new VolleyCallback<List<MyVoucherResponse>>() {
            @Override
            public void onSuccess(List<MyVoucherResponse> result) {
                MyVoucherAdapter myVoucherAdapter = new MyVoucherAdapter(MyVoucherActivity.this,result);
                recyclerView.setAdapter(myVoucherAdapter);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });

    }
    public void getListVoucher(Integer id,final MyVoucherActivity.VolleyCallback<List<MyVoucherResponse>> call){
        String url = "https://protective-toes-production.up.railway.app/apiv1/voucher/"+id;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<MyVoucherResponse> voucherList = new ArrayList<>();
                        try {
                            // Lặp qua mỗi phần tử trong mảng JSON và tạo đối tượng Voucher
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject voucherJson = response.getJSONObject(i);
                                String name = voucherJson.getString("nameVoucher");
                                Integer status = voucherJson.getInt("status");
                                Integer id = voucherJson.getInt("id");
                                Integer point = voucherJson.getInt("point");
                                Integer price = voucherJson.getInt("price");

                                MyVoucherResponse voucher = new MyVoucherResponse(name,id,point,price,status);
                                voucherList.add(voucher);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Trả về danh sách voucher thông qua callback
                        call.onSuccess(voucherList);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi
                        call.onError(error);
                    }
                });

        // Thêm yêu cầu vào hàng đợi
        requestQueue.add(jsonArrayRequest);
    }
    public interface VolleyCallback<T> {
        void onSuccess(T result);
        void onError(VolleyError error);
    }


}

