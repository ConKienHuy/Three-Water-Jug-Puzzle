package baBinhNuoc;

import java.util.ArrayList;
import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.Queue;
import java.util.Set;
import java.util.PriorityQueue;



public class thuattoan_Astar {
	
	public static int MAX_JUG1, MAX_JUG2, MAX_JUG3, GOAL;
    
    public static PriorityQueue<Vertex> open = new PriorityQueue<>() {
    	public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v :  open) {
                if ((vertex.equals(v))) {
                    return true;
                }
            }
        
            return false;
        }
    };
    
    public static Set<Vertex> closed = new HashSet<>(){
        public boolean contains(Object obj) {
            Vertex vertex = (Vertex) obj;
        
            for (Vertex v :  closed) {
                if ((vertex.equals(v))) {
                    return true;
                }
            }
        
            return false;
        }
    };
    
    public static void main(String[] args) {
    	if( !aStar())
    		System.out.println("\n\n===> Khong tim duoc loi giai trang thai muc tieu");
    }
    
    public static boolean aStar() {
    	MAX_JUG1 = 3; MAX_JUG2 = 5; MAX_JUG3 = 8;
    	Vertex.setMaxJugsCapacity(MAX_JUG1, MAX_JUG2, MAX_JUG3);
    	GOAL = 4;
    	
    	Vertex initialVertex = new Vertex(new State(0,0,0));
    	open.add(initialVertex);
    	closed.add(initialVertex);
    	initialVertex.setg(0);
    	initialVertex.setHeuristic(initialVertex.calculateHeuristic());
    	initialVertex.setfn(initialVertex.getg() + initialVertex.getHeuristic()); 
    	
    	
    	while(!open.isEmpty()) {
    		
    		Vertex currentVertex = open.peek();
    		System.out.println("\nNut Dang Xet :: " + currentVertex + " " + currentVertex.getState().getg());
    		currentVertex.addToPath();
    		
    		if(currentVertex.getState().getJug2() == GOAL ||
    				currentVertex.getState().getJug3() == GOAL)
    				 {
    			System.out.println("\n==> GOAL " +currentVertex);
    			System.out.println("==> Duong dan ngan nhat duoc tim thay");
    			currentVertex.printPath();
    			return true;
    		}
    		
    		ArrayList<Vertex> newVertices = new ArrayList<>();
    		newVertices.add(currentVertex.empty_jug1());
    		newVertices.add(currentVertex.empty_jug2());
    		newVertices.add(currentVertex.empty_jug3());
    		newVertices.add(currentVertex.full_jug1());
    		newVertices.add(currentVertex.full_jug2());
    		newVertices.add(currentVertex.full_jug3());
    		newVertices.add(currentVertex.pour_jug1_jug2());
    		newVertices.add(currentVertex.pour_jug2_jug1());
    		newVertices.add(currentVertex.pour_jug1_jug3());
    		newVertices.add(currentVertex.pour_jug3_jug1());
    		newVertices.add(currentVertex.pour_jug2_jug3());
    		newVertices.add(currentVertex.pour_jug3_jug2());
    		

    		for(Vertex newvertex : newVertices) {
    			// voi cac nut con dang xet, ta tang 1 bac buoc chuyen
    			int g = currentVertex.getg() + 1;
    			
    			//Neu nut con nay chua duoc xet
    			if(!open.contains(newvertex) && !closed.contains(newvertex)) {
    				newvertex.setPath(currentVertex.getPath());
    				newvertex.setg(g);
    				newvertex.setHeuristic(newvertex.calculateHeuristic());
    				newvertex.setfn(newvertex.getg() + newvertex.getHeuristic());
    				// In cac trang thai hop le va bac cua chung
    				System.out.println(newvertex + " " + newvertex.getState().getg());
    				open.add(newvertex); 
    			}
    			else {
    				
    				//Neu nut con nay da duoc xet roi
    				//Tinh gia tri g moi neu g nho hon
    				if(g < newvertex.getg()) {
    					System.out.println("Cap nhat gia tri G cho nut "+newvertex);
    					newvertex.setPath(currentVertex.getPath());
    					newvertex.setg(g);
    					newvertex.setfn(newvertex.calculateHeuristic() + newvertex.getg());
    				
    					if(closed.contains(newvertex)) {
    						closed.remove(newvertex);
    						open.add(newvertex);
    					}
    				}
    			}
    		}
    		
    		open.remove(currentVertex);
    		closed.add(currentVertex);
    		
    		System.out.println("OPEN   : " +open);
    		System.out.println("CLOSED : " +closed);
    		System.out.println();
    	}
    	return false;
    }
}
