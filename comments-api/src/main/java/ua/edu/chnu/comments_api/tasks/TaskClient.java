package ua.edu.chnu.comments_api.tasks;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "tasks-api", url = "http://tasks-api:8082/api/tasks")
public interface TaskClient {
    @GetMapping("{id}/is-existing")
    ResponseEntity<Boolean> isExisting(@PathVariable("id") Long id);
}
