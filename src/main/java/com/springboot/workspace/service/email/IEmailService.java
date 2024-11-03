package com.springboot.workspace.service.email;

import com.springboot.workspace.dto.EmailDto;

import java.util.List;

public interface IEmailService {
    EmailDto createEmail(EmailDto emailDto);
    EmailDto updateEmail(Integer id, EmailDto emailDto);
    void deleteEmail(Integer id);
    List<EmailDto> getAllEmails();
    EmailDto getEmailByName(String name);
    List<EmailDto> getEmailsByNames(List<String> names);
    List<EmailDto> getEmailsByContent(String content);
}
