package petfinder.petfinderapi.resposta;

public class ProximaAcao {
    
    // attributes
    private String acao;
    private String texto;
    private String tipoBotao;

    // constructor
    public ProximaAcao(DtoDemanda dto, String tipoUsuario) {

        // ABERTO OU EM ANDAMENTO
        if (dto.getStatus().equalsIgnoreCase("ABERTO")) {
            this.acao = tipoUsuario == "usuario" ? null : "Atender";
            this.texto = tipoUsuario == "usuario" ? "Aguardando atendimento" : "Atender solicitação";
            this.tipoBotao = tipoUsuario == "usuario" ? "decline" : "accept";
        } else if (dto.getStatus().equalsIgnoreCase("CONCLUIDO")) {
            this.texto = "Demanda concluida";
        } else if (dto.getStatus().equalsIgnoreCase("CANCELADO")) {
            this.texto = "Demanda cancelada";
        } else {
            // ADOCAO
            if(dto.getCategoria().equalsIgnoreCase("adocao")) {
                if(dto.getStatus().equalsIgnoreCase("EM_ANDAMENTO") || 
                    dto.getStatus().equalsIgnoreCase("DOCUMENTO_INVALIDO")) {
                    this.acao = tipoUsuario == "usuario" ? "enviar documento" : null;
                    this.texto = tipoUsuario == "usuario" ? "Documento enviado?" : "Aguardando documento";
                    this.tipoBotao = tipoUsuario == "usuario" ? "accept/decline" : null;
                } else if(dto.getStatus().equalsIgnoreCase("AGUARDANDO_VALIDACAO_DOCUMENTO")) {
                    this.acao = tipoUsuario == "usuario" ? null : "validar documento";
                    this.texto = tipoUsuario == "usuario" ? "aguardando validação" : "validar documento";
                    this.tipoBotao = tipoUsuario == "usuario" ? "decline" : "accept/decline";
                } else if (dto.getStatus().equalsIgnoreCase("DOCUMENTO_VALIDO")) {
                    this.acao = tipoUsuario == "usuario" ? "Concluir" : null;
                    this.texto = tipoUsuario == "usuario" ? "Concluir" : "Aguardando conclusão";
                    this.tipoBotao = tipoUsuario == "usuario" ? "accept/decline" : null;
                }
            } else if (dto.getCategoria().equalsIgnoreCase("pagamento")) {
                // PAGAMENTO
                if(dto.getStatus().equalsIgnoreCase("EM_ANDAMENTO")){
                    this.acao = tipoUsuario == "usuario" ? "Pagar" : null;
                    this.texto = tipoUsuario == "usuario" ? "Pagamento realizado?" : "Aguardando pagamento";
                    this.tipoBotao = tipoUsuario == "usuario" ? "accept/decline" : null;
                } else if(dto.getStatus().equalsIgnoreCase("PGTO_REALIZADO_USER")) {
                    this.acao = tipoUsuario == "usuario" ? "decline" : "confirmar pagamento";
                    this.texto = tipoUsuario == "usuario" ? "Aguardando confirmação" : "Confirmar pagamento";
                    this.tipoBotao = tipoUsuario == "usuario" ? "decline" : "accept/decline";
                } else if(dto.getStatus().equalsIgnoreCase("PGTO_REALIZADO_INST")) {
                    this.acao = tipoUsuario == "usuario" ? "concluir demanda" : null;
                    this.texto = tipoUsuario == "usuario" ? "Concluir Demanda" : "Aguardando conclusão";
                    this.tipoBotao = tipoUsuario == "usuario" ? "accept/decline" : null;
                }
            } else if(dto.getCategoria().equalsIgnoreCase("resgate")) {
                // RESGATE
            }
        }
    }

    // getters and setters
    public String getAcao() {
        return acao;
    }
    public void setAcao(String acao) {
        this.acao = acao;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public String getTipoBotao() {
        return tipoBotao;
    }
    public void setTipoBotao(String tipoBotao) {
        this.tipoBotao = tipoBotao;
    }
}
