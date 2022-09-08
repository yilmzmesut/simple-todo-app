package com.todo.app.repository;

import com.todo.app.entity.Task;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends CouchbaseRepository<Task, String> {

    Optional<List<Task>> findByUserId(String userId);
}
