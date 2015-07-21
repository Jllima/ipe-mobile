package sesec.fortaleza.ce.gov.br.ipe.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Jorge on 29/06/2015.
 */
public class Ser implements Serializable {

    private Collection<Bairro> bairrosCollection;
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private Collection<Atendimento> atendimentosCollection;

    public Ser() {
    }

    public Ser(Integer id) {
        this.id = id;
    }

    public Ser(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        if (!(object instanceof Ser)) {
            return false;
        }
        Ser other = (Ser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public Collection<Bairro> getBairrosCollection() {
        return bairrosCollection;
    }

    public void setBairrosCollection(Collection<Bairro> bairrosCollection) {
        this.bairrosCollection = bairrosCollection;
    }
}
