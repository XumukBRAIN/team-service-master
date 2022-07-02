package com.teamservice.services;

import com.teamservice.models.Group;
import com.teamservice.models.User;
import com.teamservice.repositories.GroupRepository;
import com.teamservice.repositories.UserRepository;

import java.util.List;

public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @Override
    public Group create(Group group) {
        return groupRepository.save(group);
    }

    @Override
    public void addUser(Long userId, Long teamId) {
        if (!userRepository.existsById(userId)){
            return; //todo send fault message
        }
        groupRepository.addUser(teamId, userRepository.getById(userId));
    }

    @Override
    public void removeByTeamLeadId(Long teamLeadId) {
        groupRepository.removeByTeamLeadId(teamLeadId);
    }
}
