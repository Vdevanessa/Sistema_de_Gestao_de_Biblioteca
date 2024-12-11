import java.util.*;

public class Sistema_Biblioteca {
    static Scanner scanner = new Scanner(System.in);
    static String tituloLivro;
    static String autorLivro;
    static int isbnLivro;
    static int quantidadeTotal = 0;
    static int quantidadeDisponivel = 0;
    static HashMap<String, Object[]> listaLivros = new HashMap<>();  //criar uma hashMap com chave string e valor um array de objetos
    // HashMap para guardar os livros, onde a chave é o título do livro e o valor é um array de objetos com as informações do livro
    static List<Object[]> registoEmprestimo = new ArrayList<>(); // lista para armazenar os registos de emprestimos


    public static void main(String[] args) {

        int opcao;

        do {    //Menu principal continua executando até escolher 0-sair
            opcao = mostrarMenu();  //mostra o menu e captura a opcao escolhida

            switch (opcao) {
                case 1:
                    adicionarLivro();
                    break;
                case 2:
                    procurarLivro();
                    break;
                case 3:
                    mostrarLivro();
                    break;
                case 4:
                    emprestarLivro();
                    break;
                case 5:
                    devolverLivro();
                    break;
                case 6:
                    verEstatisticas();
                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

    }

    public static int mostrarMenu() {
        System.out.println("\n========== SISTEMA DE BIBLIOTECA ==========\n");
        System.out.println("1. Adicionar Livro");
        System.out.println("2. Procurar Livros");
        System.out.println("3. Mostrar Todos os Livros");
        System.out.println("4. Emprestar Livro");
        System.out.println("5. Devolver Livro");
        System.out.println("6. Ver Estatísticas");
        System.out.println("0. Sair");

        System.out.print("\nDigite a opção escolhida: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        return opcao;  //retorna a opcao escolhida
    }

    public static void adicionarLivro() {
        System.out.println("\n_______________ADICIONAR LIVRO_______________");
        boolean continuar = false;   //controle para adicionar varios livros de uma vez

        do {
            System.out.println("\nDigite o título: ");
            tituloLivro = scanner.nextLine();

            System.out.println("Digite o autor: ");
            autorLivro = scanner.nextLine();

            System.out.println("Digite o ISBN: ");
            isbnLivro = scanner.nextInt();

            System.out.println("Digite a quantidade: ");
            quantidadeTotal = scanner.nextInt();

            quantidadeDisponivel = quantidadeTotal;

            scanner.nextLine();   // limpa o buffer após a leitura do inteiro

            Object[] detalhesLivro = {autorLivro, isbnLivro, quantidadeTotal, quantidadeDisponivel};   //armazena informações do livro
            listaLivros.put(tituloLivro, detalhesLivro);  //chave: titulo do livro e o valor: é o objeto detalhesLivro (autor, isbn, quantidade)

            System.out.println("\nLivro adicionado com sucesso!\n");
            System.out.println("Título: " + tituloLivro);
            System.out.println("Autor: " + autorLivro);
            System.out.println("ISBN: " + isbnLivro);
            System.out.println("Quantidade disponível: " + quantidadeTotal);

            System.out.println("\nDeseja inserir outro livro? (S/N) ");
            String resposta = scanner.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                continuar = true;
            } else {
                continuar = false;
            }
        } while (continuar);

    }

    public static void procurarLivro() {
        System.out.println("\n_________________PROCURAR LIVROS_________________\n");
        System.out.println("1. Procurar por título");
        System.out.println("2. Procurar por autor");
        System.out.println("3. Procurar por ISBN");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
        int opcaoProcurar = scanner.nextInt();

        scanner.nextLine(); // limpa o buffer após leitura do int

        switch (opcaoProcurar) {
            case 1:    // procurar por nome do livro
                System.out.println("\nDigite o nome do livro: ");
                String livroProcurado = scanner.nextLine();

                if (listaLivros.containsKey(livroProcurado)) {  //vê se o nome do livro procurado está na listaLivros
                    Object[] detalhesLivro = listaLivros.get(livroProcurado);
                    System.out.println("\nResultados da Busca:");
                    System.out.println("Nome do livro: " + livroProcurado);
                    System.out.println("Autor: " + detalhesLivro[0]);
                    System.out.println("ISBN: " + detalhesLivro[1]);
                    System.out.println("Quantidade Disponível: " + detalhesLivro[2]);
                } else {
                    System.out.println("Livro não encontrado na biblioteca.");
                }
                break;
            case 2:    //procurar por autor
                System.out.println("\nDigite o nome do autor: ");
                String autorProcurado = scanner.nextLine();

                for (String tituloLivro : listaLivros.keySet()) {  //Itera sobre todas as chaves (títulos dos livros) no HashMap listaLivros
                    Object[] detalhesLivro = listaLivros.get(tituloLivro);
                    if (detalhesLivro[0].equals(autorProcurado)) {
                        System.out.println("\nResultados da Busca:");
                        System.out.println("Nome do Livro: " + tituloLivro);
                        System.out.println("Autor: " + detalhesLivro[0]);
                        System.out.println("ISBN: " + detalhesLivro[1]);
                        System.out.println("Quantidade Disponível: " + detalhesLivro[2]);
                    }
                }
                break;
            case 3:   // procurar por isbn
                System.out.println("D\nigite o ISBN do livro: ");
                int isbnProcurado = scanner.nextInt();

                for (String tituloLivro : listaLivros.keySet()) {
                    Object[] detalhesLivro = listaLivros.get(tituloLivro);
                    if (detalhesLivro[1].equals(isbnProcurado)) {
                        System.out.println("\nResultados da Busca:");
                        System.out.println("Nome do Livro: " + tituloLivro);
                        System.out.println("Autor: " + detalhesLivro[0]);
                        System.out.println("ISBN: " + detalhesLivro[1]);
                        System.out.println("Quantidade Disponível: " + detalhesLivro[2]);
                    }
                }
                break;
            default:
                System.out.println("Opção inválida!");
        }

    }

    public static void mostrarLivro() {
        System.out.println("\n_____________LISTA COMPLETA DE LIVROS______________\n");
        if (listaLivros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado!");
            return;
        }

        for (String tituloLivro : listaLivros.keySet()) {
            Object[] detalhesLivro = listaLivros.get(tituloLivro);

            String autor = (String) detalhesLivro[0];
            int isbn = (int) detalhesLivro[1];
            int quantidadeTotal = (int) detalhesLivro[2];
            int quantidadeDisponivel = (int) detalhesLivro[3];
            int emprestimosAtivos = quantidadeTotal - quantidadeDisponivel;  // Empréstimos ativos


            System.out.println("Nome: " + tituloLivro);
            System.out.println("Autor: " + autor);
            System.out.println("ISBN: " + isbn);
            System.out.println("Quantidade total: " + quantidadeTotal);
            System.out.println("Disponível: " + quantidadeDisponivel);
            System.out.println("Empréstimos ativos: " + emprestimosAtivos);
            System.out.print("\n__________________________________________________\n");
        }

    }
        public static void emprestarLivro () {
            System.out.println("\n_______________EMPRESTAR LIVRO_______________\n");
            System.out.println("Digite o nome do livro: ");
            String livroEmprestar = scanner.nextLine();

            if (!listaLivros.containsKey(livroEmprestar) || listaLivros.get(tituloLivro) == null) {  // verifica se existe o livro na biblioteca
                System.out.println("Livro não encontrado na biblioteca!");
                return;  //se não for encontrado o livro, interrompe a execução da função.
            }

            System.out.println("Digite o nome do leitor: ");
            String nomeLeitor = scanner.nextLine();

            Object[] detalhesLivro = listaLivros.get(livroEmprestar);  //verifica se o livro esta disponível
            quantidadeDisponivel = (int) detalhesLivro[3];

            if (quantidadeTotal <= 0) {
                System.out.println("Não há exemplares disponíveis!");
                return;  // se não tiver disponibilidade interrompe a execução
            }

            Date dataEmprestimo = new Date(); //data atual
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(dataEmprestimo);
            calendario.add(Calendar.DAY_OF_YEAR, 7);  //acrescenta 7 dias
            Date dataDevolucao = calendario.getTime();

            quantidadeDisponivel -= 1; // atualiza a quantidade de exemplares disponíveis
            detalhesLivro[3] = quantidadeDisponivel;  // Atualiza o valor na lista de livros
            listaLivros.put(livroEmprestar, detalhesLivro);  // Atualiza o HashMap com as novas informações do livro

            registoEmprestimo.add(new Object[]{
                    livroEmprestar, nomeLeitor, dataEmprestimo, dataDevolucao
            });


            System.out.println("\nEmpréstimo realizado com sucesso!\n");

            System.out.println("\nResumo do Empréstimo:");
            System.out.println("\nTítulo do livro: " + livroEmprestar);
            System.out.println("Leitor: " + nomeLeitor);
            System.out.println("Data do Empréstimo: " + dataEmprestimo);
            System.out.println("Data da Devolução: " + dataDevolucao);
            System.out.println("Cópias restantes: " + detalhesLivro[3]);
        }

        public static void devolverLivro () {
            System.out.println("\n_________________DEVOLVER LIVRO________________\n");
            System.out.println("Digite o nome do livro: ");
            String livroDevolver = scanner.nextLine();

            boolean livroEncontrado = false;

            for (int i = 0; i < registoEmprestimo.size(); i++) {
                Object[] emprestimo = registoEmprestimo.get(i);
                String livroEmprestar = (String) emprestimo[0];
                String leitor = (String) emprestimo[1];


                if (livroEmprestar.equalsIgnoreCase(livroDevolver)) {
                    livroEncontrado = true;

                    // Recupera os detalhes do livro na lista de livros
                    if (listaLivros.containsKey(livroDevolver)) {
                        Object[] detalhesLivro = listaLivros.get(livroDevolver);

                        int quantidadeTotal = (int) detalhesLivro[2];
                        int quantidadeDisponivel = (int) detalhesLivro[3];

                        if (quantidadeDisponivel < quantidadeTotal) {
                            detalhesLivro[3] = quantidadeDisponivel + 1; // Incrementa as cópias disponíveis
                            listaLivros.put(livroDevolver, detalhesLivro); // Atualiza o HashMap
                        } else {
                            System.out.println("\nJá não há mais exemplares desse livro para emprestimo!");
                            return;
                        }

                        // Verifica se a devolução está atrasada
                        Date dataAtual = new Date();
                        Date dataDevolucao = (Date) emprestimo[3];
                        String estadoDevolucao = dataAtual.after(dataDevolucao) ? "Atrasado" : "Dentro do Prazo";

                        // Exibe os detalhes da devolução
                        System.out.println("\nDevolução realizada com sucesso!");
                        System.out.println("Livro: " + livroDevolver);
                        System.out.println("Leitor: " + leitor);
                        System.out.println("Data de devolução: " + dataAtual);
                        System.out.println("Estado: " + estadoDevolucao);
                        System.out.println("Cópias disponíveis: " + detalhesLivro[3] + "/" + quantidadeTotal);

                        // Remove o registro do empréstimo
                        registoEmprestimo.remove(i);
                        return;
                    }
                }
            }

            // Caso o livro não seja encontrado
            if (!livroEncontrado) {
                System.out.println("Erro: Livro não encontrado nos registros de empréstimos!");
            }
        }

        public static void verEstatisticas () {
            System.out.println("\n__________ESTATÍSTICAS DA BIBLIOTECA____________");

            int totalLivros = listaLivros.size();     // Contador do número total de livros cadastrados, é o equivalente ao número de chaves na HashMap
            int livrosEmprestados = 0; // Contador de livros emprestados
            int livrosDisponiveis = 0; // Contador de livros disponíveis
            int totalCopias = 0;

            for (Object[] detalhes : listaLivros.values()) {
                int quantidadeTotal = (int) detalhes[2];
                int quantidadeDisponivel = (int) detalhes[3];

                totalCopias += quantidadeTotal;           // Soma todos os livros cadastrados
                livrosEmprestados += quantidadeTotal - quantidadeDisponivel; // Calcula os emprestados
                livrosDisponiveis += quantidadeDisponivel; // Soma os disponíveis
            }

            // Exibição das estatísticas
            System.out.println("\nTotal de livros cadastrados: " + totalLivros);
            System.out.println("Total de cópias: " + totalCopias);
            System.out.println("Livros emprestados: " + livrosEmprestados);
            System.out.println("Livros disponíveis: " + livrosDisponiveis);
        }
    }