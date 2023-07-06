package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {
    private String login;
    private String nome;
    private String email;
    private String dataNascimento;
    private List<String> seguidores;
    private List<String> mensagens;
    private Map<Integer, List<String>> comentarios;

    public Usuario(String login, String nome, String email, String dataNascimento) {
        this.login = login;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.seguidores = new ArrayList<>();
        this.mensagens = new ArrayList<>();
        this.comentarios = new HashMap<>();
    }

    public String getLogin() {
        return login;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public List<String> getSeguidores() {
        return seguidores;
    }

    public List<String> getMensagens() {
        return mensagens;
    }

    public Map<Integer, List<String>> getComentarios() {
        return comentarios;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void seguirUsuario(Usuario usuario) {
        if (!seguidores.contains(usuario.getLogin())) {
            seguidores.add(usuario.getLogin());
            System.out.println("Agora você está seguindo " + usuario.getNome() + ".");
        } else {
            System.out.println("Você já está seguindo " + usuario.getNome() + ".");
        }
    }

    public void cancelarSeguirUsuario(Usuario usuario) {
        if (seguidores.contains(usuario.getLogin())) {
            seguidores.remove(usuario.getLogin());
            System.out.println("Você deixou de seguir " + usuario.getNome() + ".");
        } else {
            System.out.println("Você não está seguindo " + usuario.getNome() + ".");
        }
    }

    public void registrarMensagem(String mensagem) {
        if (mensagem.length() <= 140) {
            mensagens.add(mensagem);
            System.out.println("Mensagem registrada com sucesso!");
        } else {
            System.out.println("A mensagem excede o limite de 140 caracteres.");
        }
    }

    public void comentarMensagem(int numeroMensagem, String comentario) {
        if (mensagens.size() > numeroMensagem) {
            if (comentario.length() <= 140) {
                List<String> comentariosMensagem = comentarios.getOrDefault(numeroMensagem, new ArrayList<>());
                comentariosMensagem.add(comentario);
                comentarios.put(numeroMensagem, comentariosMensagem);
                System.out.println("Comentário registrado com sucesso!");
            } else {
                System.out.println("O comentário excede o limite de 140 caracteres.");
            }
        } else {
            System.out.println("Mensagem não encontrada.");
        }
    }

    @Override
    public String toString() {
        return "Login: " + login + "\nNome: " + nome;
    }
}
