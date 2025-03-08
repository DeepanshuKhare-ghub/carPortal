package in.springsecurity.model;

import in.springsecurity.entity.User;
import in.springsecurity.entity.evaluation.Agent;
import in.springsecurity.entity.evaluation.Area;
import in.springsecurity.entity.evaluation.CustomerVisit;
import in.springsecurity.payload.evaluationDto.AgentDto;
import in.springsecurity.payload.evaluationDto.AreaDto;
import in.springsecurity.payload.evaluationDto.CustomerVisitDto;
import in.springsecurity.payload.userDto.UserDto;
import in.springsecurity.payload.userDto.UserResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Mapping {


    @org.mapstruct.Mapping(target = "id", ignore = true)
    User user(UserDto userDto);

    @org.mapstruct.Mapping(target = "password", ignore = true)
    UserResponseDto userResp(User user);

    CustomerVisit customerVisit(CustomerVisitDto customerVisitDto);
    CustomerVisitDto customerVisitDto(CustomerVisit customerVisit);

    Agent agent(AgentDto agentDto);

    // area
    Area area(AreaDto areaDto);

    AreaDto areaDto(Area area);
}


