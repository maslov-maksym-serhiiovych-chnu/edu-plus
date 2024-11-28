package ua.edu.chnu.comments_api.courses;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "courses-api", url = "http://courses-api:8080/api/courses")
public interface CourseClient {
    @GetMapping("{id}/is-existing")
    ResponseEntity<Boolean> isExisting(@PathVariable("id") Long id);
}
