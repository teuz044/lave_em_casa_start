package laveemcasa.api_lave_em_casa.controllers;

import laveemcasa.api_lave_em_casa.config.JwtResponse;
import laveemcasa.api_lave_em_casa.config.JwtUtil;
import laveemcasa.api_lave_em_casa.models.UsuariosProprietarios;
import laveemcasa.api_lave_em_casa.services.ProprietarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final ProprietarioService proprietarioService;

    private final HttpSession httpSession;

    public AuthController(JwtUtil jwtUtil, ProprietarioService proprietarioService, HttpSession httpSession) {
        this.jwtUtil = jwtUtil;
        this.proprietarioService =  proprietarioService;
        this.httpSession = httpSession;
    }

    @PostMapping("/autenticacao")
    public ResponseEntity<?> authenticate(@RequestBody UsuariosProprietarios usuariosProprietarios) {
        // Autentica o usuário com base no email e senha
        UsuariosProprietarios user =  proprietarioService.autenticacaoProprietario(usuariosProprietarios.getEmail(), usuariosProprietarios.getSenha());

        if (user != null) {
            // As credenciais são válidas, gera o token JWT
            String token = jwtUtil.generateToken(user.getEmail());

            httpSession.setAttribute("jwtToken", token);

            // Retorna o token como resposta
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            // As credenciais são inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
