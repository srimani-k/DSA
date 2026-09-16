# Graph Pattern Recognition — What We Have Learned So Far

## 1. The Main Goal

When you see a graph problem, DON'T immediately think:

> "Should I use DFS or BFS?"

First ask:

1. What are the nodes?
2. What are the edges?
3. Is it directed or undirected?
4. Is it weighted or unweighted?
5. What exactly is the problem asking?
6. What pattern does the question represent?
7. What edge cases should I consider?

The goal is:

> **Problem → Pattern → Algorithm → Code**

Not:

> **Problem → randomly try DFS/BFS**

---

# 2. What Is a Graph?

A graph consists of:

- Nodes / Vertices
- Edges / Connections

Example:

```text
0 ---- 1
|      |
|      |
2 ---- 3
```

Nodes:

```text
0, 1, 2, 3
```

Edges:

```text
0-1
0-2
1-3
2-3
```

---

# 3. Directed vs Undirected

## Undirected Graph

The connection works both ways.

```text
0 ---- 1
```

If we can go:

```text
0 → 1
```

we can also go:

```text
1 → 0
```

Usually represented as:

```java
adj[0].add(1);
adj[1].add(0);
```

Examples:

- Friendship
- Two-way roads
- Network connections

---

## Directed Graph

The connection has a direction.

```text
0 ---> 1
```

We can go:

```text
0 → 1
```

but not:

```text
1 → 0
```

Representation:

```java
adj[0].add(1);
```

Examples:

- Course prerequisites
- Following relationships
- Task dependencies

---

# 4. Weighted vs Unweighted

## Unweighted Graph

Every edge effectively has the same cost.

```text
A ---- B ---- C
```

If the question asks:

> What is the minimum number of edges/hops from A to C?

Think:

```text
BFS
```

---

## Weighted Graph

Edges have different costs.

```text
A --5-- B
|       |
2       10
|       |
C --1-- D
```

If the question asks:

> What is the minimum total cost?

Think:

```text
Shortest Path
```

For now, the important recognition is:

```text
minimum distance / cost
        ↓
shortest-path pattern
```

---

# 5. Pattern 1 — Graph Traversal

## Problem Language

Look for:

- Visit all nodes
- Traverse the graph
- Explore the graph
- Search from a node
- Visit reachable nodes

Usually:

```text
DFS or BFS
```

---

# 6. DFS

DFS = Depth First Search.

DFS goes deep before coming back.

Example:

```text
A
|
B
|
C
|
D
```

Basic recursive structure:

```java
void dfs(int node, boolean[] visited) {

    visited[node] = true;

    for (int neighbor : graph[node]) {

        if (!visited[neighbor]) {
            dfs(neighbor, visited);
        }
    }
}
```

Mental model:

> "I'm at this node. Mark it visited, then explore every unvisited neighbor."

---

# 7. BFS

BFS = Breadth First Search.

BFS explores level by level.

Example:

```text
        A
      /   \
     B     C
    / \     \
   D   E     F
```

Traversal:

```text
A
B C
D E F
```

Basic structure:

```java
Queue<Integer> queue = new LinkedList<>();

queue.offer(start);
visited[start] = true;

while (!queue.isEmpty()) {

    int node = queue.poll();

    for (int neighbor : graph[node]) {

        if (!visited[neighbor]) {

            visited[neighbor] = true;
            queue.offer(neighbor);
        }
    }
}
```

---

# 8. DFS vs BFS — Pattern Recognition

Don't think:

> DFS is always better.

or:

> BFS is always better.

Instead ask what the problem wants.

## DFS naturally fits

- Exploring a graph
- Connected components
- Path existence
- Cycle detection
- Bipartite checking
- Recursive graph exploration

## BFS naturally fits

- Minimum number of edges
- Minimum hops
- Level-by-level traversal
- Shortest path in an unweighted graph

Important:

> Both DFS and BFS can solve many traversal problems.

The requirement determines the natural choice.

---

# 9. Pattern 2 — Connected Components

