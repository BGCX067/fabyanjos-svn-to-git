package br.com.grafos.structures;

import java.util.Iterator;
import java.util.LinkedList;

public class Vertice implements Comparable<Vertice> {

	private String nome;
	private int peso;
	private Vertice antecessor;
	// usado para o caso do grafo n�o seja dirigido
	private LinkedList<Aresta> arestasEntrada;
	private LinkedList<Aresta> arestasSaida;

	/**
	 * Construtor que recebe o nome do V�rtice
	 * @param nome
	 */
	public Vertice(String nome) {
		this.nome = nome;	
		arestasEntrada = new LinkedList<Aresta>();
		arestasSaida = new LinkedList<Aresta>();
		this.peso = Integer.MAX_VALUE;
	}

	public String getNome() {
		return nome;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + peso;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertice other = (Vertice) obj;
		if (peso != other.peso)
			return false;
		return true;
	}

	/**
	 * Insere uma aresta de saida para um <b>grafo n�o dirigido</b>
	 * @param aresta
	 */
	protected void insereAresta(Aresta aresta){
		this.arestasEntrada.add(aresta);
	}
	/**
	 * Insere uma aresta de entrada para um <b>grafo dirigido</b>
	 * @param aresta
	 */
	protected void InsereArestaEntrada(Aresta aresta){
		this.arestasEntrada.add(aresta);
	}
	/**
	 * Insere uma aresta de saida para um <b>grafo dirigido</b>
	 * @param aresta
	 */
	protected void InsereArestaSaida(Aresta aresta){
		this.arestasSaida.add(aresta);
	}
	/**
	 * M�todo que retorna o grau de incid�ncia de um v�rtice para um <b>grafo n�o dirigido</b>
	 * @return um interio com o grau de incid�ncia
	 */
	protected int grau(){
		return this.arestasEntrada.size();
	}
	/**
	 * M�todo que retorna o grau de incid�ncia de um v�rtice para um <b>grafo dirigido</b>
	 * @return um interio com o grau de incid�ncia
	 */
	protected int grauDeIncidencia(){
		return this.peso;
	}
	
	/**
	 * M�todo que retorna o grau de adjac�ncia de um v�rtice, para um <b>grafo dirigido</b>
	 * @return um intero com o grau de adjac�ncia
	 */
	protected int grauDeAdjacencia(){
		return this.arestasSaida.size();
	}
	/**
	 * m�todo que retorna um iterador das arestas incidentes para um
	 * <b>grafo dirigido</b> 
	 * @return um iterador das arestas adjacentes
	 */
	protected Iterator<Aresta> arestasIncidentes(){
		return this.arestasEntrada.iterator();
	}
	/**
	 * m�todo que retorna um iterador das arestas adjacentes para um
	 * <b>grafo dirigido</b> 
	 * @return um iterador das arestas adjacentes
	 */
	protected Iterator<Aresta> arestasAdjacentes(){
		return this.arestasSaida.iterator();
	}
	/**
	 * m�todo que retorna um iterador das arestas adjacentes para um
	 * <b>grafo n�o dirigido</b> 
	 * @return um iterador das arestas adjacentes
	 */
	protected Iterator<Aresta> arestas(){
		return this.arestasIncidentes();
	}

	public Vertice getAntecessor() {
		return antecessor;
	}

	public void setAntecessor(Vertice antecessor) {
		this.antecessor = antecessor;
	}

	public int compareTo(Vertice vertice) {
		Integer i = peso;
		return i.compareTo(vertice.peso);
	}
	@Override
	public String toString() {
		return this.nome;
	}
}
