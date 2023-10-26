package tasks.com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import tasks.com.br.task.DadosCadastroTask;
import tasks.com.br.task.DadosDetalhamentoTask;
import tasks.com.br.task.Task;
import tasks.com.br.task.TaskRepository;

@RestController
@RequestMapping("tasks")
public class TasksController {
	
	@Autowired
	private TaskRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Task> salvarTask(@RequestBody @Valid DadosCadastroTask dados, UriComponentsBuilder uriBuilder) {
		var task = new Task(dados);
		repository.save(task);
		
		var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(task);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhamentoTask> listarTaskPorId(@PathVariable Long id) {
		var task = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoTask(task));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarTaskPorId(@PathVariable Long id) {
		var task = repository.getReferenceById(id);
		task.excluir();
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	public ResponseEntity<List<Task>> listarTasks(@RequestParam(value = "concluido", required = false) String concluido,
			@RequestParam(value = "ordenar")String valores ){
		String[] valor = valores.split(",");
		String nome = valor[0];
		String data = valor[1];
		List<Task> lista = null;
		if("y".equals(concluido)) {
			lista = repository.buscarTarefasConcluidasPorNomeEData(nome, data);
		}else if("n".equals(concluido)) {
			lista = repository.buscarTarefasNaoConcluidasPorNomeEData(nome, data);
		}
		return ResponseEntity.ok(lista);
		
	}
}