This is one of the most important graph patterns.

## Problem Language

Look for:

- Number of groups
- Number of networks
- Number of provinces
- Number of connected components
- Number of disconnected groups
- Separate clusters

Underlying question:

> **"How many separate pieces of the graph exist?"**

Example:

```text
0 --- 1       2 --- 3
              |
              4
```

There are:

```text
Component 1:
0 --- 1

Component 2:
2 --- 3
|
4
```

Answer:

```text
2
```

---

# 10. Connected Components Pattern

The basic pattern is:

```text
count = 0

for every node:

    if node is not visited:

        count++

        DFS/BFS from this node
```

Java:

```java
int components = 0;

for (int i = 0; i < n; i++) {

    if (!visited[i]) {

        components++;

        dfs(i, visited);
    }
}
```

The key idea:

> Every time the outer loop finds an unvisited node, we discovered a new component.

---

# 11. Why the Outer Loop Matters

Suppose:

```text
0 --- 1

2 --- 3
```

If we only do:

```java
dfs(0);
```

we explore:

```text
0, 1
```

but never:

```text
2, 3
```

Therefore, for a potentially disconnected graph:

```java
for (int i = 0; i < n; i++) {

    if (!visited[i]) {
        dfs(i);
    }
}
```

is important.

---

# 12. Pattern 3 — Grid as a Graph

A grid can be treated as a graph.

Example:

```text
1 1 0
1 0 0
0 0 1
```

Each cell is a node.

Usually, neighboring cells are connected:

```text
up
down
left
right
```

Therefore:

```text
Grid problem
      ↓
Graph problem
      ↓
DFS / BFS
```

---

# 13. Number of Islands / Connected Regions

If the problem asks:

- Number of islands
- Number of connected groups of cells
- Connected land
- Number of regions
- Flood fill
- Connected cells

Think:

```text
Grid DFS/BFS
```

This is basically:

```text
Connected Components
```

but instead of graph nodes:

```text
0, 1, 2, 3...
```

we have:

```text
(row, column)
```

---

# 14. Grid Directions

Usually:

```text
(-1, 0) = UP
(1, 0)  = DOWN
(0, -1) = LEFT
(0, 1)  = RIGHT
```

Can represent them as:

```java
int[][] directions = {
    {-1, 0},
    {1, 0},
    {0, -1},
    {0, 1}
};
```

Then:

```java
for (int[] dir : directions) {

    int newRow = row + dir[0];
    int newCol = col + dir[1];

}
```

Important:

> Never assume diagonal movement unless the problem explicitly allows it.

---

# 15. Grid Boundary Check

Before accessing a neighboring cell:

```java
if (newRow >= 0 &&
    newRow < rows &&
    newCol >= 0 &&
    newCol < cols) {

    // valid cell
}
```

Always think about:

```text
row = -1
row = rows
col = -1
col = cols
```

These are common sources of errors.

---

# 16. Grid Edge Cases

Always consider:

### Empty grid

```text
[]
```

### One row

```text
1 1 0 1
```

### One column

```text
1
0
1
```

### All water

```text
0 0
0 0
```

### All land

```text
1 1
1 1
```

### Boundary cells

Make sure neighbor calculations don't go outside the grid.

---

# 17. Pattern 4 — Path Existence / Reachability

## Problem Language

Look for:

- Can I reach X from Y?
- Is there a path?
- Is destination reachable?
- Can we travel from source to destination?
- Are two nodes connected?

Underlying question:

> **"Can I get from A to B?"**

Think:

```text
DFS / BFS
```

Example:

```text
0 --- 1 --- 2
      |
      3
```

Question:

> Can we reach 3 from 0?

Start:

```text
DFS(0)
```

Eventually:

```text
0 → 1 → 3
```

Therefore:

```text
true
```

---

# 18. Path Existence Mental Model

```text
Start at source
      ↓
Mark visited
      ↓
Explore neighbors
      ↓
Destination found?
      ↓
YES → true

Everything exhausted?
      ↓
YES → false
```

