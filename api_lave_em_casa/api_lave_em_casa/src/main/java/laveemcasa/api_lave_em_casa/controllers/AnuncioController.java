package laveemcasa.api_lave_em_casa.controllers;

import laveemcasa.api_lave_em_casa.models.Anuncios;
import laveemcasa.api_lave_em_casa.models.UsuariosProprietarios;
import laveemcasa.api_lave_em_casa.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;

    @Autowired
    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @PostMapping("/{proprietarioId}")
    public ResponseEntity<Anuncios> criarAnuncio(@PathVariable Integer proprietarioId, @RequestBody Anuncios anuncio) {
        UsuariosProprietarios proprietario = anuncioService.buscarProprietarioPorId(proprietarioId);
        anuncio.setProprietario(proprietario);

        Anuncios novoAnuncio = anuncioService.criarAnuncio(proprietario.getId(), anuncio);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAnuncio); //CLASSE DEBUGADA, MEU NOVO ANUNCIO CONTÉM O PROPRIETARIO ID, TESTAR O BOTAO DE EDITAR
    }

    @GetMapping
    public ResponseEntity<List<Anuncios>> getAnuncios() {
        List<Anuncios> anuncios = anuncioService.getAnuncios();
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/{anuncioId}")
    public ResponseEntity<Anuncios> obterAnuncioPorId(@PathVariable Integer anuncioId) {
        Anuncios anuncio = anuncioService.obterAnuncioPorId(anuncioId);
        return ResponseEntity.ok(anuncio);
    }

    @PutMapping("/{proprietarioId}/{anuncioId}")
    public ResponseEntity<Anuncios> editarAnuncio(
            @PathVariable Integer proprietarioId,
            @PathVariable Integer anuncioId,
            @RequestBody Anuncios anuncioQueVaiSerAtualizado) {

        Anuncios anuncio = anuncioService.editarAnuncio(proprietarioId, anuncioId, anuncioQueVaiSerAtualizado);

        return ResponseEntity.ok(anuncio);
    }
}
