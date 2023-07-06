package model;

import java.util.*;

public class RedeSocial {

    private Map<String, Usuario> usuarios;
    private Scanner scanner;

    public RedeSocial() {
        this.usuarios = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Acessar com Login");
        System.out.println("3. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public void cadastrarUsuario() {
        System.out.println("\n--- Cadastro de Usuário ---");
        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        if (usuarios.containsKey(login)) {
            System.out.println("Usuário já cadastrado.");
            return;
        }
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        System.out.print("Digite a data de nascimento: ");
        String dataNascimento = scanner.nextLine();

        Usuario usuario = new Usuario(login, nome, email, dataNascimento);
        usuarios.put(login, usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    public Usuario acessarComLogin() {
        System.out.println("\n--- Acesso com Login ---");
        System.out.print("Digite o login: ");
        String login = scanner.nextLine();
        if (usuarios.containsKey(login)) {
            return usuarios.get(login);
        } else {
            System.out.println("Usuário não encontrado.");
            return null;
        }
    }

    public void exibirDadosUsuario(Usuario usuario) {
        System.out.println("\n--- Dados do Usuário ---");
        System.out.println(usuario.toString());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
    }

    public void alterarDadosUsuario(Usuario usuario) {
        System.out.println("\n--- Alterar Dados do Usuário ---");
        System.out.print("Digite o novo nome: ");
        String nome = scanner.nextLine();
        usuario.setNome(nome);
        System.out.println("Dados do usuário alterados com sucesso!");
    }

    public void seguirUsuario(Usuario seguidor) {
        System.out.println("\n--- Seguir Usuário ---");
        System.out.print("Digite o login do usuário a ser seguido: ");
        String login = scanner.nextLine();
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            seguidor.seguirUsuario(usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void cancelarSeguirUsuario(Usuario seguidor) {
        System.out.println("\n--- Cancelar Seguir Usuário ---");
        System.out.print("Digite o login do usuário a ser deixado de seguir: ");
        String login = scanner.nextLine();
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            seguidor.cancelarSeguirUsuario(usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void registrarMensagem(Usuario usuario) {
        System.out.println("\n--- Registrar Mensagem ---");
        System.out.print("Digite a mensagem: ");
        String mensagem = scanner.nextLine();
        usuario.registrarMensagem(mensagem);
    }

    public void comentarMensagem(Usuario usuario) {
        System.out.println("\n--- Comentar Mensagem ---");
        System.out.println("Mensagens do usuário " + usuario.getLogin() + ":");
        List<String> mensagens = usuario.getMensagens();
        for (int i = 0; i < mensagens.size(); i++) {
            System.out.println(i + ". " + mensagens.get(i));
        }
        System.out.print("Digite o número da mensagem: ");
        int numeroMensagem = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o comentário: ");
        String comentario = scanner.nextLine();
        usuario.comentarMensagem(numeroMensagem, comentario);
    }

    public void exibirMensagens(Usuario usuario) {
        System.out.println("\n--- Mensagens ---");
        System.out.println("Mensagens do usuário " + usuario.getLogin() + ":");
        List<String> mensagensUsuario = usuario.getMensagens();
        for (int i = 0; i < mensagensUsuario.size(); i++) {
            System.out.println(i + ". " + mensagensUsuario.get(i));
        }
        System.out.println("Mensagens dos usuários seguidos por " + usuario.getLogin() + ":");
        List<String> seguidores = usuario.getSeguidores();
        for (String seguidor : seguidores) {
            Usuario usuarioSeguido = usuarios.get(seguidor);
            List<String> mensagensSeguido = usuarioSeguido.getMensagens();
            for (int i = 0; i < mensagensSeguido.size(); i++) {
                System.out.println(i + ". " + usuarioSeguido.getLogin() + ": " + mensagensSeguido.get(i));
            }
        }

        System.out.println("Deseja escrever comentario em alguma mensagem?");
        System.out.println("1. Sim");
        System.out.println("2. Não");
        int escolhaEscritaComentario = Integer.parseInt(scanner.nextLine());

        if (escolhaEscritaComentario == 1) {
            System.out.println("Deseja escrever em suas mensagens ou dos usuários que voce segue?");
            System.out.println("1. Minhas mensagens");
            System.out.println("2. Usuarios Seguidos");
            int escolhaTipoUsuario = Integer.parseInt(scanner.nextLine());
            switch (escolhaTipoUsuario) {
                case 1 -> comentarMensagem(usuario);
                case 2 -> comentarMensagensSeguidos(seguidores);
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void comentarMensagensSeguidos(List<String> seguidores) {
        System.out.println("Escolha o usuário que voce segue para comentar em suas mensagens: ");
        int i = 0;
        Map<Integer, Usuario> listaSeguidores = new HashMap<>();
        for (String seguidor : seguidores) {
            Usuario usuarioSeguido = usuarios.get(seguidor);
            System.out.println(i + ". " + usuarioSeguido.getLogin());
            listaSeguidores.put(i, usuarioSeguido);
            i++;
        }
        int escolhaUsuarioSeguido = Integer.parseInt(scanner.nextLine());
        Usuario usuarioSeguidoEscolhido = listaSeguidores.get(escolhaUsuarioSeguido);

        List<String> mensagensSeguido = usuarioSeguidoEscolhido.getMensagens();
        System.out.println("Mensagens do usuário " + usuarioSeguidoEscolhido.getLogin() + ":");
        for (int j = 0; j < mensagensSeguido.size(); j++) {
            System.out.println(j + ". " + usuarioSeguidoEscolhido.getLogin() + ": " + mensagensSeguido.get(j));
        }

        System.out.println("Escolha o número da mensagem para comentar:");
        int mensagemSelecionada = Integer.parseInt(scanner.nextLine());

        System.out.println("Digite o seu comentário:");
        String comentario = scanner.nextLine();

        usuarioSeguidoEscolhido.comentarMensagem(mensagemSelecionada, comentario);
    }

    public void exibirComentarios(Usuario usuario) {
        System.out.println("\n--- Comentários ---");
        System.out.println("Mensagens do usuário " + usuario.getLogin() + ":");
        List<String> mensagensUsuario = usuario.getMensagens();
        for (int i = 0; i < mensagensUsuario.size(); i++) {
            System.out.println(i + ". " + mensagensUsuario.get(i));
        }
        System.out.print("Digite o número da mensagem: ");
        int numeroMensagem = Integer.parseInt(scanner.nextLine());

        List<String> comentarios = usuario.getComentarios().getOrDefault(numeroMensagem, new ArrayList<>());
        for (String comentario : comentarios) {
            System.out.println(usuario.getLogin() + ": " + comentario);
        }
    }

    public void exibirSeguidores(Usuario usuario) {
        System.out.println("\n--- Seguidores ---");
        List<String> seguidores = usuario.getSeguidores();
        for (String seguidor : seguidores) {
            Usuario usuarioSeguidor = usuarios.get(seguidor);
            System.out.println(usuarioSeguidor.toString());
        }
    }

    public void exibirSeguidos(Usuario usuario) {
        System.out.println("\n--- Seguidos ---");
        for (Usuario usuarioSeguido : usuarios.values()) {
            if (usuarioSeguido.getSeguidores().contains(usuario.getLogin())) {
                System.out.println(usuarioSeguido);
            }
        }
    }

    public void exibirMaisInfluente() {
        System.out.println("\n--- Usuário Mais Influente ---");
        String usuarioMaisInfluente = "";
        int quantidadeSeguidores = 0;
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getSeguidores().size() > quantidadeSeguidores) {
                usuarioMaisInfluente = usuario.getLogin();
                quantidadeSeguidores = usuario.getSeguidores().size();
            }
        }
        System.out.println("Usuário mais influente: " + usuarioMaisInfluente);
    }

    private void exibirAssunto() {
        System.out.print("Digite o assunto: ");
        String assunto = scanner.nextLine();
        ocorrenciaAssunto(assunto);
    }

    public void ocorrenciaAssunto(String assunto) {
        System.out.println("\n--- Ocorrência de Assunto ---");
        int ocorrenciasMensagens = 0;
        int ocorrenciasComentarios = 0;
        for (Usuario usuario : usuarios.values()) {
            List<String> mensagens = usuario.getMensagens();
            for (String mensagem : mensagens) {
                if (mensagem.contains(assunto)) {
                    ocorrenciasMensagens++;
                }
            }
            Map<Integer, List<String>> comentarios = usuario.getComentarios();
            for (List<String> listaComentarios : comentarios.values()) {
                for (String comentario : listaComentarios) {
                    if (comentario.contains(assunto)) {
                        ocorrenciasComentarios++;
                    }
                }
            }
        }
        System.out.println("Ocorrências do assunto nas mensagens: " + ocorrenciasMensagens);
        System.out.println("Ocorrências do assunto nos comentários: " + ocorrenciasComentarios);
    }

    public void executar() {
        boolean sair = false;
        Usuario usuarioLogado = null;

        while (!sair) {
            exibirMenu();
            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> {
                    usuarioLogado = acessarComLogin();
                    if (usuarioLogado != null) {
                        boolean logout = false;
                        while (!logout) {
                            System.out.println("\n--- Bem-vindo, " + usuarioLogado.getNome() + "! ---");
                            System.out.println("1. Exibir Dados do Usuário");
                            System.out.println("2. Alterar Dados do Usuário");
                            System.out.println("3. Seguir Usuário");
                            System.out.println("4. Cancelar Seguir Usuário");
                            System.out.println("5. Registrar Mensagem");
                            System.out.println("6. Comentar Mensagem");
                            System.out.println("7. Exibir Mensagens");
                            System.out.println("8. Exibir Comentários");
                            System.out.println("9. Exibir Seguidores");
                            System.out.println("10. Exibir Usuários Seguidos");
                            System.out.println("11. Exibir Usuário Mais Influente");
                            System.out.println("12. Ocorrência de Assunto");
                            System.out.println("13. Logout");
                            System.out.print("Escolha uma opção: ");
                            int escolha = Integer.parseInt(scanner.nextLine());

                            switch (escolha) {
                                case 1 -> exibirDadosUsuario(usuarioLogado);
                                case 2 -> alterarDadosUsuario(usuarioLogado);
                                case 3 -> seguirUsuario(usuarioLogado);
                                case 4 -> cancelarSeguirUsuario(usuarioLogado);
                                case 5 -> registrarMensagem(usuarioLogado);
                                case 6 -> comentarMensagem(usuarioLogado);
                                case 7 -> exibirMensagens(usuarioLogado);
                                case 8 -> exibirComentarios(usuarioLogado);
                                case 9 -> exibirSeguidores(usuarioLogado);
                                case 10 -> exibirSeguidos(usuarioLogado);
                                case 11 -> exibirMaisInfluente();
                                case 12 -> exibirAssunto();
                                case 13 -> logout = true;
                                default -> System.out.println("Opção inválida.");
                            }
                        }
                    }
                }
                case 3 -> sair = true;
                default -> System.out.println("Opção inválida.");
            }
        }

        scanner.close();
        System.out.println("Sistema encerrado.");
    }
}
