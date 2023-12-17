package io.github.aslary.jwtcrudstarter.mapper;

import io.github.aslary.jwtcrudstarter.dto.RegisterDto;
import io.github.aslary.jwtcrudstarter.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User registerDtoToUser(RegisterDto registerDto);

}
