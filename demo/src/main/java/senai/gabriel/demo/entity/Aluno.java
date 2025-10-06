package senai.gabriel.demo.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name= "TB_Aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(nullable=false)
    private String nome;

    @Column(nullable=false, unique=true)
    private String cpf;

    @Column(nullable=false)
    private LocalDate data_nascimento;

    @Column(nullable=false)
    private boolean  ativo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="plano_id")
    private Plano plano;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "aluno_treino_vinculo",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "treino_id")
    )
    private Set<Treino> treinos = new HashSet<>();

    public Aluno() {
    }

    public Aluno(long id, String nome, String cpf, LocalDate data_nascimento, boolean ativo, Plano plano,
            List<Pagamento> pagamentos, Set<Treino> treinos) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.ativo = ativo;
        this.plano = plano;
        this.pagamentos = pagamentos;
        this.treinos = treinos;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Plano getPlano() {
        return plano;
    }

    public void setPlano(Plano plano) {
        this.plano = plano;
    }

    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Set<Treino> getTreinos() {
        return treinos;
    }

    public void setTreinos(Set<Treino> treinos) {
        this.treinos = treinos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        result = prime * result + ((data_nascimento == null) ? 0 : data_nascimento.hashCode());
        result = prime * result + (ativo ? 1231 : 1237);
        result = prime * result + ((plano == null) ? 0 : plano.hashCode());
        result = prime * result + ((pagamentos == null) ? 0 : pagamentos.hashCode());
        result = prime * result + ((treinos == null) ? 0 : treinos.hashCode());
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
        Aluno other = (Aluno) obj;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (cpf == null) {
            if (other.cpf != null)
                return false;
        } else if (!cpf.equals(other.cpf))
            return false;
        if (data_nascimento == null) {
            if (other.data_nascimento != null)
                return false;
        } else if (!data_nascimento.equals(other.data_nascimento))
            return false;
        if (ativo != other.ativo)
            return false;
        if (plano == null) {
            if (other.plano != null)
                return false;
        } else if (!plano.equals(other.plano))
            return false;
        if (pagamentos == null) {
            if (other.pagamentos != null)
                return false;
        } else if (!pagamentos.equals(other.pagamentos))
            return false;
        if (treinos == null) {
            if (other.treinos != null)
                return false;
        } else if (!treinos.equals(other.treinos))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", data_nascimento=" + data_nascimento
                + ", ativo=" + ativo + ", plano=" + plano + ", pagamentos=" + pagamentos + ", treinos=" + treinos + "]";
    }

    
}