Important:

> Use `visited[]` so we don't repeatedly explore the same nodes.

---

# 19. Pattern 5 — Cycle Detection

A cycle means that the graph contains a loop.

Example:

```text
0 ---- 1
|      |
|      |
3 ---- 2
```

Cycle:

```text
0 → 1 → 2 → 3 → 0
```

But cycle detection depends on whether the graph is:

```text
Undirected
```

or:

```text
Directed
```

The logic is different.

---

# 20. Undirected Graph — Cycle Detection

Example:

```text
0 --- 1
|     |
3 --- 2
```

Suppose we travel:

```text
0 → 1
```

Because the graph is undirected, from node 1 we naturally see:

```text
0
```

again.

That does NOT automatically mean a cycle.

Why?

Because:

```text
0
↑
|
1
```

is simply the edge we came from.

Therefore we need to remember:

> **Who was my parent?**

---

# 21. Undirected Cycle Detection Rule

At every node:

```text
current
   ↓
check neighbor
```

### If neighbor is unvisited:

```text
DFS(neighbor, current)
```

The current node becomes the neighbor's parent.

### If neighbor is already visited:

Check:

```text
neighbor != parent
```

If true:

```text
CYCLE
```

Core rule:

```text
visited neighbor
        +
not my parent
        =
cycle
```

---

# 22. Undirected Cycle Detection Template

```java
boolean dfs(int node, int parent, boolean[] visited) {

    visited[node] = true;

    for (int neighbor : graph[node]) {

        if (!visited[neighbor]) {

            if (dfs(neighbor, node, visited)) {
                return true;
            }

        } else if (neighbor != parent) {

            return true;
        }
    }

    return false;
}
```

Mental model:

> "If I see an already visited node, it is okay if that node is simply the parent I came from. Otherwise, there is another connection back, so a cycle exists."

---

# 23. Directed Graph — Cycle Detection

Directed graphs use a different idea.

Example:

```text
0 → 1 → 2
    ↑   |
    |___|
```

Cycle:

```text
1 → 2 → 1
```

Here, we care about whether a node appears again in the:

> **CURRENT DFS path**

---

# 24. Directed Cycle Detection — Two States

Think of each node as having:

```text
0 = unvisited
1 = currently in DFS path
2 = completely processed
```

Or use:

```text
visited[]
pathVisited[]
```

---

# 25. Directed Cycle Detection Logic

When entering a node:

```java
visited[node] = true;
pathVisited[node] = true;
```

When completely leaving the node:

```java
pathVisited[node] = false;
```

If we encounter:

```java
pathVisited[neighbor] == true
```

then:

```text
CYCLE
```

because the neighbor is already inside our current recursion path.

---

# 26. Why `visited[]` Alone Is Not Enough

Consider:

```text
0 → 1

2 → 1
```

There is no cycle.

We might first traverse:

```text
0 → 1
```

Then later:

```text
2 → 1
```

Node 1 is already visited.

But that doesn't mean there is a cycle.

The important question is:

> Is node 1 still part of the CURRENT DFS path?

That's why we need:

```text
visited[]
```

and:

```text
pathVisited[]
```

---

# 27. Undirected vs Directed Cycle Detection

This distinction is VERY important.

## Undirected

Ask:

> "Is this neighbor already visited and not my parent?"

```java
visited[neighbor] && neighbor != parent
```

Mental model:

```text
UNDIRECTED
    ↓
parent matters
```

---

## Directed

Ask:

> "Is this neighbor currently inside my DFS path?"

```java
pathVisited[neighbor]
```

Mental model:

```text
DIRECTED
    ↓
current recursion path matters
```

---

# 28. Pattern 6 — Bipartite Graph

This is the pattern we most recently learned.

## Problem Language

Look for:

- Bipartite graph
- Divide graph into two groups
- Divide into two teams
- Two colors
- Two sets
- Adjacent nodes cannot belong to the same group
- Assign two different categories
- Can the graph be colored using two colors?

Think:

