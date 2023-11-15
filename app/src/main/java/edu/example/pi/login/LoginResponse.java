package edu.example.pi.login;

public class LoginResponse {
    private String accessToken;
    private String user; // Se o ID do usuário for uma string; ajuste conforme necessário
    private String message; // Mensagem opcional


    private String userName;


    // Construtor
    public LoginResponse(String accessToken, String user, String message) {
        this.accessToken = accessToken;
        this.user = user;
        this.message = message;
    }

    public LoginResponse(String accessToken, String user) {
        this.accessToken = accessToken;
        this.user = user;

    }


    // Método para obter o token de acesso
    public String getAccessToken() {
        return accessToken;
    }

    // Método para obter o ID do usuário
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Método para verificar se o login foi bem-sucedido
    public boolean isSuccessful() {
        // Você pode ajustar a lógica conforme necessário
        return accessToken != null && !accessToken.isEmpty() && user != null && !user.isEmpty();
    }

    // Método para obter a mensagem (opcional)
    public String getMessage() {
        return message;
    }
}
