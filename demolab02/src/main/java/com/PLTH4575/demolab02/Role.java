package com.PLTH4575.demolab02;
import lombok.AllArgsConstructor;
@AllArgsConstructor
public enum Role {
    ADMIN(1), // Vai trò quản trị viên, có quyền cao nhất trong hệ thống
    USER(2); // Vai trò người dùng bình thường , có quyền hạn giới hạn
    public final long value; // Biến này lưu giá trị số lượng tương ứng với mỗi vai trò
}
