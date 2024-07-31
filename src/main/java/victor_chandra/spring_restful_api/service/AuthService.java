package victor_chandra.spring_restful_api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import victor_chandra.spring_restful_api.entity.User;
import victor_chandra.spring_restful_api.model.LoginUserRequest;
import victor_chandra.spring_restful_api.model.TokenResponse;
import victor_chandra.spring_restful_api.repository.UserRepository;
import victor_chandra.spring_restful_api.security.BCrypt;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public TokenResponse login(LoginUserRequest request) {
        //validate
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect"));

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            //success
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next30Days());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        } else {
            //failed
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }
    }

    private Long next30Days() {
        return System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000);
    }
}
