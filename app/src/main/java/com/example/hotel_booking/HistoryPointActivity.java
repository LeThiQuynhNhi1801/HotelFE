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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel_booking.dto.response.PointResponse;
import com.example.hotel_booking.adapter.HistoryPointAdapter;
import com.example.hotel_booking.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryPointActivity extends AppCompatActivity {
    private RecyclerView rcv;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_point_layout);
        rcv = findViewById(R.id.rcv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        rcv.setLayoutManager(gridLayoutManager);

        historypoint(1, new VolleyCallbackHistory<List<PointResponse>>() {
            @Override
            public void onSuccess(List<PointResponse> list) {
                HistoryPointAdapter adapter = new HistoryPointAdapter(list);
                rcv.setAdapter(adapter);
            }
        });


    }
    public void historypoint(Integer id,final VolleyCallbackHistory<List<PointResponse>> call){
        RequestQueue requestQueue = Volley.newRequestQueue(HistoryPointActivity.this);
        String url = "https://protective-toes-production.up.railway.app/apiv1/point/history" + id;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<PointResponse> list = new ArrayList<>();
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject point = response.getJSONObject(i);
                                Integer id = point.getInt("idPoint");
                                Integer status = point.getInt("statusPoint");
                                Integer diem = point.getInt("point");
                                Integer total = point.getInt("total");

                                PointResponse a = new PointResponse(id,status,diem,total);
                                list.add(a);
                            }
                            // Trả về đối tượng User thông qua callback
                            call.onSuccess(list);
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
        requestQueue.add(jsonArrayRequest);
    }
    public interface VolleyCallbackHistory<T>{

        void onSuccess(T list);
    }
}
