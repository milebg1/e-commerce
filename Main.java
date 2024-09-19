package principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import clientes.Carrinho;
import clientes.Cliente;
import pedidos.ItemPedido;
import pedidos.Pedido;
import produtos.Estoque;
import produtos.Fornecedor;
import produtos.Produto;
import java.util.ArrayList;

public class Main {

    private static Scanner sc;
 
    public Main() {
    }
    
    static Loja loja = new Loja();

    public static void main(String[] args) {
        Main menu = new Main();
        menu.mostra();
    }
    
    public void mostra() {
        sc = new Scanner(System.in);
        loja.setClientesLoja(loja.lerClientesArquivo());
        loja.setFornecedoresLoja(loja.lerFornecedoresArquivo());
        loja.setProdutosLoja(loja.lerProdutosArquivo());
        loja.setPedidosLoja(loja.lerPedidosArquivo());
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|               Menu Principal               |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Entrar como Administrador              |");
            System.out.println("| 2 - Entrar como Cliente                    |");
            System.out.println("| 0 - Sair                                   |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
                
            switch (opcao) {
                case 1:
                    mostraMenuAdministrador();
                    break;
                case 2:
                    mostraMenuCliente();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida, digite novamente.");
            }
        } while (opcao != 0);
        sc.close();
    }
    
