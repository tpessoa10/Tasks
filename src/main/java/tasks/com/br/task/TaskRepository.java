package tasks.com.br.task;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TaskRepository extends JpaRepository<Task, Long>{
	@Query("SELECT t FROM Task t WHERE t.concluido = true AND t.nome LIKE %:nome% AND DATE(t.data) = DATE(:data)")
	List<Task> buscarTarefasConcluidasPorNomeEData(String nome, String data);
	
	@Query("SELECT t FROM Task t WHERE t.concluido = false AND t.nome LIKE %:nome% AND DATE(t.data) = DATE(:data)")
	List<Task> buscarTarefasNaoConcluidasPorNomeEData(String nome, String data);
	
}
