package Entidades;

public abstract     class Bebida {
    protected String marca;
    private Fornecedor fornecedor;
    private Integer quantidade;

    public Bebida(String marca, Integer quantidade) {
        this.marca = marca;
        this.quantidade = quantidade;
    }

    public String getMarca() {
        return marca;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void incrementa(int incremento){
        this.quantidade += incremento;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void decrementa(int qntd){
        this.quantidade -= qntd;
    }
}


