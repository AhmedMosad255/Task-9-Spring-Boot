package com.springboot.workspace.service.email;

import com.springboot.workspace.dto.EmailDto;
import com.springboot.workspace.mapper.EmailMapper;
import com.springboot.workspace.model.Email;
import com.springboot.workspace.model.Employee;
import com.springboot.workspace.repository.EmailRepository;
import com.springboot.workspace.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

    private final EmailRepository emailRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public EmailDto createEmail(EmailDto emailDto) {
        // Find the employee by ID to ensure they exist
        Employee employee = employeeRepository.findById(emailDto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + emailDto.getEmployeeId()));

        // Create and save the email
        Email email = EmailMapper.INSTANCE.toEmail(emailDto);
        email.setEmployee(employee); // Set the employee relationship
        Email savedEmail = emailRepository.save(email);

        return EmailMapper.INSTANCE.toEmailDTO(savedEmail);
    }

    @Override
    public EmailDto updateEmail(Integer id, EmailDto emailDto) {
        Email email = emailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Email not found with id: " + id));
        email.setName(emailDto.getName());
        email.setContent(emailDto.getContent());
        Email updatedEmail = emailRepository.save(email);
        return EmailMapper.INSTANCE.toEmailDTO(updatedEmail);
    }

    @Override
    public void deleteEmail(Integer id) {
        emailRepository.deleteById(id);
    }

    @Override
    public List<EmailDto> getAllEmails() {
        return emailRepository.findAll()
                .stream()
                .map(EmailMapper.INSTANCE::toEmailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmailDto getEmailByName(String name) {
        Email email = emailRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Email not found with name: " + name));
        return EmailMapper.INSTANCE.toEmailDTO(email);
    }

    @Override
    public List<EmailDto> getEmailsByNames(List<String> names) {
        return emailRepository.findByNameIn(names)
                .stream()
                .map(EmailMapper.INSTANCE::toEmailDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmailDto> getEmailsByContent(String content) {
        return emailRepository.findByContent(content)
                .stream()
                .map(EmailMapper.INSTANCE::toEmailDTO)
                .collect(Collectors.toList());
    }
}
