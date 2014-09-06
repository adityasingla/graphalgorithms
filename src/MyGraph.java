import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * This class implements some elementary graph algorithms
 * The list of algorithms is as follows:
 * 1) Dijkstras
 * 2) Bellman - Ford
 * 3) Floyd Warshal
 * 4) Minimum Spanning Tree
 * 5) Depth First Search
 * 6) Breadth First Search
 */
/*
 * Generic implementatiomn of a graph
 */
public class MyGraph<Item> {
	Map<Item,List<Node<Item>>> adjacencyList;
	Set<Item> vertices;
	int index;
	@SuppressWarnings("unchecked")
	public MyGraph(){
		vertices = new HashSet<Item>();
		index = 0;
		adjacencyList = new HashMap<>();
	}
	
	/*
	 * represents a node added in the graph
	 */
	private class Node<Item>{
		Item value;
		int weight;
		public Node(Item value,int weight){
			this.value = value;
			this.weight = weight;
		}
	}
	
	/*
	 * Add an edge to the graph
	 */
	public void addEdge(Item start, Item end, int weight){
		vertices.add(start);
		vertices.add(end);
		if(adjacencyList.containsKey(start)){
			adjacencyList.get(start).add(new Node(end,weight));
		}
		else{
			List<Node<Item>> list= new ArrayList<>();
			list.add(new Node(end,weight));
			adjacencyList.put(start, list);
		}
	}
	
	/*
	 * Print the graph : format -   start:end - weight
	 */
	public void printGraph(){
		for(Item key:adjacencyList.keySet()){
			System.out.print(key.toString() + ":");
			for(Node n:adjacencyList.get(key)){
				System.out.print(n.value.toString() + "-" + String.valueOf(n.weight) + ",");
			}
			System.out.println();
		}
	}
	
	/*
	 * Bellman Ford for calculating the shortest path weight between 2 nodes
	 */
	@SuppressWarnings("unchecked")
	public void bellmanFord(Item start,Item end){
		
		HashMap<Item,Integer> shortestPaths  = new HashMap<>();
		for(Item i:(Item[])vertices.toArray()){
			shortestPaths.put(i,999999);
		}
		shortestPaths.put(start, 0);
		
		for(int count=0;count<vertices.size()-1;count++){
			for(Item i:(Item[])vertices.toArray()){ 
				List<Node<Item>> list = adjacencyList.get(i);
				for(int j =0;j<list.size();j++){
					Node<Item> item = list.get(j);
					if(shortestPaths.get(item.value) > shortestPaths.get(i) + item.weight){
						shortestPaths.put(item.value, shortestPaths.get(i) + item.weight);
					}
				}
			}
		}
		
		
		System.out.println(start.toString() +"-"+ end.toString() + ":" + shortestPaths.get(end).toString());
		
	}
	
	public static void main(String args[]){
		MyGraph<String> graph = new MyGraph<String>();
		graph.addEdge("a", "b",2);
		graph.addEdge("a", "c",3);
		graph.addEdge("a", "e",5);
		graph.addEdge("b", "d",7);
		graph.addEdge("b", "c",2);
		graph.addEdge("c", "d",4);
		graph.addEdge("c", "e",8);
		graph.addEdge("d", "f",1);
		graph.addEdge("d", "g",4);
		graph.addEdge("e", "d",12);
		graph.addEdge("e", "g",24);
		graph.addEdge("f", "g",54);
		graph.addEdge("g", "f",74);
		
		//graph.printGraph();
		graph.bellmanFord("a", "g");
	}
	
}
