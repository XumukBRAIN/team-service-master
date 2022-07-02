package com.teamservice.endpoints;

import com.teamservice.models.User;
import com.teamservice.services.UserServiceImpl;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.NoArgsConstructor;

@WebService(endpointInterface = "com.teamservice.endpoints.NotificatorService",
        portName = "notificatorPort",
        serviceName = "notificatorService")
@NoArgsConstructor
public class NotificatorServiceImpl implements NotificatorService{

    private UserServiceImpl userService;

    @Override
    public User[] usersDayOverdue(@WebParam(name = "userID") Long userId) {
        return userService.dayOverdue(userId).toArray(new User[0]);
    }
}
