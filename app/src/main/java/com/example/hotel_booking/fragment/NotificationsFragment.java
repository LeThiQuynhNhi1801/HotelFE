//package com.example.hotel_booking.fragment;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.hotel_booking.R;
//import com.example.hotel_booking.adapter.NotificationAdapter;
//import com.example.hotel_booking.entity.Notification;
//
//import org.threeten.bp.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public class NotificationsFragment extends Fragment {
//
//    private RecyclerView rcvNotification;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
//
//        rcvNotification = root.findViewById(R.id.rcv_notification);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
//        rcvNotification.setLayoutManager(gridLayoutManager);
//
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL);
//        rcvNotification.addItemDecoration(itemDecoration);
//
//        NotificationAdapter adapter = new NotificationAdapter(this.getContext(), getListNotification());
//        rcvNotification.setAdapter(adapter);
//
//        return root;
//    }
//
//    private List<Notification> getListNotification() {
//        List<Notification> list = new ArrayList<>();
//
//        // Adding Notification objects with specific values
//        list.add(new Notification(R.drawable.ic_notread, "Notification Content 1", 1, LocalDateTime.parse("2015-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_read, "Notification Content 2", 2, LocalDateTime.parse("2016-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_notread, "Notification Content 3", 1, LocalDateTime.parse("2017-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_notread, "Notification Content 11", 1, LocalDateTime.parse("2018-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_read, "Notification Content 21", 2, LocalDateTime.parse("2015-09-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_notread, "Notification Content 31", 1, LocalDateTime.parse("2011-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_notread, "Notification Content 12", 1, LocalDateTime.parse("2012-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_read, "Notification Content 22", 2, LocalDateTime.parse("2015-03-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_notread, "Notification Content 32", 1, LocalDateTime.parse("2014-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_notread, "Notification Content 13", 1, LocalDateTime.parse("2015-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_read, "Notification Content 233", 2, LocalDateTime.parse("2015-02-20T06:30:00")));
//        list.add(new Notification(R.drawable.ic_notread, "Notification Content 33", 1, LocalDateTime.parse("2015-02-20T06:30:00")));
//        // Add more as needed
//
//        return list;
//    }
//}