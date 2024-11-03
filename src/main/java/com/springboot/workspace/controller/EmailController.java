package com.springboot.workspace.controller;

import com.springboot.workspace.dto.EmailDto;
import com.springboot.workspace.response.ApiResponse;
import com.springboot.workspace.service.email.IEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/emails")
@RequiredArgsConstructor
public class EmailController {
    private final IEmailService emailService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createEmail(@RequestBody EmailDto emailDto) {
        EmailDto createdEmail = emailService.createEmail(emailDto);
        // Ensure the response reflects the correct employee ID
        return ResponseEntity.ok().body(new ApiResponse("Email created successfully!", createdEmail));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEmail(@PathVariable Integer id, @RequestBody EmailDto emailDto) {
        EmailDto updatedEmail = emailService.updateEmail(id, emailDto);
        return ResponseEntity.ok(new ApiResponse("Email updated successfully!", updatedEmail));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmail(@PathVariable Integer id) {
        emailService.deleteEmail(id);
        return ResponseEntity.ok(new ApiResponse("Email deleted successfully!", null));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllEmails() {
        List<EmailDto> emails = emailService.getAllEmails();
        return ResponseEntity.ok(new ApiResponse("All emails retrieved successfully!", emails));
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<ApiResponse> getEmailByName(@PathVariable String name) {
        EmailDto email = emailService.getEmailByName(name);
        return ResponseEntity.ok(new ApiResponse("Email retrieved successfully by name!", email));
    }

    @PostMapping("/by-names")
    public ResponseEntity<ApiResponse> getEmailsByNames(@RequestBody List<String> names) {
        List<EmailDto> emails = emailService.getEmailsByNames(names);
        return ResponseEntity.ok(new ApiResponse("Emails retrieved successfully by names!", emails));
    }

    @GetMapping("/by-content/{content}")
    public ResponseEntity<ApiResponse> getEmailsByContent(@PathVariable String content) {
        List<EmailDto> emails = emailService.getEmailsByContent(content);
        return ResponseEntity.ok(new ApiResponse("Emails retrieved successfully by content!", emails));
    }

}