    private void mostraMenuCliente() {
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|             Entre/Cadastre-se              |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Fazer login                            |");
            System.out.println("| 2 - Registrar-se                           |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    System.out.println(" ____________________________________________");
                    System.out.println("|                   Login                    |");
                    System.out.println("|--------------------------------------------|");
                    System.out.println("| Informe seu ID:                            |");
                    System.out.println("|____________________________________________|");
                    String ID = sc.nextLine();
                    Cliente clienteUsuario = buscarClientePorID(ID);
                    if (clienteUsuario==null) {
                        break;
                    }
                    mostraMenuCliente(clienteUsuario);
                    break;
                case 2:
                    cadastrarCliente();
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
    }
    private void mostraMenuAdministrador() {
        int opcao = 0;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|             Menu Administrador             |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Gerenciar Fornecedores                 |");
            System.out.println("| 2 - Gerenciar Estoque                      |");
            System.out.println("| 3 - Gerenciar Clientes                     |");
            System.out.println("| 4 - Gerenciar Pedidos                      |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
        
            switch (opcao) {
                case 1:
                    mostraMenuAdministradorFornecedor();
                    break;
                case 2:
                    mostraMenuAdministradorEstoque();
                    break;
                case 3:
                    mostraMenuAdministradorCliente();
                    break;
                case 4:
                    mostraMenuAdministradorPedido();
                    break;
                case 0:
                    System.out.println(" Voltando ao menu principal...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao != 0);
    }
    private void mostraMenuCliente(Cliente clienteUsuario) {
        int opcao = 0;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|                Menu Cliente                |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Pesquisar Produtos                     |");
            System.out.println("| 2 - Carrinho                               |");
            System.out.println("| 3 - Pedidos                                |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            
            switch (opcao) {
                case 1:
                    pesquisarProdutos(clienteUsuario);
                    break;
                case 2:
                    mostraMenuCarrinhoCliente(clienteUsuario);
                    break;
                case 3:
                    pesquisarPedidosCliente(clienteUsuario);
                    break;
                case 0:
                    System.out.println(" Voltando ao menu principal...");
                    break;
                default:
                    System.out.println(" Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static int obterOpcao() {
        while (true) {
            try {
                int opcao = sc.nextInt();
                sc.nextLine(); 
                return opcao;
            } catch (InputMismatchException e) {
                System.out.println(" Entrada inválida! Por favor, insira um número.");
                sc.nextLine(); 
            }
        }
    }
    
    private static double obterDouble() {
    	while(true) {
    		try {
    			double opcao = sc.nextDouble();
    			sc.nextLine();
    			return opcao;
    		} catch(InputMismatchException e) {
    			System.out.println("Entrada Inválida! Por favor, insira um número.");
    			sc.nextLine();
    		}
    	}
    }
    
    private static LocalDate obterData() {
        System.out.println("| Informe o dia: ");
        int dia = obterOpcao();
        System.out.println("| Informe o mês: ");
        int mes = obterOpcao();
        System.out.println("| Informe o ano: ");
        int ano = obterOpcao();
        
        LocalDate data = LocalDate.of(ano, mes, dia);
        return data;
    }
    
    private static String formatLocalDate (LocalDate data) {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(dataFormatada);
    }

    private static Endereco criarEndereco(){
            System.out.println("| Rua: ");
            String rua = sc.nextLine();
            
            System.out.println("| Numero: ");
            int numero = obterOpcao();
            
            System.out.println("| Complemento: ");
            String complemento = sc.nextLine();
            
            System.out.println("| Bairro: ");
            String bairro = sc.nextLine();
            
            System.out.println("| Cidade: ");
            String cidade = sc.nextLine(); 
            
            System.out.println("| Estado: ");
            String estado = sc.nextLine();  
            
            System.out.println("| CEP: ");
            String cep = sc.nextLine();
            
            return new Endereco(rua, numero, complemento, bairro, cidade, estado, cep);
    }
    
    private static Produto buscarProdutoPorID() {
        System.out.println("| Informe o ID do produto: ");
        String ID = sc.nextLine();
        Produto produto = buscarProdutoPorID(ID);
        if(produto==null) {
        	System.out.println(" Nenhum produto encontrado com o ID informado.");
        	return null;
        } else {
        	return produto;
        }
    }
    private static Produto buscarProdutoPorID(String ID) {
        for (Produto produto : loja.getProdutosLoja()) {
            if(produto.getID().equals(ID)) {
                return produto;
            }
        }
        return null;
    }
    
    private static void buscarProdutoPorNome() {
        int cont = 0;
        System.out.println("| Informe o nome do produto: ");
        String nome = sc.nextLine().toLowerCase();
    	for (Produto produto : loja.getProdutosLoja()) {
    		if(produto.getNome().toLowerCase().contains(nome) || produto.getDescricao().toLowerCase().contains(nome)) {
    			System.out.println(produto);
    			cont++;
    		}
    	}
    	if(cont==0){
    	    System.out.println(" Nenhum produto encontrado com o nome informado."); 
    	}
    }
    private static int pesquisarProdutoPorNome(String nome) {
        int cont = 0;
        for (Produto produto : loja.getProdutosLoja()) {
            if (produto.getNome().toLowerCase().contains(nome.toLowerCase()) || produto.getDescricao().toLowerCase().contains(nome.toLowerCase())) {
                System.out.println(produto);
                if(produto.getEstoque().getQuantidade()<=0){
                    System.out.println(" Produto Indisponível.");
                }
                cont++;
            }
        }
        
        if(cont==0){
            System.out.println(" Nenhum produto encontrado com o nome informado!");
            return 0;
        }
        return 1;
    }
    
    private static Fornecedor buscarFornecedorPorID() {
        System.out.println("| ID do fornecedor: ");
        String ID = sc.nextLine();
        for (Fornecedor fornecedor : loja.getFornecedoresLoja()) {
        	if (fornecedor.getID().equals(ID)) {
        		return fornecedor;
        	}
        }
        System.out.println(" Nenhum fornecedor encontrado com o ID informado.");
        return null;
    }
    private static void buscarFornecedorPorNome() {
        System.out.println("| Informe o nome do fornecedor: ");
        String nome = sc.nextLine().toLowerCase();
        int cont = 0;
    	for (Fornecedor fornecedor : loja.getFornecedoresLoja()) {
    		if (fornecedor.getNome().toLowerCase().contains(nome)) {
    			System.out.println(fornecedor);
    			cont++;
    		}
    	}
    	if(cont==0){
    	    System.out.println(" Nenhum fornecedor encontrado com o nome informado.");
    	}
    }
    
    private static Cliente buscarClientePorID(String ID) {
        for (Cliente cliente : loja.getClientesLoja()) {
        	if (cliente.getID().equals(ID)) {
        		return cliente;
        	}
        }
        System.out.println(" Nenhum cliente encontrado com o ID informado.");
        return null;
    }
    private static void pesquisarClientePorNome(String nome) {
        int cont = 0;
        for (Cliente cliente : loja.getClientesLoja()) {
            if (cliente.getNome().toLowerCase().contains(nome.toLowerCase())) {
                System.out.println(cliente);
                cont++;
            }
        }
        if(cont==0){
            System.out.println(" Nenhum cliente encontrado com o nome informado!");
        }
    }
    
    private static Pedido buscarPedidoPorNumero() {
        System.out.println("| Informe o número do pedido: ");
        int numero = obterOpcao();
        for (Pedido pedido : loja.getPedidosLoja()) {
            if(pedido.getNumero()==numero) {
                System.out.println(pedido);
                for(ItemPedido itensPedido : pedido.getItensDoPedido()) {
                    System.out.println(itensPedido);
                }
                return pedido;
            }
        }
        System.out.println(" Nenhum pedido encontrado com o número informado.");
        return null;
    }
    private static Pedido buscarPedidoPorNumeroCliente(Cliente clienteUsuario) {
        System.out.println("| Informe o número do pedido: ");
        int numero = obterOpcao();
        
        for (Pedido pedido : clienteUsuario.getPedidosCliente()) {
        	if (pedido.getNumero()==numero) {
        		System.out.println(pedido);
        		for(ItemPedido itensPedido : pedido.getItensDoPedido()) {
                    System.out.println(itensPedido);
                }
        		return pedido;
        	}
        }
        System.out.println(" Nenhum pedido encontrado com o número informado.");
        return null;
    }
    private static void pesquisarPedidoPorData() {
        System.out.println("| Informe o periodode busca: ");
        System.out.println("| Início do periodo: ");
        LocalDate dataInicio = obterData();
        System.out.println("| Fim do periodo: ");
        LocalDate dataFinal = obterData();
        
        System.out.println("| Periodo de busca informado: " + formatLocalDate(dataInicio) + " até " + formatLocalDate(dataFinal) + ".");
        
        int cont = 0;
        for (Pedido pedido : loja.getPedidosLoja()) {
            if (pedido.getDataPedido().isAfter(dataInicio.minusDays(1)) && pedido.getDataPedido().isBefore(dataFinal.plusDays(1))){
                System.out.println(pedido);
                for(ItemPedido itensPedido : pedido.getItensDoPedido()) {
                    System.out.println(itensPedido);
                }
                cont++;
            }
        }
        if (cont==0) {
            System.out.println(" Nenhum pedido encontrado no periodo informado.");
        }
    }
    private static void pesquisarPedidoPorDataCliente(Cliente clienteUsuario){
        System.out.println("| Informe o periodode busca: ");
        System.out.println("| Início do periodo: ");
        LocalDate dataInicio = obterData();
        System.out.println("| Fim do periodo: ");
        LocalDate dataFinal = obterData();
        System.out.println("| Periodo de busca informado: " + formatLocalDate(dataInicio) + " até " + formatLocalDate(dataFinal) + ".");
        
        int cont = 0;
        for (Pedido pedido : clienteUsuario.getPedidosCliente()) {
            if (pedido.getDataPedido().isAfter(dataInicio.minusDays(1)) && pedido.getDataPedido().isBefore(dataFinal.plusDays(1))){
                System.out.println(pedido);
                for(ItemPedido itensPedido : pedido.getItensDoPedido()) {
                    System.out.println(itensPedido);
                }
                cont++;
            }
        }
        if (cont==0) {
            System.out.println(" Nenhum pedido encontrado no periodo informado.");
        }
    }

    private static void mostraMenuAdministradorEstoque() {
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|               Menu Estoque                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Cadastrar Produto                      |");
            System.out.println("| 2 - Adicionar Produto (Quantidade)         |");
            System.out.println("| 3 - Remover Produto (Quantidade)           |");
            System.out.println("| 4 - Alterar Produto                        |");
            System.out.println("| 5 - Excluir Produto                        |");
            System.out.println("| 6 - Consultar Produto                      |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            
            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    adicionarProdutoEstoque();
                    break;
                case 3:
                    removerProdutoEstoque();
                    break;
                case 4:
                    alterarProduto();
                    break;
                case 5:
                    excluirProduto();
                    break;
                case 6:
                	consultarProduto();
                	break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
    }
    private static void cadastrarProduto() {
        System.out.println(" ____________________________________________");
        System.out.println("|            Cadastro de Produto             |");
        System.out.println("|____________________________________________|");
        String ID;
        Produto produtoAdicionar = null;
        int quantidade = 0;
        double preco = 0;
        do {
            System.out.println("| Informe o ID do produto: ");
            ID = sc.nextLine();
            produtoAdicionar = buscarProdutoPorID(ID);
            if (produtoAdicionar != null) {
                System.out.println(" Produto já cadastrado com esse ID.");
            }    
        } while (produtoAdicionar!=null);
                
        System.out.println("| Nome: ");
        String nome = sc.nextLine();
                
        System.out.println("| Descrição: ");
        String descricao = sc.nextLine();
                
        Fornecedor fornecedor = buscarFornecedorPorID();
        if (fornecedor == null) {
            return; 
        }
        
        int i;
        do {
                System.out.println("| Deseja adicionar uma quantidade de produtos ao seu estoque? ");
                System.out.println("| 1 - Sim");
                System.out.println("| 2 - Não");
                System.out.println("| Escolha uma opção: ");
                i = obterOpcao();

                switch(i){
                    case 1:
                        System.out.print("| Informe quanto deseja adicionar: ");
                        quantidade = obterOpcao();
                        System.out.print("| Informe o valor a ser adicionado ao produto: ");
                        preco = obterDouble();
                        sc.nextLine();
                        i = 0;
                        break;
                    case 2:
                        quantidade = 0;
                        preco = 0;
                        i = 0;
                        break;
                    default:
                        System.out.println(" Opção inválida, digite novamente.");
                        break;
                }
        } while (i != 0);
        
        Estoque estoque = new Estoque(preco, quantidade);
        Produto produto = new Produto(nome, descricao, estoque, fornecedor, ID);
        loja.cadastroProdutoLoja(produto);
        fornecedor.cadastroProduto(produto);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Produto cadastrado com sucesso!");
    }
    private static void adicionarProdutoEstoque() {
        System.out.println(" ____________________________________________");
        System.out.println("|              Adicionar Produto             |");
        System.out.println("|____________________________________________|");
        
        Produto produtoAdicionar = buscarProdutoPorID();
        if (produtoAdicionar == null) {
            return;
        }
        
        System.out.println("| Informe a quantidade que deseja adicionar: ");
        int quantidadeAdicionar = obterOpcao();
        produtoAdicionar.getEstoque().adicionarQuantidadeProduto(quantidadeAdicionar);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Produto adicionado ao estoque.");
    }
    private static void removerProdutoEstoque() {
        System.out.println(" ____________________________________________");
        System.out.println("|               Remover Produto              |");
        System.out.println("|____________________________________________|");

        Produto produtoRemover = buscarProdutoPorID();
        if (produtoRemover == null) {
            return;
        }
        
        System.out.println("| Informe a quantidade que deseja remover: ");
        int quantidadeRemover = obterOpcao();
        if(produtoRemover.getEstoque().getQuantidade()<quantidadeRemover) {
            System.out.println(" Produtos insuficientes, a quantidade em estoque é: " + produtoRemover.getEstoque().getQuantidade());
            int opcao;
            do {
                System.out.println("| Deseja remover quantiade máxima deste produto em estoque? ");
                System.out.println("| 1 - Sim");
                System.out.println("| 2 - Não");
                System.out.println("| Escolha uma opção: ");
                opcao = obterOpcao();
                switch (opcao) {
                    case 1:
                        produtoRemover.getEstoque().setQuantidade(0);
                        break;
                    case 2:
                        System.out.println(" Voltando...");
                        break;
                    default:
                        System.out.println(" Opção inválida, digite novamente.");
                        break;
                }
            } while (opcao!=1 && opcao!=2);
        } else {
            produtoRemover.getEstoque().removerQuantidadeProduto(quantidadeRemover);
            System.out.println("| Produto removido!");
        } 
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    private static void alterarProduto() {
        System.out.println(" ____________________________________________");
        System.out.println("|               Alterar Produto              |");
        System.out.println("|____________________________________________|");
        
        Produto produtoAlterar = buscarProdutoPorID();
        if (produtoAlterar == null) {
            return;
        }
        
        System.out.println(produtoAlterar);
        
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("| Informe o que deseja alterar               |");
            System.out.println("| 1 - Nome                                   |");
            System.out.println("| 2 - Descrição                              |");
            System.out.println("| 3 - Preço                                  |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            
            switch (opcao) {
                case 1:
                    System.out.println("| Atual: " + produtoAlterar.getNome() + " - Novo Nome: ");
                    String novoNome = sc.nextLine();
                    produtoAlterar.setNome(novoNome);
                    break;
                case 2:
                    System.out.println("| Atual: " + produtoAlterar.getDescricao() + " - Nova Descrição: ");
                    String novaDescricao = sc.nextLine();
                    produtoAlterar.setDescricao(novaDescricao);
                    break;
                case 3:
                    System.out.println("| Atual: " + produtoAlterar.getEstoque().getPreco() + " - Novo Preço: ");
                    double novoPreco = obterDouble();
                    sc.nextLine();
                    produtoAlterar.getEstoque().setPreco(novoPreco);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Alteração confirmada.");
    }
    private static void excluirProduto() {
        System.out.println(" ____________________________________________");
        System.out.println("|               Excluir Produto              |");
        System.out.println("|____________________________________________|");
        
        Produto produtoExcluir = buscarProdutoPorID();
        if (produtoExcluir == null) {
            return;
        }
        
        produtoExcluir.getFornecedor().removeProduto(produtoExcluir);
        loja.removeProdutoLoja(produtoExcluir);
        
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Produto excluído com sucesso!");
    }
    private static void consultarProduto() {
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|            Consulta de Produtos            |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Consultar por ID                       |");
            System.out.println("| 2 - Consultar por Nome                     |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    Produto produtoConsultaID = buscarProdutoPorID();
                    if (produtoConsultaID == null) {
                        break;
                    }
                    System.out.println(produtoConsultaID);
                    break;
                case 2:
                    buscarProdutoPorNome();
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            } 
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    
    private static void mostraMenuAdministradorFornecedor() {
        int opcao = 0;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|           Gerenciar Fornecedores           |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Cadastrar Fornecedor                   |");
            System.out.println("| 2 - Alterar Fornecedor                     |");
            System.out.println("| 3 - Excluir Fornecedor                     |");
            System.out.println("| 4 - Consultar Fornecedor                   |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            
            switch (opcao) {
                case 1:
                    cadastrarFornecedor();
                    break;
                case 2:
                    alterarFornecedor();
                    break;
                case 3:
                    excluirFornecedor();
                    break;
                case 4:
                    consultarFornecedor();
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
            }
        } while (opcao != 0);
    }
    private static void cadastrarFornecedor() {
        System.out.println(" ____________________________________________");
        System.out.println("|           Cadastro de Fornecedor           |");
        System.out.println("|____________________________________________|");       
        String ID;
        int opcao;
        do {
            opcao = 0;
            System.out.println("| ID: ");
            ID = sc.nextLine();
            if(loja.getFornecedoresLoja()!=null) {
            	for (Fornecedor fornecedor : loja.getFornecedoresLoja()) {
            		if(fornecedor.getID().equals(ID)) {
            			System.out.println(" ID já cadastrado para outro fornecedor!");
            			opcao++;
            		}
            	}
            }
        } while (opcao!=0);
        
        System.out.println("| Nome: ");
        String nome = sc.nextLine(); 
        
        System.out.println("| Descrição: ");
        String descricao = sc.nextLine(); 
        
        System.out.println("| Telefone: ");
        String telefone = sc.nextLine();  
        
        System.out.println("| E-mail: ");
        String email = sc.nextLine();   
        
        Endereco endereco = criarEndereco(); 
        
        ArrayList<Produto> produtosFornecedor = new ArrayList<>();
        Fornecedor fornecedor = new Fornecedor(ID, nome, descricao, telefone, email, endereco, produtosFornecedor);
        
        do{
            System.out.println(fornecedor);
            System.out.println(" ----------------------------------------------------------------------------------------");
            System.out.println("| Confirmar o cadastro: ");
            System.out.println("| 1 - Sim ");
            System.out.println("| 2 - Não ");
            opcao = obterOpcao();
            if (opcao==1) {
                opcao = 0;
            } else if (opcao==2) {
                System.out.println(" Voltando...");   
                return;
            } else {
                System.out.println(" Opção inválida, digite novamente.");
            }
        } while (opcao!=0);
        
        loja.cadastroFornecedorLoja(fornecedor);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Fornecedor cadastrado com sucesso!");
    }
    private static void alterarFornecedor() {
        System.out.println(" ____________________________________________");
        System.out.println("|             Alterar Fornecedor             |");
        System.out.println("|____________________________________________|"); 
        
        Fornecedor fornecedor = buscarFornecedorPorID();
        if (fornecedor == null) {
            return;
        }
        
        int opcao;
        System.out.println(fornecedor);
        do {
            System.out.println(" ____________________________________________");
            System.out.println("| Informe o que deseja alterar               |");
            System.out.println("| 1 - Nome                                   |");
            System.out.println("| 2 - Descrição                              |");
            System.out.println("| 3 - Telefone                               |");
            System.out.println("| 4 - E-mail                                 |");
            System.out.println("| 5 - Endereço                               |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            
            switch (opcao) {
                case 1:
                    System.out.println("| Atual: " + fornecedor.getNome() + " - Novo Nome: ");
                    String novoNome = sc.nextLine();
                    fornecedor.setNome(novoNome);
                    break;
                case 2:
                    System.out.println("| Atual: " + fornecedor.getDescricao() + " - Nova Descrição: ");
                    String novaDescricao = sc.nextLine();
                    fornecedor.setDescricao(novaDescricao);
                    break;
                case 3:
                    System.out.println("| Atual: " + fornecedor.getTelefone() + " - Novo telefone: ");
                    String novoTelefone = sc.nextLine();
                    fornecedor.setTelefone(novoTelefone);
                    break;
                case 4:
                    System.out.println("| Atual: " + fornecedor.getEmail() + " - Novo e-mail: ");
                    String novoEmail = sc.nextLine();
                    fornecedor.setEmail(novoEmail);
                    break;
                case 5:
                    Endereco novoEndereco = criarEndereco();
                    fornecedor.setEndereco(novoEndereco);
                    break;
                case 0:
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Alteração confirmada!");
    }
    private static void excluirFornecedor() {
        System.out.println(" ____________________________________________");
        System.out.println("|             Excluir Fornecedor             |");
        System.out.println("|____________________________________________|");
        
        Fornecedor fornecedor = buscarFornecedorPorID();
        if (fornecedor == null) {
            return;
        }
        
        if(!fornecedor.getProdutosFornecedor().isEmpty()) {
        	System.out.println(" Este fornecedor possui produtos cadastrados. Não é possível excluí-lo.");
        	return;
        }
        
        loja.removeFornecedorLoja(fornecedor);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Fornecedor removido!");
    }
    private static void consultarFornecedor() {
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|          Consulta de Fornecedores          |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Consultar por ID                       |");
            System.out.println("| 2 - Consultar por Nome                     |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    Fornecedor fornecedorConsultaID = buscarFornecedorPorID();
                    if (fornecedorConsultaID == null) {
                        break;
                    }
                    System.out.println(fornecedorConsultaID);
                    break;
                case 2:
                    buscarFornecedorPorNome();
                    break;
                case 0:
                    System.out.println(" Voltando ...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    
    private static void mostraMenuAdministradorCliente () {
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|             Gerenciar Clientes             |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Cadastrar Cliente                      |");
            System.out.println("| 2 - Alterar Cliente                        |");
            System.out.println("| 3 - Excluir Cliente                        |");
            System.out.println("| 4 - Consultar Cliente                      |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            
            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    alterarCliente();
                    break;
                case 3:
                    excluirCliente();
                    break;
                case 4:
                    consultarCliente();
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    return;
                default:
                    System.out.println(" Opção inválida, digite novamente!");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    private static void cadastrarCliente() {
        System.out.println(" ____________________________________________");
        System.out.println("|            Cadastro de Clientes            |");
        System.out.println("|____________________________________________|"); 
        
        int opcao;
        String ID;
        do {
            System.out.println("| Informe o ID que deseja criar: ");
            ID = sc.nextLine();
            opcao = 1;
            if (loja.getClientesLoja() != null && !loja.getClientesLoja().isEmpty()) {
                for (Cliente cliente : loja.getClientesLoja()) {
                    if (cliente.getID().equals(ID)) {
                        System.out.println("ID já cadastrado para outro cliente!");
                        opcao = 1;
                        break;
                    } else {
                        opcao = 0;
                    }
                }
            } else {
                opcao = 0;
            }
        } while (opcao != 0);
        
        System.out.print("| Nome: ");
        String nome = sc.nextLine();  
        
        System.out.println("| Telefone: ");
        String telefone = sc.nextLine();  
        
        Endereco endereco = criarEndereco(); 
        
        System.out.print("| Email: ");
        String email = sc.nextLine();   
        
        System.out.print("| Cartao de Credito: ");
        String cartaoDeCredito = sc.nextLine();  
        
        ArrayList<ItemPedido> itensCarrinho = new ArrayList<>();
        Carrinho carrinho = new Carrinho(itensCarrinho);
        ArrayList<Pedido> pedidosCliente = new ArrayList<>();
        Cliente novoCliente = new Cliente(ID, nome, telefone, endereco, email, cartaoDeCredito, carrinho, pedidosCliente);
        
        do{
            System.out.println(novoCliente);
            System.out.println("| Confirmar o cadastro: ");
            System.out.println("| 1 - Sim ");
            System.out.println("| 2 - Não ");
            opcao = obterOpcao();
            if (opcao==1) {
                opcao = 0;
            } else if (opcao==2) {
                System.out.println(" Voltando...");   
                return;
            } else {
                System.out.println(" Opção inválida, digite novamente.");
            }
        } while (opcao!=0);
        
        loja.cadastroClienteLoja(novoCliente);      
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Cliente cadastrado com sucesso!");
    }
    private static void alterarCliente() {
        System.out.println(" ____________________________________________");
        System.out.println("|              Alterar Clientes              |");
        System.out.println("|____________________________________________|");  
        
        System.out.println("| ID do Cliente");
        String ID = sc.nextLine();
        Cliente cliente = buscarClientePorID(ID);
        if (cliente == null) {
            return;
        }
        
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("| Informe o que deseja alterar               |");
            System.out.println("| 1 - Nome                                   |");
            System.out.println("| 2 - Telefone                               |");
            System.out.println("| 3 - E-mail                                 |");
            System.out.println("| 4 - Cartão de crédito                      |");
            System.out.println("| 5 - Endereço                               |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            
            switch (opcao) {
                case 1:
                    System.out.println("| Atual: " + cliente.getNome() + " - Novo Nome: ");
                    String novoNome = sc.nextLine();
                    cliente.setNome(novoNome);
                    break;
                case 2:
                    System.out.println("| Atual: " + cliente.getTelefone() + " - Novo Telefone: ");
                    String novoTelefone = sc.nextLine();
                    cliente.setTelefone(novoTelefone);
                    break;
                case 3:
                    System.out.println("| Atual: " + cliente.getEmail() + " - Novo E-mail: ");
                    String novoEmail = sc.nextLine();
                    cliente.setEmail(novoEmail);
                    break;
                case 4:
                    System.out.println("| Atual: " + cliente.getCartaoDeCredito() + " - Novo Cartão de crédito: ");
                    String novoCartaoDeCredito = sc.nextLine();
                    cliente.setCartaoDeCredito(novoCartaoDeCredito);
                    break;
                case 5:
                    Endereco novoEndereco = criarEndereco();
                    cliente.setEndereco(novoEndereco);
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida,digite novamente.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("| Alteração confirmada!");
    }
    private static void excluirCliente() {
        System.out.println(" ____________________________________________");
        System.out.println("|              Excluir Clientes              |");
        System.out.println("|____________________________________________|");
        System.out.println("| Informe o ID do Cliente: ");
        String idCliente = sc.nextLine();
        Cliente cliente = buscarClientePorID(idCliente);
        if (cliente == null) {
            return;
        }
        
        loja.removeClienteLoja(cliente);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
        System.out.println("Cliente removido!");
    }
    private static void consultarCliente() {
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|            Consulta de Clientes            |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Consultar por ID                       |");
            System.out.println("| 2 - Consultar por Nome                     |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    System.out.println("| ID do Cliente: ");
                    String idClienteConsulta = sc.nextLine();
                    Cliente clienteConsultaID = buscarClientePorID(idClienteConsulta);
                    if (clienteConsultaID == null) {
                    	break;
                    }
                    System.out.println(clienteConsultaID);
                    break;
                case 2:
                    System.out.println("| Nome do Cliente: ");
                    String nomeClienteConsulta = sc.nextLine();
                    pesquisarClientePorNome(nomeClienteConsulta);
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    
    private static void pesquisarProdutos(Cliente clienteUsuario) {
        int opcao;
        Produto produtoAdicionar = null;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|            Pesquisa de Produtos            |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Pesquisar por ID                       |");
            System.out.println("| 2 - Pesquisar por Nome                     |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                	produtoAdicionar = buscarProdutoPorID();
                    if(produtoAdicionar==null){
                        return;
                    }
                    System.out.println(produtoAdicionar);
                    if(produtoAdicionar.getEstoque().getQuantidade()<=0) {
                        System.out.println(" Produto indisponível.");
                    }
                    opcao = 0;
                    break;
                case 2:
                    System.out.println("| Informe o nome do Produto: ");
                    String nome = sc.nextLine();
                    if(pesquisarProdutoPorNome(nome)==0){
                        return;
                    }
                    opcao = 0;
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    return;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        do {
            System.out.println("| Deseja adicionar algum produto ao carrinho?");
            System.out.println("| 1 - Sim");
            System.out.println("| 2 - Não");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    if (produtoAdicionar == null) {
                        System.out.println("| Informe o ID do produto a ser adicionado: ");
                        String IDAdicionarCarrinho = sc.nextLine();
                        produtoAdicionar = buscarProdutoPorID(IDAdicionarCarrinho);
                    }
                    
                    if(produtoAdicionar!=null){
                        if(produtoAdicionar.getEstoque().getQuantidade()==0) {
                            System.out.println(" Produto indisponível.");
                            System.out.println(" Voltando");
                            return;
                        }
                        System.out.println("| Produto selecionado: ");
                        System.out.println(produtoAdicionar);
                        System.out.println("| Informe a quantidade a ser adicionada ao carrinho: ");
                        int quantidadeItemPedido = obterOpcao();
                        double precoItemPedido;
                        
                        if(produtoAdicionar.getEstoque().getQuantidade()<quantidadeItemPedido){
                            System.out.println("| Quantidade em estoque insuficiente!"); 
                            int opcao1;
                            do {
                                System.out.println("| Deseja adicionar a quantidade máxima disponível no estoque (" + produtoAdicionar.getEstoque().getQuantidade() + ")?");
                                System.out.println("| 1 - Sim");
                                System.out.println("| 2 - Não");
                                System.out.println("| Escolha uma opção: ");
                                opcao1 = obterOpcao();
                                    
                                switch (opcao1) {
                                    case 1:
                                    	int opcao2;
                                        do {
                                            quantidadeItemPedido = produtoAdicionar.getEstoque().getQuantidade();
                                            precoItemPedido = quantidadeItemPedido * produtoAdicionar.getEstoque().getPreco();
                                            System.out.println("| Quantidade a ser adicionada: " + quantidadeItemPedido);
                                            System.out.println("| Valor total dos produtos: " + precoItemPedido);
                                            System.out.println("| Deseja realmente adicionar ao carrinho?");
                                            System.out.println("| 1 - Sim");
                                            System.out.println("| 2 - Não");
                                            System.out.println("| Escolha uma opção: ");
                                            opcao2 = obterOpcao();
                                            switch (opcao2) {
                                                case 1:
                                                    ItemPedido itemPedido = new ItemPedido(quantidadeItemPedido, precoItemPedido, produtoAdicionar);
                                                    clienteUsuario.getCarrinho().adicionarItensCarrinho(itemPedido);
                                                    System.out.println("| Atenção! Adicionar o produto ao Carrinho não garante a reserva do mesmo!");
                                                    System.out.println("| Caso esse produto esgote, você não consiguirá finalizar a compra!");
                                                    opcao2 = 2;
                                                    break;
                                                case 2:
                                                    System.out.println(" Voltando...");
                                                    return;
                                                default:
                                                    System.out.println(" Opção inválida, digite novamente.");
                                                    break;
                                            }
                                        } while (opcao2!=2);
                                        opcao1 = 2;
                                        break;
                                    case 2:
                                        System.out.println(" Voltando...");
                                        return;
                                    default:
                                        System.out.println(" Opção inválida, digite novamente.");
                                        break;
                                }
                            } while (opcao1!=2);
                        } else {
                            precoItemPedido = quantidadeItemPedido * produtoAdicionar.getEstoque().getPreco();
                            int opcao1;
                            do {
                                System.out.println("| Quantidade a ser adicionada: " + quantidadeItemPedido);
                                System.out.println("| Valor total dos produtos: " + precoItemPedido);
                                System.out.println("| Deseja realmente adicionar ao carrinho?");
                                System.out.println("| 1 - Sim");
                                System.out.println("| 2 - Não");
                                System.out.println("| Escolha uma opção: ");
                                opcao1 = obterOpcao();
                                switch (opcao1) {
                                    case 1:
                                        ItemPedido itemPedido = new ItemPedido(quantidadeItemPedido, precoItemPedido, produtoAdicionar);
                                        clienteUsuario.getCarrinho().adicionarItensCarrinho(itemPedido);
                                        System.out.println("| Atenção! Adicionar o produto ao Carrinho não garante a reserva do mesmo!");
                                        System.out.println("| Caso esse produto esgote, você não consiguirá finalizar a compra!");
                                        opcao1 = 2;
                                        break;
                                    case 2:
                                        System.out.println(" Voltando...");
                                        return;
                                    default:
                                        System.out.println(" Opção inválida, digite novamente.");
                                        break;
                                }
                            } while (opcao1 != 2);
                        }
                    } else {
                        System.out.println(" Nenhum produto selecionado ou código inválido.");
                        return;
                    }
                    break;
                case 2:
                    System.out.println(" Voltando...");
                    return;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    private static void mostraMenuCarrinhoCliente(Cliente clienteUsuario) {
        System.out.println(" ____________________________________________");
        System.out.println("|                  Carrinho                  |");
        System.out.println("|____________________________________________|");
        
        if(clienteUsuario.getCarrinho().getItensCarrinho().isEmpty()) {
            System.out.println(" O carrinho está vazio.");
            return;
        }
        
        int quantidade;
        double preco;
        
        ArrayList<ItemPedido> itens = clienteUsuario.getCarrinho().getItensCarrinho();
        int cont1 = 0;
        int contdup = 0;
        
        while (cont1 < itens.size()) {
            ItemPedido itemAtual = itens.get(cont1);
            int cont2 = cont1 + 1;
            
            while (cont2 < itens.size()) {
                ItemPedido itemComparado = itens.get(cont2);
                
                if (itemAtual.getProdutoItemPedido().equals(itemComparado.getProdutoItemPedido())) {
                    itemAtual.setQuantidade(itemAtual.getQuantidade() + itemComparado.getQuantidade());
                    itens.remove(cont2);
                    contdup++;
                } else {
                    cont2++;
                }
            }
            cont1++;
        }
        if (contdup>0) {
            System.out.println(" Produtos com multiplos itens no carrinho foram transformados em apenas 1 item.");
        }
        clienteUsuario.getCarrinho().setItensCarrinho(itens);
        
        
        for (ItemPedido itensCarrinho : clienteUsuario.getCarrinho().getItensCarrinho()) {
            if(itensCarrinho.getQuantidade()==0) {
                System.out.println(itensCarrinho);
                System.out.println(" Produto esgotado!");
                System.out.println(" Removendo...");
                clienteUsuario.getCarrinho().removerItensCarrinho(itensCarrinho);
            } else if (itensCarrinho.getQuantidade()>itensCarrinho.getProdutoItemPedido().getEstoque().getQuantidade()){
                System.out.println(itensCarrinho);
                System.out.println(" Produtos insuficientes.");
                System.out.println(" Quantidade ajustada ao máximo de produtos em estoque.");
                quantidade = itensCarrinho.getProdutoItemPedido().getEstoque().getQuantidade();
                itensCarrinho.setQuantidade(quantidade);
                preco = itensCarrinho.getProdutoItemPedido().getEstoque().getPreco();
                itensCarrinho.setPreco(quantidade*preco);
            } 
        }
        
        System.out.println(" ____________________________________________");
        System.out.println("|             Carrinho Atualizado            |");
        System.out.println("|____________________________________________|");
        
        double valorFinal = 0;
        for(ItemPedido itensCarrinho : clienteUsuario.getCarrinho().getItensCarrinho()) {
            System.out.println(itensCarrinho);
            valorFinal += (itensCarrinho.getPreco());
        }
        
        System.out.println(String.format("| Valor total dos produtos: R$ %.2f", valorFinal));
        System.out.println(String.format("| Impostos: R$ %.2f", (valorFinal*0.17)));
        System.out.println(String.format("| Valor total do pedido: R$ %.2f", (valorFinal*1.17)));
        System.out.println("| Método de pagamento: Cartão de crédito.");
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|                Menu Carrinho               |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Finalizar Pedido                       |");
            System.out.println("| 2 - Remover Produtos                       |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    System.out.println("| Finalizando Pedido: ");
                    int tamanho = loja.getPedidosLoja().size();
                    int numeroPedido = (1+tamanho);
                    String situacao = "Novo";
                    LocalDate dataPedido = LocalDate.now();
                    ArrayList<ItemPedido> itensDoPedido = new ArrayList<ItemPedido>(clienteUsuario.getCarrinho().getItensCarrinho());
                    valorFinal = 0;
                    for(ItemPedido itensCarrinho : clienteUsuario.getCarrinho().getItensCarrinho()) {
                        valorFinal += (itensCarrinho.getPreco());
                    }
                    Pedido pedido = new Pedido(clienteUsuario, numeroPedido, situacao, itensDoPedido, dataPedido, null, valorFinal);
                    loja.cadastroPedidoLoja(pedido);
                    clienteUsuario.cadastroPedidoCliente(pedido);
                    for (ItemPedido itemPedidoRemover : clienteUsuario.getCarrinho().getItensCarrinho()){
                    	itemPedidoRemover.getProdutoItemPedido().getEstoque().setQuantidade(itemPedidoRemover.getProdutoItemPedido().getEstoque().getQuantidade()-itemPedidoRemover.getQuantidade());
                    }
                    clienteUsuario.getCarrinho().getItensCarrinho().removeAll(itensDoPedido);
                    System.out.println("| Aguardando pagamento...");
                    System.out.println("| Confirmando pagamento...");
                    System.out.println("| Pagamento confirmado.");
                    System.out.println("| Estamos preparando seu pedido...");
                    opcao = 0;
                    break;
                case 2:
                	ItemPedido itensCarrinho1 = null;
                    Produto produtoRemover = buscarProdutoPorID();
	                for(ItemPedido itensCarrinho : clienteUsuario.getCarrinho().getItensCarrinho()) {
	                    if(itensCarrinho.getProdutoItemPedido().equals(produtoRemover)) {
	                        itensCarrinho1 = itensCarrinho;
	                    }
	                }
                    clienteUsuario.getCarrinho().removerItensCarrinho(itensCarrinho1);
                    System.out.println("| Produto removido.");
                    opcao = 0;
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    private static void pesquisarPedidosCliente(Cliente clienteUsuario) {
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|             Pesquisa de Pedidos            |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Pesquisar pelo Número                  |");
            System.out.println("| 2 - Pesquisar pela Data de Realização      |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            
            switch (opcao) {
                case 1:
                    buscarPedidoPorNumeroCliente(clienteUsuario);
                    break;
                case 2:
                    pesquisarPedidoPorDataCliente(clienteUsuario);
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    
    private static void mostraMenuAdministradorPedido() {
        int opcao;
        int opcaoDupla;
        int opcao1;
        Pedido pedido = null;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|                Menu Pedidos                |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Consultar Pedido                       |");
            System.out.println("| 2 - Alterar Entrega                        |");
            System.out.println("| 3 - Alterar Situação                       |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    consultarPedido();
                    break;
                case 2:
                    pedido = buscarPedidoPorNumero();
                    if(pedido==null) {
                        return;
                    }
                    do {
                        System.out.println("| Deseja alterar a data de entrega? ");
                        System.out.println("| 1 - Sim");
                        System.out.println("| 2 - Não");
                        System.out.println("| Escolha uma opção: ");
                        opcao1 = obterOpcao();
                        
                        switch (opcao1) {
                            case 1:
                                pedido.setDataEntrega(obterData());
                                opcao1 = 2;
                                break;
                            case 2:
                                System.out.println(" Voltando...");
                                break;
                            default:
                                System.out.println(" Opção inválida, digite novamente.");
                                break;
                        }
                    } while (opcao1!=2);
                    System.out.println("| Pedido alterado com sucesso.");
                    break;
                case 3:
                    pedido = buscarPedidoPorNumero();
                    if(pedido==null) {
                        return;
                    }
                    do {
                        System.out.println("| Deseja alterar o status do Pedido?");
                        System.out.println("| 1 - Sim");
                        System.out.println("| 2 - Não");
                        System.out.println("| Escolha uma opção: ");
                        opcao1 = obterOpcao();
                        switch (opcao1) {
                            case 1:
                                do {
                                    System.out.println("| Informe o novo status: ");
                                    System.out.println("| 1 - Novo");
                                    System.out.println("| 2 - Entregue");
                                    System.out.println("| 3 - Cancelado");
                                    System.out.println("| Escolha uma opção: ");
                                    opcaoDupla = obterOpcao();
                                    switch (opcaoDupla) {
                                        case 1:
                                            pedido.setSituacao("Novo");
                                            opcaoDupla = 0;
                                            break;
                                        case 2:
                                            pedido.setSituacao("Entregue");
                                            opcaoDupla = 0;
                                            break;
                                        case 3:
                                            pedido.setSituacao("Cancelado");
                                            opcaoDupla = 0;
                                            break;
                                        case 0:
                                            System.out.println(" Voltando...");
                                            break;
                                        default:
                                            System.out.println(" Opção inválida, digite novamente.");
                                            break;
                                    }
                                } while (opcaoDupla!=0);
                                opcao1 = 2;
                                break;
                            case 2:
                                System.out.println(" Voltando...");
                                break;
                            default:
                                System.out.println(" Opção inválida, digite novamente.");
                                break;
                        }
                    } while (opcao1!=2);
                    System.out.println("| Pedido alterado com sucesso.");
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opção inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }
    private static void consultarPedido() {
        int opcao;
        do {
            System.out.println(" ____________________________________________");
            System.out.println("|              Consultar Pedido              |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| 1 - Consultar pelo Número                  |");
            System.out.println("| 2 - Consultar pela Data de Realização      |");
            System.out.println("| 0 - Voltar                                 |");
            System.out.println("|--------------------------------------------|");
            System.out.println("| Escolha uma opção:                         |");
            System.out.println("|____________________________________________|");
            opcao = obterOpcao();
            switch (opcao) {
                case 1:
                    buscarPedidoPorNumero();
                    break;
                case 2:
                    pesquisarPedidoPorData();
                    break;
                case 0:
                    System.out.println(" Voltando...");
                    break;
                default:
                    System.out.println(" Opcão inválida, digite novamente.");
                    break;
            }
        } while (opcao!=0);
        loja.gravarFornecedoresArquivo();
        loja.gravarClientesArquivo();
        loja.gravarProdutosArquivo();
        loja.gravarPedidosArquivo();
    }

}