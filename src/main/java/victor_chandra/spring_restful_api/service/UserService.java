package victor_chandra.spring_restful_api.service;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import victor_chandra.spring_restful_api.entity.User;
import victor_chandra.spring_restful_api.model.RegisterUserRequest;
import victor_chandra.spring_restful_api.model.UserResponse;
import victor_chandra.spring_restful_api.repository.UserRepository;
import victor_chandra.spring_restful_api.security.BCrypt;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;

    @Transactional
    public void register(RegisterUserRequest request) {
        // validate
        validationService.validate(request);

        // cek apakah database ada user dengan username tersebut
        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }

        // jika sudah validated, maka kita bikin object untuk user nya
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt())); //pastikan gunakan library BCrypt
        user.setName(request.getUsername());

        userRepository.save(user);
    }

    public UserResponse get(User user) {
        return UserResponse.builder()
                .username(user.getUsername())
                .name(user.getName())
                .build();
    }
}
