package bctc.cabinet.auth;

import bctc.cabinet.config.JwtService;
import bctc.cabinet.models.Role;
import bctc.cabinet.models.Student;
import bctc.cabinet.services.StudentService;
import bctc.cabinet.util.ServiceError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService {
    // TODO: not only studentService
    private final StudentService studentService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    // Create user, save to DB, return generated token
    public AuthenticationResponse register(RegisterRequest request) {
        // TODO: remove upcast
        Student student = (Student) Student.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .build();

        studentService.save(student);
        String token = jwtService.generateToken(student);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    // Authenticate user
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        Student student = studentService.findByEmail(request.getEmail(), ServiceError.USER_NOT_FOUND);
        String token = jwtService.generateToken(student);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
