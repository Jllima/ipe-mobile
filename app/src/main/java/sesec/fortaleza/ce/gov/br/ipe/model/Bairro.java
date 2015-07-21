package sesec.fortaleza.ce.gov.br.ipe.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Jorge on 29/06/2015.
 */
public class Bairro implements Serializable {

    private Ser idSer;
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nome;
    private Collection<Atendimento> atendimentosCollection;

    public Bairro() {
    }

    public Bairro(Integer id) {
        this.id = id;
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
        if (!(object instanceof Bairro)) {
            return false;
        }
        Bairro other = (Bairro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getNome();
    }

    public Ser getIdSer() {
        return idSer;
    }

    public void setIdSer(Ser idSer) {
        this.idSer = idSer;
    }
}
