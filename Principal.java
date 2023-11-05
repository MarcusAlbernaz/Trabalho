package principal;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha;
        ArrayList<Fisica> fisicas = new ArrayList<>();
        GerenciaFisicas gerFisicas = new GerenciaFisicas(fisicas);
        ArrayList<Juridica> juridicas = new ArrayList<>();
        GerenciaJuridica gerJuridicas = new GerenciaJuridica(juridicas);
        ArrayList<Carro> carros = new ArrayList<>();
        GerenciaCarros gerCarro = new GerenciaCarros(carros);
        ArrayList<Caminhao> caminhoes = new ArrayList<>();
        GerenciaCaminhoes gerCaminhoes = new GerenciaCaminhoes(caminhoes);

        ArrayList<Locacao> locacao = new ArrayList<>();
        GerenciaAgenda gerenciaAgenda = new GerenciaAgenda(fisicas, juridicas, caminhoes, carros, locacao);
        GerenciaCaixa gerenciaCaixa = new GerenciaCaixa(locacao, fisicas, juridicas, caminhoes, carros);

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Gerenciar Cliente");
            System.out.println("2 - Gerenciar Veículo");
            System.out.println("3 - Gerenciar Locação");
            System.out.println("4 - Gerenciar Caixa");
            System.out.println("5 - Sair");

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    int escolha_cliente;
                    do {
                        System.out.println("Escolha uma opção:");
                        System.out.println("1- Gerenciar Pessoa Física");
                        System.out.println("2- Gerenciar Pessoa Jurídica");
                        System.out.println("3- Voltar ao menu principal");
                        escolha_cliente = scanner.nextInt();
                        switch (escolha_cliente) {
                            case 1:
                                int escolha_cliente2;
                                System.out.println("Escolha uma opção:");
                                System.out.println("1- Cadastrar Pessoa Física");
                                System.out.println("2- Alterar Pessoa Física");
                                System.out.println("3- Excluir Pessoa Física");
                                System.out.println("4- Consultar Pessoa Física");
                                System.out.println("5- Relatório de Pessoas Físicas");
                                System.out.println("6- Voltar ao menu anterior");
                                escolha_cliente2 = scanner.nextInt();
                                switch (escolha_cliente2) {
                                    case 1:
                                        gerFisicas.Cadastro();
                                        break;
                                    case 2:
                                        gerFisicas.Alteracao();
                                        break;
                                    case 3:
                                        gerFisicas.Exclusao();
                                        break;
                                    case 4:
                                        gerFisicas.Consulta();
                                        break;
                                    case 5:
                                        gerFisicas.Relatorio();
                                        break;
                                    case 6:
                                        System.out.println("Retornando ao menu principal");
                                        System.out.println("-----------------------------");
                                }
                                break;
                            case 2:
                                int escolha_juridica;
                                System.out.println("Escolha uma opção:");
                                System.out.println("1- Cadastrar Pessoa Jurídica");
                                System.out.println("2- Alterar Pessoa Jurídica");
                                System.out.println("3- Excluir Pessoa Jurídica");
                                System.out.println("4- Consultar Pessoa Jurídica");
                                System.out.println("5- Relatório de Pessoas Jurídicas");
                                System.out.println("6- Voltar ao menu anterior");
                                escolha_juridica = scanner.nextInt();
                                switch (escolha_juridica) {
                                    case 1:
                                        gerJuridicas.Cadastro();
                                        break;
                                    case 2:
                                        gerJuridicas.Alteracao();
                                        break;
                                    case 3:
                                        gerJuridicas.Exclusao();
                                        break;
                                    case 4:
                                        gerJuridicas.Consulta();
                                        break;
                                    case 5:
                                        gerJuridicas.Relatorio();
                                        break;
                                    case 6:
                                        System.out.println("Retornando ao menu principal");
                                        System.out.println("-----------------------------");
                                }
                                break;
                            case 3:
                                System.out.println("Retornando ao menu principal");
                                System.out.println("-----------------------------");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } while (escolha_cliente != 3);
                    break;

                case 2:
                    int escolha_veiculo;
                    do {
                        System.out.println("Escolha uma opção:");
                        System.out.println("1- Gerenciar Carros");
                        System.out.println("2- Gerenciar Caminhões");
                        System.out.println("3- Voltar ao menu principal");
                        escolha_veiculo = scanner.nextInt();
                        switch (escolha_veiculo) {
                            case 1:
                                int escolha_veiculo2;
                                System.out.println("Escolha uma opção:");
                                System.out.println("1- Cadastrar Carro");
                                System.out.println("2- Alterar Carro");
                                System.out.println("3- Excluir Carro");
                                System.out.println("4- Consultar Carro");
                                System.out.println("5- Relatório de Carros");
                                System.out.println("6- Voltar ao menu anterior");
                                escolha_veiculo2 = scanner.nextInt();
                                switch (escolha_veiculo2) {
                                    case 1:
                                        gerCarro.Cadastro();
                                        break;
                                    case 2:
                                        gerCarro.Alteracao();
                                        break;
                                    case 3:
                                        gerCarro.Exclusao();
                                        break;
                                    case 4:
                                        gerCarro.Consulta();
                                        break;
                                    case 5:
                                        gerCarro.Relatorio();
                                        break;
                                    case 6:
                                        System.out.println("Retornando ao menu principal");
                                        System.out.println("-----------------------------");
                                }
                                break;
                            case 2:
                                int escolha_caminhao;
                                System.out.println("Escolha uma opção:");
                                System.out.println("1- Cadastrar Caminhão");
                                System.out.println("2- Alterar Caminhão");
                                System.out.println("3- Excluir Caminhão");
                                System.out.println("4- Consultar Caminhão");
                                System.out.println("5- Relatório de Caminhões");
                                System.out.println("6- Voltar ao menu anterior");
                                escolha_caminhao = scanner.nextInt();
                                switch (escolha_caminhao) {
                                    case 1:
                                        gerCaminhoes.Cadastro();
                                        break;
                                    case 2:
                                        gerCaminhoes.Alteracao();
                                        break;
                                    case 3:
                                        gerCaminhoes.Exclusao();
                                        break;
                                    case 4:
                                        gerCaminhoes.Consulta();
                                        break;
                                    case 5:
                                        gerCaminhoes.relatorio();
                                        break;
                                    case 6:
                                        System.out.println("Retornando ao menu principal");
                                        System.out.println("-----------------------------");
                                }
                                break;
                            case 3:
                                System.out.println("Retornando ao menu principal");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } while (escolha_veiculo != 3);
                    break;

                case 3:
                    int escolha_locacao;
                    do {
                        System.out.println("--=[Gerenciar Locação]=--");
                        System.out.println("1- Agendar");
                        System.out.println("2- Alterar");
                        System.out.println("3- Cancelar");
                        System.out.println("4- Relatório de Veículos Agendados");
                        System.out.println("5- Relatório de Veículos Locados em Dia");
                        System.out.println("6- Relatório de Veículos Locados em Atraso");
                        System.out.println("7- Retornar ao menu anterior");
                        escolha_locacao = scanner.nextInt();
                        switch (escolha_locacao) {
                            case 1:
                            	gerenciaAgenda.Agendar(fisicas, juridicas, caminhoes, carros);
                                break;
                            case 2:
                            	gerenciaAgenda.Alterar();
                                break;
                            case 3:
                            	gerenciaAgenda.Cancelar();
                                break;
                            case 4:
                            	gerenciaAgenda.RelatorioVeiculosAgendados();
                                break;
                            case 5:
                            	gerenciaAgenda.RelatorioVeiculosAlocadosEmDia();
                                break;
                            case 6:
                            	gerenciaAgenda.RelatorioVeiculosAlocadosEmAtraso();
                                break;
                            case 7:
                                System.out.println("Retornando ao menu principal");
                                System.out.println("-----------------------------");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } while (escolha_locacao != 7);
                    break;

                case 4:
                	int escolha_caixa;
                    do {
                        System.out.println("[Gerenciar Caixa]");
                        System.out.println("1- Pagamento Locação");
                        System.out.println("2- Total Arrecadado");
                        System.out.println("3- Total Arrecadado por período");
                        System.out.println("4- Total a Receber");
                        System.out.println("5- Retornar ao menu anterior");
                        escolha_caixa = scanner.nextInt();
                        
                        switch (escolha_caixa) {
                            case 1:
                            	gerenciaCaixa.pagamentoLocacao();
                                break;
                            case 2:
                            	gerenciaCaixa.totalArrecadado();
                                break;
                            case 3:
                            	gerenciaCaixa.totalArrecadadoPorPeríodo();
                                break;
                            case 4:
                            	gerenciaCaixa.totalAReceber();
                                break;
                            case 5:
                                System.out.println("Retornando ao menu principal");
                                System.out.println("-----------------------------");
                                break;
                            default:
                                System.out.println("Opção inválida. Tente novamente.");
                        }
                    } while (escolha_caixa != 5);
                    break;
                case 5:
                    System.out.println("Saindo da aplicação.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 5);
        scanner.close();
        scanner.close();
    }
}
