package cn.dmego.alogrithm.binarytree;

import java.util.Arrays;

/**
 * 优先队列实现
 * @author dmego
 * @date 2021/07/18 10:38
 */
public class PriorityQueue {

    /**
     * 优先队列数组表示
     */
    private int[] array;
    /**
     * 优先队列中实际元素个数
     */
    private int size;

    public PriorityQueue() {
        // 优先队列长度初始为 32
        array = new int[32];
    }

    /**
     * 队列扩容：扩一倍
     */
    public void resize() {
        int newSize = this.size * 2;
        array = Arrays.copyOf(this.array, newSize);
    }

    /**
     * 队列入队
     * @param item 入队元素
     */
    public void enQueue(int item) {
        // 如果队列满了，需要扩容
        if(size == array.length) {
            resize();
        }
        // 将元素先放到数组最后
        array[size++] = item;
        // 元素上浮
        upAdjust();
    }

    /**
     * 队列出队
     * @return 队头元素
     */
    public int deQueue() throws Exception {
        if(size <= 0) {
            throw new Exception("this queue is empty!");
        }
        // 队首出队，也就是删除堆顶
        int head = array[0];
        // 将最后一个元素移动到堆顶位置
        array[0] = array[--size];
        // 堆顶元素下沉
        downAdjust();
        return head;
    }

    /**
     * 二叉堆最后一个元素上浮
     * 要上浮的元素与该元素的父节点比较大小
     * 如果比父节点大，则和父节点交互位置
     * 继续和交互位置之后的父节点做比较
     * 直到父节点比要上浮元素大，或者已经没有父节点了(到了堆顶)
     */
    public void upAdjust() {
        // 数组最后一个元素下标，也就是入队的元素下标
        int childIndex = size - 1;
        // 入队元素的父节点下标
        int parentIndex = (size - 1) / 2;
        // 入队元素的值
        int temp = array[childIndex];
        //上浮循环 当要上浮元素的下标大于 0 时，不断和其父节点交互位置
        while (childIndex > 0) {
            // 如果父节点比要上浮的元素大，退出上浮循环
            if(array[parentIndex] > temp) {
                break;
            } else {
                // 将父节点元素移动到当前 childIndex 的位置
                array[childIndex] = array[parentIndex];
                // 更新新的要上浮节点元素下标
                childIndex = parentIndex;
                // 更新父节点的下标
                parentIndex = (childIndex - 1) / 2;
            }
        }
        // childIndex 的位置就是 temp 元素(入队元素) 最终上浮的位置
        array[childIndex] = temp;
    }

    /**
     * 二叉堆第一个元素下沉
     */
    public void downAdjust() {
        int parentIndex = 0;
        int childIndex = 1;
        // temp 保存要下沉的元素值
        int temp = array[parentIndex];
        // 下沉循环
        while (childIndex < size) {
            // 首先是需要比较当前 parentIndex 的左右两个节点的大小，取最大的作为 childIndex
            if(childIndex + 1 < size && array[childIndex] < array[childIndex + 1]) {
                childIndex++;
            }
            // 如果当前要下沉的元素小于其左右节点中最大的节点值，说明不需要再下沉了，退出下沉循环
            if(temp > array[childIndex]) {
                break;
            } else {
                array[parentIndex] = array[childIndex];
                parentIndex = childIndex;
                childIndex = parentIndex * 2 + 1;
            }
        }
        // parentIndex 的位置就是 temp 元素(下沉元素) 最终下沉的位置
        array[parentIndex] = temp;
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue priorityQueue = new PriorityQueue();
        priorityQueue.enQueue(3);
        priorityQueue.enQueue(5);
        priorityQueue.enQueue(10);
        priorityQueue.enQueue(2);
        priorityQueue.enQueue(7);
        System.out.println("出队元素：" + priorityQueue.deQueue());
        System.out.println("出队元素：" + priorityQueue.deQueue());
        System.out.println("出队元素：" + priorityQueue.deQueue());
    }
}
