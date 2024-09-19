package principal;
import java.io.Serializable;

public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String rua;
    private String cidade;
    private String complemento;
    private String bairro;
    private String estado;
    private String cep;
    private int numero;

    public Endereco(String rua, int numero, String complemento, String bairro, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }
    public Endereco() {
    }

    public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getRua() {
        return rua;
    }
	public void setRua(String rua) {
        this.rua = rua;
    }
    
    public int getNumero() {
    	return numero;
    }
    public void setNumero(int numero) {
    	this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return rua + ", " + numero + ", " + complemento + ", " + bairro  + ", " + cidade + ", " + estado + ", " + cep;
    }
}