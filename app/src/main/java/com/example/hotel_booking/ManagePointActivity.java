package com.example.hotel_booking;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel_booking.fragment.AccountFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class ManagePointActivity extends AppCompatActivity {
    private Button exchange;
    private Button history_point;
    private Button myvoucher;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bonusmanager_layout);
        exchange = findViewById(R.id.buttonexchange);
        myvoucher = findViewById(R.id.button_myvoucher);
        history_point= findViewById(R.id.button_history_point);
        TextView sale = findViewById(R.id.sale);
        TextView point = findViewById((R.id.have_point));
        getLastPoint(1, new VolleyCallback() {
            @Override
            public void onSucess(Integer total) {
                if(total.equals(null)) point.setText("0");
                else point.setText(String.valueOf(total));
            }
        });
        getSale(1, new VolleyCallback2() {
            @Override
            public void onSucess(Float total) {
                if(total.equals(null)) sale.setText("0");
                else sale.setText(String.valueOf(total));
            }
        });


        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagePointActivity.this, ExchangepointActivity.class);
                getLastPoint(1, new VolleyCallback() {
                    @Override
                    public void onSucess(Integer total) {
                        intent.putExtra("havePoint",total);
                    }
                });
                startActivity(intent);
            }
        });
        myvoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagePointActivity.this, MyVoucherActivity.class);
                startActivity(intent);
            }
        });
        history_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagePointActivity.this, HistoryPointActivity.class);
                startActivity(intent);
            }
        });
    }

    public Float getSale(Integer id, final VolleyCallback2 callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final Float[] sale = new Float[1];

        String url = "https://protective-toes-production.up.railway.app/apiv1/salesmonth/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            sale[0] = (float) response.getDouble("total");
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
        return sale[0];
    }

    public Integer getLastPoint(Integer id, final VolleyCallback callback) {
        final Integer[] point = new Integer[1];
        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
    public interface VolleyCallback2 {
        void onSucess(Float total);
    }
}
