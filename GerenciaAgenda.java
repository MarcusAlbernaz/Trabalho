package principal;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GerenciaAgenda extends Locacao {
    ArrayList<Locacao> locacoes = new ArrayList<>();
    private Scanner lerN, lerS;
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Carro> carros = new ArrayList<>();
    ArrayList<Caminhao> caminhoes = new ArrayList<>();
    ArrayList<Veiculo> veiculos = new ArrayList<>();

    public GerenciaAgenda(Agenda agenda) {
        lerN = new Scanner(System.in);
        lerS = new Scanner(System.in);
    }

    public void Agendar(ArrayList<Fisica> fisicas, ArrayList<Juridica> juridicas, ArrayList<Caminhao> caminhoes,
            ArrayList<Carro> carros) {
        int pos;
        boolean clienteEncontrado = false;
        boolean veiculoAprovado = false;
        Cliente clienteLocacao = null;
        Locacao locacao = new Locacao();

        System.out.println("--=[AGENDAR LOCAÇÃO]=--");

        if ((fisicas.isEmpty()) && juridicas.isEmpty())
            System.out.println("Não existem CLIENTES cadastrados, favor cadastre-os para continuar");
        else if (carros.isEmpty() && caminhoes.isEmpty()) {
            System.out.println("Não há VEÍCULOS cadastrados, favor cadastre-os para continuar");
        } else {
            int escolhaLocacao;
            System.out.println("SELECIONE O TIPO DO CLIENTE");
            System.out.println("1- CLIENTE: PESSOA FÍSICA");
            System.out.println("2- CLIENTE: PESSOA JURÍDICA");
            System.out.println("Escolha: ");
            escolhaLocacao = lerN.nextInt();
            switch (escolhaLocacao) {
                case 1:
                    if (fisicas.isEmpty())
                        System.out.println("Não há clientes do tipo PESSOA FÍSICA cadastrada.");
                    else {
                        System.out.println("Digite a posição da PESSOA FÍSICA: ");
                        pos = lerN.nextInt();
                        if (pos >= 0 && pos < fisicas.size()) {
                            clienteEncontrado = true;
                            clienteLocacao = fisicas.get(pos);
                            System.out.println("Dados do cliente encontrado: ");
                            System.out.println("CPF: " + fisicas.get(pos).getCpf());
                            System.out.println("Nome: " + fisicas.get(pos).getNome());
                            System.out.println("Endereço:" + fisicas.get(pos).getEndereco());
                            System.out.println("Telefone:" + fisicas.get(pos).getTelefone());
                            System.out.println("Data de Nascimento:" + fisicas.get(pos).getDataNascimento());
                        } else
                            System.out.println("POSIÇÃO INVÁLIDA, verifique a posição e tente novamente.");
                    }
                    break;
                case 2:
                    if (juridicas.isEmpty())
                        System.out.println("Não há clientes do tipo PESSOA JURÍDICA cadastrada.");
                    else {
                        System.out.println("Digite a posição da PESSOA JURÍDICA: ");
                        pos = lerN.nextInt();
                        if (pos >= 0 && pos < juridicas.size()) {
                            clienteEncontrado = true;
                            clienteLocacao = juridicas.get(pos);
                            System.out.println("Dados do cliente encontrado: ");
                            System.out.println("CNPJ: " + juridicas.get(pos).getCnpj());
                            System.out.println("Nome: " + juridicas.get(pos).getNome());
                            System.out.println("Endereço:" + juridicas.get(pos).getEndereco());
                            System.out.println("Telefone:" + juridicas.get(pos).getTelefone());
                        } else
                            System.out.println("POSIÇÃO INVÁLIDA, verifique a posição e tente novamente.");
                    }
                    break;
            }

            if (clienteEncontrado) {
                int escolha_LocacaoVeiculo;
                int posicao_veiculoLocacao;
                ArrayList<Veiculo> veiculo = new ArrayList<>();
                int add = 1;

                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                System.out.print("Digite a data de INÍCIO da Locação no formato:(dd-MM-yyyy): ");
                String dataInicioStr = lerS.next();
                LocalDate dataInicio = LocalDate.parse(dataInicioStr, dateFormatter);
                locacao.setDataInicio(dataInicio);

                System.out.print("Digite a data Prevista da Devolução no formato:(dd-MM-yyyy): ");
                String dataPrevistaStr = lerS.next();
                LocalDate dataPrevista = LocalDate.parse(dataPrevistaStr, dateFormatter);
                locacao.setDataPrevistaDevolucao(dataPrevista);

                do {
                    if (add == 1) {
                        System.out.println("SELECIONE UM TIPO DE VEICULO ABAIXO: ");
                        System.out.println("1- CARRO");
                        System.out.println("2- CAMINHÃO");

                        escolha_LocacaoVeiculo = lerN.nextInt();
                        switch (escolha_LocacaoVeiculo) {
                            case 1:
                                if (carros.isEmpty())
                                    System.out.println("Não existem CARROS cadastrados, cadastre-os e tente novamente.");
                                else {
                                    System.out.println("Qual a posição do CARRO? ");
                                    posicao_veiculoLocacao = lerN.nextInt();

                                    if (posicao_veiculoLocacao >= 0 && posicao_veiculoLocacao < carros.size()) {
                                        boolean veiculoEmLocacao = false;
                                        LocalDate dataInicioLocacao = null;
                                        LocalDate dataPrevistaDevolucaoLocacao = null;
                                        for (Locacao locacaoItem : locacoes) {
                                            if (locacaoItem.getStatus() == 1) {
                                                for (Veiculo veiculoLocado : locacaoItem.getVeiculos()) {
                                                    if (veiculoLocado.equals(carros.get(posicao_veiculoLocacao))) {
                                                        if (dataPrevista.isBefore(locacaoItem.getDataInicio()) ||
                                                            dataInicio.isAfter(locacaoItem.getDataPrevistaDevolucao())) {
                                                            veiculoEmLocacao = false;
                                                        } else {
                                                            veiculoEmLocacao = true;
                                                            dataInicioLocacao = locacaoItem.getDataInicio();
                                                            dataPrevistaDevolucaoLocacao = locacaoItem.getDataPrevistaDevolucao();
                                                            System.out.println(
                                                                    "Este carro já está alugado no período de " + 
                                                                    dataInicioLocacao.format(dateFormatter) + " a " + 
                                                                    dataPrevistaDevolucaoLocacao.format(dateFormatter) + 
                                                                    ". Escolha outro veículo.");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!veiculoEmLocacao) {
                                            veiculoAprovado = true;
                                            veiculo.add(carros.get(posicao_veiculoLocacao));
                                            System.out.println("Veículo Alocado com SUCESSO.");
                                        }
                                    } else
                                        System.out.println("Posição Inválida. Verifique e tente novamente.");
                                }
                                break;
                            case 2:
                                if (caminhoes.isEmpty())
                                    System.out.println("Não existem CAMINHÕES cadastrados, cadastre-os e tente novamente.");
                                else {
                                    System.out.println("Qual a posição do CAMINHÃO? ");
                                    posicao_veiculoLocacao = lerN.nextInt();
                                    if (posicao_veiculoLocacao >= 0 && posicao_veiculoLocacao < caminhoes.size()) {
                                        boolean veiculoEmLocacao = false;
                                        LocalDate dataInicioLocacao = null;
                                        LocalDate dataPrevistaDevolucaoLocacao = null;
                                        for (Locacao locacaoItem : locacoes) {
                                            if (locacaoItem.getStatus() == 1) {
                                                for (Veiculo veiculoLocado : locacaoItem.getVeiculos()) {
                                                    if (veiculoLocado.equals(caminhoes.get(posicao_veiculoLocacao))) {
                                                        if (dataPrevista.isBefore(locacaoItem.getDataInicio()) ||
                                                            dataInicio.isAfter(locacaoItem.getDataPrevistaDevolucao())) {
                                                            veiculoEmLocacao = false;
                                                        } else {
                                                            veiculoEmLocacao = true;
                                                            dataInicioLocacao = locacaoItem.getDataInicio();
                                                            dataPrevistaDevolucaoLocacao = locacaoItem.getDataPrevistaDevolucao();
                                                            System.out.println(
                                                                    "Este caminhão já está alugado no período de " + 
                                                                    dataInicioLocacao.format(dateFormatter) + " a " + 
                                                                    dataPrevistaDevolucaoLocacao.format(dateFormatter) + 
                                                                    ". Escolha outro veículo.");
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        if (!veiculoEmLocacao) {
                                            veiculoAprovado = true;
                                            veiculo.add(caminhoes.get(posicao_veiculoLocacao));
                                            System.out.println("Veículo Alocado com SUCESSO.");
                                        }
                                    } else
                                        System.out.println("Posição Inválida. Verifique e tente novamente.");
                                }
                                break;
                        }
                    }
                    System.out.println("Deseja adicionar mais um veículo? (1-SIM 2-NÃO)");
                    add = lerN.nextInt();
                } while (add != 2); // TODOS VEÍCULOS ADICIONADOS

                if (veiculoAprovado) {
                    locacao.setCliente(clienteLocacao);
                    locacao.setVeiculos(veiculo);
                    locacao.setStatus(1);
                    locacoes.add(locacao);
                } else
                    System.out.println("ADICIONE UM VEÍCULO VÁLIDO.");
            } else
                System.out.println("ADICIONE UM CLIENTE VÁLIDO.");
        }
    }

    public void Cancelar() {
        System.out.println("--=[CANCELAR LOCAÇÃO]=--");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações para cancelar.");
        } else {
            System.out.println("Selecione o número da locação que deseja cancelar:");
            int pos = lerN.nextInt();

            if (pos >= 0 && pos < locacoes.size()) {
                Locacao locacaoParaCancelar = locacoes.get(pos);
                if (locacaoParaCancelar.getStatus() == 1) { 
                    locacaoParaCancelar.setStatus(2); 
                    System.out.println("Locação cancelada com sucesso.");
                } else {
                    System.out.println("Esta locação já está cancelada ou concluída.");
                }
            } else {
                System.out.println("Escolha inválida. Por favor, insira o número de uma locação existente.");
            }
        }
    }

    public void Alterar() {
        System.out.println("--=[ALTERAR LOCAÇÃO]=--");
        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações para Alterar.");
        } else {
            int escolha = 1, pos;
            System.out.println("Digite qual posição de Locação deseja alterar: ");
            pos = lerN.nextInt();

            if ((pos >= 0) && (pos < locacoes.size())) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                Locacao locacao = locacoes.get(pos);
                do {
                    System.out.println("Digite os dados a seguir: ");
                    System.out.print("- Nova data de Início da Locação (dd-MM-yyyy): ");
                    String novaDataInicioStr = lerS.next();
                    LocalDate novaDataInicio = LocalDate.parse(novaDataInicioStr, dateFormatter);
                    locacao.setDataInicio(novaDataInicio);

                    System.out.print("- Nova data de Devolução (dd-MM-yyyy): ");
                    String novaDataPrevistaStr = lerS.next();
                    LocalDate novaDataPrevista = LocalDate.parse(novaDataPrevistaStr, dateFormatter);
                    locacao.setDataPrevistaDevolucao(novaDataPrevista);

                    System.out.println("Deseja fazer mais alterações? (1 - Sim / 2 - Não)");
                    escolha = lerN.nextInt();
                } while (escolha == 1);
            }
        }
    }
    public void RelatorioVeiculosAgendados() {
        System.out.println("--=[RELATÓRIO DE VEÍCULOS AGENDADOS]=--");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações registradas.");
        } else {
            for (Locacao locacao : locacoes) {
                if (locacao.getStatus() == 1) { // Verifica apenas locações ativas
                    System.out.println("Locação ID: " + locacoes.indexOf(locacao));
                    System.out.println("Cliente: " + locacao.getCliente().getNome());
                    System.out.println("Data de Início: " + locacao.getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    System.out.println("Data Prevista de Devolução: " + locacao.getDataPrevistaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    System.out.println("Veículos Agendados:");

                    for (Veiculo veiculo : locacao.getVeiculos()) {
                        if (veiculo instanceof Carro) {
                            Carro carro = (Carro) veiculo;
                            System.out.println("   - Carro: " + carro.getMarca() + " " + carro.getModelo() + " Placa: " + carro.getPlaca());
                        } else if (veiculo instanceof Caminhao) {
                            Caminhao caminhao = (Caminhao) veiculo;
                            System.out.println("   - Caminhão: " + caminhao.getMarca() + " " + caminhao.getModelo() + " Placa: " + caminhao.getPlaca());
                        }
                    }

                    System.out.println();
                }
            }
        }
    }
    
    public void RelatorioVeiculosAlocadosEmDia() {
        System.out.println("--=[RELATÓRIO DE VEÍCULOS LOCADOS EM DIA]=--");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações registradas.");
        } else {
            for (Locacao locacao : locacoes) {
                if (locacao.getStatus() == 1) { // Verifica apenas locações ativas
                    LocalDate dataAtual = LocalDate.now();
                    if (dataAtual.isAfter(locacao.getDataInicio()) && dataAtual.isBefore(locacao.getDataPrevistaDevolucao())) {
                        System.out.println("Locação ID: " + locacoes.indexOf(locacao));
                        System.out.println("Cliente: " + locacao.getCliente().getNome());
                        System.out.println("Data de Início: " + locacao.getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        System.out.println("Data Prevista de Devolução: " + locacao.getDataPrevistaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        System.out.println("Veículos Agendados:");

                        for (Veiculo veiculo : locacao.getVeiculos()) {
                            if (veiculo instanceof Carro) {
                                Carro carro = (Carro) veiculo;
                                System.out.println("   - Carro: " + carro.getMarca() + " " + carro.getModelo() + " Placa: " + carro.getPlaca());
                            } else if (veiculo instanceof Caminhao) {
                                Caminhao caminhao = (Caminhao) veiculo;
                                System.out.println("   - Caminhão: " + caminhao.getMarca() + " " + caminhao.getModelo() + " Placa: " + caminhao.getPlaca());
                            }
                        }

                        System.out.println();
                    }
                }
            }
        }
    }
    
    public void RelatorioVeiculosAlocadosEmAtraso() {
        System.out.println("--=[RELATÓRIO DE VEÍCULOS LOCADOS EM ATRASO]=--");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações registradas.");
        } else {
            LocalDate dataAtual = LocalDate.now();

            for (Locacao locacao : locacoes) {
                if (locacao.getStatus() == 1) { // Verifica apenas locações ativas
                    if (dataAtual.isAfter(locacao.getDataPrevistaDevolucao())) {
                        System.out.println("Locação ID: " + locacoes.indexOf(locacao));
                        System.out.println("Cliente: " + locacao.getCliente().getNome());
                        System.out.println("Data de Início: " + locacao.getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        System.out.println("Data Prevista de Devolução: " + locacao.getDataPrevistaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                        System.out.println("Veículos Agendados:");

                        for (Veiculo veiculo : locacao.getVeiculos()) {
                            if (veiculo instanceof Carro) {
                                Carro carro = (Carro) veiculo;
                                System.out.println("   - Carro: " + carro.getMarca() + " " + carro.getModelo() + " Placa: " + carro.getPlaca());
                            } else if (veiculo instanceof Caminhao) {
                                Caminhao caminhao = (Caminhao) veiculo;
                                System.out.println("   - Caminhão: " + caminhao.getMarca() + " " + caminhao.getModelo() + " Placa: " + caminhao.getPlaca());
                            }
                        }

                        System.out.println();
                    }
                }
            }
        }
    }
    
}