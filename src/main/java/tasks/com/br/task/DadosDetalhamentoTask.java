package tasks.com.br.task;

import java.time.LocalDateTime;



public record DadosDetalhamentoTask(Long id,String nome,String descricao,Boolean concluido,LocalDateTime data) {
	public DadosDetalhamentoTask(Task task) {
		this(task.getId(), task.getNome(), task.getDescricao(), task.getConcluido(), task.getData());
	}
}
