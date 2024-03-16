package baBinhNuoc;

public class State {

    private int jug1, jug2, jug3;
    private static int MAX_JUG1;
    private static int MAX_JUG2;
    private static int MAX_JUG3;
    //Cac gia tri f, g heuristic cua 1 dinh
    private int g;
    private double heuristic, fn;
    
    public State(){
        this.jug1 = 0;
        this.jug2 = 0;
        this.jug3 = 0;
        this.fn = 0;
        this.g = 0;
        this.heuristic = 0;
    }
    
    public State(int jug1, int jug2, int jug3){
        this.jug1 = jug1;
        this.jug2 = jug2;
        this.jug3 = jug3;
        
    }
    // Tinh heuristic la so lit nuoc gan nhat so voi cua GOAL jug2 + jug3 va chia cho 2 
    public double calculateHeuristic() {
    	double heuristic_value;
    	double sumof_differences = Math.abs(this.jug2 - 4) + Math.abs(this.jug3 -4);
    	heuristic_value = sumof_differences /2;
    	
    	return heuristic_value;
    }
    
    public void setHeuristic(double h) {this.heuristic = h;}
    public double getHeuristic() {return this.heuristic;}    
    public void setfn(double value) {this.fn = value;} 
    public double getfn() {return this.fn;}
    public void setg(int g) {this.g = g;}
    public int getg() {return this.g;}
    
    public static void setMaxJugsCapacity(int maxJug1, int maxJug2, int maxJug3){
        MAX_JUG1 = maxJug1;
        MAX_JUG2 = maxJug2;
        MAX_JUG3 = maxJug3;
    }
    
    public int getJug1(){
        return jug1;
    } 
    
    public int getJug2(){
        return jug2;
    }
    
    public int getJug3() {
    	return jug3;
    }
    
    public State full_jug1(){
	return new State(MAX_JUG1, jug2, jug3);
    }
    
    public State full_jug2(){
	return new State(jug1, MAX_JUG2, jug3);
    }
    
    public State full_jug3() {
    	return new State(jug1, jug2, MAX_JUG3);
    }
    
    public State empty_jug1(){
	return new State(0, jug2, jug3);
    }
    
    public State empty_jug2(){
	return new State(jug1, 0, jug3);
    }
    
    public State empty_jug3() {
    	return new State(jug1, jug2, 0);
    }
    
    public State pour_jug1_jug2(){
        if ((jug1 + jug2) >= MAX_JUG2)
            return new State((jug1 + jug2 - MAX_JUG2), MAX_JUG2, jug3);
        else
            return new State(0, (jug1 + jug2), jug3);
    }
    
    public State pour_jug2_jug1(){
        if ((jug1 + jug2) >= MAX_JUG1)
            return new State(MAX_JUG1, (jug1 + jug2 - MAX_JUG1), jug3);
        else
            return new State((jug1 + jug2), 0, jug3);
    }
    
    public State pour_jug1_jug3() {
    	if ((jug1 + jug3) >= MAX_JUG3)
    		return new State((jug1 + jug3 - MAX_JUG3), jug2, MAX_JUG3);
    	return new State(0, jug2, (jug1 + jug3));
    }
    
    public State pour_jug3_jug1(){
        if ((jug1 + jug3) >= MAX_JUG1)
            return new State(MAX_JUG1, jug2, (jug1 + jug3 -MAX_JUG1));
        else
            return new State((jug1 + jug3), jug2, 0);
    }
    
    public State pour_jug2_jug3(){
        if ((jug2 + jug3) >= MAX_JUG3)
            return new State(jug1, (jug2 + jug3 - MAX_JUG3), MAX_JUG3);
        else
            return new State(jug1, 0, (jug2 + jug3));
    }
    
    public State pour_jug3_jug2(){
        if ((jug2 + jug3) >= MAX_JUG2)
            return new State(jug1, MAX_JUG2, (jug2 + jug3 - MAX_JUG2));
        else
            return new State(jug1, (jug2 + jug3), 0);
    }
        
    @Override
    public boolean equals(Object obj) {
        State s = (State) obj;        
        
	if (!(s instanceof State))
            return false;	

        return ((s.getJug1() == this.getJug1()) && (s.getJug2() == this.getJug2()) && s.getJug3() == this.getJug3());
    }
    
    @Override
    public String toString(){
        return "(" + jug1 + "-" + jug2 + "-" + jug3 +")";
    }
}
