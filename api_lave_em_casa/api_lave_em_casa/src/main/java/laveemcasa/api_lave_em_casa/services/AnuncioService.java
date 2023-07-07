package laveemcasa.api_lave_em_casa.services;

import laveemcasa.api_lave_em_casa.models.Anuncios;
import laveemcasa.api_lave_em_casa.models.UsuariosProprietarios;
import laveemcasa.api_lave_em_casa.repository.AnuncioRepository;
import laveemcasa.api_lave_em_casa.repository.ProprietariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final ProprietariosRepository proprietariosRepository;

    public AnuncioService(AnuncioRepository anuncioRepository, ProprietariosRepository proprietariosRepository) {
        this.anuncioRepository = anuncioRepository;
        this.proprietariosRepository = proprietariosRepository;
    }

    public Anuncios criarAnuncio(Integer proprietarioId, Anuncios anuncio) {
        UsuariosProprietarios proprietario = proprietariosRepository.findById(proprietarioId)
                .orElseThrow(() -> new RuntimeException("Usuário proprietário não encontrado"));
        anuncio.setProprietario(proprietario);


        Anuncios novoAnuncio = anuncioRepository.save(anuncio);

        return novoAnuncio;
    }

    public List<Anuncios> getAnuncios() {
        return anuncioRepository.findAll();
    }

    public Anuncios obterAnuncioPorId(Integer anuncioId) {
        return anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));
    }

    public Anuncios editarAnuncio(Integer proprietarioId, Integer anuncioId, Anuncios anuncioQueVaiSerAtualizado) {
        UsuariosProprietarios proprietario = proprietariosRepository.findById(proprietarioId)
                .orElseThrow(() -> new RuntimeException("Usuário proprietário não encontrado"));

        Anuncios anuncio = anuncioRepository.findById(anuncioId)
                .orElseThrow(() -> new RuntimeException("Anúncio não encontrado"));

        if (!anuncio.getProprietario().getId().equals(proprietarioId)) {
            throw new RuntimeException("Você não tem permissão para editar este anúncio");
        }

        Anuncios anuncioAtualizado = anuncioRepository.save(anuncioQueVaiSerAtualizado);

        return anuncioAtualizado;
    }

    public UsuariosProprietarios buscarProprietarioPorId(Integer proprietarioId) {
        Optional<UsuariosProprietarios> optionalProprietario = proprietariosRepository.findById(proprietarioId);
        return optionalProprietario.orElse(null);
    }

}
