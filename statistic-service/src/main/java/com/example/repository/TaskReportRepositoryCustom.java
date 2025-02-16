package com.example.repository;

import com.example.dto.TaskReportRequest;
import com.example.model.TaskReport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskReportRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public List<TaskReport> findByFilters(TaskReportRequest request) {
        Sort sort = Sort.by("createdAt");
        if ("asc".equalsIgnoreCase(request.getSortDirection())) {
            sort = sort.descending();
        } else if ("desc".equalsIgnoreCase(request.getSortDirection())) {
            sort = sort.ascending();
        } else {
            throw new IllegalStateException("Invalid sort direction: " + request.getSortDirection());
        }

        Criteria criteria = new Criteria();

        if (request.getExecutorId() != null) {
            criteria = criteria.and("executorId").is(request.getExecutorId());
        }
        if (request.getAuthorId() != null) {
            criteria = criteria.and("authorId").is(request.getAuthorId());
        }
        if (request.getTitle() != null) {
            criteria = criteria.and("title").regex(request.getTitle(), "i");
        }
        if (request.getPriority() != null) {
            criteria = criteria.and("priority").is(request.getPriority());
        }
        if (request.getTaskState() != null) {
            criteria = criteria.and("state").is(request.getTaskState());
        }

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sort);
        Query query = new Query(criteria);
        query.with(pageable);
        return mongoTemplate.find(query, TaskReport.class);
    }
}