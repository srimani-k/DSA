# 🌳 Tree Recursion Patterns

> Instead of memorizing problems, identify the **pattern**.
> Most Binary Tree recursion problems belong to one of these patterns.

---

# 1. Count Pattern

### Template

```java
if(root == null){
    return 0;
}

int left = helper(root.left);
int right = helper(root.right);

int current = (condition) ? 1 : 0;

return current + left + right;
```

### Mental Model

* Ask left subtree for its count.
* Ask right subtree for its count.
* Decide whether the **current node contributes 1 or 0**.
* Return the total.

### Problems

* Count Total Nodes
* Count Leaf Nodes
* Count Internal Nodes
* Count Even Nodes
* Count Odd Nodes
* Count Nodes Greater Than X
* Count Nodes in Range `[L, R]`
* Count Nodes with Exactly One Child
* Count Occurrences of X

---

# 2. Sum Pattern

### Template

```java
if(root == null){
    return 0;
}

int left = helper(root.left);
int right = helper(root.right);

int current = (condition) ? root.value : 0;

return current + left + right;
```

### Mental Model

* Ask left subtree for its sum.
* Ask right subtree for its sum.
* Decide whether the **current node contributes its value or 0**.
* Return the total.

### Problems

* Sum of All Nodes
* Sum of Left Leaves
* Sum of Even Nodes
* Sum of Odd Nodes
* Sum of Nodes Greater Than X
* Sum of Nodes in Range

---

# 3. Maximum / Minimum Pattern

### Maximum

```java
if(root == null){
    return Integer.MIN_VALUE;
}

int left = helper(root.left);
int right = helper(root.right);

return Math.max(root.value, Math.max(left, right));
```

### Minimum

```java
if(root == null){
    return Integer.MAX_VALUE;
}

int left = helper(root.left);
int right = helper(root.right);

return Math.min(root.value, Math.min(left, right));
```

### Mental Model

Every subtree tells its parent:

> "This is the maximum/minimum value found in my subtree."

### Problems

* Maximum Value
* Minimum Value

---

# 4. Height Pattern

### Template

```java
if(root == null){
    return 0;
}

int left = helper(root.left);
int right = helper(root.right);

return 1 + Math.max(left, right);
```

### Mental Model

Every subtree tells its parent:

> "My height is..."

Parent computes:

```
1 + max(leftHeight, rightHeight)
```

### Problems

* Maximum Depth
* Height of Tree

---

# 5. Search Pattern

### Template

```java
if(root == null){
    return false;
}

if(root.value == x){
    return true;
}

boolean left = helper(root.left);
boolean right = helper(root.right);

return left || right;
```

### Mental Model

Ask:

> "Am I the answer?"

If not,

> "Left child, did you find it?"

> "Right child, did you find it?"

### Problems

* Search Value
* Find Node
* Search Condition

---

# 6. Compare Pattern

### Template

```java
if(p == null && q == null){
    return true;
}

if(p == null || q == null){
    return false;
}

return p.value == q.value
        && helper(p.left, q.left)
        && helper(p.right, q.right);
```

### Mental Model

Current nodes must match.

AND

Left subtrees must match.

AND

Right subtrees must match.

### Problems

* Same Tree
* Compare Trees

---

# 7. Mirror Compare Pattern

### Template

```java
return p.value == q.value
    && helper(p.left, q.right)
    && helper(p.right, q.left);
```

### Problems

* Symmetric Tree

---

# 8. Modify Tree Pattern

### Template

```java
Node left = helper(root.left);
Node right = helper(root.right);

root.left = right;
root.right = left;

return root;
```

### Mental Model

Modify children first.

Then modify the current node.

### Problems

* Invert Binary Tree
* Mirror Tree

---

# 9. Optimized Height Pattern (Balanced Tree)

### Template

```java
if(root == null){
    return 0;
}

int left = helper(root.left);
int right = helper(root.right);

if(left == -1 || right == -1){
    return -1;
}

if(Math.abs(left - right) > 1){
    return -1;
}

return 1 + Math.max(left, right);
```

### Mental Model

The helper returns:

* Height → if subtree is balanced.
* `-1` → if subtree is already unbalanced.

`-1` is **not a height**.

It is a **special signal**.

### Problem

* Balanced Binary Tree

---

# General Recursive Flow

Almost every tree recursion follows this order:

```
1. Base Case

↓

2. Ask Children

↓

3. Handle Special Signals (if any)

↓

4. Solve Current Node

↓

5. Return Answer
```

---

# Quick Pattern Cheat Sheet

| Problem Type   | Current Contribution                |   |        |
| -------------- | ----------------------------------- | - | ------ |
| Count          | `1` or `0`                          |   |        |
| Sum            | `root.value` or `0`                 |   |        |
| Maximum        | `Math.max(root.value, left, right)` |   |        |
| Minimum        | `Math.min(root.value, left, right)` |   |        |
| Height         | `1 + max(left, right)`              |   |        |
| Search         | `left                               |   | right` |
| Compare        | `condition && left && right`        |   |        |
| Mirror Compare | `condition && left-right swapped`   |   |        |
| Modify         | Change current node after recursion |   |        |

---

# Golden Rule

Don't memorize solutions.

Ask yourself:

1. **What should my helper return?**
2. **What is the base case?**
3. **What information do I need from my left and right children?**
4. **How does the current node contribute?**
5. **What should I return to my parent?**

If you can answer these five questions, you can derive almost every Binary Tree recursion problem in an interview.
