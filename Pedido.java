package pedidos;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import clientes.Cliente;

import java.time.format.DateTimeFormatter;

public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Cliente cliente;
	private int numero;
	private String situacao;
	private ArrayList<ItemPedido> itensDoPedido;
	private LocalDate dataPedido;
	private LocalDate dataEntrega;
	private double preco;

	public Pedido(Cliente cliente, int numero, String situacao, ArrayList<ItemPedido> itensDoPedido, LocalDate dataPedido, LocalDate dataEntrega, double preco) {
		this.numero = numero;
		this.situacao = situacao;
		this.cliente = cliente;
		this.itensDoPedido = itensDoPedido;
	    this.dataPedido = dataPedido;
	    this.dataEntrega = dataEntrega;
	    this.preco = preco;
	}
	
	public Pedido() {
	}
	
	public double getPreco() {
		return preco;
	}
	
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	public Cliente getCliente() {
	    return cliente;
	}
	public void setCliente(Cliente cliente) {
	    this.cliente = cliente;
	}
	
	public LocalDate getDataPedido() {
	    return dataPedido;
	}
	public void setDataPedido(LocalDate dataPedido) {
	    this.dataPedido = dataPedido;
	}
	
	public LocalDate getDataEntrega() {
	    return dataEntrega;
	}
	public void setDataEntrega(LocalDate dataEntrega) {
	    this.dataEntrega = dataEntrega;
	}

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public ArrayList<ItemPedido> getItensDoPedido() {
		return itensDoPedido;
	}
	public void setItensDoPedido(ArrayList<ItemPedido> itensDoPedido) {
		this.itensDoPedido= itensDoPedido;
	}
	
	public String formatarLocalDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(formatter);
    }
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append(" -----------------------------------------------------------------------------------------").append("\n");
	    sb.append("| Cliente: ").append(cliente.getNome()).append(" | ID: ").append(cliente.getID()).append("\n");
	    sb.append("| Número do Pedido: ").append(numero).append("\n");
	    sb.append("| Situação do Pedido: ").append(situacao).append("\n");
	    sb.append("| Data de realização do pedido: ").append(formatarLocalDate(dataPedido)).append("\n");
	    
	    if (dataEntrega != null) {
	        sb.append("| Data da entrega: ").append(formatarLocalDate(dataEntrega)).append("\n");
	    } else {
	        sb.append("| Data da entrega: Não informada").append("\n");
	    }
	    
	    sb.append("| Valor Total sem Impostos: ").append(String.format("R$ %.2f", preco)).append("\n");
	    sb.append("| Valor Total com Impostos: ").append(String.format("R$ %.2f", preco * 1.17)).append("\n");
	    sb.append(" -----------------------------------------------------------------------------------------").append("\n");
	    sb.append("| Lista de Itens: ");
	    
	    return sb.toString();
	}
}
