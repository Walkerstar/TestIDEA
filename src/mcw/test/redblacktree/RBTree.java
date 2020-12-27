package mcw.test.redblacktree;


/**
 * 性质一：节点是红色或者是黑色；
 *
 * 性质二：根节点是黑色；
 *
 * 性质三：每个叶节点（NIL或空节点）是黑色；
 *
 * 性质四：每个红色节点的两个子节点都是黑色的（也就是说不存在两个连续的红色节点）；
 *
 * 性质五：从任一节点到其每个叶子节点的所有路径都包含相同数目的黑色节点；
 *
 * @author mcw 2020\2\3 0003-10:39
 */
public class RBTree<T extends Comparable<T>> {

    //根节点
    private RBTNode<T> mRoot;

    private static final boolean RED=false;
    private static final boolean BLACK=true;


    public class RBTNode<T extends Comparable<T>> {
        boolean color;      //颜色
        T key;              //键值
        RBTNode<T> left;    //左孩子
        RBTNode<T> right;   //右孩子
        RBTNode<T> parent;  //父节点

        public RBTNode(boolean color, T key, RBTNode<T> left, RBTNode<T> right, RBTNode<T> parent) {
            this.color = color;
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    /**
     * 左旋示意图
     *        px                              px
     *       /                               /
     *      x      (对 x 节点左旋)           y
     *    /  \     ===============>       /  \
     *   lx  y                           x    ry
     *      / \                         / \
     *    ly  ry                      lx  ly
     * @param x
     */
    private void leftRotate(RBTNode<T> x){
        //设置x的右孩子为y
        RBTNode<T> y=x.right;

        //将 “y的左孩子”  设置为 “x的右孩子”
        //如果y的左孩子非空，将 "x" 设为 “y的左孩子的父亲”
        x.right=y.left;
        if(y.left!=null){
            y.left.parent=x;
        }

        //将 “x的父亲” 设为 “y的父亲”
        y.parent=x.parent;

        //如果"x的父亲" 是空节点 ，则将y设为根节点
        if(x.parent==null){
            this.mRoot=y;
        }else {
            //如果 x 是父节点的左节点，则 y 为 “x的父节点的左节点 ”
            if(x.parent.left==x) {
                x.parent.left=y;
            } else {
                x.parent.right=y;  //如果 x 是父节点的右节点，则 y 为 “x的父节点的右节点 ”
            }
        }

        y.left=x;     //将 “x” 设为 “y的左孩子”
        x.parent=y;   //将“x的父节点” 设为 “y”
    }

    /**
     * 右旋示意图
     *
     *         py                                 py
     *         /                                 /
     *        y                                 x
     *      /  \       (对 y 节点右旋)         /  \
     *     x    ry     ==========>           lx   y
     *   /  \                                   /  \
     *  lx  rx                                 rx   ry
     *
     * @param y
     */
    private void rightRotate(RBTNode<T> y){
        //取当前节点的左孩子
        RBTNode<T> x = y.left;

        //将 “x的右孩子” 设为 “y的左孩子”
        //如果 “x的右孩子” 不为空。就将 “y” 设为 “x的右孩子的父亲”
        y.left=x.right;
        if(x.right!=null){
            x.right.parent=y;
        }
        //将 “y” 设为 “x的父亲”
        x.parent=y.parent;

        //如果 “y的父节点” 为空，则将 “x” 设为根节点
        if(y.parent==null){
            this.mRoot=x;
        }else {
            if(y.parent.left==y) {
                y.parent.left=x;
            } else {
                y.parent.right=x;
            }
        }

        x.right=y;
        y.parent=x;
    }

    /**
     * 内部接口 （外部不允许访问）
     * 将节点插入到红黑树中
     *
     * @param node 待插入的节点
     */
    private void insert(RBTNode<T> node){
       int cmp;
       RBTNode<T> y=null;
       RBTNode<T> x=this.mRoot;

       //1.将红黑树当做一棵二叉查找树，将节点添加到二叉查找树中
        while (x!=null){
            y=x;
            cmp=node.key.compareTo(x.key);
            if(cmp<0) {
                x=x.left;
            } else {
                x=x.right;
            }
        }

        node.parent=y;
        if(y!=null){
            cmp=node.key.compareTo(y.key);
            if(cmp<0) {
                y.left=node;
            } else {
                y.right=node;
            }
        }else {
            this.mRoot=node;
        }

        //2.设置节点的颜色为红色
        node.color=RED;

        //3.将它重新修正为一棵二叉查找树
        insertFixUp(node);
    }

    /**
     * 外部接口    新建节点（key），插入到红黑树中
     * @param key
     */
    public void insert(T key){
        RBTNode<T> node=new RBTNode<>(BLACK,key,null,null,null);
        //if(node!=null)
        insert(node);
    }

    /**
     * 插入节点后，修正红黑树
     * @param node
     */
    private void insertFixUp(RBTNode<T> node) {
        RBTNode<T> parent,gparent;

        //若父节点存在，且父节点的颜色是红色
        while((parent=parentOf(node))!=null && isRed(parent)){
            gparent=parentOf(parent);

            //若“父节点” 是“祖父节点的左孩子”
            if(parent==gparent.left){
                //1.叔叔节点是红色的
                RBTNode<T> uncle=gparent.right;
                if(uncle!=null && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    continue;
                }

                //2.叔叔节点是黑的，且当前节点是右孩子
                if(parent.right==node){
                    RBTNode<T> tmp;
                    leftRotate(parent);
                    tmp=parent;
                    parent=node;
                    node=tmp;
                }

                //3.叔叔节点是黑色，且当前节点是左孩子
                    setBlack(parent);
                    setRed(gparent);
                    rightRotate(gparent);
            }else { //若 “当前节点的父节点” 是 “当前节点的祖父节点的右孩子”
                //case 1.叔叔节点是红色的
                RBTNode<T> uncle=gparent.left;
                if((uncle!=null) && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node=gparent;
                    continue;
                }

                //case 2.叔叔节点是黑色，且当前节点是左孩子
                if(parent.left==node){
                    RBTNode<T> tmp;
                    rightRotate(parent);
                    tmp=parent;
                    parent=node;
                    node=tmp;
                }

                //case 3.叔叔节点是黑色，且当前节点是右孩子
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
        //将根节点设为黑色
        setBlack(this.mRoot);
    }

    /**
     * 内部接口    删除节点
     * @param node 待删除的节点
     */
    private void remove(RBTNode<T> node){
        RBTNode<T> child,parent;
        boolean color;

        //被删除的节点 左右孩子都不为空 的情况
        if((node.left!=null) && (node.right!=null)){
            //被删除节点的后继节点 （称为 取代节点）
            //用它来取代被删除节点的位置，然后再将 “被删节点” 去掉
            RBTNode<T> replace=node;

            //获取后继节点
            replace=replace.right;
            while(replace.left!=null) {
                replace=replace.left;
            }

            //node 不是根节点 （只有根节点不存在父亲节点）
            if(parentOf(node)!=null){
                if(parentOf(node).left==node) {
                    parentOf(node).left=replace;
                } else {
                    parentOf(node).right=replace;
                }
            }else {
                //node 是根节点 ，更新根节点
                this.mRoot=replace;
            }

            //child 是取代节点的右孩子，也是需要调整的节点
            //后继节点不存在左孩子
            child=replace.right;
            parent=parentOf(replace);
            //保存 取代节点 的颜色
            color=replace.color;

            // 被删除节点 是 它的后继节点的父节点
            if(parent==node){
                parent=replace;
            }else {
                //child 不为空
                if(child!=null){
                    //?????? 未解决
                    setParent(child,parent);
                }
                parent.left=child;

                replace.right=node.right;
                setParent(node.right,replace);
            }

            replace.parent=node.parent;
            replace.color=node.color;
            replace.left=node.left;
            node.left.parent=replace;

            if(color==BLACK) {
                removeFixUp(child,parent);
            }

            node=null;
            return;
        }
        if(node.left!=null){
            child=node.left;
        }else {
            child=node.right;
        }

        parent=node.parent;
        //保存取代节点的颜色
        color=node.color;

        if(child!=null) {
            child.parent=parent;
        }

        //node 不是根节点
        if(parent!=null){
            if(parent.left==node) {
                parent.left=child;
            } else {
                parent.right=child;
            }
        }else {
            this.mRoot=child;
        }

        if(color==BLACK) {
            removeFixUp(child,parent);
        }
        node=null;
    }

    /**
     * 删除节点后，修正红黑树
     * @param node 待删节点
     * @param parent 待删节点的父节点
     */
    private void removeFixUp(RBTNode<T> node, RBTNode<T> parent) {
        RBTNode<T> other;

        while((node==null || isBlack(node)) && (node != this.mRoot)){
            if(parent.left==node){
                other=parent.right;
                if(isRed(other)){
                    // 1. x 的兄弟 w 是红色的
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other=parent.right;
                }

                if((other.left==null || isBlack(other.left)) &&
                        (other.right==null || isBlack(other.right))){
                    //2. x的兄弟w是黑色，且 w 的两个孩子也都是黑色的
                    setRed(other);
                    node=parent;
                    parent=parentOf(node);
                }else {
                    if(other.right==null || isBlack(other.right)){
                        //3.x 的兄弟 w 的左孩子是红色的，右孩子为黑色
                        assert other.left != null;
                        setBlack(other.left);
                        setRed(other);
                        rightRotate(other);
                        other=parent.right;
                    }

                    //4.x 的兄弟 w 是黑色的，并且 w 的右孩子是红色的，左孩子任意颜色
                    //setColor(other,colorOf(parent));  //所用函数未实现，故注释掉
                    setBlack(parent);
                    setBlack(other.right);
                    leftRotate(parent);
                    node=this.mRoot;
                    break;
                }
            }else {
               other=parent.left;
               if(isRed(other)){
                   //1.x 的兄弟 w 是红色的
                   setBlack(other);
                   setRed(parent);
                   rightRotate(parent);
                   other=parent.left;
               }

               if((other.left==null || isBlack(other.left)) &&
                       (other.right==null || isBlack(other.right))){
                   //2. x 的兄弟 w 是黑色的，且 w 的两个孩子也都是黑色的
                   setRed(other);
                   node=parent;
                   parent=parentOf(node);
               }else {
                   if(other.left==null || isBlack(other.left)){
                       //3. x 的兄弟 w 是黑色的，并且w 的左孩子是红色的，右孩子是黑色
                       setBlack(other.right);
                       setRed(parent);
                       leftRotate(other);
                       other=parent.left;
                   }

                   //4.x 的兄弟 w 是黑色的，并且 w 的右孩子是红色的，左孩子任意颜色
                   //setColor(other,colorOf(parent));
                   setBlack(parent);
                   setBlack(other.left);
                   rightRotate(parent);
                   node=this.mRoot;
                   break;
               }
            }
        }
        if(node!=null) {
            setBlack(node);
        }
    }



    /**
     * 外部接口
     * @param key
     */
    public void remove(T key){
        RBTNode<T> node;
        if((node=search(mRoot,key))!=null) {
            remove(node);
        }
    }

    private RBTNode<T> search(RBTNode<T> mRoot, T key) {
        RBTNode<T> node=new RBTNode<>(BLACK,key,null,null,null);
        if(node.key.compareTo(mRoot.key)<0){
            mRoot=mRoot.left;
            search(mRoot,key);
        } else if(node.key.compareTo(mRoot.key)>0){
            mRoot=mRoot.right;
            search(mRoot,key);
        }else if(node.key.compareTo(mRoot.key)==0) {
            node=mRoot;
        }
        return node;
    }

    private void setParent(RBTNode<T> child, RBTNode<T> parent) {
        child.parent=parent;
        parent.left=child;
    }


    private void setRed(RBTNode<T> node) {
        node.color=RED;
    }

    private void setBlack(RBTNode<T> node) {
        node.color=BLACK;
    }

    private boolean isBlack(RBTNode<T> node) {
        if (node==null) {
            return true;
        }
        return node.color==BLACK;
    }

    private boolean isRed(RBTNode<T> node) {
        if(node==null) return true;
        return node.color==RED;
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node==null?null:node.parent;
    }
}
