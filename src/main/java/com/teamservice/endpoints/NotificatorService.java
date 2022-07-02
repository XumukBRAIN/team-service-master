package com.teamservice.endpoints;

import com.teamservice.models.User;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.List;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT,
             use = SOAPBinding.Use.ENCODED,
             parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface NotificatorService {


    User [] usersDayOverdue(Long userId);


}
