package principal;

import java.util.Scanner;
import java.util.ArrayList;

public class GerenciaCarros {
	private Scanner lerN,lerS;
	private ArrayList<Carro> carros;
	
	public GerenciaCarros(ArrayList<Carro> carros) {
		this.lerS = new Scanner(System.in);
		this.lerN = new Scanner(System.in);
		this.carros = carros;
	}
	
	public void Cadastro() {
		System.out.println("--=[Cadastrar Carro]=--");
		Carro carro = new Carro();
		
		System.out.println("Digite os seguintes dados:");
		System.out.println("- Marca");
		carro.setMarca(lerS.nextLine());
		
		System.out.println("- Modelo");
		carro.setModelo(lerS.nextLine());
		
		System.out.println("- Ano de Fabricação");
		carro.setAnoFabricacao(lerN.nextInt());
		
		System.out.println("- Ano do modelo");
		carro.setAnoModelo(lerN.nextInt());
		
		System.out.println("- Placa");
		carro.setPlaca(lerS.nextLine());
		
		System.out.println("- Capacidade de Passageiros");
		carro.setCapacidadePassageiros(lerN.nextInt());
		
		System.out.println("- Quantidade de Portas");
		carro.setQuantidadePortas(lerN.nextInt());
		
		carros.add(carro);
		
		System.out.println("Cadastro efetuado com sucesso.");
		
	}
	
	public void Alteracao() {
		System.out.println("--=[Alterar Carro]=--");
		
		if(carros.isEmpty()) 
			System.out.println("Não existem carros cadastrados.");
		else {
			int resp, pos;
			
			System.out.println("Qual a posição que deseja alterar?");
			pos = lerN.nextInt();
			
			if((pos>=0)&& (pos < carros.size())) {
				System.out.println("-[Dados do Carro]-");
				imprime(carros.get(pos));
				System.out.println("---------------");
				
				System.out.println("Deseja realmente alterar os dados acima?(1-sim/2-não)");
				resp = lerN.nextInt();
				
				if(resp == 1) {
					System.out.println("Digite os novos dados para: ");
					Carro carro = carros.get(pos);
					
					System.out.println("- Marca");
					carro.setMarca(lerS.nextLine());
					
					System.out.println("- Modelo");
					carro.setModelo(lerS.nextLine());
					
					System.out.println("- Ano de Fabricação");
					carro.setAnoFabricacao(lerN.nextInt());
					
					System.out.println("- Ano do modelo");
					carro.setAnoModelo(lerN.nextInt());
					
					System.out.println("- Placa");
					carro.setPlaca(lerS.nextLine());
					
					System.out.println("- Capacidade de Passageiros");
					carro.setCapacidadePassageiros(lerN.nextInt());
					
					System.out.println("- Quantidade de Portas");
					carro.setQuantidadePortas(lerN.nextInt());
					
					System.out.println("Dados alterados com sucesso.");
					
				}else
					System.out.println("Operação cancelada pelo usuário.");
				
			}else
				System.out.println("Digite uma posição válida.");
			
		}
		
	}
	
	public void Exclusao() {
		System.out.println("--=[Excluir Carro]=--");
		if(carros.isEmpty()) 
			System.out.println("Não existem carros cadastrados.");
		else {
			int resp, pos;
			
			System.out.println("Qual a posição que deseja EXCLUIR?");
			pos = lerN.nextInt();
			
			if((pos>=0)&& (pos < carros.size())) {
				System.out.println("-[Dados do Carro]-");
				imprime(carros.get(pos));
				System.out.println("---------------");
				
				System.out.println("Deseja realmente EXCLUIR os dados acima?(1-sim/2-não)");
				resp = lerN.nextInt();
				
				if(resp == 1) {
					carros.remove(pos);
				
					System.out.println("Carro excluído com sucesso.");
					
				}else
					System.out.println("Operação cancelada pelo usuário. Carro NÃO excluído");
			}else
				System.out.println("Digite uma posição válida.");
			
		}
	}
	
	public void Consulta() {
		System.out.println("--=[Consultar Carro]=--");
		if(carros.isEmpty()) 
			System.out.println("Não existem carros cadastrados.");
		else {
			int pos;
			
			System.out.println("Qual a posição que deseja Consultar?");
			pos = lerN.nextInt();
			
			if((pos>=0)&& (pos < carros.size())) {
				System.out.println("-[Dados do Carro]-");
				imprime(carros.get(pos));
				System.out.println("---------------");
		
			}else
				System.out.println("Digite uma posição válida.");
		}
	}
	
	public void Relatorio() {
		System.out.println("--=[Relatório de Carros]=--");
		
		if(carros.isEmpty()) 
			System.out.println("Não existem carros cadastrados.");
		else {
			
			for(Carro m: carros) {
				
				imprime(m);
				System.out.println("---------------");
			}
		}
	}
	
	private void imprime(Carro carro) {
		System.out.println("- Marca: "+ carro.getMarca());
		System.out.println("- Modelo: "+ carro.getModelo());
		System.out.println("- Ano de Fabriação: "+ carro.getAnoFabricacao());
		System.out.println("- Ano do Modelo: "+ carro.getAnoModelo());
		System.out.println("- Placa: "+ carro.getPlaca());
		System.out.println("- Capacidade de Passageiros: "+ carro.getCapacidadePassageiros());
		System.out.println("- Quantidade de Portas: "+ carro.getQuantidadePortas());
	}
}