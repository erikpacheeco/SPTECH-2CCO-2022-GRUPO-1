package petfinder.petfinderapi.entidades;
public class Usuario extends Pessoa {

    public Usuario(String nome, String email, String senha, Endereco endereco) {
        super(nome, email, senha, endereco);
    }

    @Override
    public Boolean validar() {
        // TODO Auto-generated method stub
        return null;
    }
}
