package produtos;
import principal.Endereco;

import java.io.Serializable;
import java.util.ArrayList;

public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
		private String nome;
    	private String descricao;
    	private String telefone;
    	private String email;
    	private Endereco endereco;
    	private String ID;
    	private ArrayList<Produto> produtosFornecedor;
    
    	public Fornecedor( String ID, String nome, String descricao, String telefone, String email, Endereco endereco, ArrayList<Produto> produtosFornecedor) {
    		this.ID = ID;
        	this.nome = nome;
        	this.descricao = descricao;
        	this.telefone = telefone;
        	this.email = email;
        	this.endereco = endereco;
        	this.produtosFornecedor = produtosFornecedor;
    	}
    
    	public Fornecedor() {
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

	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<Produto> getProdutosFornecedor() {
		return produtosFornecedor;
	}
	public void setProdutosFornecedor(ArrayList<Produto> produtosFornecedor) {
		this.produtosFornecedor = produtosFornecedor;
	}
	
	public void cadastroProduto(Produto produtoFornecedor) {
		produtosFornecedor.add(produtoFornecedor);
	}
	public void removeProduto(Produto produtoFornecedor) {
	    produtosFornecedor.remove(produtoFornecedor);
	}
	
	@Override
	public String toString() {
		return  " -----------------------------------------------------------------------------------------" + "\n" +
				"| ID: " + ID + "| Fornecedor: " + nome + " \n" +
				"| Descrição: " + descricao + "\n" +
				"| Telefone: " + telefone + "\n" +
				"| E-mail: " + email + "\n" +
				"| Endereço: " + endereco;
	}
}