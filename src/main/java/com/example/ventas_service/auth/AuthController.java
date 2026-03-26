package com.example.ventas_service.auth;


import com.example.ventas_service.Config.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request) {
        if(request.getUsername().equals("admin") && request.getPassword().equals("1234")){
            return JwtUtil.generateToken(request.getUsername());
        }
        return "Credenciales incorrectas";
    }
}
