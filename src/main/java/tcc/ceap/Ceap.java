package tcc.ceap;


public class Ceap {
	
	private String txNomeParlamentar;
	private Long ideCadastro;
	private Integer numCarteiraParlamentar;
	private String sgUF;
	private String sgPartido;
	private String txtDescricao;
	private String txtDescricaoEspecificacao;
	private Double vlrDocumento;
	private String txtFornecedor;
	private String txtCNPJCPF;
	
	public Ceap(String txNomeParlamentar, long ideCadastro, int numCarteiraParlamentar, String sgUF, String sgPartido,
			String txtDescricao, String txtDescricaoEspecificacao, Double vlrDocumento, String txtFornecedor,
			String txtCNPJCPF) {
		this.txNomeParlamentar = txNomeParlamentar;
		this.ideCadastro = ideCadastro;
		this.numCarteiraParlamentar = numCarteiraParlamentar;
		this.sgUF = sgUF;
		this.sgPartido = sgPartido;
		this.txtDescricao = txtDescricao;
		this.txtDescricaoEspecificacao = txtDescricaoEspecificacao;
		this.vlrDocumento = vlrDocumento;
		this.txtFornecedor = txtFornecedor;
		this.txtCNPJCPF = txtCNPJCPF;
	}

	public Ceap() {
		
	}
	
	public Ceap buildCeap(String[] lineCeap) {
		if(lineCeap.length == 29) {
			String txNomeParlamentar = lineCeap[0];
			Long ideCadastro;
			Integer numCarteiraParlamentar;
			if(!lineCeap[1].equals("")) {
				ideCadastro = Long.parseLong(lineCeap[1]);
				numCarteiraParlamentar = Integer.parseInt(lineCeap[2]);
			} else {
				ideCadastro = -1L;
				numCarteiraParlamentar = -1;
			}
			String sgUF = lineCeap[4];
			String sgPartido = lineCeap[5];
			String txtDescricao = lineCeap[8];
			String txtDescricaoEspecificacao = lineCeap[10];
			String txtFornecedor = lineCeap[11];
			String txtCNPJCPF = lineCeap[12];
			Double vlrDocumento = Double.parseDouble(lineCeap[16].replace(',','.'));
			return new Ceap(txNomeParlamentar, ideCadastro, numCarteiraParlamentar, sgUF, sgPartido, txtDescricao, txtDescricaoEspecificacao, vlrDocumento, txtFornecedor, txtCNPJCPF);
		} else {
			System.out.println(lineCeap[9]);
		}
		return null;
	}

	public String getTxNomeParlamentar() {
		return txNomeParlamentar;
	}

	public void setTxNomeParlamentar(String txNomeParlamentar) {
		this.txNomeParlamentar = txNomeParlamentar;
	}

	public Long getIdeCadastro() {
		return ideCadastro;
	}

	public void setIdeCadastro(Long ideCadastro) {
		this.ideCadastro = ideCadastro;
	}

	public Integer getNumCarteiraParlamentar() {
		return numCarteiraParlamentar;
	}

	public void setNumCarteiraParlamentar(Integer numCarteiraParlamentar) {
		this.numCarteiraParlamentar = numCarteiraParlamentar;
	}

	public String getSgUF() {
		return sgUF;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public String getSgPartido() {
		return sgPartido;
	}

	public void setSgPartido(String sgPartido) {
		this.sgPartido = sgPartido;
	}

	public String getTxtDescricao() {
		return txtDescricao;
	}

	public void setTxtDescricao(String txtDescricao) {
		this.txtDescricao = txtDescricao;
	}

	public String getTxtDescricaoEspecificacao() {
		return txtDescricaoEspecificacao;
	}

	public void setTxtDescricaoEspecificacao(String txtDescricaoEspecificacao) {
		this.txtDescricaoEspecificacao = txtDescricaoEspecificacao;
	}

	public Double getVlrDocumento() {
		return vlrDocumento;
	}

	public void setVlrDocumento(Double vlrDocumento) {
		this.vlrDocumento = vlrDocumento;
	}

	public String getTxtFornecedor() {
		return txtFornecedor;
	}

	public void setTxtFornecedor(String txtFornecedor) {
		this.txtFornecedor = txtFornecedor;
	}

	public String getTxtCNPJCPF() {
		return txtCNPJCPF;
	}

	public void setTxtCNPJCPF(String txtCNPJCPF) {
		this.txtCNPJCPF = txtCNPJCPF;
	}
}
