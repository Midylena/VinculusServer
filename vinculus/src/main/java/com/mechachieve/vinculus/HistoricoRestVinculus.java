package com.mechachieve.vinculus;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mechachieve.vinculus.user.User;


@RestController
@RequestMapping("/historicoVinculus/{login}")
public class HistoricoRestVinculus {

	@Autowired
	private HistoricoDaoVinculusGet historicoVinculusGet;
	@Autowired
	private HistoricoDaoVinculusPost historicoVinculusPost;
	@Autowired
	private NotificacaoDaoVinculus notificacaoVinculus;
	@Autowired
	private ImagemDaoVinculusGet imagemVinculusGet;
	@Autowired
	private ImagemDaoVinculusPost imagemVinculusPost;
	@Autowired
	private AtividadeDaoVinculusList atividadeVinculusList;
	@Autowired
	private AtividadeDaoVinculusLast atividadeVinculusLast;
	@Autowired
	private AtividadeDaoVinculusLastLeg atividadeVinculusLastLeg;

	@GetMapping("/listAll")
	public List<HistoricoVinculus> get(){
		return historicoVinculusGet.findAll();
	}

	@GetMapping("/get")
    public ResponseEntity<?> getClienteDados(@PathVariable String login) {
        Optional<HistoricoVinculus> cliente = historicoVinculusGet.findFirstByClienteIdOrderByIdDesc(login);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Cliente não encontrado");
        }
    }

	@PostMapping("/post")
    public ResponseEntity<?> atualizarDados(@PathVariable String login, @RequestBody HistoricoVinculus novosDados) {
        Optional<User> autorizado = historicoVinculusPost.findByLogin(login);

        if (autorizado.isPresent()) {
        	HistoricoVinculus cliente = historicoVinculusGet.save(novosDados);
            return ResponseEntity.ok("ENVIADO");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Cliente não encontrado");
        }
    }

	@GetMapping("/notificacaoGet")
	public Collection<NotificacaoVinculus> notificacao(){
		return notificacaoVinculus.notificacao();
	}

	@PostMapping("/notificacaoPost")
	public String post(@RequestBody NotificacaoVinculus notificacao) {
		notificacaoVinculus.save(notificacao);
		return "ENVIADO";
	}

	@GetMapping("/imagemGet")
    public ResponseEntity<?> getImagem(@PathVariable String login) {
        Optional<ImagemVinculus> cliente = imagemVinculusGet.findImagemByClienteId(login);

        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Cliente não encontrado");
        }
    }

	@PostMapping("/imagemPost")
    public ResponseEntity<?> postImagem(@PathVariable String login, @RequestBody ImagemVinculus novosDados) {
        Optional<User> autorizado = imagemVinculusPost.findByLogin(login);

        if (autorizado.isPresent()) {
        	ImagemVinculus cliente = imagemVinculusGet.save(novosDados);
            return ResponseEntity.ok("ENVIADO");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                                 .body("Cliente não encontrado");
        }
    }
	
	@GetMapping("/atividadeList/{data}")
	public ResponseEntity<?> getHistoricoPorClienteEData(@PathVariable String login, @PathVariable String data) {
	    List<AtividadeVinculus> clienteList = atividadeVinculusList.findByClienteIdAndDateOrderByIdDesc(login, data);
	    
	    if (!clienteList.isEmpty()) {
	        return ResponseEntity.ok(clienteList);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("Cliente não encontrado");
	    }
	}
	
	@GetMapping("/atividadeLast")
	public Collection<AtividadeVinculus> atividade(){
		return atividadeVinculusLast.atividade();
	}
	
	@GetMapping("/atividadeLastLeg/{data}")
	public ResponseEntity<?> getLastLeg(@PathVariable String login, @PathVariable String data) {
	    Optional<AtividadeVinculus> autorizado = atividadeVinculusLastLeg.findLastByClienteIdAndDate(login, data);

	    if (autorizado.isPresent()) {
	        return ResponseEntity.ok(autorizado.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                             .body("Cliente não encontrado");
	    }
	}

	
	@PostMapping("/atividadePost")
	public String atividadePost(@RequestBody AtividadeVinculus atividade) {
		atividadeVinculusList.save(atividade);
		return "ENVIADO";
	}

}