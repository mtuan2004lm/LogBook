package com.example.logbook3;

public class Contact {
    private int id;
    private String name;
    private String phone;
    private int avatarResourceId; // Resource ID (e.g., R.drawable.avatar_1)

    public Contact(int id, String name, String phone, int avatarResourceId) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.avatarResourceId = avatarResourceId;
    }

    // Constructor cho việc tạo liên hệ mới (ID sẽ được tự động tạo trong DB)
    public Contact(String name, String phone, int avatarResourceId) {
        this.name = name;
        this.phone = phone;
        this.avatarResourceId = avatarResourceId;
    }

    // Getters and Setters (Bắt buộc)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getAvatarResourceId() { return avatarResourceId; }
    public void setAvatarResourceId(int avatarResourceId) { this.avatarResourceId = avatarResourceId; }
}