```text
BIPARTITE
    ↓
2-COLORING
    ↓
DFS / BFS
```

---

# 29. What Is a Bipartite Graph?

A graph is bipartite if we can divide its nodes into two groups:

```text
Group A
Group B
```

such that:

> No edge connects two nodes belonging to the same group.

Example:

```text
    0
   / \
  1   3
   \ /
    2
```

Possible coloring:

```text
Color 0:
0, 2

Color 1:
1, 3
```

Every edge connects different colors.

---

# 30. Bipartite = 2-Coloring

Use:

```text
-1 = not colored
 0 = color 0
 1 = color 1
```

Example:

```java
int[] color = new int[n];

Arrays.fill(color, -1);
```

Start with:

```java
color[start] = 0;
```

For every neighbor:

```java
color[neighbor] = 1 - color[node];
```

---

# 31. Why `1 - color[node]`?

If:

```text
color[node] = 0
```

then:

```text
1 - 0 = 1
```

If:

```text
color[node] = 1
```

then:

```text
1 - 1 = 0
```

Therefore:

```text
0 → 1
1 → 0
```

It always gives the opposite color.

This is the key coloring trick:

```java
color[neighbor] = 1 - color[node];
```

---

# 32. Bipartite Recognition Logic

At every edge:

```text
current → neighbor
```

there are two cases.

## Case 1 — Neighbor is uncolored

```text
color[neighbor] == -1
```

Assign opposite color:

```java
color[neighbor] = 1 - color[current];
```

Then continue DFS/BFS.

---

## Case 2 — Neighbor is already colored

Check:

```java
color[neighbor] == color[current]
```

If true:

```text
CONTRADICTION
```

Therefore:

```text
NOT BIPARTITE
```

If different:

```text
OK
```

---

# 33. Bipartite Core Rule

Memorize this:

```text
neighbor uncolored
        ↓
assign opposite color

neighbor already colored
        ↓
must be opposite

same color
        ↓
NOT BIPARTITE
```

Or simply:

```text
uncolored → assign opposite
colored   → verify opposite
```

---

# 34. Bipartite DFS Template

```java
boolean dfs(int node) {

    for (int neighbor : graph[node]) {

        if (color[neighbor] == -1) {

            color[neighbor] = 1 - color[node];

            if (!dfs(neighbor)) {
                return false;
            }

        } else if (color[neighbor] == color[node]) {

            return false;
        }
    }

    return true;
}
```

Important:

Before calling:

```java
dfs(start)
```

we must give the starting node a color:

```java
color[start] = 0;
```

---

# 35. Disconnected Graph + Bipartite

This is an important edge case.

Example:

```text
0 --- 1

2 --- 3

4 --- 5
```

If we only do:

```java
color[0] = 0;
dfs(0);
```

we only check the first component.

Therefore:

```java
for (int i = 0; i < n; i++) {

    if (color[i] == -1) {

        color[i] = 0;

        if (!dfs(i)) {
            return false;
        }
    }
}
```

Why?

Because:

> **Every connected component must be bipartite.**

---

# 36. Bipartite Graph and Odd Cycles

A useful deeper connection:

> A graph is bipartite if and only if it does not contain an odd-length cycle.

## Even Cycle

```text
0 ---- 1
|      |
3 ---- 2
```

Cycle:

```text
0 → 1 → 2 → 3 → 0
```

Length:

```text
4
```

Can be 2-colored.

---

## Odd Cycle

```text
    0
   / \
  1---2
```

Cycle:

```text
0 → 1 → 2 → 0
```

Length:

```text
3
```

Try coloring:

```text
0 = color 0

1 = color 1
2 = color 1
```

But:

```text
1 --- 2
```

means both endpoints have the same color.

Contradiction.

Therefore:

```text
NOT BIPARTITE
```

---

# 37. Important Bipartite Edge Case — Self Loop

Example:

```text
0 ----> 0
```

The node would need to have a different color from itself.

Impossible.

Therefore:

```text
self-loop → not bipartite
```

---

