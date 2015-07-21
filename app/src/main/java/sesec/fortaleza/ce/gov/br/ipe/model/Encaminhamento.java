package sesec.fortaleza.ce.gov.br.ipe.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Jorge on 29/06/2015.
 */
public class Encaminhamento implements Serializable {

    private Integer id;
    private String descricao;
    private Collection<Atendimento> atendimentosCollection;

    public Encaminhamento() {
    }

    public Encaminhamento(Integer id) {
        this.id = id;
    }

    public Encaminhamento(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Collection<Atendimento> getAtendimentosCollection() {
        return atendimentosCollection;
    }

    public void setAtendimentosCollection(Collection<Atendimento> atendimentosCollection) {
        this.atendimentosCollection = atendimentosCollection;
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
        if (!(object instanceof Encaminhamento)) {
            return false;
        }
        Encaminhamento other = (Encaminhamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Encaminhamentos[ id=" + id + " ]";
    }
}
