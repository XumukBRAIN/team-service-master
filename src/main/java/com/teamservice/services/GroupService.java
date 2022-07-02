package com.teamservice.services;

import com.teamservice.models.Group;
import com.teamservice.models.User;

import java.util.List;

public interface GroupService {
    Group create(Group group);

    void addUser(Long userId, Long teamId);

    void removeByTeamLeadId(Long teamLeadId);
}
