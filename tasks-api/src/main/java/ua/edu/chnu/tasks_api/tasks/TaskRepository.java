package ua.edu.chnu.tasks_api.tasks;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCourseIdAndNameContainsIgnoreCaseAndCompleted(Long courseId, String name, boolean completed);

    List<Task> findByCourseIdAndNameContainsIgnoreCaseAndCompleted(Long courseId, String name, boolean completed, Sort sort);

    List<Task> findByCourseIdAndNameContainsIgnoreCase(Long courseId, String name);

    List<Task> findByCourseIdAndNameContainsIgnoreCase(Long courseId, String name, Sort sort);

    List<Task> findByCourseIdAndCompleted(Long courseId, boolean completed);

    List<Task> findByCourseIdAndCompleted(Long courseId, boolean completed, Sort sort);

    List<Task> findByNameContainsIgnoreCaseAndCompleted(String name, boolean completed);

    List<Task> findByNameContainsIgnoreCaseAndCompleted(String name, boolean completed, Sort sort);

    List<Task> findByCourseId(Long courseId);

    List<Task> findByCourseId(Long courseId, Sort sort);

    List<Task> findByNameContainsIgnoreCase(String name);

    List<Task> findByNameContainsIgnoreCase(String name, Sort sort);

    List<Task> findByCompleted(boolean completed);

    List<Task> findByCompleted(boolean completed, Sort sort);
}
