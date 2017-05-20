
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
        
        for(String vic : victims){ //error here, using indexs such as i and j, rather than actual hospital  int i = 0; i < victims.length; i++

            for(String hos : hospitals){ //int j = 0; j < hospitals.length; j++
                
                path = "";
                cost = "";
                //working out path and cost from hospital to victim
                g.dijkstra(hos); //start at the given hospital's node
                path = g.pathAll(vic);
                cost = g.pathCost(vic);
                
                g.dijkstra(vic);
                path += g.pathAll(hos).substring(1);
                cost = "" + (Double.parseDouble(cost) + Double.parseDouble(g.pathCost(hos)));
                
                paths.add("v:" + vic + " h:" + hos + " C:" + cost + ";" + path);
                
            }
  
        }
        
        //System.out.println(paths.toString()); for testing 
        
        double mincost = 999999.0;
        double wcost = 0.0;
        String vic = "";
        String print;
        
        for(String victim : victims ){
            
            System.out.println("victim " + victim);
            
            for (int i = 0; i < paths.size(); i++){ //loop to determine min cost
            
            path = paths.get(i);
            vic = path.substring(2,3);
            wcost = Double.parseDouble(path.substring(path.lastIndexOf(":")+1, path.lastIndexOf(";")));
            
            if (Integer.parseInt(victim) == Integer.parseInt(vic)){
                
                if (mincost > wcost){
                    
                    mincost = wcost;
                    
                }
                
            }
            
            } //looping through each path, selecting those for the same victim, determining min cost from those set to mincost
            
            for (int i = 0; i < paths.size(); i++){ //loop through each path, checking that victim is the same (i.e. as victim in vic loop) and checking if cost = mincost, then priting if it is
                
                path = paths.get(i);
                vic = path.substring(2,3);
                wcost = Double.parseDouble(path.substring(path.lastIndexOf(":")+1, path.lastIndexOf(";")));
                    
                if (Integer.parseInt(victim) == Integer.parseInt(vic)){
                
                    if (mincost == wcost){
                    
                        print = "hospital " + path.substring(6, 7) + "\n" + path.substring(path.indexOf(";")+1);
                        System.out.println(print);
                    
                    }
                } 
               
            }
            
            mincost = 999999.0;
            wcost = 0.0;
            
        }
           
    }
   
}
