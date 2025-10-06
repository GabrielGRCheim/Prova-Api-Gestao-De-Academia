package senai.gabriel.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_Pagamentos")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private LocalDate data_pagemento;

    @Column(nullable = false)
    private Double valor_pago;

    @Column(nullable = false)
    private String status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private formaPagamento forma_pagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    public enum formaPagamento {
        DINHEIRO,
        PIX,
        CARTAO
    }

    public Pagamento() {
    }

    public Pagamento(long id, LocalDate data_pagemento, Double valor_pago, String status,
            formaPagamento forma_pagamento,
            Aluno aluno) {
        this.id = id;
        this.data_pagemento = data_pagemento;
        this.valor_pago = valor_pago;
        this.status = status;
        this.forma_pagamento = forma_pagamento;
        this.aluno = aluno;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData_pagemento() {
        return data_pagemento;
    }

    public void setData_pagemento(LocalDate data_pagemento) {
        this.data_pagemento = data_pagemento;
    }

    public Double getValor_pago() {
        return valor_pago;
    }

    public void setValor_pago(Double valor_pago) {
        this.valor_pago = valor_pago;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public formaPagamento getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(formaPagamento forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((data_pagemento == null) ? 0 : data_pagemento.hashCode());
        result = prime * result + ((valor_pago == null) ? 0 : valor_pago.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((forma_pagamento == null) ? 0 : forma_pagamento.hashCode());
        result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
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
        Pagamento other = (Pagamento) obj;
        if (id != other.id)
            return false;
        if (data_pagemento == null) {
            if (other.data_pagemento != null)
                return false;
        } else if (!data_pagemento.equals(other.data_pagemento))
            return false;
        if (valor_pago == null) {
            if (other.valor_pago != null)
                return false;
        } else if (!valor_pago.equals(other.valor_pago))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (forma_pagamento == null) {
            if (other.forma_pagamento != null)
                return false;
        } else if (!forma_pagamento.equals(other.forma_pagamento))
            return false;
        if (aluno == null) {
            if (other.aluno != null)
                return false;
        } else if (!aluno.equals(other.aluno))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Pagamento [id=" + id + ", data_pagemento=" + data_pagemento + ", valor_pago=" + valor_pago + ", status="
                + status + ", forma_pagamento=" + forma_pagamento + ", aluno=" + aluno + "]";
    }

}
