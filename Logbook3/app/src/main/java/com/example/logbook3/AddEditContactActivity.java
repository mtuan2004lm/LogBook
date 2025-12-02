package com.example.logbook3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

// Tên tệp BẮT BUỘC phải là AddEditContactActivity.java
public class AddEditContactActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextPhone;
    private Button buttonSave;
    private DatabaseHelper db;
    private int selectedAvatarId = R.drawable.default_avatar;
    private ImageView selectedAvatarView = null;

    // Resource IDs cho các avatar (BẠN PHẢI TẠO TẤT CẢ FILE NÀY TRONG DRAWABLE)
    private final int[] avatarResources = new int[] {
            R.drawable.avatar_1,
            R.drawable.avatar_2,
            R.drawable.avatar_3,
            R.drawable.default_avatar
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_contact);

        db = new DatabaseHelper(this);

        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        buttonSave = findViewById(R.id.buttonSave);

        setupAvatarSelection();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveContact();
            }
        });
    }

    private void setupAvatarSelection() {
        ImageView avatar1 = findViewById(R.id.selectAvatar1);
        ImageView avatar2 = findViewById(R.id.selectAvatar2);
        ImageView avatar3 = findViewById(R.id.selectAvatar3);

        avatar1.setOnClickListener(v -> selectAvatar(v, avatarResources[0]));
        avatar2.setOnClickListener(v -> selectAvatar(v, avatarResources[1]));
        avatar3.setOnClickListener(v -> selectAvatar(v, avatarResources[2]));

        // Thiết lập avatar mặc định
        if (selectedAvatarView == null) {
            selectedAvatarView = avatar1;
            selectedAvatarId = avatarResources[0];
            selectedAvatarView.setBackgroundResource(R.drawable.avatar_border_selected);
        }
    }

    private void selectAvatar(View view, int resourceId) {
        // Bỏ chọn avatar cũ
        if (selectedAvatarView != null) {
            selectedAvatarView.setBackgroundResource(R.drawable.avatar_border_unselected);
        }

        // Chọn avatar mới
        selectedAvatarView = (ImageView) view;
        selectedAvatarId = resourceId;
        selectedAvatarView.setBackgroundResource(R.drawable.avatar_border_selected);

        Toast.makeText(this, "Avatar selected!", Toast.LENGTH_SHORT).show();
    }


    private void saveContact() {
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();

        if (name.trim().isEmpty() || phone.trim().isEmpty()) {
            Toast.makeText(this, "Please enter name and phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact newContact = new Contact(0, name, phone, selectedAvatarId);

        db.addContact(newContact);
        Toast.makeText(this, "Contact saved successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }
}