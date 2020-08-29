package br.com.taskapi.domain.taskItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskItemRepository extends JpaRepository<TaskItem, UUID> {

    Optional<TaskItem> findById(UUID id);
}
