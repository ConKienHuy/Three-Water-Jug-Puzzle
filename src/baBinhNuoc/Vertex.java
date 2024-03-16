package baBinhNuoc;
import java.util.List;

public class Vertex implements Comparable<Vertex>  {
    private State state;
    private Path<Vertex> path;
    
    public Vertex(){
        this.state = new State();
        this.path = new Path<>();
    }
    
    public Vertex(State state){
        this.state = state;        
        this.path = new Path<>();
    }
    
    public static void setMaxJugsCapacity(int maxJug1, int maxJug2, int maxJug3){
        State.setMaxJugsCapacity(maxJug1, maxJug2, maxJug3);
    }
    
    public double calculateHeuristic() {	
    	
    	return this.state.calculateHeuristic();
    }
    
    public void setHeuristic(double h) {this.state.setHeuristic(h);;}
    public double getHeuristic() {return this.state.getHeuristic();}    
    public void setfn(double value) {this.state.setfn(value);}
    public double getfn() {return this.state.getfn();} 
    public void setg(int g) {this.state.setg(g);}
    public int getg() {return this.state.getg();}
    
    public State getState(){
        return state;
    }
     
    public Vertex full_jug1(){
	return new Vertex(state.full_jug1());
    }
    
    public Vertex full_jug2(){
	return new Vertex (state.full_jug2());
    }
    
    public Vertex full_jug3() {
    	return new Vertex(state.full_jug3());
    }
    
    public Vertex empty_jug1(){
	return new Vertex(state.empty_jug1());
    }
    
    public Vertex empty_jug2(){
	return new Vertex(state.empty_jug2());
    }
    
    public Vertex empty_jug3() {
    	return new Vertex(state.empty_jug3());
    }
    
    public Vertex pour_jug1_jug2(){
        return new Vertex(state.pour_jug1_jug2());
    }
    
    public Vertex pour_jug2_jug1(){
        return new Vertex(state.pour_jug2_jug1());
    }
    
    public Vertex pour_jug1_jug3(){
        return new Vertex(state.pour_jug1_jug3());
    }
    
    public Vertex pour_jug3_jug1(){
        return new Vertex(state.pour_jug3_jug1());
    }
    
    public Vertex pour_jug2_jug3(){
        return new Vertex(state.pour_jug2_jug3());
    }
    
    public Vertex pour_jug3_jug2(){
        return new Vertex(state.pour_jug3_jug2());
    }
    
    public void addToPath() {
        this.path.addVertex(this);
    }
    
    public List<Vertex> getPath(){
        return path.getPath();
    }
    
    public void setPath(List<Vertex> path){
        this.path.setPath(path);
    }

    public void printPath(){
        path.printPath();
    }        

    @Override
    public boolean equals(Object obj) {
        Vertex v = (Vertex) obj;        
        
	if (!(v instanceof Vertex))
            return false;	
        return (this.getState().equals(v.getState()));
    } 
    
    //So sanh gia tri cua hai dinh
    //De luu vao Priority Queue theo gia tri tang dan
    public int compareTo(Vertex v) {
    	return Double.compare(this.state.getfn(), v.state.getfn());
    }
    
    @Override
    public String toString(){
        return state.toString();
    }
}