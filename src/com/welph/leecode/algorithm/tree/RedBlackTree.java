package com.welph.leecode.algorithm.tree;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [红黑树]
 * 1.任何一个节点都有颜色，黑色或者红色。
 * 2.根节点是黑色的。
 * 3.父子节点之间不能出现两个连续的红节点。
 * 4.任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等。
 * 5.空节点被认为是黑色的。
 * @since 2021/8/24 16:13
 */
public class RedBlackTree {

    public static final boolean BLACK = true;
    public static final boolean RED = false;

    Node root;
    int size = 0;

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        tree.put(1);
        tree.put(5);
        tree.put(2);
        tree.put(8);
        tree.put(4);
        tree.put(9);
        tree.put(10);
        System.out.println(1);
    }

    public void remove(int value) {
        //找到对应的值
        Node cur = root;
        while (cur != null) {
            if (cur.value > value) {
                cur = cur.left;
            } else if (cur.value < value) {
                cur = cur.right;
            } else {
                break;
            }
        }
        deleteNode(cur);
    }

    /**
     * --------------使用中序遍历的[中继节点]补充需要删除的代替
     * //若是替换节点为黑色, 则可能影响定义4, 每条路径相同的黑色个数
     */
    private void deleteNode(Node node) {
        size--;

        //找到中继节点(所属子节点)
        if (node.left != null && node.right != null) {
            Node next = successor(node);
            node.value = next.value;
            node = next;
        }

        Node replace = node.left != null ? node.left : node.right;
        if (replace != null) {//原本的node或者next节点, 需要将left或right补全到对应的节点上去
            //replace替换node节点
            replace.parent = node.parent;
            if (node.parent == null) {
                root = replace;
            } else if (node.parent.left == node) {
                node.parent.left = replace;
            } else {
                node.parent.right = replace;
            }

            node.left = node.right = node.parent = null;
            if (node.color == BLACK)
                fixAfterDelete(replace); //进行树的修复
        } else if (node.parent == null) {
            root = null;
        } else {
            if (node.color == BLACK)
                fixAfterDelete(node);
            if (node.parent != null) {
                if (node == node.parent.left) {
                    node.parent.left = null;
                } else if (node == node.parent.right) {
                    node.parent.right = null;
                }
                node.parent = null;
            }
        }
    }

    private void fixAfterDelete(Node node) {
        while (node != root && color(node) == BLACK) {
            if (node == left(parent(node))) {
                Node sib = right(parent(node));
                if (color(sib) == RED) { //node->parent->uncle 一条直线(黑->红->黑)
                    setColor(sib, BLACK);
                    setColor(parent(node), RED);
                    rotateLeft(parent(node));
                    sib = right(parent(node));
                }

                if (color(left(sib)) == BLACK
                        && color(right(sib)) == BLACK) {
                    setColor(sib, RED);
                    node = parent(node);  //这里在parent和right(parent)都是red,但对于局部是平衡的, 最后会统一设置为黑色
                } else {
                    if (color(right(sib)) == BLACK) { //parent的右节点拉平了
                        setColor(left(sib), BLACK);
                        setColor(sib, RED);
                        rotateRight(sib);
                        sib = right(parent(node));
                    }
                    setColor(sib, color(parent(node)));
                    setColor(parent(node), BLACK);
                    setColor(right(sib), BLACK);
                    rotateRight(left(parent(node)));
                    node = root;// 完结
                }
            } else {//镜像
                Node sib = left(parent(node));
                if (color(sib) == RED) {
                    setColor(sib, BLACK);
                    setColor(parent(node), RED);
                    rotateRight(parent(node));
                    sib = left(parent(node));
                }

                if (color(left(sib)) == BLACK
                        && color(right(sib)) == BLACK) {
                    setColor(sib, RED);
                    node = parent(node);
                } else {
                    if (color(left(sib)) == BLACK) {
                        setColor(right(sib), BLACK);
                        setColor(sib, RED);
                        rotateLeft(sib);
                        sib = left(parent(node));
                    }
                    setColor(sib, color(parent(node)));
                    setColor(parent(node), BLACK);
                    setColor(left(sib), BLACK);
                    rotateRight(right(parent(node)));
                    node = root;
                }
            }
        }
        setColor(node, BLACK);//能进来都是黑色节点
    }

    private Node successor(Node node) {
        if (null == node) {
            return null;
        } else if (null != node.right) {
            Node target = node.right;
            while (target.left != null) {
                target = target.left;
            }
            return target;
        } else {
            //向上找父节点
            Node target = node.parent;
            Node cur = node;
            while (target != null && cur == target.right) {
                cur = target;
                target = target.parent;
            }
            return target;
        }
    }

    public void put(int value) {
        //找到对应的parent;
        if (root == null) {
            root = new Node(value, null);
            size++;
            return;
        }
        Node parent;
        Node cur = root;
        do {
            parent = cur;
            if (cur.value > value) {
                cur = cur.left;
            } else if (cur.value < value) {
                cur = cur.right;
            } else {
                //这里不考虑重复数据
                return;
            }
        } while (cur != null);

        Node node = new Node(value, parent);
        if (parent.value > value) {
            parent.left = node;
        } else {
            parent.right = node;
        }
        //节点插入
        fixAfterInsert(node);
        size++;
    }

    /**
     * 叔叔节点也为红色。
     * 叔叔节点为空或黑色，且祖父节点、父节点和新节点处于一条斜线上。
     * 叔叔节点为空或黑色，且祖父节点、父节点和新节点不处于一条斜线上。
     */
    private void fixAfterInsert(Node node) {
        node.color = RED;

        while (node != null && node != root && node.parent.color == RED) {
            //左节点
            if (parent(node) == left(parent(parent(node)))) {
                Node uncle = right(parent(parent(node)));
                if (color(uncle) == RED) {
                    //父节点设置为黑色 祖父设置为红色
                    setColor(parent(node), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parent(parent(node)), RED);
                    node = parent(parent(node));
                } else {
                    //因为父节点为左节点, 若新节点为父节点的右节点,需要左旋一次
                    if (node == right(parent(node))) {
                        node = parent(node);
                        rotateLeft(node);//左旋
                    }
                    setColor(parent(node), BLACK);
                    setColor(parent(parent(node)), RED);
                    rotateRight(parent(parent(node)));//右旋
                }
            } else {
                Node uncle = left(parent(parent(node)));
                if (color(uncle) == RED) {
                    setColor(parent(node), BLACK);
                    setColor(uncle, BLACK);
                    setColor(parent(parent(node)), RED);
                    node = parent(parent(node));
                } else {
                    if (node == left(parent(node))) {
                        node = parent(node);
                        rotateRight(node);
                    }
                    setColor(parent(node), BLACK);
                    setColor(parent(parent(node)), RED);
                    rotateLeft(parent(parent(node)));  //祖父节点进行
                }
            }
        }
        root.color = BLACK;
    }

    private void rotateRight(Node node) {
        if (node != null) {
            Node r = node.left;

            node.left = r.right;
            if (r.right != null)
                r.right.parent = node;

            r.parent = node.parent;
            if (node.parent == null) {
                root = r;
            } else if (node.parent.left == node) {
                node.parent.left = r;
            } else
                node.parent.right = r;

            r.right = node;
            node.parent = r;
        }
    }

    private void rotateLeft(Node node) {
        if (node != null) {
            Node r = node.right;

            node.right = r.left;
            if (r.left != null)
                r.left.parent = node;

            r.parent = node.parent;
            //root节点
            if (node.parent == null)
                root = r;
            else if (node.parent.left == node) //更新祖父节点的rl
                node.parent.left = r;
            else
                node.parent.right = r;

            r.left = node;
            node.parent = r;
        }
    }

    public Node parent(Node node) {
        return node == null ? null : node.parent;
    }

    public Node right(Node node) {
        return node == null ? null : node.right;
    }

    public Node left(Node node) {
        return node == null ? null : node.left;
    }

    public boolean color(Node node) {
        return node == null ? BLACK : node.color;
    }

    public void setColor(Node node, boolean color) {
        if (node != null)
            node.color = color;
    }

    static class Node {


        int value;
        Node parent;
        Node left;
        Node right;
        boolean color = BLACK;

        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
        }
    }
}
