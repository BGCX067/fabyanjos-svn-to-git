package br.com.grafos.structures;

public class Aresta {

	private Vertice origem;
	private Vertice destino;
	private int custo;
	private String nome;
	private boolean dirigido;
	

	public Aresta(){
		this(null,null,0);
	}
	
	public Aresta(Vertice origem, Vertice destino, int custo) {
		this.origem = origem;
		this.destino = destino;
		this.custo = custo;
	}
	
	public Vertice getOrigem(Vertice v) {
		if(dirigido){
			if(!v.getNome().equals(origem.getNome())){
				return origem;
			}
			return null;
		}else{
			if(!v.getNome().equals(destino.getNome())){
				return destino;
			}else if(!v.getNome().equals(origem.getNome())){
				return origem;
			}
			return null;
		}
	}
	protected Vertice getDestino(){
		return destino;
	}
	protected Vertice getOrigem(){
		return origem;
	}
	public void setOrigem(Vertice origem) {
		this.origem = origem;
	}

	public Vertice getDestino(Vertice v) {
		if(dirigido){
			if(!v.getNome().equals(destino.getNome())){
				return destino;
			}
			return null;
		}else{
			if(!v.getNome().equals(destino.getNome())){
				return destino;
			}else if(!v.getNome().equals(origem.getNome())){
				return origem;
			}
			return null;
		}
		
	}
	public void setDestino(Vertice destino) {
		this.destino = destino;
	}

	public int getCusto() {
		return custo;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isDirigido() {
		return dirigido;
	}

	public void setDirigido(boolean dirigido) {
		this.dirigido = dirigido;
	}
	
	@Override
	public String toString() {
		String s = origem.getNome() + " " + destino.getNome() + " " + custo;
		return s;
	}
}
