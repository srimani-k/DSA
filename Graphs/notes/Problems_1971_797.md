# Graph Problems — Revision Notes

## 1. LeetCode 1971 — Find if Path Exists in Graph

**Pattern:** DFS + Visited Array
**Goal:** Check whether a path exists from `source` to `destination`.

### Approach

1. Build an adjacency list. Add both directions because the graph is undirected.
2. Start DFS from `source`.
3. If `node == destination`, return `true`.
4. Mark the current node visited and explore unvisited neighbors.
5. If DFS finishes without finding the destination, return `false`.

### Issues We Faced

* Initially checked `neighbor == destination` only when the neighbor was already visited. This missed destinations reached for the first time.
* **Fix:** Check `node == destination` at the beginning of DFS.
* `parent` is unnecessary for reachability; it is used in undirected cycle detection.

### Edge Cases

* `source == destination` → `true`.
* Destination is disconnected from source → `false`.
* Cycles → use `visited[]` to avoid revisiting nodes.
* Isolated nodes → DFS handles them naturally.

**Complexity:** Time `O(V + E)`; space `O(V)`.

---

## 2. LeetCode 797 — All Paths From Source to Target

**Pattern:** DFS + Backtracking
**Goal:** Find every path from node `0` to node `n - 1` in a DAG.

### Approach

1. Maintain `currentPath` and `allPaths`.
2. Add the current node to `currentPath`.
3. If the destination is reached, save a copy: `allPaths.add(new ArrayList<>(currentPath))`.
4. Otherwise, recursively explore every outgoing neighbor.
5. Remove the current node when returning to restore the previous path.

### Issues We Faced

* **Why backtrack?** Remove the current node so it doesn't remain in another branch's path.
* **Why copy the path?** Adding `currentPath` directly stores the same mutable list. Later changes would affect saved results.
* **Why no `visited[]`?** We're collecting all paths, and nodes may legitimately appear in different paths. The DAG guarantees no cycles.

### Edge Cases

* Direct edge `0 → destination` → one path.
* Multiple branches → explore every neighbor.
* No path to destination → return an empty list.
* Single node → the path is `[0]`.
* Many possible paths → output size can grow exponentially.

**Complexity:** Depends on the total size of all paths returned; the number of paths can be exponential.

---

## Key Difference

| Problem           | What DFS finds          | Main technique               |
| ----------------- | ----------------------- | ---------------------------- |
| 1971 — Valid Path | Whether one path exists | `visited[]`                  |
| 797 — All Paths   | Every valid path        | `currentPath` + backtracking |
