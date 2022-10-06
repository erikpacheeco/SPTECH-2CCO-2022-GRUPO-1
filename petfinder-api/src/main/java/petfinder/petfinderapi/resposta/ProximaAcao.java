package petfinder.petfinderapi.resposta;

public class ProximaAcao {
    
    // attributes
    private String acao;
    private String texto;
    private String tipoBotao;
    private String descricao;

    // constructor
    public ProximaAcao(DtoDemanda dto, String tipoUsuario) {

        // ABERTO OU EM ANDAMENTO
        if (dto.getStatus().equalsIgnoreCase("ABERTO")) {
            this.acao = tipoUsuario == "usuario" ? null : "Atender";
            this.texto = tipoUsuario == "usuario" ? "Aguardando atendimento" : "Atender solicitação";
            this.tipoBotao = tipoUsuario == "usuario" ? "decline" : "accept";
            this.descricao = tipoUsuario == "usuario" ? "Assim que possível, um atendente entrará em contato..." : "O usuário que abriu essa demanda está esperando por um atendente...";
        } else if (dto.getStatus().equalsIgnoreCase("CONCLUIDO")) {
            this.texto = "Demanda concluida";
            this.descricao = "Demanda concluída com sucesso. Nenhuma ação pode ser tomada";
        } else if (dto.getStatus().equalsIgnoreCase("CANCELADO")) {
            this.texto = "Demanda cancelada";
            this.descricao = "Infelizmente essa demanda foi cancelada pelo usuário. De qualquer forma, nenhuma ação pode ser tomada";
        } else {
            // ADOCAO
            if(dto.getCategoria().equalsIgnoreCase("adocao")) {
                if(dto.getStatus().equalsIgnoreCase("EM_ANDAMENTO") || 
                    dto.getStatus().equalsIgnoreCase("DOCUMENTO_INVALIDO")) {
                    this.acao = tipoUsuario == "usuario" ? "enviar documento" : null;
                    this.texto = tipoUsuario == "usuario" ? "Documento enviado?" : "Aguardando documento";
                    this.tipoBotao = tipoUsuario == "usuario" ? "accept/decline" : null;
                    this.descricao = tipoUsuario == "usuario" ? "Assim que você enviar o documento, pode marcar como enviado ao clicar no botão verde." : "O usuário ainda não enviou o documento, apenas aguarde...";
                } else if(dto.getStatus().equalsIgnoreCase("AGUARDANDO_VALIDACAO_DOCUMENTO")) {
                    this.acao = tipoUsuario == "usuario" ? null : "validar documento";
                    this.texto = tipoUsuario == "usuario" ? "aguardando validação" : "validar documento";
                    this.tipoBotao = tipoUsuario == "usuario" ? "decline" : "accept/decline";
                    this.descricao = tipoUsuario == "usuario" ? "O atendente está validando seu documento, apenas aguarde..." : "O usuário está aguardando a confirmação do documento. Se o documento estiver válido, clique no botão verde.";
                } else if (dto.getStatus().equalsIgnoreCase("DOCUMENTO_VALIDO")) {
                    this.acao = tipoUsuario == "usuario" ? "Concluir" : null;
                    this.texto = tipoUsuario == "usuario" ? "Concluir" : "Aguardando conclusão";
                    this.tipoBotao = tipoUsuario == "usuario" ? "accept/decline" : null;
                    this.descricao = tipoUsuario == "usuario" ? "O atendente está validando seu documento, apenas aguarde..." : "O usuário está aguardando a confirmação do documento. Se o documento estiver válido, clique no botão verde.";
                }
            } else if (dto.getCategoria().equalsIgnoreCase("pagamento")) {
                // PAGAMENTO
                if(dto.getStatus().equalsIgnoreCase("EM_ANDAMENTO")){
                    this.acao = tipoUsuario == "usuario" ? "Pagar" : null;
                    this.texto = tipoUsuario == "usuario" ? "Pagamento realizado?" : "Aguardando pagamento";
                    this.tipoBotao = tipoUsuario == "usuario" ? "accept/decline" : null;
                    this.descricao = tipoUsuario == "usuario" ? "Se você já tiver realizado o pagamento, clique no botão verde." : "O usuário ainda não realizou o pagamento, apenas aguarde";
                } else if(dto.getStatus().equalsIgnoreCase("PGTO_REALIZADO_USER")) {
                    this.acao = tipoUsuario == "usuario" ? "decline" : "confirmar pagamento";
                    this.texto = tipoUsuario == "usuario" ? "Aguardando confirmação" : "Confirmar pagamento";
                    this.tipoBotao = tipoUsuario == "usuario" ? "decline" : "accept/decline";
                    this.descricao = tipoUsuario == "usuario" ? "Seu pagamento está sendo validado pelo atendente, apenas aguarde..." : "O usuário já realizou o pagamento, verifique se o valor foi recebido e caso tenha sido conforme clicando no botão verde.";
                } else if(dto.getStatus().equalsIgnoreCase("PGTO_REALIZADO_INST")) {
                    this.acao = tipoUsuario == "usuario" ? "concluir demanda" : null;
                    this.texto = tipoUsuario == "usuario" ? "Concluir Demanda" : "Aguardando conclusão";
                    this.tipoBotao = tipoUsuario == "usuario" ? "accept/decline" : null;
                    this.descricao = tipoUsuario == "usuario" ? "Caso tudo tenha saído como o esperado, clique no botão verde para concluír a demanda" : "Aguardando o usuário concluir a demanda, apenas aguarde...";
                }
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
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
