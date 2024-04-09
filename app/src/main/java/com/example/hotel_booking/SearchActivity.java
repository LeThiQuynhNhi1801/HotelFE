package com.example.hotel_booking;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel_booking.dto.response.BranchResponse;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private Button search;
    private RequestQueue requestQueue;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestQueue = Volley.newRequestQueue(this);
        setContentView(R.layout.search_layout);
        search = findViewById(R.id.submit);
        EditText value1 = findViewById(R.id.valueSearch);
        // Trong Fragment hoặc Activity của bạn
        LinearLayout editTextDate = findViewById(R.id.checkin);
        LinearLayout editTextDate2 = findViewById(R.id.checkout);
        TextView in = findViewById(R.id.in);
        TextView out = findViewById(R.id.out);
// Bắt sự kiện click vào trường EditText
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Tạo DatePickerDialog và đặt ngày tối thiểu là ngày hiện tại
                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý khi người dùng chọn ngày
                        LocalDate selectedDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);

                        // Định dạng ngày dưới dạng chuỗi
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedDate = selectedDate.format(formatter);
                        in.setText(formattedDate);
                    }
                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); // Đặt ngày tối thiểu là ngày hiện tại
                datePickerDialog.show(); // Hiển thị DatePickerDialog
            }
        });
        editTextDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy ngày hiện tại
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Tạo DatePickerDialog và đặt ngày tối thiểu là ngày hiện tại
                DatePickerDialog datePickerDialog = new DatePickerDialog(SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Xử lý khi người dùng chọn ngày

                        LocalDate selectedDate = LocalDate.of(year, monthOfYear + 1, dayOfMonth);

                        // Định dạng ngày dưới dạng chuỗi
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        String formattedDate = selectedDate.format(formatter);
                        out.setText(formattedDate);
                    }
                }, year, month, dayOfMonth);

                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000); // Đặt ngày tối thiểu là ngày hiện tại
                datePickerDialog.show(); // Hiển thị DatePickerDialog
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                String checkin = in.getText().toString();
                String checkout = out.getText().toString();
                String value = value1.getText().toString();
                listSearch(checkin, checkout, value, new VolleyCallbackSearch<List<BranchResponse>>() {
                    @Override
                    public void onSuccess(List<BranchResponse> list) {
                        String branchJson = new Gson().toJson(list);

                        // Tạo Intent để mở ResultActivity
                        Intent intent = new Intent(SearchActivity.this, ResultActivity.class);
                        intent.putExtra("branchJson", branchJson);
                        intent.putExtra("valuesearch",value);
                        startActivity(intent);
                    }

                    @Override
                    public void onError(VolleyError error) {

                    }
                });

            }
        });

    }
    public void listSearch(String checkIN,String checkOut,String s, final  VolleyCallbackSearch<List<BranchResponse>> callback){


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, "https://protective-toes-production.up.railway.app/apiv1/search/"+s+"?checkin="+checkIN+"&checkout="+checkOut,null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<BranchResponse> list = new ArrayList<>();
                        try {
                            // Lặp qua mỗi phần tử trong mảng JSON và tạo đối tượng Voucher
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject branch = response.getJSONObject(i);
                                Integer id = branch.getInt("idBranch");
                                String name = branch.getString("nameHotel");
                                String district = branch.getString("district");
                                String city = branch.getString("city");
                                String province = branch.getString("province");
                                Integer rate = branch.getInt("rate");
                                Float price = (float) branch.getDouble("price");
                                BranchResponse branchResponse = new BranchResponse(id,name,district,city,province,rate,price);

                                list.add(branchResponse);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        // Trả về danh sách voucher thông qua callback
                        callback.onSuccess(list);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi
                        callback.onError(error);
                    }
                });

        // Thêm yêu cầu vào hàng đợi
        requestQueue.add(jsonArrayRequest);
    }
    public interface VolleyCallbackSearch<T>{

        void onSuccess(T list);

        void onError(VolleyError error);
    }
}
