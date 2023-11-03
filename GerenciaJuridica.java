package principal;

import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaJuridica {
	private ArrayList<Juridica> juridicas;
	Scanner lerS = new Scanner(System.in);
	Scanner lerN = new Scanner(System.in);

	public GerenciaJuridica() {
	}
	
	public GerenciaJuridica(ArrayList<Juridica> juridicas) {
		super();
		this.juridicas = juridicas;
	}
	
	public void Cadastro() {
		System.out.println("--=[Cadastrar Pessoa Jurídica]=--");
		Juridica juridica = new Juridica();
		
		System.out.println("Digite os seguintes dados:");
		System.out.println("- Nome");
		juridica.setNome(lerS.nextLine());
		
		System.out.println("- Endereço");
		juridica.setEndereco(lerS.nextLine());
		
		System.out.println("- Telefone");
		juridica.setTelefone(lerS.nextLine());
		
		System.out.println("- CNPJ");
		juridica.setCnpj(lerS.nextLine());
		
		
		juridicas.add(juridica);
		
		System.out.println("Cadastro efetuado com sucesso.");
		
	}
	
	public void Alteracao() {
		System.out.println("--=[Alterar Pessoa Jurídica]=--");
		
		if(juridicas.isEmpty()) 
			System.out.println("Não existem pessoas jurídicas cadastradas.");
		else {
			int resp, pos;
			
			System.out.println("Qual a posição que deseja alterar?");
			pos = lerN.nextInt();
			
			if((pos>=0)&& (pos < juridicas.size())) {
				System.out.println("-[Dados da Pessoa Física]-");
				imprime(juridicas.get(pos));
				System.out.println("---------------");
				
				System.out.println("Deseja realmente alterar os dados acima?(1-sim/2-não)");
				resp = lerN.nextInt();
				
				if(resp == 1) {
					System.out.println("Digite os novos dados para: ");
					Juridica juridica = juridicas.get(pos);
					
					System.out.println("- Nome");
					juridica.setNome(lerS.nextLine());
					
					System.out.println("- Endereço");
					juridica.setEndereco(lerS.nextLine());
					
					System.out.println("- Telefone");
					juridica.setTelefone(lerS.nextLine());
					
					System.out.println("- CNPJ");
					juridica.setCnpj(lerS.nextLine());
				
					
					System.out.println("Dados alterados com sucesso.");
					
				}else
					System.out.println("Operação cancelada pelo usuário.");
				
			}else
				System.out.println("Digite uma posição válida.");
			
		}
	}
	
	public void Exclusao() {
		
		System.out.println("--=[Excluir Pessoa Jurídica]=--");
		if(juridicas.isEmpty()) 
			System.out.println("Não existem pessoas jurídicas cadastradas.");
		else {
			int resp, pos;
			
			System.out.println("Qual a posição que deseja EXCLUIR?");
			pos = lerN.nextInt();
			
			if((pos>=0)&& (pos < juridicas.size())) {
				System.out.println("-[Dados da Pessoa Jurídica]-");
				imprime(juridicas.get(pos));
				System.out.println("---------------");
				
				System.out.println("Deseja realmente EXCLUIR os dados acima?(1-sim/2-não)");
				resp = lerN.nextInt();
				
				if(resp == 1) {
					juridicas.remove(pos);
				
					System.out.println("Pessoa Jurídica excluída com sucesso.");
					
				}else
					System.out.println("Operação cancelada pelo usuário.");
			}else
				System.out.println("Digite uma posição válida.");
			
		}
	}
	
	public void Consulta() {
	System.out.println("--=[Consultar Pessoa Jurídica]=--");
	if(juridicas.isEmpty()) 
		System.out.println("Não existem pessoas jurídicas cadastradas.");
	else {
		int pos;
		
		System.out.println("Qual a posição que deseja Consultar?");
		pos = lerN.nextInt();
		
		if((pos>=0)&& (pos < juridicas.size())) {
			System.out.println("-[Dados da Pessoa Jurídica]-");
			imprime(juridicas.get(pos));
			System.out.println("---------------");
	
		}else
			System.out.println("Digite uma posição válida.");
	}
}

public void Relatorio() {
	System.out.println("--=[Relatório de Pessoas Jurídicas]=--");
	
	if(juridicas.isEmpty()) 
		System.out.println("Não existem pessoas jurídicas cadastradas.");
	else {
		
		for(Juridica m: juridicas) {
			
			imprime(m);
			System.out.println("---------------");
		}
	}
}
	
	private void imprime(Juridica juridicas) {
		System.out.println("- Nome: "+ juridicas.getNome());
		System.out.println("- Endereço: "+ juridicas.getEndereco());
		System.out.println("- Telefone: "+ juridicas.getTelefone());
		System.out.println("- CNPJ: "+ juridicas.getCnpj());

	}
	
}