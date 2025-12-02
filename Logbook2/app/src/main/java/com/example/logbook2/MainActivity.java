package com.example.logbook2;
// Chắc chắn rằng package name này khớp với dự án của bạn

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Khai báo các thành phần UI
    private ImageView imageViewDisplay;
    private Button buttonPrevious;
    private Button buttonNext;

    // 1. Mảng chứa ID tài nguyên (Resource IDs) của các hình ảnh
    // Bạn cần đảm bảo các tệp này tồn tại trong res/drawable/
    private int[] imageResources = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4
    };

    // 2. Biến chỉ mục để theo dõi vị trí ảnh hiện tại
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ View từ layout XML
        imageViewDisplay = findViewById(R.id.imageViewDisplay);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);

        // Khởi tạo hiển thị ảnh đầu tiên
        updateImageDisplay();

        // Xử lý sự kiện cho nút NEXT
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tăng chỉ mục lên 1. Nếu vượt quá giới hạn, quay lại 0 (ảnh đầu tiên)
                currentIndex = (currentIndex + 1) % imageResources.length;
                updateImageDisplay();
            }
        });

        // Xử lý sự kiện cho nút PREVIOUS
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Giảm chỉ mục đi 1. Nếu nhỏ hơn 0, quay lại ảnh cuối cùng
                currentIndex = (currentIndex - 1);
                if (currentIndex < 0) {
                    currentIndex = imageResources.length - 1;
                }
                updateImageDisplay();
            }
        });
    }

    /**
     * Hàm dùng để cập nhật ImageView với ảnh ở currentIndex
     */
    private void updateImageDisplay() {
        // Lấy Resource ID từ mảng và đặt nó vào ImageView
        imageViewDisplay.setImageResource(imageResources[currentIndex]);
    }
}