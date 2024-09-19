package pedidos;
import produtos.Produto;
import java.io.Serializable;

public class ItemPedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Produto produtoItemPedido;
    private int quantidade;
    private double preco;
	
	public ItemPedido(int quantidade, double preco, Produto produtoItemPedido) {
		this.quantidade = quantidade;
		this.preco = preco;
		this.produtoItemPedido = produtoItemPedido;
	}
	
	public ItemPedido(){
	}
	
	public Produto getProdutoItemPedido() {
	    return produtoItemPedido;
	}
	public void setProdutoItemPedido(Produto produtoItemPedido){
	    this.produtoItemPedido = produtoItemPedido;
	}
	
	public int getQuantidade(){
	    return quantidade;
	}
	public void setQuantidade(int quantidade) {
	    this.quantidade = quantidade;
	}
	
	public double getPreco() {
	    return preco;
	}
	public void setPreco(double preco) {
	    this.preco = preco;
	}
	
	@Override
    public String toString() {
        return " -----------------------------------------------------------------------------------------" + "\n" +
               "| Nome: " + produtoItemPedido.getNome() + "\n" +
               "| Descrição: " + produtoItemPedido.getDescricao() + "\n" +
               "| Quantidade: " + quantidade + "\n" +
               "| Preço unitário: " + produtoItemPedido.getEstoque().getPreco() + "\n" +
               "| Preço total: " + preco + "\n";
    }
}
