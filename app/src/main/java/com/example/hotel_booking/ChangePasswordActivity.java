package com.example.hotel_booking;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel_booking.fragment.AccountFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class ChangePasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_layout);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button save = findViewById(R.id.buttonSave);
        EditText oldpassword = findViewById(R.id.editOldpassword);
        EditText newpassword = findViewById(R.id.editNewpassword);
        EditText repeat = findViewById(R.id.editRepeat);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                String a = oldpassword.getText().toString();
                String b = newpassword.getText().toString();
                String c = repeat.getText().toString();
                if(a.isEmpty()||b.isEmpty()||c.isEmpty()){
                    builder.setTitle("");
                    builder.setMessage("Vui lòng điền đầy đủ thông tin");
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                    if(!b.equals(c)){
                        builder.setTitle("");
                        builder.setMessage("Nhập lại mật khẩu không khớp");
                        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                        getOldPassword(1, new VolleyCallback() {
                            @Override
                            public void onSuccess(String password) {
                                if (a.equals(password)) {
                                    changePass(1, a, b);
                                            builder.setTitle("");
                                            builder.setMessage("Lưu mật khẩu thành công");
                                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(ChangePasswordActivity.this, ProfileActivity.class);

                                                    // Đặt cờ để xác định rằng hoạt động mới là hoạt động mới và không phải là một phần của ngăn xếp hoạt động trước đó
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                                    // Khởi chạy hoạt động mới
                                                    startActivity(intent);

                                                    // Đóng hộp thoại
                                                    dialog.dismiss();
                                                    // Xử lý khi người dùng click vào nút "OK"
                                                    // Ở đây bạn có thể thực hiện các thao tác cần thiết sau khi xác nhận đổi điểm
                                                }
                                            });
                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                } else {
                                    builder.setTitle("");
                                    builder.setMessage("Mật khẩu cũ không đúng, vui lòng nhập lại");
                                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Xử lý khi người dùng click vào nút "Cancel"
                                            dialog.dismiss(); // Đóng hộp thoại
                                        }
                                    });
                                }
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            }
                        });

                    }
                }


            }
        });
    }
    public void getOldPassword(Integer id, final VolleyCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        final String[] pass = {""};
        String url = "https://protective-toes-production.up.railway.app/apiv1/profile/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String password = response.getString("password");
                            callback.onSuccess(password);
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

    }

    // Interface để trả về dữ liệu từ phương thức getOldPassword
    public interface VolleyCallback {
        void onSuccess(String password);
    }
    public void  changePass(Integer id,String old,String newpass){
        RequestQueue requestQueue = Volley.newRequestQueue(ChangePasswordActivity.this);
        JSONObject requestBody = new JSONObject();
        try {
            // Thêm mật khẩu mới vào JSON object
            requestBody.put("oldPassword", old);
            requestBody.put("newPassword",newpass);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PATCH, "https://protective-toes-production.up.railway.app/apiv1/patch/password/"+id, requestBody, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Xử lý phản hồi từ server nếu cần
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Xử lý lỗi
                    }
                });

        // Thêm yêu cầu vào hàng đợi
        requestQueue.add(jsonObjectRequest);
    }
}
