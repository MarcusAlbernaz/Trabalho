package principal;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class GerenciaFisicas {
	private Scanner lerN, lerS;
	private ArrayList<Fisica> fisicas;

	public GerenciaFisicas(ArrayList<Fisica> fisicas) {
		this.lerS = new Scanner(System.in);
		this.lerN = new Scanner(System.in);
		this.fisicas = fisicas;
	}

	public void Cadastro() {
		System.out.println("--=[Cadastrar Pessoa Física]=--");
		Fisica fisica = new Fisica();
		String dataNascimentoStr;

		System.out.println("Digite os seguintes dados:");
		System.out.println("- Nome");
		fisica.setNome(lerS.nextLine());

		System.out.println("- Endereço");
		fisica.setEndereco(lerS.nextLine());

		System.out.println("- Telefone");
		fisica.setTelefone(lerS.nextLine());

		System.out.println("- CPF");
		fisica.setCpf(lerS.nextLine());

		System.out.println("- Data de Nascimento no formato (dd-MM-yyyy)");
		dataNascimentoStr = lerS.nextLine();
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Formato "dd-MM-yyyy"
		try {
		    LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, dateFormatter);
		    fisica.setDataNascimento(dataNascimento);
		} catch (DateTimeParseException e) {
		    System.out.println("Formato de data inválido. Certifique-se de que está no formato correto (dd-MM-yyyy).");
		    return;
		}
		
		if (dataNascimentoStr != null) {
			System.out.println("Data de Nascimento: " + dataNascimentoStr);
		} else {
			System.out.println("Erro ao processar a data de nascimento. Por favor, tente novamente.");
		}

		fisicas.add(fisica);

		System.out.println("Cadastro efetuado com sucesso.");
		System.out.println("-----------------------------");
	}

	public void Alteracao() {
		    System.out.println("--=[Alterar Pessoa Física]=--");

		    if (fisicas.isEmpty())
		        System.out.println("Não existem pessoas físicas cadastradas.");
		    else {
		        int resp, pos;

		        System.out.println("Qual a posição que deseja alterar?");
		        pos = lerN.nextInt();

		        if ((pos >= 0) && (pos < fisicas.size())) {
		            System.out.println("-[Dados da Pessoa Física]-");
		            imprime(fisicas.get(pos));
		            System.out.println("---------------");

		            System.out.println("Deseja realmente alterar os dados acima?(1-sim/2-não)");
		            resp = lerN.nextInt();

		            if (resp == 1) {
		                System.out.println("Digite os novos dados para: ");
		                Fisica fisica = fisicas.get(pos);

		                System.out.println("- Nome");
		                fisica.setNome(lerS.nextLine());

		                System.out.println("- Endereço");
		                fisica.setEndereco(lerS.nextLine());

		                System.out.println("- Telefone");
		                fisica.setTelefone(lerS.nextLine());

		                System.out.println("- CPF");
		                fisica.setCpf(lerS.nextLine());

		                System.out.println("- Data de Nascimento no formato (dd-MM-yyyy)");
		                String dataNascimentoStr = lerS.nextLine();
		                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Formato "dd-MM-yyyy"

		                try {
		                    LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, dateFormatter);
		                    fisica.setDataNascimento(dataNascimento);
		                    System.out.println("Dados alterados com sucesso.");
		                    System.out.println("----------------------------------");
		                } catch (DateTimeParseException e) {
		                    System.out.println("Formato de data inválido. Certifique-se de que está no formato correto (dd-MM-yyyy).");
		                }

		            } else
		                System.out.println("Operação cancelada pelo usuário.");

		        } else
		            System.out.println("Digite uma posição válida.");
		    }
		}

	public void Exclusao() {

		System.out.println("--=[Excluir Pessoa Física]=--");
		if (fisicas.isEmpty())
			System.out.println("Não existem pessoas físicas cadastradas.");
		else {
			int resp, pos;

			System.out.println("Qual a posição que deseja EXCLUIR?");
			pos = lerN.nextInt();

			if ((pos >= 0) && (pos < fisicas.size())) {
				System.out.println("-[Dados da Pessoa Física]-");
				imprime(fisicas.get(pos));
				System.out.println("---------------");

				System.out.println("Deseja realmente EXCLUIR os dados acima?(1-sim/2-não)");
				resp = lerN.nextInt();

				if (resp == 1) {
					fisicas.remove(pos);

					System.out.println("Pessoa Física excluída com sucesso.");
					System.out.println("-----------------------------");
				} else
					System.out.println("Operação cancelada pelo usuário.");
			} else
				System.out.println("Digite uma posição válida.");

		}
	}

	public void Consulta() {
		System.out.println("--=[Consultar Pessoa Física]=--");
		if (fisicas.isEmpty())
			System.out.println("Não existem pessoas físicas cadastradas.");
		else {
			int pos;

			System.out.println("Qual a posição que deseja Consultar?");
			pos = lerN.nextInt();

			if ((pos >= 0) && (pos < fisicas.size())) {
				System.out.println("-[Dados da Pessoa Física]-");
				imprime(fisicas.get(pos));
				System.out.println("---------------");

			} else
				System.out.println("Digite uma posição válida.");
		}
	}

	public void Relatorio() {
		System.out.println("--=[Relatório de Pessoas Físicas]=--");

		if (fisicas.isEmpty())
			System.out.println("Não existem pessoas fisicas cadastrados.");
		else {

			for (Fisica m : fisicas) {

				imprime(m);
				System.out.println("---------------");
			}
		}
	}

	private void imprime(Fisica fisica) {
		System.out.println("- Nome: " + fisica.getNome());
		System.out.println("- Endereço: " + fisica.getEndereco());
		System.out.println("- Telefone: " + fisica.getTelefone());
		System.out.println("- CPF: " + fisica.getCpf());
		System.out.println("- Data de Nascimento: " + fisica.getDataNascimento());

	}

}