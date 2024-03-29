package br.com.agftec.entidades;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class NotaFactory {

	private double valorMeioPagamento;
	private Venda venda;
	private Transportadora transportadora;

	private int prazoPagamento;
	private int parcelas;

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	public int getPrazoPagamento() {
		return prazoPagamento;
	}

	public void setPrazoPagamento(int prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public List<Nota> getNotas() {

		List<Nota> lst = new ArrayList<Nota>();

		if (this.venda != null) {

			if (this.venda.getProdutos().stream().filter(p -> p.getQuantidade() > 0).count() == 0) {

				throw new RuntimeException("A venda esta sem produtos");

			}

			Nota nota = new Nota();

			nota.setData_emissao(Calendar.getInstance());
			nota.setData_saida_entrada(Calendar.getInstance());
			nota.setDestinatario(this.venda.getCliente());
			nota.setEmitente(venda.getEmpresa().getPj());
			nota.setEmpresa(this.venda.getEmpresa());
			nota.setModelo(ModeloNota.NFCE);

			OperacaoLogistica op = OperacaoLogistica.VENDA_DENTRO_ESTADO;

			if (this.venda.getCliente() != null) {

				if (this.venda.getCliente().getEndereco().getCidade().getEstado().getId() != this.venda.getEmpresa()
						.getPj().getEndereco().getCidade().getEstado().getId()) {

					op = OperacaoLogistica.VENDA_FORA_ESTADO;

				}

			}

			nota.setNatureza_operacao(
					this.venda.getProdutos().stream().filter(p -> p.getQuantidade() > 0).collect(Collectors.toList())
							.get(0).getProduto().getCategoria().getTabelaCfop().getCFOP(op).getDescricao());

			if (this.valorMeioPagamento == 0) {

				throw new RuntimeException("Pagamento no dinheiro, mas nao informado o valor do meio de pagamento");

			}

			nota.setValorMeioDePagamento(this.valorMeioPagamento);

			nota.setObservacoes("Nota emitida referente a venda da empresa " + venda.getEmpresa().getPj().getNome());

			nota.setOperacao(SaidaEntrada.SAIDA);

			nota.setStatus(StatusNota.ATIVA);

			nota.setTipo(TipoNota.NORMAL);

			nota.setTransportadora(this.transportadora);

			this.parcelas = Math.max(1, this.parcelas);

			List<Vencimento> vencimentos = new ArrayList<Vencimento>();

			for (int i = 0; i < this.parcelas; i++) {

				double valor = venda.getTotal() - venda.getTotal() % this.parcelas;

				valor /= this.parcelas;

				if (i == this.parcelas - 1) {

					valor += venda.getTotal() % this.parcelas;

				}

				int dias = (this.prazoPagamento / this.parcelas) * (i + 1);

				Calendar data = Calendar.getInstance();
				data.add(Calendar.DATE, dias);

				Vencimento v = new Vencimento();
				v.setData(data);
				v.setNota(nota);
				v.setValor(valor);

				System.out.println(v.getValor());

				vencimentos.add(v);

			}

			nota.setVencimentos(vencimentos);

			List<ProdutoNota> produtos = new ArrayList<ProdutoNota>();

			for (ProdutoVenda p : this.venda.getProdutos()) {

				if (p.getQuantidade() == 0)
					continue;

				ProdutoNota pn = new ProdutoNota();

				pn.setCompoeValorTotal(true);

				pn.setDesconto(0);

				pn.setFrete(0);

				pn.setNota(nota);

				pn.setObservacoes("Produto referente a nota");

				pn.setOutro(0);

				pn.setProduto(p.getProduto());

				pn.setQuantidade(p.getQuantidade());

				pn.setSeguro(0);

				pn.setValor(p.getValor());

				produtos.add(pn);

			}

			nota.setProdutos(produtos);

			nota.atualizarRegrasFiscais();

			lst.add(nota);

		}

		return lst;

	}

	public double getValorMeioPagamento() {
		return valorMeioPagamento;
	}

	public void setValorMeioPagamento(double valorMeioPagamento) {
		this.valorMeioPagamento = valorMeioPagamento;
	}

}
