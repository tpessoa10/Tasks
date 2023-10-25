package tasks.com.br.task;



import jakarta.validation.constraints.NotBlank;

public record DadosCadastroTask(
		@NotBlank
		String nome,
		@NotBlank
		String descricao) {}