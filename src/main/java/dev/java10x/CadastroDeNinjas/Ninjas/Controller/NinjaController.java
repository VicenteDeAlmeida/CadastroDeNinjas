package dev.java10x.CadastroDeNinjas.Ninjas.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ninjas")
public class NinjaController {
    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas Vindas",description = "Essa rota apresenta uma mensagem de boas vindas")
    public String boasVindas(){
        return "Essa é minha primeira mensagem nessa rota.";
    }

    //Adicionar Ninja(Create)
    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja",description = "Rota cria um novo ninja e insere no banco de dados")
    @ApiResponses(value = {@ApiResponse(responseCode = "201",description = "Ninja criado com sucesso"),
            @ApiResponse(responseCode = "400",description = "Erro na criação do ninja")})
    public ResponseEntity<String> criarNovoNinja(@RequestBody NinjaDTO ninja){
        NinjaDTO novoNinja = ninjaService.criarNovoNinja(ninja);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + novoNinja.getNome()+ " ID: " + novoNinja.getId());
    }

    //Procurar Ninja por Id(Read)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista ninja por id",description = "Rota lista ninja quando passamos o id correto")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Ninja listado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Ninja com esse id não encontrado")})
    public ResponseEntity<?> listarNinjasPorId(@PathVariable Long id){

        NinjaDTO ninja =  ninjaService.listarninjasPorId(id);
        if (ninja !=null){
            return ResponseEntity.ok(ninja);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id " + id + " não existe nos nossos registros.");
        }
    }

    //Mostrar todos os ninjas (Read)
    @GetMapping("/listar")
    public ResponseEntity<List <NinjaDTO>> listarNinjas(){
        List<NinjaDTO> ninjas =  ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    //Alterar dados dos ninja(Update)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera o ninja por id",description = "Rota altera o ninja quando passamos o id correto")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404",description = "Ninja não encontrado,não foi possível alterar")})
    public ResponseEntity<?> alterarNinjaPorId(@Parameter(description = "Usuario manda o id no caminho da requisição") @PathVariable Long id, @RequestBody NinjaDTO ninjaAtualizadp){
        NinjaDTO ninja =  ninjaService.atualizarNinja(id,ninjaAtualizadp);
        if (ninja != null){
            return ResponseEntity.ok(ninja);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja com o id " + id + " não encontrado.");
        }
    }

    //Deletar Ninja(Delete)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarNinjaPorID(@PathVariable Long id){
        if (ninjaService.listarninjasPorId(id)!= null){
        ninjaService.deletarNinjaporId(id);
        return ResponseEntity.ok("Ninja com o id " + id + " deletado com sucesso.");
        }else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com id " + id + " não encontrado.");
        }
    }


}
