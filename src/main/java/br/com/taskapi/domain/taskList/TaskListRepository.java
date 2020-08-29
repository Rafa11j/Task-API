package br.com.taskapi.domain.taskList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

    Optional<TaskList> findById(UUID id);
    Optional<TaskList> findByName(String name);
}
