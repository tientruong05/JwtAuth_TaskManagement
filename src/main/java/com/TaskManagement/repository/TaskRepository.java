package com.TaskManagement.repository;

import com.TaskManagement.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Tìm tất cả các công việc thuộc về một người dùng cụ thể,
     * sắp xếp theo thời gian tạo gần nhất lên đầu.
     * Spring Data JPA sẽ tự động tạo query dựa trên tên phương thức.
     *
     * @param userId ID của người dùng cần tìm công việc.
     * @return Danh sách các công việc của người dùng đó.
     */
    List<Task> findByUserIdOrderByCreatedAtDesc(Long UserId);
}
