package com.example.logbook3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private final Context context;
    // ĐÃ SỬA: Bỏ "final" để có thể cập nhật nội dung list
    private List<Contact> contactList;

    public ContactAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.textViewName.setText(contact.getName());
        holder.textViewPhone.setText(contact.getPhone());

        // Đặt ảnh avatar dựa trên Resource ID đã lưu
        holder.imageViewAvatar.setImageResource(contact.getAvatarResourceId());

        // Xử lý sự kiện khi nhấp vào một mục (ví dụ: để chỉnh sửa)
        holder.itemView.setOnClickListener(v -> {
            // TODO: Triển khai logic mở AddEditContactActivity để chỉnh sửa
        });
    }

    @Override
    public int getItemCount() {
        if (contactList == null) return 0;
        return contactList.size();
    }

    /**
     * Hàm được gọi bởi ContactListActivity để cập nhật dữ liệu mới từ Database
     */
    public void setContactList(List<Contact> newContactList) {
        // Cập nhật tham chiếu list mới
        this.contactList = newContactList;
    }

    // ViewHolder: Giữ các tham chiếu đến View cho mỗi mục
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewAvatar;
        TextView textViewName;
        TextView textViewPhone;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
        }
    }
}