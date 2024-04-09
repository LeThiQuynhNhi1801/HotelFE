package com.example.hotel_booking;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hotel_booking.adapter.SearchAdapter;
import com.example.hotel_booking.dto.response.BranchResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private String selectedOption = "";

    private SearchAdapter adapter;

    List<BranchResponse> branchList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_search_layout);
        recyclerView = findViewById(R.id.rcvresult);

        EditText editText = findViewById(R.id.valueSearch1);
        editText.setText(getIntent().getStringExtra("valuesearch"));

        // Nhận chuỗi JSON từ Intent
        String branchJson = getIntent().getStringExtra("branchJson");

// Chuyển đổi chuỗi JSON thành danh sách Branch
        branchList = new Gson().fromJson(branchJson, new TypeToken<List<BranchResponse>>(){}.getType());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new SearchAdapter(branchList);
        recyclerView.setAdapter(adapter);
        LinearLayout sort = findViewById(R.id.sort);
        LinearLayout filter = findViewById(R.id.filter);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortOptions();
            }
        });

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterOptions();
            }
        });
    }



    private void showSortOptions() {

        final String[] options = {"Sắp xếp theo giá tăng dần", "Sắp xếp theo giá giảm dần",
                "Sắp xếp theo số sao tăng dần", "Sắp xếp theo số sao giảm dần"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn cách sắp xếp");

        // Tạo layout để chứa các radio button
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.sort_options_layout, null);
        builder.setView(view);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        // Thêm radio button cho từng tùy chọn
        for (int i = 0; i < options.length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[i]);
            radioButton.setId(i);
            radioGroup.addView(radioButton);
        }

        // Thiết lập sự kiện khi người dùng chọn radio button
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedOption = options[checkedId];
                if(selectedOption.equals("Sắp xếp theo giá tăng dần")){
                    Collections.sort(branchList, new Comparator<BranchResponse>() {
                        @Override
                        public int compare(BranchResponse hotel1, BranchResponse hotel2) {
                            return Double.compare(hotel1.getPrice(), hotel2.getPrice());
                        }
                    });
                    // Cập nhật giao diện người dùng sau khi sắp xếp
                    adapter.notifyDataSetChanged();
                } else if (selectedOption.equals("Sắp xếp theo giá giảm dần")) {
                    Collections.sort(branchList, new Comparator<BranchResponse>() {
                        @Override
                        public int compare(BranchResponse hotel1, BranchResponse hotel2) {
                            return Double.compare(hotel2.getPrice(), hotel1.getPrice());
                        }
                    });
                    // Cập nhật giao diện người dùng sau khi sắp xếp
                    adapter.notifyDataSetChanged();
                } else if (selectedOption.equals("Sắp xếp theo số sao tăng dần")) {
                    Collections.sort(branchList, new Comparator<BranchResponse>() {
                        @Override
                        public int compare(BranchResponse hotel1, BranchResponse hotel2) {
                            return Double.compare(hotel1.getRate(), hotel2.getRate());
                        }
                    });
                    // Cập nhật giao diện người dùng sau khi sắp xếp
                    adapter.notifyDataSetChanged();
                }else {
                    Collections.sort(branchList, new Comparator<BranchResponse>() {
                        @Override
                        public int compare(BranchResponse hotel1, BranchResponse hotel2) {
                            return Double.compare(hotel2.getRate(), hotel1.getRate());
                        }
                    });
                    // Cập nhật giao diện người dùng sau khi sắp xếp
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // Thêm nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Đóng hộp thoại khi người dùng click OK
                dialog.dismiss();
                // Xử lý lựa chọn được chọn ở đây
            }
        });

        // Tạo và hiển thị hộp thoại
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showFilterOptions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chọn điều kiện phòng");

        // Inflate custom layout
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.filter_options_layout, null);
        builder.setView(view);

        // Find views in custom layout
        RadioGroup radioGroupType = view.findViewById(R.id.radioGroupType);
        RadioGroup radioGroupPriceRange = view.findViewById(R.id.radioGroupPriceRange);

        // Set OK button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle filter options here
                int selectedTypeId = radioGroupType.getCheckedRadioButtonId();
                int selectedPriceId = radioGroupPriceRange.getCheckedRadioButtonId();

                // Do something with selected options
                // For example:
                RadioButton radioButtonType = view.findViewById(selectedTypeId);
                String selectedType = radioButtonType.getText().toString();

                RadioButton radioButtonPrice = view.findViewById(selectedPriceId);
                String selectedPriceRange = radioButtonPrice.getText().toString();

                // Perform filtering based on selected options
                filterResults(selectedType, selectedPriceRange);

                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void filterResults(String selectedType, String selectedPriceRange) {
        // Perform filtering of results based on selected options
        // For example:
        // Reload RecyclerView with filtered results
    }
}
