package principal;

import java.util.Scanner;
import java.util.ArrayList;

public class GerenciaCaminhoes {
	private Scanner lerS, lerN;
	private ArrayList<Caminhao> caminhoes;
	
	public GerenciaCaminhoes(ArrayList<Caminhao> caminhoes) {
		this.lerS = new Scanner(System.in);
		this.lerN = new Scanner(System.in);
		this.caminhoes = caminhoes;
	}
	
	public void Cadastro() {
		System.out.println("--=[Cadastrar Caminhão]=--");
		Caminhao caminhao = new Caminhao();
		
		System.out.println("Digite os seguintes dados:");
		System.out.println("- Marca");
		caminhao.setMarca(lerS.nextLine());
		
		System.out.println("- Modelo");
		caminhao.setModelo(lerS.nextLine());
		
		System.out.println("- Ano de Fabricação");
		caminhao.setAnoFabricacao(lerN.nextInt());
		
		System.out.println("- Ano do modelo");
		caminhao.setAnoModelo(lerN.nextInt());
		
		System.out.println("- Placa");
		caminhao.setPlaca(lerS.nextLine());
		
		System.out.println("- Capacidade de Carga");
		caminhao.setCapacidadeCarga(lerN.nextFloat());
		
		System.out.println("- Quantidade de Eixos");
		caminhao.setNumeroDeEixos(lerN.nextInt());
		
		caminhoes.add(caminhao);
		
		System.out.println("Cadastro efetuado com sucesso.");
		
	}
	
	public void Alteracao() {
		System.out.println("--=[Alterar Caminhão]=--");
		
		if(caminhoes.isEmpty()) 
			System.out.println("Não existem caminhões cadastrados.");
		else {
			int resp, pos;
			
			System.out.println("Qual a posição que deseja alterar?");
			pos = lerN.nextInt();
			
			if((pos>=0)&& (pos < caminhoes.size())) {
				System.out.println("-[Dados do Caminhão]-");
				imprime(caminhoes.get(pos));
				System.out.println("---------------");
				
				System.out.println("Deseja realmente alterar os dados acima?(1-sim/2-não)");
				resp = lerN.nextInt();
				
				if(resp == 1) {
					System.out.println("Digite os seguintes dados:");
					Caminhao caminhao = caminhoes.get(pos);
					
					System.out.println("- Marca");
					caminhao.setMarca(lerS.nextLine());
					
					System.out.println("- Modelo");
					caminhao.setModelo(lerS.nextLine());
					
					System.out.println("- Ano de Fabricação");
					caminhao.setAnoFabricacao(lerN.nextInt());
					
					System.out.println("- Ano do modelo");
					caminhao.setAnoModelo(lerN.nextInt());
					
					System.out.println("- Placa");
					caminhao.setPlaca(lerS.nextLine());
					
					System.out.println("- Capacidade de Carga");
					caminhao.setCapacidadeCarga(lerN.nextFloat());
					
					System.out.println("- Quantidade de Eixos");
					caminhao.setNumeroDeEixos(lerN.nextInt());
					
					caminhoes.add(caminhao);
					
					System.out.println("Dados alterados com sucesso.");
					
				}else
					System.out.println("Operação cancelada pelo usuário.");
				
			}else
				System.out.println("Digite uma posição válida.");
			
		}
	}
	
	public void Exclusao() {
		System.out.println("--=[Excluir Caminhão]=--");
		
		if(caminhoes.isEmpty()) 
			System.out.println("Não existem caminhões cadastrados.");
			else {
				int resp, pos;
				
				System.out.println("Qual a posição que deseja EXCLUIR?");
				pos = lerN.nextInt();
		
				if((pos>=0)&&(pos<caminhoes.size())){
					System.out.println("-[Dados do Caminhão]-");
					imprime(caminhoes.get(pos));
					System.out.println("---------------");
					
					System.out.println("Deseja realmente EXCLUIR os dados acima?(1-sim/2-não)");
					resp = lerN.nextInt();
					
					if(resp == 1) {
						caminhoes.remove(pos);
						
						System.out.println("Caminhão excluído com sucesso.");
					
					}else
						System.out.println("Operação cancelada pelo usuário. Caminhão NÃO excluído");
				}else
					System.out.println("Digite uma posição válida.");
			}
	}
	
	public void Consulta() {
		System.out.println("--=[Consultar Caminhão]=--");
		if(caminhoes.isEmpty()) 
			System.out.println("Não existem caminhões cadastrados.");
		else {
			int pos;
			
			System.out.println("Qual a posição que deseja Consultar?");
			pos = lerN.nextInt();
			
			if((pos>=0)&& (pos < caminhoes.size())) {
				System.out.println("-[Dados do Caminhão]-");
				imprime(caminhoes.get(pos));
				System.out.println("---------------");
		
			}else
				System.out.println("Digite uma posição válida.");
		}
	}
	
	public void relatorio() {
		System.out.println("--=[Relatório Caminhões]=--");
		if(caminhoes.isEmpty()) 
			System.out.println("Não existem caminhões cadastrados.");
		else {
				for(Caminhao m: caminhoes) {
				
				imprime(m);
				System.out.println("---------------");
			}
		}
	}
	
	
	private void imprime(Caminhao caminhao) {
		System.out.println("- Marca: "+ caminhao.getMarca());
		System.out.println("- Modelo: "+ caminhao.getModelo());
		System.out.println("- Ano de Fabriação: "+ caminhao.getAnoFabricacao());
		System.out.println("- Ano do Modelo: "+ caminhao.getAnoModelo());
		System.out.println("- Placa: "+ caminhao.getPlaca());
		System.out.println("- Capacidade de Carga: "+ caminhao.getCapacidadeCarga());
		System.out.println("- Quantidade de Eixos: "+ caminhao.getNumeroDeEixos());
	}
	
}