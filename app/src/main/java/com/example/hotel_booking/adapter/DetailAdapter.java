//package com.example.hotel_booking.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.hotel_booking.R;
//import com.example.hotel_booking.entity.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class DetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    // View types
//    private static final int TYPE_CUSTOMER_INFO = 0;
//    private static final int TYPE_ROOM_INFO = 1;
//    private static final int TYPE_BILL_INFO = 2;
//
//    private List<Object> mItems;
//
//    public void setBillData (Bill bill){
//        mItems = new ArrayList<>();
//        User user = bill.getUser();
//        Room room = bill.getRoom();
//
//        this.mItems.add(user);
//        this.mItems.add(room);
//        this.mItems.add(bill);
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view;
//        switch (viewType) {
//            case TYPE_CUSTOMER_INFO:
//                view = inflater.inflate(R.layout.item_customer_info, parent, false);
//                return new UserInforViewHolder(view);
//            case TYPE_ROOM_INFO:
//                view = inflater.inflate(R.layout.item_room_info, parent, false);
//                return new RoomInforViewHolder(view);
//            case TYPE_BILL_INFO:
//                view = inflater.inflate(R.layout.item_bill_info, parent, false);
//                return new BillInforViewHolder(view);
//            default:
//                throw new IllegalArgumentException("Invalid view type");
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Object item = mItems.get(position);
//        switch (holder.getItemViewType()) {
//            case TYPE_CUSTOMER_INFO:
//                Bill billInfo = (Bill) mItems.get(2);
//                UserInforViewHolder userInforViewHolder = (UserInforViewHolder) holder;
//                User customerInfo = billInfo.getUser();
//
//                userInforViewHolder.nameCustomer.setText("Tên khách hàng: " + customerInfo.getFullName());
//                userInforViewHolder.cccd.setText("CCCD/CMND: " + customerInfo.getCccd());
//                userInforViewHolder.email.setText("Email: " + customerInfo.getEmail());
//                userInforViewHolder.phoneNumber.setText("Số điện thoại: " + customerInfo.getPhoneNumber());
//                userInforViewHolder.dateBooking.setText("Thời gian đặt phòng: " + billInfo.getBookingTime().toString());
//                userInforViewHolder.dateCheckin.setText("Thời gian nhận phòng: " + billInfo.getCheckinTime().toString());
//                userInforViewHolder.dateCheckout.setText("Thời gian trả phòng: " + billInfo.getCheckoutTime().toString());
//                userInforViewHolder.payStatus.setText("Trạng thái thanh toán: " + billInfo.getBillCode());
//                break;
//            case TYPE_ROOM_INFO:
//                billInfo = (Bill) mItems.get(2);
//                Room roomInfo = billInfo.getRoom();
//                RoomInforViewHolder roomInfoViewHolder = (RoomInforViewHolder) holder;
//
//                roomInfoViewHolder.roomNumber.setText("Số phòng: " + roomInfo.getRoomNumber());
//                roomInfoViewHolder.roomType.setText("Loại phòng: " +roomInfo.getTypeRoom());
//                roomInfoViewHolder.hotel.setText("Tên khách sạn: " +roomInfo.getBranch().getHotel().getHotelName());
//                roomInfoViewHolder.costPerDay.setText("Giá theo ngày: " +roomInfo.getPricePerDay().toString());
//                roomInfoViewHolder.costPerHour.setText("Giá theo ngày: " + roomInfo.getPricePerHour().toString());
//                break;
//            case TYPE_BILL_INFO:
//                billInfo = (Bill) mItems.get(2);
//                BillInforViewHolder billInfoViewHolder = (BillInforViewHolder) holder;
//
//                billInfoViewHolder.billCode.setText("Mã hóa đơn: " + billInfo.getBillCode());
//                billInfoViewHolder.totalPrice.setText("Tổng giá" + billInfo.getTotalPrice().toString());
//                billInfoViewHolder.vat.setText("Thuế VAT (10%): " + billInfo.getVAT().toString());
//                billInfoViewHolder.gross.setText("Thành tiền: " + billInfo.getGross().toString());
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mItems != null) {
//            return mItems.size();
//        }
//        return 0;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        Object item = mItems.get(position);
//        if (item instanceof Bill) {
//            return TYPE_BILL_INFO;
//        } else if (item instanceof User) {
//            return TYPE_CUSTOMER_INFO;
//        } else if (item instanceof Room) {
//            return TYPE_ROOM_INFO;
//        }
//        return super.getItemViewType(position);
//    }
//
//    public class UserInforViewHolder extends RecyclerView.ViewHolder{
//        private TextView nameCustomer;
//        private TextView cccd;
//        private TextView email;
//        private TextView phoneNumber;
//        private TextView dateBooking;
//        private TextView dateCheckin;
//        private TextView dateCheckout;
//        private TextView payStatus;
//        public UserInforViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            nameCustomer = itemView.findViewById(R.id.customerNameTextView);
//            cccd = itemView.findViewById(R.id.cccdTextView);
//            email = itemView.findViewById(R.id.emailTextView);
//            phoneNumber = itemView.findViewById(R.id.phoneNumberTextView);
//            dateBooking = itemView.findViewById(R.id.dateBookingTextView);
//            dateCheckin = itemView.findViewById(R.id.dateCheckinTextView);
//            dateCheckout = itemView.findViewById(R.id.dateCheckoutTextView);
//            payStatus = itemView.findViewById(R.id.payStatusTextView);
//        }
//    }
//
//    public class RoomInforViewHolder extends RecyclerView.ViewHolder{
//        private TextView roomNumber;
//        private TextView roomType;
//        private TextView hotel;
//        private TextView costPerHour;
//        private TextView costPerDay;
//        public RoomInforViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            roomNumber = itemView.findViewById(R.id.roomNumberTextView);
//            roomType = itemView.findViewById(R.id.roomTypeTextView);
//            hotel = itemView.findViewById(R.id.hotelTextView);
//            costPerHour = itemView.findViewById(R.id.costPerHourTextView);
//            costPerDay = itemView.findViewById(R.id.costPerDayTextView);
//
//        }
//    }
//
//    public class BillInforViewHolder extends RecyclerView.ViewHolder {
//        private TextView billCode;
//        private TextView totalPrice;
//        private TextView vat;
//        private TextView gross;
//
//        public BillInforViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            billCode = itemView.findViewById(R.id.billCodeTextView);
//            totalPrice = itemView.findViewById(R.id.totalPriceTextView);
//            vat = itemView.findViewById(R.id.vatTextView);
//            gross = itemView.findViewById(R.id.grossTextView);
//        }
//    }
//
//
//}
