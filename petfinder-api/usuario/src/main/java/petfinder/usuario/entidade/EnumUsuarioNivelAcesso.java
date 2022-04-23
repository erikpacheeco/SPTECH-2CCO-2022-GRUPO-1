package petfinder.usuario.entidade;

public enum EnumUsuarioNivelAcesso {

    ADMIN("admin"),
    USER("user"),
    ADMOPS("admOps"),
    PETOPS("petOps"),
    CHATOPS("chatOps"),
    UNSIGNED("unsigned");

    EnumUsuarioNivelAcesso(String nivelAcesso) {
    }
}
