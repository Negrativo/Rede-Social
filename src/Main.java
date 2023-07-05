import model.RedeSocial;

public class Main {

    public static void main(String[] args) {
        RedeSocial redeSocial = new RedeSocial();

        redeSocial.cadastrarUsuario("usuario1", "Usuário 1", "usuario1@teste.com", "01/01/1990");
        redeSocial.cadastrarUsuario("usuario2", "Usuário 2", "usuario2@teste.com", "02/02/1991");
        redeSocial.cadastrarUsuario("usuario3", "Usuário 3", "usuario3@teste.com", "03/03/1992");
        redeSocial.cadastrarUsuario("usuario4", "Usuário 4", "usuario4@teste.com", "04/04/1993");

        redeSocial.listarUsuarios();

        //Pesquisar usuario existente
        redeSocial.pesquisarUsuario("usuario1");

        //Pesquisar usuario não cadastrado
        redeSocial.excluirUsuario("usuario4");
        redeSocial.pesquisarUsuario("usuario4");

        redeSocial.listarUsuarios();

        redeSocial.seguirUsuario("usuario1", "usuario2");
        redeSocial.seguirUsuario("usuario1", "usuario3");


        redeSocial.registrarMensagem("usuario1", "Mensagem 1 do Usuário 1");
        redeSocial.registrarMensagem("usuario2", "Mensagem 1 do Usuário 2");
        redeSocial.registrarMensagem("usuario2", "Mensagem 2 do Usuário 2");

        redeSocial.comentarMensagem("usuario1", 0, "usuario2", "Comentário na Mensagem 1 do Usuário 1");
        redeSocial.comentarMensagem("usuario2", 1, "usuario1", "Comentário na Mensagem 2 do Usuário 2");

        redeSocial.verMensagens("usuario1");
        redeSocial.verComentarios("usuario2", 1);

        redeSocial.exibirSeguidores("usuario1");
        redeSocial.exibirSeguidos("usuario2");
        redeSocial.exibirMaisInfluente();

        redeSocial.ocorrenciaAssunto("Mensagem");

        //Alterar data de nascimento do usuario 1
        redeSocial.alterarUsuario("usuario1", "Usuário 1", "usuario1@example.com", "01/01/2000");
        //Pesquisar usuario após alterar dados
        redeSocial.pesquisarUsuario("usuario1");

        redeSocial.cancelarSeguirUsuario("usuario1", "usuario2");

    }

}