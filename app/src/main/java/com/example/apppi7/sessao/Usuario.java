package com.example.apppi7.sessao;

public class Usuario {

    private static Usuario _usuario;

    private String email;
    private String senha;

    private Usuario()
    {

    }


    public static Usuario GetInstance()
    {
        if(_usuario == null)
            _usuario = new Usuario();

        return _usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
