package com.neko;

public class AVLFunc {
    static class AVLNode{
        int data;
        int depth;
        AVLNode left,right;
        AVLNode parent;

        AVLNode (int data)
        {
            this.data = data;
            this.depth = 0;
            this.left = this.right = this.parent = null;
        }

        int getBF()
        {
            int lh = this.left != null ? this.left.getHeight() : 0;
            int rh = this.right != null ? this.right.getHeight() : 0;
            return lh - rh;
        }

        int getHeight()
        {
            int lf = 0;
            int rf = 0;

            if(this.left != null)
                lf = this.left.getHeight();

            if(this.right != null)
                rf = this.right.getHeight();

            return lf>rf?lf+1:rf+1;
        }

        public void print() {
            print("", this, false);
        }

        public void print(String prefix, AVLNode n, boolean isLeft) {
            if (n != null) {

                System.out.println (prefix + (isLeft ? "├── " : "└── ") + n.data +":"+ n.getBF());

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


    static AVLNode insertIter(AVLNode tree, int data)
    {
        AVLNode newNode = new AVLNode(data);
        AVLNode searchpoint = tree;

        while(true)
        {
            if(searchpoint == null)
            {
                searchpoint = newNode;
                return searchpoint;
            }

            if(data < searchpoint.data)
            {
                if(searchpoint.right == null)
                {
                    newNode.parent = searchpoint;
                    searchpoint.right = newNode;
                    break;
                }
                else
                {
                    searchpoint = searchpoint.right;
                    continue;
                }


            }
            else
            {
                if(tree.left == null)
                {
                    newNode.parent = searchpoint;
                    searchpoint.left = newNode;
                    break;
                }
                else{

                    searchpoint = searchpoint.left;
                    continue;
                }
            }
        }

        balanceInsertIter(newNode);
        return newNode;
    }

    static void balanceInsertIter(AVLNode tree)
    {
        AVLNode check = tree;
        AVLNode pathroot1 = null;
        AVLNode pathroot2 = null;
        AVLNode pathroot3 = null;

        boolean oneto2right = false;
        boolean twoto3right = false;

        while(true)
        {
            check = check.parent;
            if(check == null)
                return;

            if(Math.abs(check.getBF()) >= 2)
                break;
        }


        pathroot1 = check;

        if(pathroot1 == null)
            return;


        oneto2right = tree.data < pathroot1.data;
        pathroot2 =  oneto2right ? pathroot1.right : pathroot1.left;
        //should not trigger, but fail-safe
        if(pathroot2 == null)
            return;


        twoto3right = tree.data < pathroot2.data;
        pathroot3 =  twoto3right? pathroot2.right:pathroot2.left;
        //another fail-safe
        if(pathroot3 == null)
            return;

        AVLNode T1 = null;
        AVLNode T2 = null;
        AVLNode T3 = null;
        AVLNode T4 = null;

        int p1d = pathroot1.data;
        int p2d = pathroot2.data;
        int p3d = pathroot3.data;



        if(oneto2right)
        {
            if(twoto3right)
            {
                //left - left

                T1 = pathroot3.right;
                T2 = pathroot3.left;
                T3 = pathroot2.left;
                T4 = pathroot1.left;


                pathroot1.left = pathroot2;
                pathroot2.parent = pathroot1;
                pathroot1.right = pathroot3;
                pathroot3.parent = pathroot1;

                pathroot1.data = p2d;
                pathroot2.data = p3d;
                pathroot3.data = p1d;

                pathroot2.right = T1;
                pathroot2.left = T2;
                pathroot3.right = T3;
                pathroot3.left = T4;


                if(T1!=null)
                    T1.parent = pathroot2;
                if(T2!=null)
                    T2.parent = pathroot2;
                if(T3!=null)
                    T3.parent = pathroot3;
                if(T4!=null)
                    T4.parent = pathroot3;
            }

            else
            {
                //left - right

                T1 = pathroot2.right;
                T2 = pathroot3.right;
                T3 = pathroot3.left;
                T4 = pathroot1.left;


                pathroot1.left = pathroot2;
                pathroot2.parent = pathroot1;
                pathroot1.right = pathroot3;
                pathroot3.parent = pathroot1;

                pathroot1.data = p3d;
                pathroot2.data = p2d;
                pathroot3.data = p1d;

                pathroot2.right = T1;
                pathroot2.left = T2;
                pathroot3.right = T3;
                pathroot3.left = T4;


                if(T1!=null)
                    T1.parent = pathroot2;
                if(T2!=null)
                    T2.parent = pathroot2;
                if(T3!=null)
                    T3.parent = pathroot3;
                if(T4!=null)
                    T4.parent = pathroot3;
            }
        }
        else
        {
            if(twoto3right)
            {
                //right - left

                T1 = pathroot1.left;
                T2 = pathroot2.left;
                T3 = pathroot3.left;
                T4 = pathroot3.right;


                pathroot1.left = pathroot2;
                pathroot2.parent = pathroot1;
                pathroot1.right = pathroot3;
                pathroot3.parent = pathroot1;

                pathroot1.data = p2d;
                pathroot2.data = p1d;
                pathroot3.data = p3d;

                pathroot2.right = T1;
                pathroot2.left = T2;
                pathroot3.right = T3;
                pathroot3.left = T4;


                if(T1!=null)
                    T1.parent = pathroot2;
                if(T2!=null)
                    T2.parent = pathroot2;
                if(T3!=null)
                    T3.parent = pathroot3;
                if(T4!=null)
                    T4.parent = pathroot3;
            }
            else
            {
                //right - right

                T1 = pathroot1.left;
                T2 = pathroot3.right;
                T3 = pathroot3.left;
                T4 = pathroot2.left;


                pathroot1.left = pathroot2;
                pathroot2.parent = pathroot1;
                pathroot1.right = pathroot3;
                pathroot3.parent = pathroot1;

                pathroot1.data = p3d;
                pathroot2.data = p1d;
                pathroot3.data = p2d;

                pathroot2.right = T1;
                pathroot2.left = T2;
                pathroot3.right = T3;
                pathroot3.left = T4;


                if(T1!=null)
                    T1.parent = pathroot2;
                if(T2!=null)
                    T2.parent = pathroot2;
                if(T3!=null)
                    T3.parent = pathroot3;
                if(T4!=null)
                    T4.parent = pathroot3;
            }
        }



    }

    static AVLNode deleteIter(AVLNode tree, AVLNode parent, int data)
    {
        AVLNode check = tree;
        while(true) {
            if (check == null) {
                return null;
            }

            if (data == check.data) {
                AVLNode prev = findPrevIter(check);

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
                }
                else{
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

    static AVLNode findNextIter(AVLNode root) {
        return findMinIter(root.right);
    }

    static AVLNode findPrevIter(AVLNode root) {
        return findMaxIter(root.left);
    }

    static AVLNode findMinIter(AVLNode root) {
        AVLNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    static AVLNode findMaxIter(AVLNode root) {
        AVLNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current;
    }
}
