package com.teamservice.endpoints;

import com.teamservice.models.Group;
import com.teamservice.models.User;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC,
        use = SOAPBinding.Use.ENCODED,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface RouterService {

    User createUser(User user);

    Group createGroup(Group group);

    void addUser(Long userId, Long teamId);

    void removeByTeamLeadId(Long teamleadId);

    User[] usersNotInGroup(Long groupId);

}
