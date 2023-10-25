package tasks.com.br.controller;

import java.awt.print.Pageable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
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
	public ResponseEntity deletarTaskPorId(@PathVariable Long id) {
		var task = repository.getReferenceById(id);
		task.excluir();
		return ResponseEntity.noContent().build();
	}
}
