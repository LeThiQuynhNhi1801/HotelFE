//package com.example.hotel_booking;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//
//import com.example.hotel_booking.adapter.DetailAdapter;
//import com.example.hotel_booking.entity.Hotel;
//import com.example.hotel_booking.entity.TypeRoom;
//import com.example.hotel_booking.entity.User;
//
//import org.threeten.bp.LocalDateTime;
//import org.threeten.bp.Month;
//
//public class DetailNotificationActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private DetailAdapter detailAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_notification);
//
////        Bundle bundle = getIntent().getExtras();
////        if (bundle == null) {
////            return;
////        }
////        Notification notification = (Notification) bundle.get("notification");
//
//
//        recyclerView = findViewById(R.id.detaiNotificationRclView);
//        detailAdapter = new DetailAdapter();
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//
//        detailAdapter.setBillData(getBill());
//        recyclerView.setAdapter(detailAdapter);
//    }
//
//    private Bill getBill(){
//        Hotel hotel1 = new Hotel();
//        hotel1.setIdHotel(1L);
//        hotel1.setHotelName("Example Hotel");
//
//        Branch branch1 = new Branch(1L, "Branch 1", "123 Example St", hotel1);
//
//        TypeRoom typeRoom1 = new TypeRoom(1L, "Standard");
//        TypeRoom typeRoom2 = new TypeRoom(2L, "Deluxe");
//
//        Room room1 = new Room(1L, "101", 50.0f, 100.0f, typeRoom1,branch1);
//        room1.setBranch(branch1);
//
//        User user1 = new User(1L, "exampleuser", "password", "1234728393", "John", "Doe", "Smith", "123456789", "john@example.com");
//
//        LocalDateTime checkinTime = LocalDateTime.of(2024, Month.MARCH, 19, 14, 0);
//        LocalDateTime checkoutTime = LocalDateTime.of(2024, Month.MARCH, 21, 12, 0);
//        Bill bill1 = new Bill(1L, "BILL001", room1, user1, checkinTime, checkoutTime, 250.0f);
//        bill1.setBookingTime(LocalDateTime.of(2024, Month.FEBRUARY, 19, 14, 0));
//
//        return bill1;
//    }
//}