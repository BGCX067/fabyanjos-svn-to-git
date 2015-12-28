package br.com.grafos.file;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import br.com.grafos.exception.InvalidArestaException;
import br.com.grafos.exception.InvalidVerticeException;
import br.com.grafos.search.Dijkstra;
import br.com.grafos.structures.Aresta;
import br.com.grafos.structures.Grafo;
import br.com.grafos.structures.Vertice;
import br.com.grafos.structures.interfaces.IGrafoSimples;

public class PopulateGrafo {

	private List<String> informations = null;
	private LinkedHashMap<String, Vertice> vertices = null;
	private LinkedList<Aresta> arestas = null;
	private String inicio;
	private void createVertices() {
		vertices = new LinkedHashMap<String, Vertice>();
		String[] s = informations.get(0).split("\\s");
		this.inicio = s[1];
		int qtdeVertices = Integer.parseInt(s[0]);
		String[] vertices = informations.get(1).split("\\s"); 
		for (int i = 0; i < qtdeVertices; i++) {
			Vertice vertice = new Vertice(vertices[i]);
			this.vertices.put(vertice.getNome(), vertice);
		}
	}

	private void createArestas() {
		arestas = new LinkedList<Aresta>();
		for (int i = 2; i < informations.size(); i++) {
			String[] s = informations.get(i).split("\\s");
			Vertice origem = vertices.get(s[0]);
			Vertice destino = vertices.get(s[1]);
			int custo = Integer.parseInt(s[2]);
			Aresta aresta = new Aresta(origem, destino, custo);
			arestas.add(aresta);
		}
	}
	
	public static void main(String[] args) throws IOException, InvalidVerticeException, InvalidArestaException {
		ReaderFile reader = new ReaderFile("input.txt");
		PopulateGrafo populate = new PopulateGrafo();
		populate.informations = reader.readAll();
		populate.createVertices();
		populate.createArestas();

		IGrafoSimples grafo = new Grafo();
		
		for(Aresta aresta : populate.arestas) {
			grafo.insereAresta(aresta);
		}
		System.out.println("grafo: \n" + grafo);
		
		Dijkstra dijkstra = new Dijkstra(grafo, grafo.findVertice(populate.inicio));
		dijkstra.execute();
	}
}
