package principal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class GerenciaCaixa {
    private Scanner lerN;
    private Scanner lerS;
    private DateTimeFormatter dataF;
    private ArrayList<Locacao> locacoes;

    public GerenciaCaixa(
        ArrayList<Locacao> locacoes,
        ArrayList<Fisica> fisicas,
        ArrayList<Juridica> juridicas,
        ArrayList<Caminhao> caminhoes,
        ArrayList<Carro> carros
    ) {
        this.locacoes = locacoes;
        lerN = new Scanner(System.in);
        lerS = new Scanner(System.in);
        dataF = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    }
    
    double totalArrecadado = 0;
    
    public void pagamentoLocacao() {
        if (locacoes.isEmpty()) {
            System.out.println("Sem locações cadastradas");
        } else {
            System.out.print("Qual a posição da locação que deseja efetuar o pagamento? ");
            int pos = lerN.nextInt();

            if (pos >= 0 && pos < locacoes.size()) {
                Locacao loc = locacoes.get(pos);

                if (loc.getStatus() == 1) { // Verifica se a locação não está cancelada
                    System.out.println("- Dados da Locação:");
                    System.out.println("Detalhes da Locação:");
                    System.out.println("Cliente: " + loc.getCliente().getNome());
                    System.out.println("Data de Início: " + loc.getDataInicio());
                    System.out.println("Data Prevista de Devolução: " + loc.getDataPrevistaDevolucao());

                    System.out.println("Confirmar seleção da posição? (1-sim/2-não)");
                    int op = lerN.nextInt();

                    if (op == 1) {
                        System.out.print("Qual a data Devolução (dd-MM-yyyy)? ");
                        String dataDevStr = lerS.nextLine();
                        LocalDate dataDevolucao = LocalDate.parse(dataDevStr, dataF);

                        if (dataDevolucao.compareTo(loc.getDataInicio()) < 0) {
                            System.out.println("PAGAMENTO NÃO REALIZADO, DATA DE DEVOLUÇÃO DEVE SER ANTERIOR À DO INÍCIO");
                        } else {
                            loc.setDataDevolucao(dataDevolucao);

                            if (loc.getDataDevolucao().compareTo(loc.getDataPrevistaDevolucao()) > 0) {
                                long diasAtraso = ChronoUnit.DAYS.between(loc.getDataPrevistaDevolucao(), loc.getDataDevolucao());
                                double precoPorDia = 1.0; // Preço por dia de locação
                                double valorTotal = precoPorDia * diasAtraso;
                                double multa = valorTotal * (0.0028 * diasAtraso); // 0.28% por dia de atraso (juros sobre juros)

                                System.out.println("LOCAÇÃO " + diasAtraso + " DIAS ATRASADA");
                                System.out.println("Valor da Locação (sem multa): R$" + valorTotal);
                                System.out.println("Multa: R$" + multa);
                                System.out.println("Valor Total: R$" + (valorTotal + multa));
                                totalArrecadado = totalArrecadado + (valorTotal + multa);
                                System.out.println("Pagamento realizado com sucesso");
                            } else {
                                System.out.println("Pagamento realizado com sucesso");
                            }
                        }
                    } else {
                        System.out.println("Operação cancelada pelo usuário");
                    }
                } else {
                    System.out.println("Essa locação está cancelada e não pode ser paga.");
                }
            } else {
                System.out.println("Posição inválida");
            }
        }
    }

    public void totalArrecadado() {
    	System.out.println("Total arrecadado desde a abertura da empresa, incluindo as multas: R$" + totalArrecadado);

        /*for (Locacao locacao : locacoes) {
            if (locacao.getStatus() == 1) { // Considerar apenas locações ativas
                double precoPorDia = 1.0; // Preço por dia de locação
                LocalDate dataDevolucao = locacao.getDataDevolucao();
                LocalDate dataPrevistaDevolucao = locacao.getDataPrevistaDevolucao();

                if (dataDevolucao != null) {
                    long diasLocacao = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucao);
                    double valorLocacao = precoPorDia * diasLocacao;
                    
                    // Verificar se há multa
                    Double multa = locacao.getMulta();
                    if (multa != null) {
                        valorLocacao += multa; // Adicionar multa ao valor total
                    }

                    totalArrecadado += valorLocacao;
                }
            }
        }

        System.out.println("Total arrecadado desde a abertura da empresa, incluindo as multas: R$" + totalArrecadado);
    */
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

                if (dataDevolucao != null) {
                    if (dataDevolucao.isEqual(dataInicio) || dataDevolucao.isEqual(dataFim) || (dataDevolucao.isAfter(dataInicio) && dataDevolucao.isBefore(dataFim))) {
                        double precoPorDia = 1.0; // Preço por dia de locação
                        long diasLocacao = ChronoUnit.DAYS.between(locacao.getDataPrevistaDevolucao(), dataDevolucao);
                        double valorLocacao = precoPorDia * diasLocacao;

                        // Verificar se há multa
                        Double multa = locacao.getMulta();
                        if (multa != null) {
                            valorLocacao += multa; // Adicionar multa ao valor total
                        }

                        totalArrecadado += valorLocacao;
                    }
                }
            }
        }

        System.out.println("Valor arrecadado no período de " + dataInicioStr + " a " + dataFimStr + ", incluindo as multas: R$" + totalArrecadado);
    }
    

    public void totalAReceber() {
        double valorAReceber = 0;

        for (Locacao locacao : locacoes) {
            if (locacao.getStatus() == 1 && locacao.getDataDevolucao() == null) {
                LocalDate dataInicio = locacao.getDataInicio();
                LocalDate dataAtual = LocalDate.now();

                if (dataAtual.isAfter(dataInicio)) {
                    long diasLocacao = ChronoUnit.DAYS.between(dataInicio, dataAtual);
                    double precoPorDia = 1.0; // Preço por dia de locação
                    double multa = 0;

                    // Verificar se há multa
                    Double multaLocacao = locacao.getMulta();
                    if (multaLocacao != null) {
                        multa = multaLocacao;
                    }

                    valorAReceber += (diasLocacao * precoPorDia) + multa;
                }
            }
        }

        System.out.println("Valor total a receber dos carros alugados e não devolvidos: R$" + valorAReceber);
    }
}
