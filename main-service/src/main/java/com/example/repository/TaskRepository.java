package com.example.repository;

import com.example.model.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {


    @EntityGraph(attributePaths = {"author", "executor"})
    @Query("""
            select t
            from Task as t
            LEFT JOIN FETCH t.comments
            where t.author.id = :authorId
            AND t.title ILIKE COALESCE(CONCAT('%', :title, '%'), '%')
            """)
    List<Task> findAllByAuthorId(@Param("authorId") UUID authorId, @Param("title") String title, Pageable pageable);

    @EntityGraph(attributePaths = {"author", "executor"})
    @Query("""
            select t
            from Task as t
            LEFT JOIN FETCH t.comments
            where t.executor.id = :executorId
            AND t.title ILIKE COALESCE(CONCAT('%', :title, '%'), '%')
            """)
    List<Task> findAllByExecutorId(@Param("executorId") UUID authorId, @Param("title") String title, Pageable pageable);

    @EntityGraph(attributePaths = {"author", "executor", "comments"})
    @Query("""
            select t
            from Task as t
            LEFT JOIN FETCH t.comments
            where t.id = :taskId
            """)
    Optional<Task> findByIdForAdmin(@Param("taskId") UUID taskId);
}