package sesec.fortaleza.ce.gov.br.ipe.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Jorge on 29/06/2015.
 */
public class Atendimento implements Serializable{

    private String latitude;
    private String longitude;
    private String numCasa;
    private String pontoReferencia;
    private String equipe;
    private String vtr;
    private String proprietarioResidencia;
    private Integer qtdComodosCasa;
    private Integer qtdPessoasAtingidas;
    private static final long serialVersionUID = 1L;

    private Long id;
    private String protocolo;
    private String titulo;
    private String procedencia;
    private String protProcedencia;
    private Date dataSolicitacao;
    private Date horaSolicitacao;
    private String localizacao;
    private String solicitante;
    private String responsavel;
    private String telSolicitante;
    private String descInicial;
    private String descFinal;
    private Date dataConclusao;
    private Date horaConclusao;
    private String observacao;
    private Collection<Encaminhamento> encaminhamentosCollection;
    private Estatu idEstatus;
    private Tipologia idTipologia;
    private Ser idSer;
    private Bairro idBairro;

    public Atendimento() {
    }

    public Atendimento(Long id) {
        this.id = id;
    }

    public Atendimento(Long id, String titulo, String procedencia, Date dataSolicitacao, Date horaSolicitacao, String localizacao, String solicitante, String responsavel, String descInicial) {
        this.id = id;
        this.titulo = titulo;
        this.procedencia = procedencia;
        this.dataSolicitacao = dataSolicitacao;
        this.horaSolicitacao = horaSolicitacao;
        this.localizacao = localizacao;
        this.solicitante = solicitante;
        this.responsavel = responsavel;
        this.descInicial = descInicial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getProtProcedencia() {
        return protProcedencia;
    }

    public void setProtProcedencia(String protProcedencia) {
        this.protProcedencia = protProcedencia;
    }

    public Date getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Date dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public Date getHoraSolicitacao() {
        return horaSolicitacao;
    }

    public void setHoraSolicitacao(Date horaSolicitacao) {
        this.horaSolicitacao = horaSolicitacao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getTelSolicitante() {
        return telSolicitante;
    }

    public void setTelSolicitante(String telSolicitante) {
        this.telSolicitante = telSolicitante;
    }

    public String getDescInicial() {
        return descInicial;
    }

    public void setDescInicial(String descInicial) {
        this.descInicial = descInicial;
    }

    public String getDescFinal() {
        return descFinal;
    }

    public void setDescFinal(String descFinal) {
        this.descFinal = descFinal;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Date getHoraConclusao() {
        return horaConclusao;
    }

    public void setHoraConclusao(Date horaConclusao) {
        this.horaConclusao = horaConclusao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }


    public Collection<Encaminhamento> getEncaminhamentosCollection() {
        return encaminhamentosCollection;
    }

    public void setEncaminhamentosCollection(Collection<Encaminhamento> encaminhamentosCollection) {
        this.encaminhamentosCollection = encaminhamentosCollection;
    }

    public Estatu getIdEstatus() {
        return idEstatus;
    }

    public void setIdEstatus(Estatu idEstatus) {
        this.idEstatus = idEstatus;
    }

    public Tipologia getIdTipologia() {
        return idTipologia;
    }

    public void setIdTipologia(Tipologia idTipologia) {
        this.idTipologia = idTipologia;
    }

    public Ser getIdSer() {
        return idSer;
    }

    public void setIdSer(Ser idSer) {
        this.idSer = idSer;
    }

    public Bairro getIdBairro() {
        return idBairro;
    }

    public void setIdBairro(Bairro idBairro) {
        this.idBairro = idBairro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atendimento)) {
            return false;
        }
        Atendimento other = (Atendimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Atendimentos[ id=" + id + " ]";
    }

    public String getNumCasa() {
        return numCasa;
    }

    public void setNumCasa(String numCasa) {
        this.numCasa = numCasa;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getVtr() {
        return vtr;
    }

    public void setVtr(String vtr) {
        this.vtr = vtr;
    }

    public String getProprietarioResidencia() {
        return proprietarioResidencia;
    }

    public void setProprietarioResidencia(String proprietarioResidencia) {
        this.proprietarioResidencia = proprietarioResidencia;
    }

    public Integer getQtdComodosCasa() {
        return qtdComodosCasa;
    }

    public void setQtdComodosCasa(Integer qtdComodosCasa) {
        this.qtdComodosCasa = qtdComodosCasa;
    }

    public Integer getQtdPessoasAtingidas() {
        return qtdPessoasAtingidas;
    }

    public void setQtdPessoasAtingidas(Integer qtdPessoasAtingidas) {
        this.qtdPessoasAtingidas = qtdPessoasAtingidas;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
