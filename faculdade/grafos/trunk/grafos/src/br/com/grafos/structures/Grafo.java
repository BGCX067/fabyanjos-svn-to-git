package br.com.grafos.structures;

import java.util.Iterator;
import java.util.LinkedHashMap;

import br.com.grafos.exception.InvalidArestaException;
import br.com.grafos.exception.InvalidVerticeException;
import br.com.grafos.structures.interfaces.IGrafoSimples;
import br.com.grafos.structures.interfaces.IGrafoDigrafo;

public class Grafo implements IGrafoSimples, IGrafoDigrafo {
	private LinkedHashMap<String, Aresta> arestas;
	private LinkedHashMap<String, Vertice> vertices;
	private boolean dirigido;
	/**
	 *Construtor vazio que assume que o grafo � n�o dirigido	 * 
	 */
	public Grafo() {
		this(false);
	}
	/**
	 * Construtor que recebe o par�metro indicando
	 * se ele � dirigido ou n�o
	 * @param dirigido
	 */
	public Grafo(boolean dirigido){
		arestas = new LinkedHashMap<String, Aresta>();
		vertices = new LinkedHashMap<String, Vertice>();
		this.dirigido = dirigido;
	}
	/**
	 * M�todo que retorn o grau do grafo
	 * @return int
	 */
	public int grau(){
		return vertices.size();
	}
	/**
	 * M�todo que retorna o tamanho do grafo 
	 * @return
	 */
	public int tamanho(){
		return arestas.size();
	}
	/**
	 * M�todo que retorna um iterador dos v�rtices presentes do grafo
	 * @return Iterator
	 */
	public Iterator<Vertice> vertices(){
		return vertices.values().iterator();
	}
	/**
	 * M�todo que retorna um iterador com as arestas presentes no grafo
	 * @return Iterator
	 */
	public Iterator<Aresta> arestas(){
		return arestas.values().iterator();
	}
	/**
	 * M�todo que retorna um v�rtice qualquer do grafo
	 * @return
	 */
	public Vertice getVertice(){
		return vertices.values().iterator().next();
	}
	
	/**
	 * M�todo que insere uma aresta no grafo
	 * @param v1 Vertice de Origem
	 * @param v2 Vertice de Destino
	 * @param custo
	 * @return Aresta inserida
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Vertice v1, Vertice v2,int custo) throws InvalidArestaException{
		return this.insereAresta(v1, v2, custo, "");
	}
	/**
	 * M�todo que insere uma aresta no grafo com um nome em particular
	 * @param v1 V�rice de Origem
	 * @param v2 V�rtice de Destino
	 * @param custo
	 * @param nome
	 * @return
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Vertice v1, Vertice v2,int custo,String nome) throws InvalidArestaException{
		Aresta aresta = new Aresta(v1,v2,custo);
		aresta.setNome(nome);
		return this.insereAresta(aresta);
	}
	/**
	 * M�todo que recebe uma aresta e a inserre no Grafo
	 * @param aresta
	 * @return
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Aresta aresta) throws InvalidArestaException{
		if(aresta.getCusto()<=0){
			throw new InvalidArestaException("Aresta inv�lida, n�o h� um custo v�lido");
		}
		if(aresta.getDestino()==null){
			throw new InvalidArestaException("Aresta inv�lida, n�o h� um V�rtice de Destino para a aresta");
		}
		if(aresta.getOrigem()==null){
			throw new InvalidArestaException("Aresta inv�lida, n�o h� um V�rtice de Origem para a aresta");
		}
		try{
			if(dirigido){
				aresta.getOrigem().InsereArestaSaida(aresta);
				aresta.getDestino().InsereArestaEntrada(aresta);
			}else{
				aresta.getOrigem().insereAresta(aresta);
				aresta.getDestino().insereAresta(aresta);
			}
			insereVertice(aresta.getOrigem());
			insereVertice(aresta.getDestino());
		}catch (InvalidVerticeException e) {
			throw new InvalidArestaException("V�rtice inv�lido."); 
		}
		if(aresta.getNome()==null || aresta.getNome().trim().length()==0){
			aresta.setNome("("+aresta.getOrigem().getNome()+","+aresta.getDestino().getNome()+")");
		}
		aresta.setDirigido(dirigido);
		arestas.put(aresta.getNome(), aresta);
		return aresta;
	}
	/**
	 * M�todo que inserre uma aresta isolada no grafo
	 * @param vertice
	 * @return
	 * @throws InvalidVerticeException
	 */
	public Vertice insereVertice(Vertice vertice) throws InvalidVerticeException{
		if(vertice.getNome()==null || vertice.getNome().trim().length()==0){
			throw new InvalidVerticeException("V�rtice inv�lido, n�o possui um nome");
		}
		vertices.put(vertice.getNome(), vertice);
		return vertice;
	}
	public Iterator<Aresta> arestas(Vertice v){
		return v.arestas();
	}
	public Iterator<Aresta> arestasIncidentes(Vertice v){
		return v.arestasAdjacentes();
	}
	public Iterator<Aresta> arestasAdjacentes(Vertice v){
		return v.arestasAdjacentes();
	}
	public int grau(Vertice v){
		return v.grau();
	}
	public int grauAdjacencia(Vertice v){
		return v.grauDeAdjacencia();
	}
	public int grauIncidencia(Vertice v){
		return v.grauDeIncidencia();
	}
	
	public Vertice findVertice(String nome){
		return this.vertices.get(nome);
	}
	
	@Override
	public String toString() {
		String s = new String();
		for(Aresta aresta : arestas.values()) {
			s += "[" +aresta.toString()+"]" + "\n";
		}
		return s;
	}
}
