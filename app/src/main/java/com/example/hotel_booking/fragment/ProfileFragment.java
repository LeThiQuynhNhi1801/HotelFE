package com.example.hotel_booking.fragment;

import static com.example.hotel_booking.R.drawable.ic_done;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hotel_booking.ChangePasswordActivity;
import com.example.hotel_booking.ProfileActivity;
import com.example.hotel_booking.R;
import com.example.hotel_booking.entity.User;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileFragment extends Fragment {
    private ImageView avata;
    private TextView username;
    private TextView phonenumber;
    private Button editphonenumber;
    private TextView email;
    private Button editemail;
    private TextView address;
    private Button editaddress;
    private TextView password;
    private Button editPassword;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.profile_layout, container, false);
        avata = view.findViewById(R.id.imageAvata);
        username = view.findViewById(R.id.texUsername);
        phonenumber = view.findViewById(R.id.textPhonenumber);
        editphonenumber = view.findViewById(R.id.buttonPhonenumber);
        email = view.findViewById(R.id.textEmail);
        editemail = view.findViewById(R.id.buttonEmail);
        address = view.findViewById(R.id.textAddress);
        editaddress = view.findViewById(R.id.buttonAddress);
        password = view.findViewById(R.id.textPassword);
        editPassword = view.findViewById(R.id.buttonPassword);

        User currentUser = getCurrentUser(1, new AccountFragment.VolleyCallback() {
            @Override
            public void onSuccess(User user) {
                avata.setImageResource(R.drawable.avata);
                username.setText(user.getUsername());
                phonenumber.setText(user.getPhoneNumber());
                editphonenumber.setText("edit");
                email.setText(user.getEmail());
                editemail.setText("edit");
                address.setText(user.getCccd());
                editaddress.setText("edit");
                password.setText("********");
            }
        });


        editPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để mở ProfileActivity
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        edit(editaddress,address,"https://protective-toes-production.up.railway.app/apiv1/patch/cccd/1","cccd");
        edit(editemail,email,"https://protective-toes-production.up.railway.app/apiv1/patch/email/1","email");
        edit(editphonenumber,phonenumber,"https://protective-toes-production.up.railway.app/apiv1/patch/phonenumber/1","sdt");
        return view;
    }
    public void edit(Button button,TextView textView, String url, String col){
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                // Tạo một EditText mới
                EditText editTextAddress = new EditText(getContext());
                Button a = new Button(getContext());

                // Thiết lập các thuộc tính của EditText
                editTextAddress.setLayoutParams(textView.getLayoutParams());
                editTextAddress.setText(textView.getText());
                editTextAddress.setTextColor(textView.getTextColors());
                editTextAddress.setTypeface(textView.getTypeface());
                editTextAddress.setHint(textView.getHint());

                // Thêm EditText vào LinearLayout (hoặc ViewGroup tương ứng)
                ViewGroup parent = (ViewGroup) textView.getParent();
                int index = parent.indexOfChild(textView);
                parent.removeView(textView);
                parent.addView(editTextAddress, index);

                a.setCompoundDrawablesWithIntrinsicBounds(ic_done, 0, 0, 0);
                a.setWidth(52);
                a.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                a.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F4F1F1")));
                a.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//                a.setTextAppearance(R.style.TextAppearance_AppCompat_Body1);
                a.setTextColor(Color.parseColor("#4C4949"));
                a.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                a.setCompoundDrawablePadding(8); // Khoảng cách giữa icon và văn bản
                a.setTextColor(Color.parseColor("#E08FED"));
                ViewGroup parent1 = (ViewGroup) button.getParent();
                int index1 = parent1.indexOfChild(button);
                parent1.removeView(button);
                parent1.addView(a, index1);
                a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        changeinfor(url, editTextAddress.getText().toString(), col, new AccountFragment.VolleyCallback() {
                            @Override
                            public void onSuccess(User user) {

                            }
                        });
                        // Thiết lập tiêu đề và nội dung của hộp thoại
                        builder.setTitle("");
                        builder.setMessage("Lưu thành công");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getActivity(), ProfileActivity.class);

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
                    }
                });
            }
        });
    }

    public User getCurrentUser(Integer id, final AccountFragment.VolleyCallback callback) {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = "https://protective-toes-production.up.railway.app/apiv1/profile/" + id;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String username = response.getString("username");
                            String email = response.getString("email");
                            String cccd = response.getString("cccd");
                            String phonenumber = response.getString("phoneNumber");
                            String password = response.getString("password");

                            User user = new User();
                            user.setUsername(username);
                            user.setEmail(email);
                            user.setCccd(cccd);
                            user.setPhoneNumber(phonenumber);

                            // Trả về đối tượng User thông qua callback
                            callback.onSuccess(user);
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
        return null;
    }
    public void  changeinfor(String url,String value,String col,final AccountFragment.VolleyCallback callback){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JSONObject requestBody = new JSONObject();
        try {
            // Thêm mật khẩu mới vào JSON object
            requestBody.put(col, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.PATCH, url, requestBody, new Response.Listener<JSONObject>() {

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
