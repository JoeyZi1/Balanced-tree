# Balanced-tree
Balanced search tree is a classical data structure. AVL tree and Red-black tree are two representative kinds of balanced tree. This is a trial to implement both of them using Java.



## AVL tree
I use deep recursion to implement insert and delete operations in AVL tree, the core codes are only about 100 lines.

I use a class `TreeVisualize.java` to print tree structure in terminal, you can run ` AVLTreeTest.java` directly to test the the AVL tree.



## Red-balck tree
The delete operation in red-balck tree is kind of confusing. I use equivalent 2-3-4 tree to help comprehend those complex cases, then I find it easy to understand these cases without too much memorize.

Also, you can run ` RBTreeTest.java` directly to test the the red-black tree. Here is a standard red-black tree visualization website https://www.cs.usfca.edu/~galles/visualization/RedBlack.html, because both our codes use predecessor replacement in delete operation, the result of our trees are theoretically the same.
