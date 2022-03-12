package petfinder.petfinderapi.entidades;

public class Pet implements Validavel{
    private String nome;
    private String tipo;
    private String raca;
    private String porte;
    private Boolean emAdocao;
    private String telefone;

    public Pet(String nome, String tipo, String raca, String porte, Boolean emAdocao, String telefone) {
        this.nome = nome;
        this.tipo = tipo;
        this.raca = raca;
        this.porte = porte;
        this.emAdocao = emAdocao;
        this.telefone = telefone;
    }

    //    GETTERS E SETTERS

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getPorte() {
        return porte;
    }
    public void setPorte(String porte) {
        this.porte = porte;
    }

    public Boolean isAdotado() {
        return emAdocao;
    }
    public void setAdotado(Boolean adotado) {
        this.emAdocao = adotado;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public Boolean validar() {
        return null;
    }
}
