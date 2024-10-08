package com.example.fitwithme.application.service;

import com.example.fitwithme.common.exception.ErrorStatus;
import com.example.fitwithme.common.exception.BadRequestException;
import com.example.fitwithme.domain.model.User;
import com.example.fitwithme.infrastructure.dao.UserDao;
import com.example.fitwithme.jwt.JwtUtil;
import com.example.fitwithme.presentation.dto.request.UserRequest;
import com.example.fitwithme.presentation.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDao userDao;

    private final JwtUtil jwtUtil;

    private final S3ImageService s3ImageService;

    @Transactional
    public UserResponse.tokenInfo login(UserRequest.login loginRequest) {

        String userId = loginRequest.getUserId();
        User user = userDao.findById(userId);

        if(user == null){
            throw new BadRequestException(ErrorStatus.NOT_FOUND_USER);
        }

        if(!loginRequest.getUserPassword().equals(user.userPassword())){
            throw new BadRequestException(ErrorStatus.WRONG_PASSWORD);
        }
        return jwtUtil.generateTokens(user.userId());
    }

    @Transactional
    public String signUp(UserRequest.signUp userRequest) {
        if (!isUserIdAvailable(userRequest.getUserId())) {
            throw new BadRequestException(ErrorStatus.CHECK_DUPLICATE_ID);
        }

        User user = userRequest.toDomain();
        User result = userDao.create(user);

        return result.userName();
    }

    public boolean isUserIdAvailable(String userId) {
        return !userDao.existsByUserId(userId);
    }

    @Transactional
    public String upload(String userId, MultipartFile image) {
        String profileImage = s3ImageService.upload(image);
        userDao.uploadProfile(userId, profileImage);

        return userId;
    }

    @Transactional
    public String updateProfile(String userId, MultipartFile image) {
        String imageUrl = userDao.findById(userId).imageUrl();
        s3ImageService.deleteImageFromS3(imageUrl);

        String profileImage = s3ImageService.upload(image);
        userDao.uploadProfile(userId, profileImage);

        return userId;
    }

    @Transactional
    public boolean leave(String userId) {
        int result = userDao.deleteUser(userId);

        if(result > 0){
            return true;
        }else {
            return false;
        }
    }

    public User findUserDetail(String userId) {
        User user = userDao.findById(userId);

        return User.builder()
                .id(user.id())
                .userId(user.userId())
                .userName(user.userName())
                .email(user.email())
                .phone(user.phone())
                .imageUrl(user.imageUrl())
                .build();
    }

    @Transactional
    public String updateUserInfo(UserRequest.update userRequest) {

        User user = userRequest.toDomain();
        User result = userDao.update(user);

        return result.userName();
    }

}