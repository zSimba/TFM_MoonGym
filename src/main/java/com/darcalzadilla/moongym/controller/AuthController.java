package com.darcalzadilla.moongym.controller;

import com.darcalzadilla.moongym.dto.UserDto;
import com.darcalzadilla.moongym.entity.Role;
import com.darcalzadilla.moongym.entity.User;
import com.darcalzadilla.moongym.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            @RequestParam(value = "registered", required = false) String registered,
            Model model) {

        if (error != null) {
            model.addAttribute("errorMsg", "Usuario o contraseña inválidos");
        }
        if (logout != null) {
            model.addAttribute("msg", "Has cerrado sesión correctamente");
        }
        if (registered != null) {
            model.addAttribute("msg", "Registro exitoso. Ya puedes iniciar sesión");
        }
        return "login";
    }

    @GetMapping("/registro")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "registro";
    }

    @PostMapping("/registro")
    public String processRegistration(
            @ModelAttribute("userDto") @Valid UserDto dto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "registro";
        }
        if (userService.existsByUsername(dto.getUsername())) {
            result.rejectValue("username", "error.user", "Ese nombre de usuario ya existe");
            return "registro";
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.CUSTOMER);
        userService.save(user);

        return "redirect:/login?registered";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws Exception {
        request.logout();
        return "redirect:/";
    }
}
