package tcc.main;

import java.io.IOException;

import com.opencsv.CSVReader;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

import tcc.ceap.Ceap;

public class ServiceReader {
	public void readFileAndBuildGraph(CSVReader reader) throws IOException {
		String[] line;
		ServiceGraphBuild serviceGraphBuilder = new ServiceGraphBuild();
		Ceap ceap = new Ceap();
		
		try {
			ignoreHeader(reader);
			// CREATE A SERVER ADMIN CLIENT AGAINST A REMOTE SERVER TO CHECK IF DB EXISTS		
			//OServerAdmin serverAdmin = new OServerAdmin("remote:localhost:2425/CartoesPagamentos").connect("root","e54gfgfgf");
			//if(!serverAdmin.existsDatabase("CartoesPagamentos", "plocal")) {
				//serverAdmin.createDatabase("CartoesPagamentos", "graph", "plocal");
			//}
			OrientGraphFactory factory = new OrientGraphFactory("plocal:/home/gabriel/Desktop/UnB/10-semestre/TG1/implementação/orientdb-community-2.2.17/databases/CotasParlamentares").setupPool(1,10);
			OrientGraph graph = factory.getTx();
			while ((line = reader.readNext()) != null) {
				Ceap ceapUnit = ceap.buildCeap(line);
				System.out.println(ceapUnit.getTxNomeParlamentar());
				System.out.println(ceapUnit.getVlrDocumento());
				serviceGraphBuilder.buildGraph(graph, ceapUnit);
			}
			graph.shutdown();
			//serverAdmin.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String[] ignoreHeader(CSVReader reader) throws IOException {
		String[] line = reader.readNext();
		return line;
	}
}
