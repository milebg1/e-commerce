package clientes;
import principal.Endereco;
import java.io.Serializable;
import java.util.ArrayList;
import pedidos.Pedido;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String ID;
    private String nome;
    private String telefone;
    private Endereco endereco;
    private String email;
    private String cartaoDeCredito;
    private Carrinho carrinho;
    private ArrayList<Pedido> pedidosCliente;

    public Cliente(String ID, String nome, String telefone, Endereco endereco, String email, String cartaoDeCredito, Carrinho carrinho, ArrayList<Pedido> pedidosCliente) {
        this.ID = ID;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.cartaoDeCredito = cartaoDeCredito;
        this.carrinho = carrinho;
        this.pedidosCliente = pedidosCliente;
    }
    public Cliente() {
    }
    
    public String getID() {
		return ID;
	}
    public void setID(String ID) {
		this.ID = ID;
	}

    public String getNome() {
		return nome;
	}
    public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getCartaoDeCredito() {
		return cartaoDeCredito;
	}
	public void setCartaoDeCredito(String cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}
	
	public Carrinho getCarrinho() {
	    return carrinho;
	}
    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }
    
	public ArrayList<Pedido> getPedidosCliente() {
		return pedidosCliente;
	}
	public void setPedidosCliente(ArrayList<Pedido> pedidosCliente) {
		this.pedidosCliente = pedidosCliente;
	}
    
	public void cadastroPedidoCliente(Pedido pedido) {
		pedidosCliente.add(pedido);
	}
	
	@Override
    public String toString() {
        return " -----------------------------------------------------------------------------------------" + "\n" +
               " | ID: " + ID + " | Nome: " + nome + "\n" +
               " | Endereco: " + endereco + "\n" +
               " | Email: " + email + "\n" +
               " | Cartao de Credito: " + cartaoDeCredito;
    }
}