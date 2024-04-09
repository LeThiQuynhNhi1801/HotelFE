//package com.example.hotel_booking.adapter;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.hotel_booking.DetailNotificationActivity;
//import com.example.hotel_booking.R;
//import com.example.hotel_booking.entity.Notification;
//
//import java.util.List;
//
//public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {
//    private List<Notification> mListNotification;
//    private Context mcontext; //cung cấp thông tin về môi trường ứng dụng
//
//    public NotificationAdapter(Context context, List<Notification> mListNotification) {
//        this.mcontext = context;
//        this.mListNotification = mListNotification;
//    }
//
//    @NonNull
//    @Override
//    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
//
//        return new NotificationViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
//        final Notification notification = mListNotification.get(position);
//        if (notification == null){
//            return;
//        }
//        holder.imgNotification.setImageResource(notification.getImgId());
//        holder.txtContent.setText(notification.getContent());
//        holder.txtDate.setText(String.format(notification.getDate()));
//
//        holder.itemNotification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onClickGoToNotificationDetail(notification);
//            }
//        });
//    }
//
//    private void onClickGoToNotificationDetail(Notification notification){
//        Intent intent = new Intent(mcontext, DetailNotificationActivity.class); //khai báo để mở activity mới
////        Bundle bundle = new Bundle();
////        bundle.putSerializable("notification", notification);
////        intent.putExtras(bundle);
//        mcontext.startActivity(intent);
//    }
//    @Override
//    public int getItemCount() {
//        if (mListNotification != null){
//            return mListNotification.size();
//        }
//        return 0;
//    }
//
//    public class NotificationViewHolder extends RecyclerView.ViewHolder{
//        private LinearLayout itemNotification;
//
//        private ImageView imgNotification;
//        private TextView txtContent;
//        private TextView txtDate;
//
//        public NotificationViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            this.itemNotification = itemView.findViewById(R.id.item_notification);
//            this.imgNotification = itemView.findViewById(R.id.img_read);
//            this.txtContent = itemView.findViewById(R.id.name_notification);
//            this.txtDate = itemView.findViewById(R.id.date_notification);
//        }
//    }
//}
