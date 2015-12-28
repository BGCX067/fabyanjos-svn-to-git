package br.com.grafos.search;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import br.com.grafos.structures.Aresta;
import br.com.grafos.structures.Vertice;
import br.com.grafos.structures.interfaces.IGrafoDigrafo;
import br.com.grafos.structures.interfaces.IGrafoSimples;

public class Dijkstra {

	private PriorityQueue<Vertice> pq;
	private List<Vertice> S;
	private IGrafoSimples g;
	private IGrafoDigrafo dg;
	private Vertice begin;
	public Dijkstra(IGrafoSimples g,Vertice begin) {
		begin.setPeso(0);
		this.g = g;
		pq = new PriorityQueue<Vertice>();
		S = new LinkedList<Vertice>();
		this.begin = begin;
		Iterator<Vertice> iter = g.vertices();
		while (iter.hasNext()) {
			pq.add(iter.next());
		}
	}
	public Dijkstra(IGrafoDigrafo g,Vertice begin) {
		begin.setPeso(0);
		this.dg = g;
		pq = new PriorityQueue<Vertice>();
		S = new LinkedList<Vertice>();
		Iterator<Vertice> iter = g.vertices();
		while (iter.hasNext()) {
			pq.add(iter.next());
		}
	}
	
	private boolean relax(Vertice u, Vertice v, Aresta a){
		return v.getPeso() > u.getPeso() + a.getCusto();
	}
	
	
	public void execute(){
		if(this.g==null){
			executeDigrafo();
		}else{
			executeSimples();
		}
		String s = imprimeCaminho();
		System.out.println(s);
	}
	private String imprimeCaminho() {
		Iterator<Vertice> iter = g.vertices();
		String s ="";
		boolean go = false;
		int custo = 0;
		Stack<String> pilha = new Stack<String>();
		while (iter.hasNext()) {
			Vertice v = iter.next();
			custo = v.getPeso();
			if(!v.getNome().equals(begin.getNome())){
				s += begin.getNome() + " ";
				while(!v.getAntecessor().getNome().equals(begin.getNome())){
					pilha.push(v.getNome() + " ");
					v = v.getAntecessor();
					go = true;
				}
				if(pilha.isEmpty()){
					s+= v.getNome() + " ";
				}
				while (!pilha.isEmpty()) {
					if(go){
						s+= v.getNome() + " ";
						go = false;
					}
					s+=pilha.pop();
				}
				s+= "custo: " + custo + "\n";
			}
		}
		return s;
	}
	private void executeSimples(){
		while(!pq.isEmpty()){
			Vertice u = pq.remove();
			S.add(u);
			Iterator<Aresta> arestas = g.arestas(u);
			while (arestas.hasNext()) {
				Aresta a = arestas.next();
				Vertice v = a.getDestino(u);
				if(relax(u,v,a)){
					v.setPeso(u.getPeso()+a.getCusto());
					v.setAntecessor(u);
				}
			}
		}
	}
	private void executeDigrafo(){
		while(!pq.isEmpty()){
			Vertice v = pq.remove();
			S.add(v);
			Iterator<Aresta> arestas = dg.arestasAdjacentes(v);
			while (arestas.hasNext()) {
				Aresta a = arestas.next();
				Vertice w = a.getDestino(v);
				if(relax(v,w,a)){
					w.setPeso(v.getPeso()+a.getCusto());
					w.setAntecessor(v);
				}
			}
		}
	}
}
