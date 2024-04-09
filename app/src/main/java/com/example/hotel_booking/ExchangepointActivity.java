package com.example.hotel_booking;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel_booking.adapter.VoucherAdapter;
import com.example.hotel_booking.dto.response.VoucherResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ExchangepointActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Integer havepoint = Integer.parseInt(getIntent().getStringExtra("havePoint"));
        setContentView(R.layout.list_voucher_layout);
        recyclerView = findViewById(R.id.list_voucher);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        requestQueue = Volley.newRequestQueue(this);
        getListVoucher(new VolleyCallback<List<VoucherResponse>>() {
            @Override
            public void onSuccess(List<VoucherResponse> result) {
                VoucherAdapter adapter = new VoucherAdapter(ExchangepointActivity.this,result);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });



    }

    public void getListVoucher(final VolleyCallback<List<VoucherResponse>> call){
        String url = "https://protective-toes-production.up.railway.app/apiv1/voucher";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<VoucherResponse> voucherList = new ArrayList<>();
                        try {
                            // Lặp qua mỗi phần tử trong mảng JSON và tạo đối tượng Voucher
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject voucherJson = response.getJSONObject(i);
                                String name = voucherJson.getString("nameVoucher");
                                Integer id = voucherJson.getInt("id");
                                Integer price = voucherJson.getInt("price");
                                Integer point = voucherJson.getInt("point");

                                VoucherResponse voucher = new VoucherResponse(id,name,price,point);
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
