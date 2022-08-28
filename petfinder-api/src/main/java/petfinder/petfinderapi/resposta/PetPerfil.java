package petfinder.petfinderapi.resposta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnore;
import petfinder.petfinderapi.entidades.Pet;
import petfinder.petfinderapi.entidades.PetHasCaracteristica;

public class PetPerfil {
    
    // attributes
    private Integer id;
    private String instituicao;
    private Integer distancia;
    private String nome;
    private String idade;
    private String especie;
    private String porte;
    private String raca;
    private Integer mimosPorMes;
    private String descricao;
    private String caminhoImagem;
    private List<String> caracteristicas;
    private Boolean isDoente;
    @JsonIgnore
    private String cepInstituicao;

    // constructor
    public PetPerfil() {
        this.caracteristicas = new ArrayList<String>();
    }
    public PetPerfil(Pet entity) {
        this.id = entity.getId();
        this.instituicao = entity.getInstituicao().getNome();
        this.nome = entity.getNome();
        this.especie = entity.getEspecie();
        this.porte = entity.getPorte();
        this.raca = entity.getRaca();
        this.descricao = entity.getDescricao();
        this.caminhoImagem = entity.getCaminhoImagem();
        this.caracteristicas = addCaracteristicas(entity.getPetHasCaracteristica(), null, 0);
        this.idade = getIdadeFromDataNasc(entity.getDataNasc());
        this.cepInstituicao = entity.getInstituicao().getEndereco().getCep();
        this.isDoente = entity.getDoente();
        
        // não dinamico por enquanto
        this.mimosPorMes = 10;
    }

    // methods

    @Deprecated
    protected List<String> addCaracteristicas(List<PetHasCaracteristica> array) {

        List<String> caracteristicas = new ArrayList<String>();

        for (PetHasCaracteristica phc : array) {
            caracteristicas.add(phc.getCaracteristica().getCaracteristica());
        }

        return caracteristicas;
    }

    private List<String> addCaracteristicas(List<PetHasCaracteristica> entity, List<String> caracteristicas, int start) {
        
        // inicializa o ArrayList<String> caso o mesmo seja nulo
        if (Objects.isNull(caracteristicas)) {
            caracteristicas = new ArrayList<String>();
        }

        // percorre lista de caracteristicas, adicionando cada caracteristica ao array de caracteristicas
        if(start < entity.size()) {
            caracteristicas.add(entity.get(start).getCaracteristica().getCaracteristica());
            return addCaracteristicas(entity, caracteristicas, start + 1);
        }

        return caracteristicas;
    }

    // retorna idade do pet baseado na data atual
    // ex: 1 ano, 3 anos, 2 meses, recém nascido.
    private String getIdadeFromDataNasc(Date before) {
        String idade = "";

        long difference = before.getTime() - new Date().getTime();
        double anos = Math.abs(difference / (1000 * 60 * 60 * 24 * 7 * 4.5d * 12));
        double meses = anos * 12;

        if (anos >= 1) {
            idade = String.format("%.0f ano", anos) + (anos >= 2 ? "s" : "");
        } else if (meses >= 1) {
            idade = String.format("%.0f mês", meses);
            if (meses >= 2) {
                idade = idade.replace("ê", "e") + "es";
            }
        } else {
            idade = "Recém-nascido";
        }

        return idade;
    }

    // getters and setters
    public String getInstituicao() {
        return instituicao;
    }
    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }
    public Integer getDistancia() {
        return distancia;
    }
    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getIdade() {
        return idade;
    }
    public void setIdade(String idade) {
        this.idade = idade;
    }
    public String getEspecie() {
        return especie;
    }
    public void setEspecie(String especie) {
        this.especie = especie;
    }
    public String getPorte() {
        return porte;
    }
    public void setPorte(String porte) {
        this.porte = porte;
    }
    public String getRaca() {
        return raca;
    }
    public void setRaca(String raca) {
        this.raca = raca;
    }
    public Integer getMimosPorMes() {
        return mimosPorMes;
    }
    public void setMimosPorMes(Integer mimosPorMes) {
        this.mimosPorMes = mimosPorMes;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getCaminhoImagem() {
        return caminhoImagem;
    }
    public void setCaminhoImagem(String caminhoImagem) {
        this.caminhoImagem = caminhoImagem;
    }
    public List<String> getCaracteristicas() {
        return caracteristicas;
    }
    public void setCaracteristicas(List<String> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }   
    public String getCepInstituicao() {
        return cepInstituicao;
    }
    public void setCepInstituicao(String cepInstituicao) {
        this.cepInstituicao = cepInstituicao;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Boolean getIsDoente() {
        return isDoente;
    }
    public void setIsDoente(Boolean isDoente) {
        this.isDoente = isDoente;
    } 
}
