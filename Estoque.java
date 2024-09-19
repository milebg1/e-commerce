package produtos;
import java.io.Serializable;

public class Estoque implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private double preco;
    private int quantidade;

    public Estoque(double preco, int quantidade) {
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    public Estoque() {
    }
    
    public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public void adicionarQuantidadeProduto(int quantidade) {
	this.quantidade += quantidade;
    }
    public void removerQuantidadeProduto(int quantidade) {
        if(quantidade>this.quantidade){
            System.out.println("Quantidade solicitada maior do que os itens em estoque.");
            System.out.println("Estoque zerado.");
            this.quantidade = 0;
            return;
        }
		this.quantidade -= quantidade;
    }
    
    public void removerProdutoPedido(int remover) {
    	this.quantidade -= remover;
    }
    
    @Override
    public String toString() {
        return  "| Quantidade em estoque: " + quantidade + "\n" + 
                "| Preço unitário: " + preco;
    }
}
