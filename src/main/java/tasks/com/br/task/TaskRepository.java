package tasks.com.br.task;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TaskRepository extends JpaRepository<Task, Long>{
	@Query("SELECT t FROM Task t WHERE t.concluido = true AND t.nome = :nome")
	List<Task> buscarTarefasConcluidasPorNome(String nome);
	
	@Query("SELECT t FROM Task t WHERE t.concluido = false AND t.nome = :nome")
	List<Task> buscarTarefasNaoConcluidasPorNome(String nome);
	
}
