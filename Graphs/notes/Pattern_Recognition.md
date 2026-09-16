# Graph Problem Pattern Recognition — Quick Notes

## 1. First Understand the Graph

When you see a graph problem, ask:

1. What are the **nodes (vertices)**?
2. What are the **edges (relationships)**?
3. Is it **directed or undirected**?
4. Is it **weighted or unweighted**?
5. Can the graph be **disconnected**?
6. Can there be **cycles**?

---

## 2. Identify What the Problem Is Asking

| Problem Clue | Think |
|---|---|
| Can I reach X? | BFS / DFS |
| Visit/explore all nodes | BFS / DFS |
| How many groups? | Connected Components |
| Are nodes connected? | BFS / DFS |
| Is there a cycle? | Cycle Detection |
| Can divide nodes into 2 groups? | Bipartite / Coloring |
| Minimum number of steps (unweighted) | BFS |
| Shortest path with positive weights | Dijkstra |
| Dependencies / prerequisites | Topological Sort |
| Grid of cells | Treat cells as graph nodes |

---

## 3. Choose BFS vs DFS

### BFS

Think BFS when:
- Minimum number of steps/edges
- Level-by-level traversal
- Nearest/closest node
- Multi-source expansion

Main structure:

```java
Queue<Integer> q;
boolean[] visited;