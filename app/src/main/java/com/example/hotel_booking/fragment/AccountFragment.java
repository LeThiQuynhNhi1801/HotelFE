package com.example.hotel_booking.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.hotel_booking.ManagePointActivity;
import com.example.hotel_booking.ProfileActivity;
import com.example.hotel_booking.R;
import com.example.hotel_booking.entity.User;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountFragment extends Fragment { // Thay vì AppCompatActivity
    private ImageView profileImageView;
    private TextView usernameTextView;
    private Button profile;
    private Button bonus;
    private Button language;
    private Button logout;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        profileImageView = view.findViewById(R.id.avata);
        usernameTextView = view.findViewById(R.id.username);
        profile = view.findViewById(R.id.profile);
        bonus = view.findViewById(R.id.bonus);
        language = view.findViewById(R.id.language);
        logout = view.findViewById(R.id.logout);


        // Lấy thông tin cá nhân từ tài khoản
        User currentUser = getCurrentUser(1, new VolleyCallback() {
            @Override
            public void onSuccess(User user) {
                profileImageView.setImageResource(R.drawable.avata);
                usernameTextView.setText(user.getUsername());
            }
        });
        // Hiển thị thông tin cá nhân

        profile.setText("Thông tin cá nhân");
        bonus.setText("Điểm thưởng");
        language.setText("Ngôn ngữ");
        logout.setText("Đăng xuất");



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để mở ProfileActivity
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        bonus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManagePointActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    public User getCurrentUser(Integer id, final VolleyCallback callback) {
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

    // Định nghĩa một interface để trả về dữ liệu từ phương thức
    public interface VolleyCallback {
        void onSuccess(User user);
    }

}
