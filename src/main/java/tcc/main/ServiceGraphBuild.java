package tcc.main;

import com.tinkerpop.blueprints.Direction;
import com.tinkerpop.blueprints.Edge;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;

import tcc.ceap.Ceap;


public class ServiceGraphBuild {
	public void buildGraph(OrientGraph graph, Ceap ceap) {
		try{
			Vertex parlamentar = buildVertexParlamentar(graph, ceap);
			
			Vertex transacao = buildVertexTransacao(graph, ceap);
			graph.addEdge(null, parlamentar, transacao, "RealizaTransacao");
			
			Vertex empresaFornecedora = buildVertexEmpresaFornecedora(graph, ceap);
			graph.addEdge(null, transacao, empresaFornecedora, "FornecidaPor");
			
		} catch( Exception e ) {
			graph.rollback();
		} finally {
			graph.commit();
		}
		
	}
	
	private Vertex buildVertexTransacao(OrientGraph graph, Ceap ceap) {
		 Vertex transacao = graph.addVertex("class:Transacao");
		 transacao.setProperty("TxtDescricao", ceap.getTxtDescricao());
		 transacao.setProperty("TxtDescricaoEspecificacao", ceap.getTxtDescricaoEspecificacao());
		 transacao.setProperty("VlrDocumento", String.valueOf(ceap.getVlrDocumento()));
		 return transacao;
	}
	
	private Vertex buildVertexParlamentar (OrientGraph graph, Ceap ceap) {
		Iterable<Vertex> vertice = graph.getVertices("Parlamentar.TxNomeParlamentar", ceap.getTxNomeParlamentar());
		if(!vertice.iterator().hasNext()) {
			 Vertex parlamentar = graph.addVertex("class:Parlamentar");
			 parlamentar.setProperty("TxNomeParlamentar", ceap.getTxNomeParlamentar());
			 parlamentar.setProperty("IdeCadastro", ceap.getIdeCadastro());
			 parlamentar.setProperty("NumCarteiraParlamentar", ceap.getNumCarteiraParlamentar());
			 parlamentar.setProperty("SgUF", ceap.getSgUF());
			 parlamentar.setProperty("SgPartido", ceap.getSgPartido());
			 return parlamentar;
		} else {
			return vertice.iterator().next();
		}
	}
	
	private Vertex buildVertexEmpresaFornecedora (OrientGraph graph, Ceap ceap) {
		Iterable<Vertex> vertice = graph.getVertices("EmpresaFornecedora.TxtFornecedor", ceap.getTxtFornecedor());
		if(!vertice.iterator().hasNext()) {
			 Vertex empresaFornecedora = graph.addVertex("class:EmpresaFornecedora");
			 empresaFornecedora.setProperty("TxtFornecedor", ceap.getTxtFornecedor());
			 empresaFornecedora.setProperty("TxtCNPJCPF", ceap.getTxtCNPJCPF());
			 return empresaFornecedora;
		} else {
			return vertice.iterator().next();
		}
	}
	
	private Vertex buildVertexUnidadeFederativa (OrientGraph graph, Ceap ceap) {
		Iterable<Vertex> vertice = graph.getVertices("UnidadeFederativa.SgUF", ceap.getSgUF());
		if(!vertice.iterator().hasNext()) {
			 Vertex unidadeFederativa = graph.addVertex("class:UnidadeFederativa");
			 unidadeFederativa.setProperty("SgUF", ceap.getSgUF());
			 return unidadeFederativa;
		} else {
			return vertice.iterator().next();
		}
	}
	
	private Edge buildEdgePertenceUnidade(OrientGraph graph, Vertex parlamentar, Vertex unidadeFederativa) {
		Iterable<Edge> itOut = parlamentar.getEdges(Direction.OUT, "Pertence");
		for(Edge e:itOut){
			String TxNomeParlamentar = e.getVertex(Direction.OUT).getProperty("TxNomeParlamentar");
			String SgUF = e.getVertex(Direction.IN).getProperty("SgUF");
			
			if(TxNomeParlamentar.equals(parlamentar.getProperty("TxNomeParlamentar")) && SgUF.equals(unidadeFederativa.getProperty("SgUF"))) {
				return null;
			}
		}
		return graph.addEdge(null, parlamentar, unidadeFederativa, "Pertence");
	}
	
	private Edge buildEdgeFiliadoPartido(OrientGraph graph, Vertex parlamentar, Vertex partido) {
		Iterable<Edge> itOut = parlamentar.getEdges(Direction.OUT, "Filiado");
		for(Edge e:itOut){
			String TxNomeParlamentar = e.getVertex(Direction.OUT).getProperty("TxNomeParlamentar");
			String SgPartido = e.getVertex(Direction.IN).getProperty("SgPartido");
			
			if(TxNomeParlamentar.equals(parlamentar.getProperty("TxNomeParlamentar")) && SgPartido.equals(partido.getProperty("SgPartido"))) {
				return null;
			}
		}
		return graph.addEdge(null, parlamentar, partido, "Filiado");
	}
	
	private Vertex buildVertexPartido (OrientGraph graph, Ceap ceap) {
		Iterable<Vertex> vertice = graph.getVertices("Partido.SgPartido", ceap.getSgPartido());
		if(!vertice.iterator().hasNext()) {
			 Vertex partido = graph.addVertex("class:Partido");
			 partido.setProperty("SgPartido", ceap.getSgPartido());
			 return partido;
		} else {
			return vertice.iterator().next();
		}
	}

}
