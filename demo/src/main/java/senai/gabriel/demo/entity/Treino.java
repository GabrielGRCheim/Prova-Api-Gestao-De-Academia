package senai.gabriel.demo.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_Treinos")
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private nivelTreino nivel;

    @ManyToMany(mappedBy = "treinos", fetch = FetchType.LAZY)
    private Set<Aluno> alunos = new HashSet<>();

    public enum nivelTreino {
        INICIANTE,
        INTERMEDIARIO,
        AVANCADO
    }

    public Treino() {
    }

    public Treino(long id, String nome, String descricao, nivelTreino nivel, Set<Aluno> alunos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.nivel = nivel;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public nivelTreino getNivel() {
        return nivel;
    }

    public void setNivel(nivelTreino nivel) {
        this.nivel = nivel;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(Set<Aluno> alunos) {
        this.alunos = alunos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
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
        Treino other = (Treino) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (descricao == null) {
            if (other.descricao != null)
                return false;
        } else if (!descricao.equals(other.descricao))
            return false;
        if (nivel == null) {
            if (other.nivel != null)
                return false;
        } else if (!nivel.equals(other.nivel))
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
        return "Treino [id=" + id + ", nome=" + nome + ", descricao=" + descricao + ", nivel=" + nivel + ", alunos="
                + alunos + "]";
    }

}
