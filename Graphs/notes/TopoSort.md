# Topological Sort — Kahn's Algorithm (BFS)

## Pattern
- Directed Acyclic Graph (DAG)
- BFS + Indegree + Queue
- Used to order tasks/courses with dependencies.
- Also detects directed cycles.

## Intuition
For every directed edge `u → v`, node `u` must appear before node `v`.

**Indegree** = number of incoming edges to a node.

A node with indegree `0` has no remaining prerequisites, so we can process it.

## Approach — Quick Revision

1. **Build adjacency list:** For each edge `[u, v]`, add `v` to `adjList.get(u)`.
2. **Calculate indegree:** For every edge `u → v`, increment `indegree[v]`.
3. **Initialize queue:** Add every node whose indegree is `0`.
4. **Run BFS:**
    - Remove a node from the queue.
    - Add it to the result.
    - For each outgoing neighbor, decrement its indegree.
    - If a neighbor's indegree becomes `0`, add it to the queue.
5. **Detect cycle:** If `result.size() != vertices`, return an empty list. Otherwise, return the result.

## Java Implementation

```java
import java.util.*;

public class TopologicalSort {

    private static List<Integer> topo(
            List<List<Integer>> edges, int vertices) {

        // 1. Build adjacency list
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            adjList.get(u).add(v);
        }

        // 2. Calculate indegree
        int[] indegree = new int[vertices];

        for (int i = 0; i < adjList.size(); i++) {
            for (int neighbor : adjList.get(i)) {
                indegree[neighbor]++;
            }
        }

        // 3. Initialize queue with indegree-0 nodes
        List<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // 4. BFS — Kahn's algorithm
        while (!q.isEmpty()) {
            int node = q.poll();
            result.add(node);

            for (int neighbor : adjList.get(node)) {
                indegree[neighbor]--;

                if (indegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }

        // 5. Cycle detection
        if (result.size() != vertices) {
            return new ArrayList<>();
        }

        return result;
    }
}
```

## Example

Input edges:

```text
0 → 1
0 → 2
1 → 3
2 → 3
```

Indegree array:

```java
{0, 1, 1, 2}
```

One valid output:

```text
[0, 1, 2, 3]
```

## Cycle Detection Example

Input edges:

```text
0 → 1
1 → 2
2 → 0
2 → 3
```

The cycle is `0 → 1 → 2 → 0`.

Every node has positive indegree initially, so the queue starts empty. The result contains fewer nodes than `vertices`, and the method returns:

```text
[]
```

## Complexity

- **Time:** `O(V + E)` — each vertex and edge is processed a constant number of times.
- **Space:** `O(V + E)` — adjacency list, indegree array, queue, and result.

Here, `V` = number of vertices and `E` = number of directed edges.

## Common Mistakes

- Incrementing `indegree[node]` instead of `indegree[neighbor]`.
- Forgetting to add initial indegree-0 nodes to the queue.
- Forgetting to decrement a neighbor's indegree after processing an edge.
- Adding a neighbor before its indegree reaches `0`.
- Forgetting the final cycle check.

## Remember

**Build graph → Calculate indegree → Queue indegree-0 nodes → Process BFS → Decrease indegrees → Check cycle.**

Kahn's algorithm is a BFS-based topological sort. DFS can also implement topological sorting using a different approach.
