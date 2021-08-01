package com.dsalgo.graphs;


import java.util.*;
import java.util.LinkedList;

class WeightedNode{
    int node;
    int wt;

    WeightedNode(int n, int w){
        node = n;
        wt = w;
    }
}

public class GraphsDijkstra {
    ArrayList<Integer> []graph;
    ArrayList<WeightedNode> []weightedGraph;

    boolean [] visited;
    int N;
    int [] distance;

    GraphsDijkstra(int n){
        N = n+1;
        graph = new ArrayList[n+1];
        weightedGraph = new ArrayList[n+1];
        visited = new boolean[n+1];
        distance = new int[n+1];
    }

    public void addWeightedEdge(int u, int v, int w){
        weightedGraph[u].add(new WeightedNode(v, w));
        weightedGraph[v].add(new WeightedNode(u, w));
    }

    public void addAEdge(int u, int v){
        graph[u].add(v);
        graph[v].add(u);
    }


    void dfs(int s){
        visited[s] = true;
        for(int v: graph[s]){
            if(!visited[v]){
                dfs(v);
            }
        }
    }


    void bfs(int s){
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        visited[s] = true;
        while (!q.isEmpty()){
            int u = q.poll();
            for(int v: graph[u]){
                if(!visited[v]){
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
    }

    void dijkstra(int src){
        for(int i=0;i<N;i++){
            distance[i] = Integer.MAX_VALUE;
        }
        TreeSet<WeightedNode> pq = new TreeSet<>();
        pq.add(new WeightedNode(src, 0));
        distance[src] = 0;

        while (!pq.isEmpty()){

        }
    }
}



