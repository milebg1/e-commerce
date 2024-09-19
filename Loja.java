package principal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import clientes.Cliente;
import pedidos.Pedido;
import produtos.Fornecedor;
import produtos.Produto;

public class Loja implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Produto> produtosLoja;
	private String produtos;
	private ArrayList<Fornecedor> fornecedoresLoja;
	private String fornecedores;
	private ArrayList<Cliente> clientesLoja;
	private String clientes;
	private ArrayList<Pedido> pedidosLoja;
	private String pedidos;
	
	public Loja(ArrayList<Produto> produtosLoja, ArrayList<Fornecedor> fornecedoresLoja, ArrayList<Cliente> clientesLoja, ArrayList<Pedido> pedidosLoja) {
		this.produtosLoja = produtosLoja;
		this.produtos = "C:produtos.ser";
		this.fornecedoresLoja = fornecedoresLoja;
		this.fornecedores = "C:fornecedores.ser";
		this.clientesLoja = clientesLoja;
		this.clientes = "C:clientes.ser";
		this.pedidosLoja = pedidosLoja;
		this.pedidos = "C:pedidos.ser";
	}

	public Loja() {
		this.fornecedoresLoja = new ArrayList<>();
		this.produtosLoja = new ArrayList<>();
		this.clientesLoja = new ArrayList<>();
		this.pedidosLoja = new ArrayList<>();
		this.pedidos = "C:pedidos.ser";
		this.clientes = "C:clientes.ser";
		this.fornecedores = "C:fornecedores.ser";
		this.produtos = "C:produtos.ser";
	}

	public String getProdutos() {
		return produtos;
	}
	public void setProdutos(String produtos) {
		this.produtos = produtos;
	}

	public String getFornecedores() {
		return fornecedores;
	}
	public void setFornecedores(String fornecedores) {
		this.fornecedores = fornecedores;
	}

	public String getClientes() {
		return clientes;
	}
	public void setClientes(String clientes) {
		this.clientes = clientes;
	}

	public String getPedidos() {
		return pedidos;
	}
	public void setPedidos(String pedidos) {
		this.pedidos = pedidos;
	}

	public ArrayList<Produto> getProdutosLoja() {
		return produtosLoja;
	}
	public void setProdutosLoja(ArrayList<Produto> produtos) {
		this.produtosLoja = produtos;
	}

	public ArrayList<Fornecedor> getFornecedoresLoja() {
		return fornecedoresLoja;
	}
	public void setFornecedoresLoja(ArrayList<Fornecedor> fornecedores) {
		this.fornecedoresLoja = fornecedores;
	}

	public ArrayList<Cliente> getClientesLoja() {
		return clientesLoja;
	}
	public void setClientesLoja(ArrayList<Cliente> clientes) {
		this.clientesLoja = clientes;
	}
	
	public ArrayList<Pedido> getPedidosLoja() {
		return pedidosLoja;
	}
	public void setPedidosLoja(ArrayList<Pedido> pedidosLoja) {
		this.pedidosLoja = pedidosLoja;
	}
	
	public void cadastroProdutoLoja(Produto produto) {
		produtosLoja.add(produto);
	}
	public void cadastroFornecedorLoja(Fornecedor fornecedor) {
		fornecedoresLoja.add(fornecedor);
	}
	public void cadastroClienteLoja(Cliente cliente) {
		clientesLoja.add(cliente);
	}
	public void cadastroPedidoLoja(Pedido pedido) {
		pedidosLoja.add(pedido);
	}
	
	public void removeProdutoLoja(Produto produto) {
		produtosLoja.remove(produto);
	}
	public void removeFornecedorLoja(Fornecedor fornecedor) {
		fornecedoresLoja.remove(fornecedor);
	}
	public void removeClienteLoja(Cliente cliente) {
		clientesLoja.remove(cliente);
	}
	public void removePedidoLoja(Pedido pedido) {
		pedidosLoja.remove(pedido);
	}
	
    public void gravarClientesArquivo() {
        try {
            File file = new File(this.getClientes());
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this.getClientesLoja());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gravarProdutosArquivo() {
        try {
            File file = new File(this.getProdutos());
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this.getProdutosLoja());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gravarFornecedoresArquivo() {
        try {
            File file = new File(this.getFornecedores());
            file.getParentFile().mkdirs();
            file.createNewFile();
            
            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this.getFornecedoresLoja());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void gravarPedidosArquivo() {
    	try {
            File file = new File(this.getPedidos());
            file.getParentFile().mkdirs();
            file.createNewFile();

            try (FileOutputStream fos = new FileOutputStream(file);
                 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(this.getPedidosLoja());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
	public ArrayList<Cliente> lerClientesArquivo() {
	    File file = new File(this.getClientes());

	    try {
	        file.createNewFile();
	    } catch (IOException io) {
	        io.printStackTrace();
	    }

	    if (!file.exists() || file.length() == 0) {
	        return new ArrayList<>();
	    }

	    ArrayList<Cliente> clientes = null;
	    try (FileInputStream fis = new FileInputStream(this.getClientes());
	         ObjectInputStream ois = new ObjectInputStream(fis)) {
	        Object obj = ois.readObject();
	        if (obj instanceof ArrayList<?>) {
	            clientes = (ArrayList<Cliente>) obj;
	        }
	    }  catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    if (clientes == null) {
	        clientes = new ArrayList<>();
	    }
	    this.setClientesLoja(clientes);
	    return clientes;
	}
    @SuppressWarnings("unchecked")
	public ArrayList<Produto> lerProdutosArquivo() {
	    File file = new File(this.getProdutos());

	    try {
	        file.createNewFile();
	    } catch (IOException io) {
	        io.printStackTrace();
	    }

	    if (!file.exists() || file.length() == 0) {
	        return new ArrayList<>();
	    }

	    ArrayList<Produto> produtos = null;
	    try (FileInputStream fis = new FileInputStream(this.getProdutos());
	         ObjectInputStream ois = new ObjectInputStream(fis)) {
	        Object obj = ois.readObject();
	        if (obj instanceof ArrayList<?>) {
	            produtos = (ArrayList<Produto>) obj;
	        }
	    }  catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    if (produtos == null) {
	        produtos = new ArrayList<>();
	    }
	    this.setProdutosLoja(produtos);
	    return produtos;
	}
    @SuppressWarnings("unchecked")
    public ArrayList<Fornecedor> lerFornecedoresArquivo() {
    	    File file = new File(this.getFornecedores());

    	    try {
    	        file.createNewFile();
    	    } catch (IOException io) {
    	        io.printStackTrace();
    	    }

    	    if (!file.exists() || file.length() == 0) {
    	        return new ArrayList<>();
    	    }

    	    ArrayList<Fornecedor> fornecedores = null;
    	    try (FileInputStream fis = new FileInputStream(this.getFornecedores());
    	         ObjectInputStream ois = new ObjectInputStream(fis)) {
    	        Object obj = ois.readObject();
    	        if (obj instanceof ArrayList<?>) {
    	            fornecedores = (ArrayList<Fornecedor>) obj;
    	        }
    	    }  catch (IOException | ClassNotFoundException e) {
    	        e.printStackTrace();
    	    }

    	    if (fornecedores == null) {
    	        fornecedores = new ArrayList<>();
    	    }
    	    this.setFornecedoresLoja(fornecedores);
    	    return fornecedores;
    	}
    @SuppressWarnings("unchecked")
	public ArrayList<Pedido> lerPedidosArquivo() {
	    File file = new File(this.getPedidos());

	    try {
	        file.createNewFile();
	    } catch (IOException io) {
	        io.printStackTrace();
	    }

	    if (!file.exists() || file.length() == 0) {
	        return new ArrayList<>();
	    }

	    ArrayList<Pedido> pedidos = null;
	    try (FileInputStream fis = new FileInputStream(this.getPedidos());
	         ObjectInputStream ois = new ObjectInputStream(fis)) {
	        Object obj = ois.readObject();
	        if (obj instanceof ArrayList<?>) {
	            pedidos = (ArrayList<Pedido>) obj;
	        }
	    }  catch (IOException | ClassNotFoundException e) {
	        e.printStackTrace();
	    }

	    if (pedidos == null) {
	        pedidos = new ArrayList<>();
	    }
	    this.setPedidosLoja(pedidos);
	    return pedidos;
	}
}
