package com.student.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.dtos.ApiResponse;
import com.student.dtos.NoticeDto;
import com.student.entities.Notice;
import com.student.services.NoticeService;

import lombok.AllArgsConstructor;

@RequestMapping("/notices")
@RestController
@AllArgsConstructor
public class NoticeController {

	private final NoticeService service;

	@PostMapping("/post")
	public ResponseEntity<?> postNotice(@RequestBody NoticeDto dto) {
		try {
			return ResponseEntity.ok(service.postNotice(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), "FAILED"));
		}
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllNotices() {
		try {
            List<NoticeDto> notices = service.getAllNotices();
            return ResponseEntity.ok(notices);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(e.getMessage(), "FAILED"));
        }
	}

	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<?> getNoticesByTeacher(@PathVariable Long teacherId) {
	try {
        List<Notice> notices = service.getNoticesByTeacher(teacherId);
        return ResponseEntity.ok(notices);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse(e.getMessage(), "FAILED"));
    }
	}
	
	
}
