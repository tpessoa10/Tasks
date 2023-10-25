package tasks.com.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import tasks.com.br.task.DadosCadastroTask;
import tasks.com.br.task.Task;
import tasks.com.br.task.TaskRepository;

@RestController
@RequestMapping("tasks")
public class TasksController {
	
	@Autowired
	private TaskRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity salvarTask(@RequestBody @Valid DadosCadastroTask dados, UriComponentsBuilder uriBuilder) {
		var task = new Task(dados);
		repository.save(task);
		
		var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();
		return ResponseEntity.created(uri).body(task);
	}
}