# 38. Graph Pattern Recognition Cheat Sheet

| If the problem says... | Think... |
|---|---|
| Visit / explore / traverse | DFS / BFS |
| Can reach destination? | DFS / BFS |
| Is there a path? | DFS / BFS |
| Number of groups | Connected Components |
| Number of networks | Connected Components |
| Number of islands | Grid DFS / BFS |
| Connected regions | Grid DFS / BFS |
| Minimum hops | BFS |
| Minimum number of edges | BFS |
| Cycle in undirected graph | DFS/BFS + parent |
| Cycle in directed graph | DFS + path state |
| Two groups | Bipartite |
| Two teams | Bipartite |
| Two colors | Bipartite |
| Adjacent nodes must be different | Bipartite |

---

# 39. Pattern Recognition Flow

When you see a graph problem, follow this order.

```text
GRAPH
  ↓
What are the nodes?
  ↓
What are the edges?
  ↓
Directed or Undirected?
  ↓
Weighted or Unweighted?
  ↓
What exactly is being asked?
```

Then:

```text
Just explore?
    ↓
DFS / BFS
```

```text
Can I reach A → B?
    ↓
DFS / BFS
```

```text
How many groups?
    ↓
Connected Components
```

```text
Grid / Islands / Regions?
    ↓
Grid DFS / BFS
```

```text
Minimum hops?
    ↓
BFS
```

```text
Cycle?
    ↓
Directed or Undirected?
```

```text
Undirected cycle
    ↓
Parent
```

```text
Directed cycle
    ↓
Current DFS path
```

```text
Two groups / two colors?
    ↓
Bipartite
    ↓
2-Coloring
```

---

# 40. DFS Mental Template

Whenever you recognize a DFS problem, start with:

```text
DFS(node)
```

Think:

```text
1. Mark node
2. Process node
3. Visit neighbors
4. Recursively explore unvisited neighbors
```

Basic template:

```java
void dfs(int node) {

    visited[node] = true;

    // process node

    for (int neighbor : graph[node]) {

        if (!visited[neighbor]) {

            dfs(neighbor);
        }
    }
}
```

Then modify the template according to the pattern.

---

# 41. DFS Variations We Have Learned

## Normal DFS

```text
DFS(node)
```

Used for:

```text
Traversal
Connected Components
Reachability
```

---

## DFS + Parent

```text
DFS(node, parent)
```

Used for:

```text
Undirected Cycle Detection
```

Core check:

```java
visited[neighbor] && neighbor != parent
```

---

## DFS + Path State

```text
visited[]
pathVisited[]
```

Used for:

```text
Directed Cycle Detection
```

Core check:

```java
pathVisited[neighbor]
```

---

## DFS + Color

```text
color[]
```

Used for:

```text
Bipartite Checking
```

Core checks:

```java
color[neighbor] == -1
```

and:

```java
color[neighbor] == color[node]
```

---

# 42. Common Mistakes

## Mistake 1 — Forgetting `visited[]`

Without visited tracking, a graph can repeatedly travel through the same nodes.

Example:

```text
0 --- 1
|     |
3 --- 2
```

You could repeatedly travel:

```text
0 → 1 → 2 → 3 → 0 → ...
```

This can cause:

```text
infinite recursion
```

or repeated processing.

---

# 43. Mistake 2 — Starting DFS Only Once

For a disconnected graph:

```java
dfs(0);
```

may not visit everything.

Instead:

```java
for (int i = 0; i < n; i++) {

    if (!visited[i]) {
        dfs(i);
    }
}
```

when the entire graph needs to be processed.

---

# 44. Mistake 3 — Same Cycle Logic for Both Graph Types

Don't use the same logic blindly.

```text
Undirected
    ↓
parent matters
```

```text
Directed
    ↓
current DFS path matters
```

---

# 45. Mistake 4 — Using `visited[]` for Bipartite

Bipartite checking is not simply:

```text
visited / unvisited
```

We need:

```text
color[]
```

because we need to know:

> What group/color does this node belong to?

