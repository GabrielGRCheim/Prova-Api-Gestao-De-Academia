package senai.gabriel.demo.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_Plano")
public class Plano {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false)
    private Double valor_mensal;

    @OneToMany(mappedBy = "plano", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aluno> alunos = new ArrayList<>();

    public Plano() {
    }

    public Plano(long id, String nome, Double valor_mensal, List<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.valor_mensal = valor_mensal;
        this.alunos = alunos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor_mensal() {
        return valor_mensal;
    }

    public void setValor_mensal(Double valormensal) {
        this.valor_mensal = valormensal;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((valor_mensal == null) ? 0 : valor_mensal.hashCode());
        result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Plano other = (Plano) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (valor_mensal == null) {
            if (other.valor_mensal != null)
                return false;
        } else if (!valor_mensal.equals(other.valor_mensal))
            return false;
        if (alunos == null) {
            if (other.alunos != null)
                return false;
        } else if (!alunos.equals(other.alunos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Plano [id=" + id + ", nome=" + nome + ", valormensal=" + valor_mensal + ", alunos=" + alunos + "]";
    }

    
}
