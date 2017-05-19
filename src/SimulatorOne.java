
import Graphs.Graph;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dino
 */
public class SimulatorOne {
      
    public static void main (String [] args){
        
        String line;
        String start;
        String [] items;
        Graph g = new Graph();
        String [] hospitals;
        String [] victims;
        System.out.println("enter input: ");
        
        
        Scanner scanf = new Scanner(System.in);
        int numnodes = scanf.nextInt();
        scanf.nextLine();
        
        for (int i = 0; i < numnodes; i++){ //add nodes, edges and appropriate costs
            
            line = scanf.nextLine();
            start = line.substring(0, 1);
            line = line.substring(2);

            items = line.split(" ");
            
            for(int z = 0; z < items.length; z = z+2){
                
                g.addEdge(start, items[z], Double.parseDouble(items[z+1]));
                System.out.println("edge: from: " + start + " to: " + items[z] + " cost: " + items[z+1]);
                
            }
            
        }
        
        int numhosps = scanf.nextInt();
        scanf.nextLine();
        line = scanf.nextLine();
        hospitals = line.split(" ");
        int numvics = scanf.nextInt();
        scanf.nextLine();
        line = scanf.nextLine();
        victims = line.split(" ");
        
        String path = "";
        String cost = "";
                
        ArrayList<String> paths = new ArrayList<>();
        
        for(int i = 0; i < victims.length; i++){
            
            
            
            for(int j = 0; j < hospitals.length; j++){
                
                path = "";
                cost = "";
                //working out path and cost from hospital to victim
                g.dijkstra(hospitals[j]); //start at the given hospital's node
                path = g.pathAll(victims[i]);
                cost = g.pathCost(victims[i]);
                
                g.dijkstra(victims[i]);
                path += " " + g.pathAll(hospitals[j]).substring(2);
                cost = "" + (Double.parseDouble(cost) + Double.parseDouble(g.pathCost(hospitals[j])));
                
                paths.add("v:" + i + " h:" + j + " C:" + cost + ";" + path);
                
            }
  
        }
        
        System.out.println(paths.toString());
        
        
         
    }
   
}