---

# 46. Mistake 5 — Coloring Without Checking

Don't blindly assign a new color to an already-colored node.

First check:

```text
Is neighbor uncolored?
```

If yes:

```java
color[neighbor] = 1 - color[node];
```

If already colored:

```text
verify that it is the opposite color
```

---

# 47. Mistake 6 — Forgetting the Parent in Undirected Cycle Detection

This is wrong:

```java
if (visited[neighbor]) {
    return true;
}
```

Why?

Because:

```text
0 -- 1
```

is stored as:

```text
0 → 1
1 → 0
```

When at node 1, seeing node 0 again is normal.

Correct idea:

```java
if (visited[neighbor] && neighbor != parent) {
    return true;
}
```

---

# 48. Edge Cases Checklist

Before submitting a graph solution, think about:

```text
[ ] Empty graph

[ ] One node

[ ] No edges

[ ] One edge

[ ] Disconnected graph

[ ] Single connected component

[ ] Self-loop

[ ] Duplicate edges

[ ] Cycles

[ ] No cycles

[ ] Source = destination

[ ] Grid boundary cells

[ ] Empty grid

[ ] One-row grid

[ ] One-column grid

[ ] All cells connected

[ ] No cells connected
```

Not every edge case applies to every problem.

---

# 49. Interview Pattern Recognition

When an interviewer gives you a graph problem, don't immediately start coding.

First say/think:

```text
1. What are the nodes?

2. What are the edges?

3. Is it directed or undirected?

4. Is it weighted or unweighted?

5. Can the graph be disconnected?

6. What exactly is being asked?

7. Is this traversal?

8. Is this reachability?

9. Is this connected components?

10. Is this a grid traversal?

11. Is this cycle detection?

12. Is this bipartite?

13. What edge cases exist?
```

This gives you a structured way to recognize the pattern.

---

# 50. Our Current Graph Learning Boundary

## COMPLETED SO FAR

We have learned these patterns:

```text
1. Graph basics
2. DFS
3. BFS
4. Connected Components
5. Grid DFS/BFS
6. Path / Reachability
7. Undirected Cycle Detection
8. Directed Cycle Detection
9. Bipartite Graph
10. 2-Coloring
```

## NOT YET INCLUDED

We have NOT learned these deeply yet:

```text
- Topological Sort
- Shortest Path algorithms in depth
- Dijkstra
- Bellman-Ford
- Floyd-Warshall
- Union Find / DSU
- Minimum Spanning Tree
- Advanced graph algorithms
```

So for now, **stop at Bipartite / 2-Coloring**.

The next goal is NOT to keep adding patterns.

The goal is:

> **Practice these patterns repeatedly until we can recognize them quickly and code them confidently.**

---

# 51. Final Mental Model

The most important thing to remember:

```text
                 GRAPH PROBLEM
                       |
                       ↓
             What is being asked?
                       |
        ┌──────────────┼──────────────┐
        ↓              ↓              ↓
     Explore?       Groups?        Reachable?
        ↓              ↓              ↓
    DFS/BFS       Components       DFS/BFS
        |
        |
        ├── Minimum hops?
        |       ↓
        |      BFS
        |
        ├── Cycle?
        |       |
        |       ├── Undirected → Parent
        |       |
        |       └── Directed → DFS Path
        |
        └── Two groups/colors?
                ↓
            Bipartite
                ↓
           2-Coloring
```

## The Golden Rule

> **Don't memorize the solution first. Recognize the pattern first.**

Once you recognize:

```text
"number of groups"
```

you should think:

```text
Connected Components
```

Once you recognize:

```text
"can I reach B from A?"
```

you should think:

```text
DFS / BFS
```

Once you recognize:

```text
"cycle"
```

you should immediately ask:

```text
Directed or Undirected?
```

Once you recognize:

```text
"two groups / two colors"
```

you should think:

```text
Bipartite
→ 2-coloring
→ DFS/BFS
```

That pattern-recognition skill is what we are trying to master before moving forward.