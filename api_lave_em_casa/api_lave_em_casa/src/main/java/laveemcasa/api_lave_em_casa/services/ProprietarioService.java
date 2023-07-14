package laveemcasa.api_lave_em_casa.services;

import laveemcasa.api_lave_em_casa.models.UsuariosProprietarios;
import laveemcasa.api_lave_em_casa.repository.ProprietariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProprietarioService {

    private final ProprietariosRepository proprietariosRepository;

    @Autowired
    public ProprietarioService(ProprietariosRepository proprietariosRepository) {
        this.proprietariosRepository = proprietariosRepository;
    }

    public UsuariosProprietarios criarProprietario(UsuariosProprietarios proprietario) {
        return proprietariosRepository.save(proprietario);
    }

    // Método para autenticar o usuário
    public UsuariosProprietarios autenticacaoProprietario(String email, String senha) {
        // Consulta o usuário com base no email
        Optional<UsuariosProprietarios> userOptional = proprietariosRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UsuariosProprietarios user = userOptional.get();

            // Verifica se a senha fornecida corresponde à senha do usuário
            if (senha.equals(user.getSenha())) {
                return user; // Credenciais válidas
            }
        }

        return null; // Credenciais inválidas ou usuário não encontrado
    }
}

