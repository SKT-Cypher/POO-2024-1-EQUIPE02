public class Ingresso {
    private String tipo;
    private double preco;
    private int quantidadeDisponivel;
    private int quantidadeInicial;
    private double desconto;

    public Ingresso(String tipo, double preco, int quantidadeDisponivel, int quantidadeInicial, double desconto) {
        this.tipo = tipo;
        this.preco = preco;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.quantidadeInicial = quantidadeInicial;
        this.desconto = desconto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }
    
    public int getQuantidadeInicial() {
    	return quantidadeInicial;
    }
    
    public void setQuantidadeInicial(int quantidadeInicial) {
    	this.quantidadeInicial = quantidadeInicial;
    }

    public void setDesconto(double desconto) {
    	this.desconto = desconto;
    }
    public double getDesconto() {
    	return desconto;
    }

    // Outros métodos, se necessário
}
