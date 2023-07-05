package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RedeSocial {
    private Map<String, Usuario> usuarios;

    public RedeSocial() {
        this.usuarios = new HashMap<>();
    }

    public void cadastrarUsuario(String login, String nome, String email, String dataNascimento) {
        if (!usuarios.containsKey(login)) {
            usuarios.put(login, new Usuario(login, nome, email, dataNascimento));
        } else {
            System.out.println("Já existe um usuário cadastrado com esse login.");
        }
    }

    public void excluirUsuario(String login) {
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            usuario.excluirUsuario(usuarios);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void listarUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            System.out.println("Login: " + usuario.getLogin() + " - Nome: " + usuario.getNome());
        }
    }

    public void pesquisarUsuario(String login) {
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            System.out.println("Login: " + usuario.getLogin());
            System.out.println("Nome: " + usuario.getNome());
            System.out.println("Email: " + usuario.getEmail());
            System.out.println("Data de Nascimento: " + usuario.getDataNascimento());
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void alterarUsuario(String login, String nome, String email, String dataNascimento) {
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setDataNascimento(dataNascimento);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void seguirUsuario(String loginSeguidor, String loginSeguido) {
        if (usuarios.containsKey(loginSeguidor) && usuarios.containsKey(loginSeguido)) {
            Usuario seguidor = usuarios.get(loginSeguidor);
            Usuario seguido = usuarios.get(loginSeguido);
            seguidor.seguirUsuario(seguido);
        } else {
            System.out.println("Usuário(s) não encontrado(s).");
        }
    }

    public void cancelarSeguirUsuario(String loginSeguidor, String loginSeguido) {
        if (usuarios.containsKey(loginSeguidor) && usuarios.containsKey(loginSeguido)) {
            Usuario seguidor = usuarios.get(loginSeguidor);
            Usuario seguido = usuarios.get(loginSeguido);
            seguidor.cancelarSeguirUsuario(seguido);
        } else {
            System.out.println("Usuário(s) não encontrado(s).");
        }
    }

    public void registrarMensagem(String login, String mensagem) {
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            usuario.registrarMensagem(mensagem);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void comentarMensagem(String loginAutor, int numeroMensagem, String loginComentarista, String comentario) {
        if (usuarios.containsKey(loginAutor) && usuarios.containsKey(loginComentarista)) {
            Usuario autor = usuarios.get(loginAutor);
            Usuario comentarista = usuarios.get(loginComentarista);
            if (autor.getSeguidores().contains(comentarista.getLogin())) {
                autor.comentarMensagem(numeroMensagem, comentario);
            } else {
                System.out.println("Somente seguidores podem comentar mensagens");
            }
        } else {
            System.out.println("Usuário(s) não encontrado(s).");
        }
    }

    public void verMensagens(String login) {
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            System.out.println("Mensagens do usuário " + login + ":");
            for (int i = 0; i < usuario.getMensagens().size(); i++) {
                System.out.println("[" + i + "] " + usuario.getMensagens().get(i));
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void verComentarios(String login, int numeroMensagem) {
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            if (usuario.getMensagens().size() >= numeroMensagem) {
                List<String> comentarios = usuario.getComentarios().getOrDefault(numeroMensagem, new ArrayList<>());
                System.out.println("Comentários da mensagem " + numeroMensagem + ":");
                for (String comentario : comentarios) {
                    System.out.println(comentario);
                }
            } else {
                System.out.println("Mensagem não encontrada.");
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void exibirSeguidores(String login) {
        if (usuarios.containsKey(login)) {
            Usuario usuario = usuarios.get(login);
            System.out.println("Seguidores do usuário " + login + ":");
            for (String seguidor : usuario.getSeguidores()) {
                Usuario seguidorUsuario = usuarios.get(seguidor);
                System.out.println(seguidorUsuario.toString());
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void exibirSeguidos(String login) {
        if (usuarios.containsKey(login)) {
            System.out.println("Usuários seguidos por " + login + ":");
            for (Usuario seguido : usuarios.values()) {
                if (seguido.getSeguidores().contains(login)) {
                    System.out.println(seguido);
                }
            }
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public void exibirMaisInfluente() {
        Usuario maisInfluente = null;
        int quantidadeSeguidores = 0;
        for (Usuario usuario : usuarios.values()) {
            if (usuario.getSeguidores().size() > quantidadeSeguidores) {
                quantidadeSeguidores = usuario.getSeguidores().size();
                maisInfluente = usuario;
            }
        }
        if (maisInfluente != null) {
            System.out.println("Usuário mais influente:");
            System.out.println("Login: " + maisInfluente.getLogin());
            System.out.println("Nome: " + maisInfluente.getNome());
            System.out.println("Quantidade de seguidores: " + quantidadeSeguidores);
        } else {
            System.out.println("Não há usuários cadastrados.");
        }
    }

    public void ocorrenciaAssunto(String assunto) {
        int quantidadeMensagens = 0;
        int quantidadeComentarios = 0;
        for (Usuario usuario : usuarios.values()) {
            for (String mensagem : usuario.getMensagens()) {
                if (mensagem.contains(assunto)) {
                    quantidadeMensagens++;
                }
            }
            for (List<String> comentarios : usuario.getComentarios().values()) {
                for (String comentario : comentarios) {
                    if (comentario.contains(assunto)) {
                        quantidadeComentarios++;
                    }
                }
            }
        }
        System.out.println("Ocorrência do assunto \"" + assunto + "\":");
        System.out.println("Mensagens: " + quantidadeMensagens);
        System.out.println("Comentários: " + quantidadeComentarios);
    }
}
