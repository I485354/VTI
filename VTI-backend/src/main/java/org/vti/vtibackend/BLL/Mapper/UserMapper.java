package org.vti.vtibackend.BLL.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.vti.vtibackend.DAL.Entity.User;
import org.vti.vtibackend.model.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(source = "user_id", target = "user_id")
    UserDTO ToDTO(User user);
    @Mapping(source = "user_id", target = "user_id")
    User ToEntity(UserDTO userDTO);
}
