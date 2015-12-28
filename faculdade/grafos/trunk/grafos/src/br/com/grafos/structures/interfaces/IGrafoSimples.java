package br.com.grafos.structures.interfaces;

import java.util.Iterator;

import br.com.grafos.exception.InvalidArestaException;
import br.com.grafos.exception.InvalidVerticeException;
import br.com.grafos.structures.Aresta;
import br.com.grafos.structures.Vertice;

/**
 * Interface que define um Grafo sem dire��o definida nas Arestas.
 */
public interface IGrafoSimples {
	
	/**
	 * M�todo que retorn o grau do grafo
	 * @return int
	 */
	public int grau();
	/**
	 * M�todo que retorna o tamanho do grafo 
	 * @return
	 */
	public int tamanho();
	/**
	 * M�todo que retorna um iterador dos v�rtices presentes do grafo
	 * @return Iterator
	 */
	public Iterator<Vertice> vertices();
	/**
	 * M�todo que retorna um iterador com as arestas presentes no grafo
	 * @return Iterator
	 */
	public Iterator<Aresta> arestas();
	/**
	 * M�todo que retorna um v�rtice qualquer do grafo
	 * @return
	 */
	public Vertice getVertice();
	
	/**
	 * M�todo que insere uma aresta no grafo
	 * @param v1 Vertice de Origem
	 * @param v2 Vertice de Destino
	 * @param custo
	 * @return Aresta inserida
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Vertice v1, Vertice v2,int custo) throws InvalidArestaException;
	/**
	 * M�todo que insere uma aresta no grafo com um nome em particular
	 * @param v1 V�rice de Origem
	 * @param v2 V�rtice de Destino
	 * @param custo
	 * @param nome
	 * @return
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Vertice v1, Vertice v2,int custo,String nome) throws InvalidArestaException;
	/**
	 * M�todo que recebe uma aresta e a inserre no Grafo
	 * @param aresta
	 * @return
	 * @throws InvalidArestaException
	 */
	public Aresta insereAresta(Aresta aresta) throws InvalidArestaException;
	/**
	 * M�todo que inserre uma aresta isolada no grafo
	 * @param vertice
	 * @return
	 * @throws InvalidVerticeException
	 */
	public Vertice insereVertice(Vertice vertice) throws InvalidVerticeException;
	public Iterator<Aresta> arestas(Vertice v);
	public int grau(Vertice v);
	
	public Vertice findVertice(String nome);
}
