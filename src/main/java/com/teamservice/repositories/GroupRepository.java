package com.teamservice.repositories;

import com.teamservice.models.Group;
import com.teamservice.models.User;

public interface GroupRepository extends CrudRepository<Group, Long> {
    void removeByTeamLeadId(Long teamLeadId);

    void addUser(Long groupId, User user);
}
