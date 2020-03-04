package com.neko;

import java.util.ArrayList;
import java.util.List;

public class BSTFunc {
    /*
        part c of the question 1.

        i. Recursive implementation of Binary Search tree functions.
            These include:
                a) void insertRec (BSTNode root, int item);
                b) BSTNode deleteRec (BSTNode root, int item);
                c) BSTNode findNextRec (BSTNode searchpoint);
                d) BSTNode findPrevRec (BSTNode searchpoint);
                e) BSTNode findMinRec (BSTNode root);
                f) BSTNode findMaxRec (BSTNode root);

            ps. going to refer them after as letters.

         ii. Edge cases
            a) edge case on when item = null. edge case on when root = null, when comparable is not comparable in root. also, when there is equal element inside tree.
            b) edge case on when item does not exist, when item = null, when root = null, when comparable is not comparable in root
            c) edge case when the given searchpoint is null, or there is no next node
            d) edge case when the given searchpoint is null, or there is no previous node
            e) edge case when given root is null
            f) edge case when given root is null

         iii. Examples of given output and input
            all cases input and output is illustrated in code.
            I have made BSTNode class in order to work as a tree's node.

         iv. all added together in the final code.

         v. same as above!

         vi. same as above.

         v. Improvements and Takeaway of the algorithms.

     */


    //node class. nothing usual
    static class BSTNode {
        int data;
        BSTNode left, right;

        BSTNode(int data) {
            this.data = data;
            this.left = this.right = null;
        }

        public void print() {
            print("", this, false);
        }

        public void print(String prefix, BSTNode n, boolean isLeft) {
            if (n != null) {

                System.out.println (prefix + (isLeft ? "├── " : "└── ") + n.data);

                if(n.left == null && n.right == null)
                    return;
                print(prefix + (isLeft ? "│   " : "    "), n.right, true);
                print(prefix + (isLeft ? "│   " : "    "), n.left, false);
            }
            else
            {
                System.out.println (prefix + (isLeft ? "├── " : "└── ") + "(empty)");
            }
        }
    }


    //recursive solutions
    static BSTNode insertRec(BSTNode tree, int data) {

        BSTNode newNode = new BSTNode(data);

        if (tree == null) {
            tree = newNode;
            return newNode;
        }

        if (data < tree.data) {
            if (tree.right == null)
                tree.right = newNode;
            else {
                newNode = insertRec(tree.right, data);
            }
        } else {
            if (tree.left == null)
                tree.left = newNode;
            else {
                newNode = insertRec(tree.left, data);
            }
        }
        return newNode;
    }

    static BSTNode deleteRec(BSTNode tree, BSTNode parent, int data) {
        if (tree == null) {
            return null;
        }

        if (data == tree.data) {
            BSTNode prev = findPrevRec(tree);

            if (tree.left == null && tree.right != null) {
                if (tree.data < parent.data) {
                    parent.left = tree.right;
                } else {
                    parent.right = tree.right;
                }
            } else if (tree.right == null && tree.left != null) {
                if (tree.data < parent.data) {
                    parent.left = tree.left;
                } else {
                    parent.right = tree.left;
                }
            } else if (tree.right == null && tree.left == null) {
                if (tree.data < parent.data) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                int temp = prev.data;
                prev.data = tree.data;
                tree.data = temp;

                return deleteRec(tree.left, tree, data);
            }
        } else if (data < tree.data) {
            if (tree.left == null)
                return null;
            else {
                return deleteRec(tree.left, tree, data);
            }
        } else {
            if (tree.right == null)
                return null;
            else {
                return deleteRec(tree.right, tree, data);
            }
        }
        return null;
    }

    static BSTNode findNextRec(BSTNode root) {
        return findMinRec(root.right);
    }

    static BSTNode findPrevRec(BSTNode root) {
        return findMaxRec(root.left);
    }

    static BSTNode findMinRec(BSTNode root) {
        if (root.left != null)
            return findMinRec(root.left);
        else
            return root;
    }

    static BSTNode findMaxRec(BSTNode root) {
        if (root.right != null)
            return findMaxRec(root.right);
        else
            return root;
    }

    //iterative solutions

    static BSTNode insertIter(BSTNode tree, int data) {

        BSTNode newNode = new BSTNode(data);
        BSTNode searchpoint = tree;

        while (true) {
            if (searchpoint == null) {
                searchpoint = newNode;
                return searchpoint;
            }

            if (data < searchpoint.data) {
                if (searchpoint.left == null) {
                    searchpoint.left = newNode;
                    return newNode;
                }
                else {
                    searchpoint = searchpoint.left;
                    continue;
                }

            } else {
                if (searchpoint.right == null) {
                    searchpoint.right = newNode;
                    return newNode;
                }
                else {
                    searchpoint = searchpoint.right;
                    continue;
                }

            }
        }
    }

    static BSTNode deleteIter(BSTNode tree, BSTNode parent, int data) {
        BSTNode check = tree;
        while (true) {
            if (check == null) {
                return null;
            }

            if (data == check.data) {
                BSTNode prev = findPrevIter(check);

                if (check.left == null && check.right != null) {
                    if (check.data < parent.data) {
                        parent.left = check.right;
                    } else {
                        parent.right = check.right;
                    }
                } else if (check.right == null && tree.left != null) {
                    if (check.data < parent.data) {
                        parent.left = check.left;
                    } else {
                        parent.right = check.left;
                    }
                } else if (check.right == null && check.left == null) {
                    if (check.data < parent.data) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else {
                    int temp = prev.data;
                    prev.data = check.data;
                    check.data = temp;

                    parent = check;
                    check = check.left;
                    continue;
                }
            } else if (data < check.data) {
                if (check.left == null)
                    return null;
                else {
                    parent = check;
                    check = check.left;
                }
            } else {
                if (tree.right == null)
                    return null;
                else {
                    parent = check;
                    check = check.right;
                }
            }
        }
    }

    static BSTNode findNextIter(BSTNode root) {
        return findMinIter(root.right);
    }

    static BSTNode findPrevIter(BSTNode root) {
        return findMaxIter(root.left);
    }

    static BSTNode findMinIter(BSTNode root) {
        BSTNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    static BSTNode findMaxIter(BSTNode root) {
        BSTNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }


}
