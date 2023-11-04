package principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaCaixa {
    private ArrayList<Locacao> locacoes;
    private ArrayList<Caixa> caixa;
    private Scanner lerS;
    private Scanner lerN;
    private DateTimeFormatter dataF;
    
    public GerenciaCaixa(ArrayList<Locacao> locacoes, ArrayList<Caixa> caixa) {
        this.locacoes = locacoes;
        this.caixa = caixa;
        this.lerS = new Scanner(System.in);
        this.lerN = new Scanner(System.in);
        dataF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }

    public void pagamentoLocacao(ArrayList<Locacao> locacoes) {
        if (!locacoes.isEmpty()) {
            System.out.print("Qual a posição da locação que deseja efetuar o pagamento? ");
            int pos = lerN.nextInt();

            if (pos >= 0 && pos < locacoes.size()) {
                System.out.println("- Dados da Locação:");
                System.out.println("Detalhes da Locação:");
                System.out.println("Cliente: " + locacoes.get(pos).getCliente().getNome());
                System.out.println("Data de Início: " + locacoes.get(pos).getDataInicio());
                System.out.println("Data Prevista de Devolução: " + locacoes.get(pos).getDataPrevistaDevolucao());

                System.out.println("Confirmar seleção da posição? (1-sim/2-não)");
                int op = lerN.nextInt();

                if (op == 1) {
                    Locacao loc = locacoes.get(pos);
                    System.out.print("Qual a data Devolução(dd-MM-yyyy)? ");
                    String dataDevStr = lerS.nextLine();
                    LocalDate dataDevolucao = LocalDate.parse(dataDevStr, dataF);

                    if (dataDevolucao.compareTo(loc.getDataInicio()) < 0) {
                        System.out.println("PAGAMENTO NÃO REALIZADO, DATA DE DEVOLUÇÃO DEVE SER ANTERIOR À DO INÍCIO");
                    } else {
                        loc.setDataDevolucao(dataDevolucao);

                        if (loc.getDataDevolucao().compareTo(loc.getDataPrevistaDevolucao()) > 0) {
                            long diasAtraso = ChronoUnit.DAYS.between(loc.getDataPrevistaDevolucao(), loc.getDataDevolucao());
                            double valorTotal = 0;
                            double multa;

                            valorTotal = loc.getPreco() * Math.pow(1 + 0.0028, diasAtraso);
                            multa = valorTotal - loc.getPreco();
                            loc.setMulta(multa);

                            String multaStr = String.format("%.2f", multa);
                            System.out.println("LOCAÇÃO " + diasAtraso + " DIAS ATRASADA, SERÁ ACRESCIDO R$" + multaStr);
                            System.out.println("Pagamento realizado com sucesso");
                        } else {
                            System.out.println("Pagamento realizado com sucesso");
                        }
                    }
                } else {
                    System.out.println("Operação cancelada pelo usuário");
                }
            } else {
                System.out.println("Posição inválida");
            }
        } else {
            System.out.println("Sem locações cadastradas");
        }
    }
    
    public void totalArrecadado() {
        double totalArrecadado = 0;

        for (Locacao locacao : locacoes) {
            if (locacao.getStatus() == 1) { // Considerar apenas locações ativas
                totalArrecadado += locacao.getPreco() + locacao.getMulta();
            }
        }

        System.out.println("Total arrecadado desde a abertura da empresa: R$" + totalArrecadado);
    }

    public void totalArrecadadoPorPeríodo() {
        System.out.print("Digite a data de início (dd-MM-yyyy): ");
        String dataInicioStr = lerS.nextLine();
        LocalDate dataInicio = LocalDate.parse(dataInicioStr, dataF);

        System.out.print("Digite a data de fim (dd-MM-yyyy): ");
        String dataFimStr = lerS.nextLine();
        LocalDate dataFim = LocalDate.parse(dataFimStr, dataF);

        double totalArrecadado = 0;

        for (Locacao locacao : locacoes) {
            if (locacao.getStatus() == 1) { // Considerar apenas locações ativas
                LocalDate dataDevolucao = locacao.getDataDevolucao();
                if (dataDevolucao != null && (dataDevolucao.isEqual(dataInicio) || dataDevolucao.isEqual(dataFim) || (dataDevolucao.isAfter(dataInicio) && dataDevolucao.isBefore(dataFim)))) {
                    totalArrecadado += locacao.getPreco() + locacao.getMulta();
                }
            }
        }

        System.out.println("Total arrecadado no período de " + dataInicioStr + " a " + dataFimStr + ": R$" + totalArrecadado);
    }

    public void totalAReceber() {
        double valorAReceber = 0;

        for (Locacao locacao : locacoes) {
            if (locacao.getStatus() == 1 && locacao.getDataDevolucao() == null) {
                valorAReceber += locacao.getPreco();
            }
        }

        System.out.println("Valor total a receber dos carros alugados e não devolvidos: R$" + valorAReceber);
    }

}
