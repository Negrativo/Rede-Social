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

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public void seguirUsuario(Usuario usuario) {
        usuario.getSeguidores().add(this.login);
    }

    public void cancelarSeguirUsuario(Usuario usuario) {
        usuario.getSeguidores().remove(this.login);
    }

    public void registrarMensagem(String mensagem) {
        if (mensagem.length() <= 140) {
            mensagens.add(mensagem);
        } else {
            System.out.println("A mensagem excede o limite de 140 caracteres.");
        }
    }

    public void comentarMensagem(int numeroMensagem, String comentario) {
        if (mensagens.size() >= numeroMensagem) {
            List<String> comentariosMensagem = comentarios.getOrDefault(numeroMensagem, new ArrayList<>());
            comentariosMensagem.add(comentario);
            comentarios.put(numeroMensagem, comentariosMensagem);
        } else {
            System.out.println("Mensagem não encontrada.");
        }
    }

    public void excluirUsuario(Map<String, Usuario> usuarios) {
        for (Usuario seguidor : usuarios.values()) {
            seguidor.getSeguidores().remove(this.login);
        }
        usuarios.remove(this.login);
    }

    @Override
    public String toString() {
        return "Usuário: " + login + " - Nome: " + nome;
    }
}
