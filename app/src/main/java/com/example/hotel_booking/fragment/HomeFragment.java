package com.example.hotel_booking.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.hotel_booking.ProfileActivity;
import com.example.hotel_booking.R;
import com.example.hotel_booking.SearchActivity;
import com.example.hotel_booking.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private TextView search;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // Tạo ViewModel để quản lý dữ liệu liên quan đến giao diện
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        // Sử dụng Data Binding để gán layout cho Fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        search = root.findViewById(R.id.searchEditText);


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để mở ProfileActivity
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return root;// Trả về giao diện
    }

    @Override
    public void onDestroyView() { //chuyển qua trang thì nó giải phóng bộ nhớ
        super.onDestroyView();
        binding = null;
    }
}