package com.TaskManagement.repository;

import com.TaskManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Tìm một người dùng dựa trên tên đăng nhập.
     * Dùng Optional để xử lý trường hợp không tìm thấy người dùng một cách an toàn.
     * Đây là phương thức cốt lõi cho Spring Security.
     *
     * @param username Tên đăng nhập cần tìm.
     * @return một Optional chứa User nếu tìm thấy, ngược lại là Optional rỗng.
     */
    Optional<User> findByUsername(String username);

    /**
     * Kiểm tra xem một tên đăng nhập đã tồn tại trong DB hay chưa.
     * Hiệu quả hơn việc dùng findByUsername rồi kiểm tra null.
     *
     * @param username Tên đăng nhập cần kiểm tra.
     * @return true nếu tồn tại, false nếu không.
     */
    Boolean existsByUsername(String username);

    /**
     * Kiểm tra xem một email đã tồn tại trong DB hay chưa.
     *
     * @param email Email cần kiểm tra.
     * @return true nếu tồn tại, false nếu không.
     */
    Boolean existsByEmail(String email);
}
