package com.springboot.workspace.mapper;

import com.springboot.workspace.dto.EmailDto;
import com.springboot.workspace.model.Email;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmailMapper {
    EmailMapper INSTANCE = Mappers.getMapper(EmailMapper.class);
    Email toEmail(EmailDto emailDto);
    EmailDto toEmailDTO(Email email);
}
