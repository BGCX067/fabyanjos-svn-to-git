package br.com.grafos.structures.interfaces;

import java.util.Iterator;

import br.com.grafos.exception.InvalidArestaException;
import br.com.grafos.exception.InvalidVerticeException;
import br.com.grafos.structures.Aresta;
import br.com.grafos.structures.Vertice;

/**
 * Interface que define um Grafo que possui Arestas com direção(Digrafo).
 */
public interface IGrafoDigrafo {

	/**
	 * Método que retorna o tamanho do grafo 
	 * @return
	 */
	public int tamanho();
	/**
	 * Método que retorna um iterador dos vértices presentes do grafo
	 * @return Iterator
	 */
	public Iterator<Vertice> vertices();
	/**
	 * Método que retorna um vértice qualquer do grafo
	 * @return
	 */
	public Vertice getVertice();
	
	/**
	 * Método que insere uma aresta no grafo
	 * @param v1 Vertice de Origem
	 * @param v2 Vertice de Destino
	 * @param custo
	 * @return Aresta inserida
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Vertice v1, Vertice v2,int custo) throws InvalidArestaException;
	/**
	 * Método que insere uma aresta no grafo com um nome em particular
	 * @param v1 Vérice de Origem
	 * @param v2 Vértice de Destino
	 * @param custo
	 * @param nome
	 * @return
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Vertice v1, Vertice v2,int custo,String nome) throws InvalidArestaException;
	/**
	 * Método que recebe uma aresta e a inserre no Grafo
	 * @param aresta
	 * @return
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Aresta aresta) throws InvalidArestaException;
	/**
	 * Método que inserre uma aresta isolada no grafo
	 * @param vertice
	 * @return
	 * @throws InvalidVerticeException
	 */
	public Vertice insereVertice(Vertice vertice) throws InvalidVerticeException;
	public Iterator<Aresta> arestasIncidentes(Vertice v);
	public Iterator<Aresta> arestasAdjacentes(Vertice v);
	public int grauAdjacencia(Vertice v);
	public int grauIncidencia(Vertice v);

}
