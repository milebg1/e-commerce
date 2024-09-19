package clientes;
import java.io.Serializable;
import java.util.ArrayList;

import pedidos.ItemPedido;

public class Carrinho implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ArrayList <ItemPedido> itensCarrinho;
  
	public Carrinho (ArrayList <ItemPedido> itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	}
	
	public Carrinho () {
	}
  
	public ArrayList < ItemPedido > getItensCarrinho () {
		return itensCarrinho;
	}
	public void setItensCarrinho (ArrayList < ItemPedido > itensCarrinho) {
		this.itensCarrinho = itensCarrinho;
	} 
 
	public void adicionarItensCarrinho (ItemPedido itemCarrinho) {
		itensCarrinho.add(itemCarrinho);
	} 
	public void removerItensCarrinho (ItemPedido itemCarrinho)	{
		itensCarrinho.remove(itemCarrinho);
	} 
} 
