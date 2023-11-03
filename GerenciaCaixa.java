package principal;

import java.util.Scanner;
import java.util.ArrayList;

public class GerenciaCaixa {
    private ArrayList<Locacao> locacoes;
    private ArrayList<Caixa> caixa;
    private Scanner lerS,lerN;
    
    

    public GerenciaCaixa(Caixa caixa) {
		super();
		this.locacoes = locacoes;
		this.lerS = lerS;
		this.lerN = lerN;
	}

	public void pagamentoLocacao() {
        System.out.println("Agendamento efetuado com sucesso.");

    }

    public void totalArrecadado() {
        System.out.println("Cancelado com sucesso!");

    }

    public void totalArrecadadoPorPeriodo() {
        System.out.println("Alterado com sucesso!");

    }

    public void totalAReceber() {
        System.out.println("Relatório impresso com sucesso!");
    }

}