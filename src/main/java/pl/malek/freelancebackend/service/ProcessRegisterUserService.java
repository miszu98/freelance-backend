package pl.malek.freelancebackend.service;

import pl.malek.freelancebackend.entity.ProcessEntity;
import pl.malek.freelancebackend.entity.UserEntity;


public interface ProcessRegisterUserService {

    ProcessEntity saveProcess(UserEntity userEntity);

}
