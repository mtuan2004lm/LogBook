package com.example.logbook3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private DatabaseHelper db;
    // Khởi tạo contactList là một danh sách rỗng để tránh NullPointerException khi cần
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        db = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewContacts);
        FloatingActionButton fab = findViewById(R.id.fabAddContact);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Khởi tạo Adapter ở đây (trước onResume) để tránh NullPointerException
        contactList = db.getAllContacts(); // Tải dữ liệu ban đầu
        adapter = new ContactAdapter(this, contactList);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, AddEditContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Tải lại dữ liệu và cập nhật Adapter
        loadContacts();
    }

    private void loadContacts() {
        // BƯỚC 1: Tải dữ liệu mới nhất từ Database
        List<Contact> newContactList = db.getAllContacts();

        // BƯỚC 2: Cập nhật dữ liệu cho Adapter
        // Bạn cần đảm bảo đã thêm hàm setContactList vào ContactAdapter.java (xem bên dưới)
        if (adapter != null) {
            adapter.setContactList(newContactList);

            // BẮT BUỘC: Thông báo cho RecyclerView biết dữ liệu đã thay đổi
            adapter.notifyDataSetChanged();
        }
        // Nếu adapter là null (chỉ xảy ra khi onCreate bị bỏ qua), logic khởi tạo trong onCreate sẽ xử lý.
    }
}