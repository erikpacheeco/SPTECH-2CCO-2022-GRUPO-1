package petfinder.petfinderapi.entidades;

public class Pet implements Validavel{
    private String nome;
    private String tipo;
    private String raca;
    private String porte;
    private String sexo;
    private Boolean emAdocao = false;
    private Instituicao instituicao;

    public Pet(String nome, String tipo, String raca, String porte, String sexo) {
        this.nome = nome;
        this.tipo = tipo;
        this.raca = raca;
        this.porte = porte;
        this.sexo = sexo;
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

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Boolean isEmAdocao() {
        return emAdocao;
    }
    public void setEstadoAdocao(Boolean adotado) {
        this.emAdocao = adotado;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public Boolean validar() {
        return null;
    }
}
