package principal;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class GerenciaAgenda extends Locacao {
    private Scanner lerN;
    private Scanner lerS;

    private ArrayList<Locacao> locacoes;

    public GerenciaAgenda(ArrayList<Fisica> fisicas, ArrayList<Juridica> juridicas, ArrayList<Caminhao> caminhoes, ArrayList<Carro> carros, ArrayList<Locacao> locacoes) {
        this.locacoes = locacoes;
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

            if (fisicas.isEmpty() && juridicas.isEmpty()) {
                System.out.println("Não existem CLIENTES cadastrados. Por favor, cadastre-os para continuar.");
            } else if (carros.isEmpty() && caminhoes.isEmpty()) {
                System.out.println("Não há VEÍCULOS cadastrados. Por favor, cadastre-os para continuar.");
            } else {
                int escolhaLocacao;
                System.out.println("SELECIONE O TIPO DO CLIENTE");
                System.out.println("1- CLIENTE: PESSOA FÍSICA");
                System.out.println("2- CLIENTE: PESSOA JURÍDICA");
                System.out.print("Escolha: ");
                escolhaLocacao = lerN.nextInt();
                switch (escolhaLocacao) {
                    case 1:
                        if (fisicas.isEmpty()) {
                            System.out.println("Não há clientes do tipo PESSOA FÍSICA cadastrados.");
                        } else {
                            System.out.print("Digite a posição da PESSOA FÍSICA: ");
                            pos = lerN.nextInt();
                            if (pos >= 0 && pos < fisicas.size()) {
                                System.out.println("Dados do cliente encontrado: ");
                                Fisica fisica = fisicas.get(pos);
                                System.out.println("CPF: " + fisica.getCpf());
                                System.out.println("Nome: " + fisica.getNome());
                                System.out.println("Endereço: " + fisica.getEndereco());
                                System.out.println("Telefone: " + fisica.getTelefone());
                                System.out.println("Data de Nascimento: " + fisica.getDataNascimento());
                                System.out.println("-------------------------");
                                System.out.print("Deseja realmente alocar para este cliente? (1- sim/2- não): ");
                                int conf = lerN.nextInt();
                                if (conf == 1) {
                                    clienteEncontrado = true;
                                    clienteLocacao = fisica;
                                    System.out.println("Cliente Selecionado com SUCESSO.");
                                } else {
                                    System.out.println("Seleção de Cliente cancelada pelo usuário.");
                                    clienteEncontrado = false;
                                }
                            } else {
                                System.out.println("POSIÇÃO INVÁLIDA. Verifique a posição e tente novamente.");
                            }
                        }
                        break;
                    case 2:
                        if (juridicas.isEmpty()) {
                            System.out.println("Não há clientes do tipo PESSOA JURÍDICA cadastrados.");
                        } else {
                            System.out.print("Digite a posição da PESSOA JURÍDICA: ");
                            pos = lerN.nextInt();
                            if (pos >= 0 && pos < juridicas.size()) {
                                System.out.print("Deseja realmente alocar para este cliente? (1- sim/2- não): ");
                                int conf = lerN.nextInt();
                                if (conf == 1) {
                                    clienteEncontrado = true;
                                    clienteLocacao = juridicas.get(pos);
                                    System.out.println("Cliente Selecionado com SUCESSO.");
                                } else {
                                    System.out.println("Seleção de Cliente cancelada pelo usuário.");
                                    clienteEncontrado = false;
                                }
                            } else {
                                System.out.println("POSIÇÃO INVÁLIDA. Verifique a posição e tente novamente.");
                            }
                        }
                        break;
                }

                if (clienteEncontrado) {
                    int escolhaLocacaoVeiculo;
                    int posicao_veiculoLocacao;
                    ArrayList<Veiculo> veiculo = new ArrayList<>();
                    int add = 1;

                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                    System.out.print("Digite a data de INÍCIO da Locação no formato (dd-MM-yyyy): ");
                    String dataInicioStr = lerS.next();
                    LocalDate dataInicio = LocalDate.parse(dataInicioStr, dateFormatter);
                    locacao.setDataInicio(dataInicio);

                    System.out.print("Digite a data Prevista da Devolução no formato (dd-MM-yyyy): ");
                    String dataPrevistaStr = lerS.next();
                    LocalDate dataPrevista = LocalDate.parse(dataPrevistaStr, dateFormatter);
                    locacao.setDataPrevistaDevolucao(dataPrevista);

                    do {
                        if (add == 1) {
                            System.out.println("SELECIONE UM TIPO DE VEÍCULO ABAIXO: ");
                            System.out.println("1- CARRO");
                            System.out.println("2- CAMINHÃO");

                            escolhaLocacaoVeiculo = lerN.nextInt();
                            switch (escolhaLocacaoVeiculo) {
                                case 1:
                                    if (carros.isEmpty()) {
                                        System.out.println("Não existem CARROS cadastrados. Cadastre-os e tente novamente.");
                                    } else {
                                        System.out.print("Qual a posição do CARRO? ");
                                        posicao_veiculoLocacao = lerN.nextInt();

                                        if (posicao_veiculoLocacao >= 0 && posicao_veiculoLocacao < carros.size()) {
                                            Carro carro = carros.get(posicao_veiculoLocacao);
                                            boolean veiculoEmLocacao = false;
                                            LocalDate dataInicioLocacao = null;
                                            LocalDate dataPrevistaDevolucaoLocacao = null;
                                            for (Locacao locacaoItem : locacoes) {
                                                if (locacaoItem.getStatus() == 1) {
                                                    for (Veiculo veiculoLocado : locacaoItem.getVeiculos()) {
                                                        if (veiculoLocado.equals(carro)) {
                                                            if (dataPrevista.isBefore(locacaoItem.getDataInicio()) ||
                                                                dataInicio.isAfter(locacaoItem.getDataPrevistaDevolucao())) {
                                                                veiculoEmLocacao = false;
                                                            } else {
                                                                veiculoEmLocacao = true;
                                                                dataInicioLocacao = locacaoItem.getDataInicio();
                                                                dataPrevistaDevolucaoLocacao = locacaoItem.getDataPrevistaDevolucao();
                                                                System.out.println("Este carro já está alugado no período de " +
                                                                    dataInicioLocacao.format(dateFormatter) + " a " +
                                                                    dataPrevistaDevolucaoLocacao.format(dateFormatter) +
                                                                    ". Escolha outro veículo.");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (!veiculoEmLocacao) {
                                                System.out.println("Dados do Carro: ");
                                                System.out.println("- Marca: " + carro.getMarca());
                                                System.out.println("- Modelo: " + carro.getModelo());
                                                System.out.println("- Ano de Fabricação: " + carro.getAnoFabricacao());
                                                System.out.println("- Ano do Modelo: " + carro.getAnoModelo());
                                                System.out.println("- Placa: " + carro.getPlaca());
                                                System.out.println("- Capacidade de Passageiros: " + carro.getCapacidadePassageiros());
                                                System.out.println("- Quantidade de Portas: " + carro.getQuantidadePortas());
                                                System.out.println("-----------------------");
                                                System.out.print("Deseja realmente Alocar o carro? (1- sim/2- não): ");
                                                int conf = lerN.nextInt();
                                                if (conf == 1) {
                                                    veiculoAprovado = true;
                                                    veiculo.add(carro);
                                                    System.out.println("Veículo Alocado com SUCESSO.");
                                                } else {
                                                    System.out.println("Alocação cancelada pelo usuário.");
                                                }
                                            }
                                        } else {
                                            System.out.println("Posição Inválida. Verifique e tente novamente.");
                                        }
                                    }
                                    break;
                                case 2:
                                    if (caminhoes.isEmpty()) {
                                        System.out.println("Não existem CAMINHÕES cadastrados. Cadastre-os e tente novamente.");
                                    } else {
                                        System.out.print("Qual a posição do CAMINHÃO? ");
                                        posicao_veiculoLocacao = lerN.nextInt();
                                        if (posicao_veiculoLocacao >= 0 && posicao_veiculoLocacao < caminhoes.size()) {
                                            Caminhao caminhao = caminhoes.get(posicao_veiculoLocacao);
                                            boolean veiculoEmLocacao = false;
                                            LocalDate dataInicioLocacao = null;
                                            LocalDate dataPrevistaDevolucaoLocacao = null;
                                            for (Locacao locacaoItem : locacoes) {
                                                if (locacaoItem.getStatus() == 1) {
                                                    for (Veiculo veiculoLocado : locacaoItem.getVeiculos()) {
                                                        if (veiculoLocado.equals(caminhao)) {
                                                            if (dataPrevista.isBefore(locacaoItem.getDataInicio()) ||
                                                                dataInicio.isAfter(locacaoItem.getDataPrevistaDevolucao())) {
                                                                veiculoEmLocacao = false;
                                                            } else {
                                                                veiculoEmLocacao = true;
                                                                dataInicioLocacao = locacaoItem.getDataInicio();
                                                                dataPrevistaDevolucaoLocacao = locacaoItem.getDataPrevistaDevolucao();
                                                                System.out.println("Este caminhão já está alugado no período de " +
                                                                    dataInicioLocacao.format(dateFormatter) + " a " +
                                                                    dataPrevistaDevolucaoLocacao.format(dateFormatter) +
                                                                    ". Escolha outro veículo.");
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                            if (!veiculoEmLocacao) {
                                                System.out.println("Dados do Caminhão: ");
                                                System.out.println("- Marca: " + caminhao.getMarca());
                                                System.out.println("- Modelo: " + caminhao.getModelo());
                                                System.out.println("- Ano de Fabricação: " + caminhao.getAnoFabricacao());
                                                System.out.println("- Ano do Modelo: " + caminhao.getAnoModelo());
                                                System.out.println("- Placa: " + caminhao.getPlaca());
                                                System.out.println("- Capacidade de Cargas: " + caminhao.getCapacidadeCarga());
                                                System.out.println("- Quantidade de Eixos: " + caminhao.getNumeroDeEixos());
                                                System.out.println("-----------------------");
                                                System.out.print("Deseja realmente Alocar o caminhão? (1- sim/2- não): ");
                                                int conf = lerN.nextInt();
                                                if (conf == 1) {
                                                    veiculoAprovado = true;
                                                    veiculo.add(caminhao);
                                                    System.out.println("Veículo Alocado com SUCESSO.");
                                                } else {
                                                    System.out.println("Alocação cancelada pelo usuário.");
                                                }
                                            }
                                        } else {
                                            System.out.println("Posição Inválida. Verifique e tente novamente.");
                                        }
                                    }
                                    break;
                            }
                        }
                        System.out.print("Deseja adicionar mais um veículo? (1- SIM/2- NÃO): ");
                        add = lerN.nextInt();

                    } while (add != 2); // TODOS VEÍCULOS ADICIONADOS

                    if (veiculoAprovado) {
                        locacao.setCliente(clienteLocacao);
                        locacao.setVeiculos(veiculo);
                        locacao.setStatus(1);
                        locacoes.add(locacao);
                    } else {
                        System.out.println("ADICIONE UM VEÍCULO VÁLIDO.");
                    }
                } else {
                    System.out.println("ERRO - Tente novamente mais tarde.");
                }
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
            	int conf;
            	Locacao locacao = locacoes.get(pos);
            	System.out.println("Locação ID: " + locacoes.indexOf(locacao));
                System.out.println("Cliente: " + locacoes.get(pos).getCliente().getNome());
                System.out.println("Data de Início: " + locacoes.get(pos).getDataInicio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                System.out.println("Data Prevista de Devolução: " + locacoes.get(pos).getDataPrevistaDevolucao().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            	System.out.println("-----------------------");
            	System.out.println("Deseja realmente Alterar a data de Início e Devolução?(1- sim/2-não)");
            	conf = lerN.nextInt();
            	
            	if(conf == 1) {
            		Locacao locacaoParaCancelar = locacoes.get(pos);
                    if (locacaoParaCancelar.getStatus() == 1) { 
                        locacaoParaCancelar.setStatus(2); 
                        System.out.println("Locação cancelada com sucesso.");
            	}else {
                    System.out.println("Esta locação já está cancelada ou concluída.");
            	}
                }else {
                	System.out.println("Solicitação Cancelada pelo usuário");
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
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

            System.out.println("Digite qual posição de Locação deseja alterar: ");
            pos = lerN.nextInt();

            if ((pos >= 0) && (pos < locacoes.size())) {
                int conf;
                Locacao locacao = locacoes.get(pos);

                if (locacao.getStatus() == 1) { // Verifica se a locação não está cancelada
                    System.out.println("Locação ID: " + locacoes.indexOf(locacao));
                    System.out.println("Cliente: " + locacoes.get(pos).getCliente().getNome());
                    LocalDate dataInicio = locacoes.get(pos).getDataInicio();
                    LocalDate dataPrevistaDevolucao = locacoes.get(pos).getDataPrevistaDevolucao();
                    
                    if (dataInicio != null) {
                        System.out.println("Data de Início: " + dataInicio.format(dateFormatter));
                    } else {
                        System.out.println("Data de Início: Data não definida.");
                    }

                    if (dataPrevistaDevolucao != null) {
                        System.out.println("Data Prevista de Devolução: " + dataPrevistaDevolucao.format(dateFormatter));
                    } else {
                        System.out.println("Data Prevista de Devolução: Data não definida.");
                    }
                    System.out.println("-----------------------");
                    System.out.println("Deseja realmente Alterar a data de Início e Devolução? (1 - sim / 2 - não)");
                    conf = lerN.nextInt();

                    if (conf == 2) {
                        System.out.println("Alteração cancelada pelo usuário.");
                    } else {
                        do {
                            System.out.println("Digite os dados a seguir: ");
                            LocalDate novaDataInicio = null;
                            LocalDate novaDataPrevista = null;

                            while (novaDataInicio == null) {
                                System.out.print("- Nova data de Início da Locação (dd-MM-yyyy): ");
                                String novaDataInicioStr = lerS.next();
                                try {
                                    novaDataInicio = LocalDate.parse(novaDataInicioStr, dateFormatter);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Data no formato inválido. Use (dd-MM-yyyy).");
                                }
                            }

                            while (novaDataPrevista == null) {
                                System.out.print("- Nova data de Devolução (dd-MM-yyyy): ");
                                String novaDataPrevistaStr = lerS.next();
                                try {
                                    novaDataPrevista = LocalDate.parse(novaDataPrevistaStr, dateFormatter);
                                } catch (DateTimeParseException e) {
                                    System.out.println("Data no formato inválido. Use (dd-MM-yyyy).");
                                }
                            }

                            locacao.setDataInicio(novaDataInicio);
                            locacao.setDataPrevistaDevolucao(novaDataPrevista);
                            System.out.println("Alteração Efetuada com SUCESSO.");
                            System.out.println("----------------------------------");
                            System.out.println("Deseja fazer mais alguma alteração? (1 - Sim / 2 - Não)");
                            escolha = lerN.nextInt();
                        } while (escolha == 1);
                    }
                } else {
                    System.out.println("Essa locação está cancelada e não pode ser alterada.");
                }
            } else {
                System.out.println("Posição Inválida.");
            }
        }
    }

    
    public void RelatorioVeiculosAgendados() {
        System.out.println("--=[RELATÓRIO DE VEÍCULOS AGENDADOS]=--");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações registradas.");
        } else {
            boolean locacoesAtivasEncontradas = false;

            for (Locacao locacao : locacoes) {
                if (locacao.getStatus() == 1) { // Verifica apenas locações ativas
                    locacoesAtivasEncontradas = true;
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

            if (!locacoesAtivasEncontradas) {
                System.out.println("Não há locações ativas registradas.");
            }
        }
    }
    
    public void RelatorioVeiculosAlocadosEmDia() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.println("--=[RELATÓRIO DE VEÍCULOS LOCADOS EM DIA]=--");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações registradas.");
        } else {
            System.out.print("Digite a data que deseja verificar (dd-MM-yyyy): ");
            String dataInformadaStr = lerS.next();

            try {
                LocalDate dataInformada = LocalDate.parse(dataInformadaStr, dateFormatter);

                boolean locacoesAtivasEncontradas = false;

                for (Locacao locacao : locacoes) {
                    if (locacao.getStatus() == 1) { // Verifica apenas locações ativas
                        if (dataInformada.isEqual(locacao.getDataInicio()) || (dataInformada.isAfter(locacao.getDataInicio()) && dataInformada.isBefore(locacao.getDataPrevistaDevolucao()))) {
                            locacoesAtivasEncontradas = true;
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

                if (!locacoesAtivasEncontradas) {
                    System.out.println("Não há locações ativas registradas para a data informada.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Data no formato inválido. Use (dd-MM-yyyy).");
            }
        }
    }

    
    public void RelatorioVeiculosAlocadosEmAtraso() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        System.out.println("--=[RELATÓRIO DE VEÍCULOS LOCADOS EM ATRASO]=--");

        if (locacoes.isEmpty()) {
            System.out.println("Não existem locações registradas.");
        } else {
            try {
                System.out.print("Qual a data que deseja verificar (dd-MM-yyyy)? ");
                String dataInformadaStr = lerS.next();
                LocalDate dataAtual = LocalDate.parse(dataInformadaStr, dateFormatter);

                boolean locacoesEmAtrasoEncontradas = false;

                for (Locacao locacao : locacoes) {
                    if (locacao.getStatus() == 1) { // Verifica apenas locações ativas
                        if (dataAtual.isAfter(locacao.getDataPrevistaDevolucao())) {
                            locacoesEmAtrasoEncontradas = true;
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

                if (!locacoesEmAtrasoEncontradas) {
                    System.out.println("Não há locações em atraso registradas.");
                    System.out.println("-------------------------");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Data no formato inválido. Use (dd-MM-yyyy).");
            }
        }
    }
}