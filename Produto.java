package produtos;
import java.io.Serializable;

public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nome;
    private String descricao;
    private Estoque estoque;
    private Fornecedor fornecedor;
    private String ID;

    public Produto(String nome, String descricao, Estoque estoque, Fornecedor fornecedor, String ID) {
        this.nome = nome;
        this.descricao = descricao;
        this.estoque = estoque;
        this.fornecedor = fornecedor;
        this.ID = ID;
    }
    
    public Produto() {
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

	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

	@Override
    public String toString() {
        return  " -----------------------------------------------------------------------------------------" + "\n" +
                "| ID: " + ID + "\n" +
            	"| Nome: " + nome + "\n" +
                "| Descrição: " + descricao + "\n" +
                "| Fornecedor: " + fornecedor.getNome() + "\n" +
                estoque;
    }
}