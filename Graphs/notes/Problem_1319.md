
# LC 1319 — Number of Operations to Make Network Connected

## Pattern
Connected Components + DFS

## Approach
1. **Check cables:** If `connections.length < n - 1`, return `-1` because there aren't enough cables to connect all computers.
2. **Build adjacency list:** For every cable `[u, v]`, add `v` to `u`'s neighbors and `u` to `v`'s neighbors.
3. **Count components:** Run DFS from every unvisited computer. Each new DFS traversal identifies one connected component.
4. **Calculate moves:** Return `components - 1`.

## Key Intuition
Each cable move can connect two separate components, reducing the component count by one.

Therefore, connecting `k` components requires `k - 1` moves, provided enough cables exist.

## Complexity
- Time: O(n + m) — build the graph and visit all computers and cables.
- Space: O(n + m) — adjacency list, visited array, and DFS stack.

`n` = number of computers, `m` = number of cables.
