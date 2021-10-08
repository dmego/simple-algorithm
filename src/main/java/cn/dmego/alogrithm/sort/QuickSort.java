package cn.dmego.alogrithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 *
 * 快速排序：快速排序也属于 交换排序，通过元素之间的比较和交换位置来达到排序的目的
 * 快速排序采用分治法，在每一轮挑选一个基准元素，让比基准元素大的元素移动到一边，比基准元素小的移动到另一边
 * 这样原来需要排序的数组在每一轮下来都被分为两部分，每一部分在下一轮又被分为两部分，直到不可分割为止
 *
 * @author dmego
 * @date 2021/10/08 15:48
 */
public class QuickSort {

    public static void quickSort(int[] nums, int start, int end){
        // 递归循环结束条件
        if (start >= end) {
            return;
        }
        // 调用划分函数，返回排好序的元素 pos 下标
        int pos = partition3(nums, start, end);
        // 对 pos 左侧区间继续进行递归排序
        quickSort(nums, start, pos - 1);
        // 对 pos 右侧区间继续进行递归排序
        quickSort(nums, pos + 1, end);
    }

    /**
     单边循环法实现划划分函数：
     1. 选定基准元素(主元) pivot,可以选择最左边的元素 nums[left]，或者随机从[left, right]区间中选择一个元素
     2. 设置一个 mark 指针，代表小于等于基准元素的区域(左边区域)边界，初始位置在 left, 也就是与基准元素相同
     3. 从 mark 的下一个位置开始遍历区间, 对于遍历到没一个元素下标
        3.1 如果 nums[i] <= pivot，说明添加到基准元素左边区域(小于等于)中
            3.1.1 mark 指针右移一位，因为左边区域需要新加一个元素
            3.1.2 将 nums[i] 与 nums[mark] 两个元素交换位置
        3.2 如果 nums[i] > pivot 继续遍历，大于基准元素的所有元素永远都在右边区域
     4 遍历结束后，将基准元素 pivot(nums[left]) 与 nums[mark] 交换位置
     5 返回 mark, 也就是说 nums[mark] 此时在整个数组中已经是排好序的
     */
    public static int partition(int[] nums, int left, int right) {
        // 随机获取区间 [left, right] 中的一个下标 nextInt(right - left + 1) + 1
        int randomIndex = new Random().nextInt(right - left + 1) + left;
        // 把基准元素 nums[randomIndex] 移动到 最左边来(与nums[left] 交换位置)
        swap(nums, left, randomIndex);
        // 基准元素
        int pivot = nums[left];
        // mark 初始在区间最左边，left 位置
        int mark = left;
        for (int i = mark + 1; i <= right; i++) {
            if (nums[i] <= pivot) {
                mark++;
                swap(nums, mark, i);
            }
        }
        swap(nums, mark, left);
        return mark;
    }

    /**
     双边循环法实现划分函数：
     1. 选定基准元素(主元) pivot,可以选择最左边的元素 nums[left]，或者随机从[left, right]区间中选择一个元素
     2. 定义两个指针 l 和 r， 初始化分别指向区间的left和right位置，表示划分的左右区域边界
     3. 遍历区间，循环的条件是 l <= r (或者 l != r)
        3.1 判断 nums[r] >= pivot
            3.1.1 如果是 则 r--，表示右边区域扩大一位
            3.1.2 如果不是， 说明此时 nums[r] < pivot，则保持 r 指针不动，开始移动 l 指针，执行 3.2
        3.2 判断 nums[l] <= pivot
            3.2.1 如果是 则 l++, 表示左边区域扩大一位
            3.2.2 如果不是，说明此时 nums[l] > pivot, 保持 l 指针不动，交换此时的 l 和 r 位置上的元素，
                  交换完成后，l 位置元素满足 < pivot , r 位置元素满足 > pivot
     4. 循环结束后，把 基准元素 pivot 和 l 位置元素交换
     5. 返回 l, 也就是说 nums[l] 此时在整个数组中是排好序的，左边都是小于它的元素，右边都是大于它的元素
     */
    public static int partition2(int[] nums, int left, int right) {
        // 随机获取区间 [left, right] 中的一个下标 nextInt(right - left + 1) + 1
        int randomIndex = new Random().nextInt(right - left + 1) + left;
        // 把基准元素 nums[randomIndex] 移动到 最左边来(与nums[left] 交换位置)
        swap(nums, left, randomIndex);
        // 基准元素
        int pivot = nums[left];
        // 定义两个指针 l 和 r
        int l = left, r = right;
        while (l != r) {
            while (l < r && nums[r] >= pivot) r--;
            while (l < r && nums[l] <= pivot) l++;
            // 最后 l = r 时，不用交换，所以只交换 l < r 的情况
            if (l < r) {
                swap(nums, l, r);
            }
        }
        // 退出循环时，一定满足 l == r, 两个指针重合，返回 l 和 r 都行
        swap(nums, left, l);
        return l;
    }

    /**
     双边循环法实现划分函数, 划分过程中不用交换
     */
    public static int partition3(int[] nums, int left, int right) {
        // 随机获取区间 [left, right] 中的一个下标 nextInt(right - left + 1) + 1
        int randomIndex = new Random().nextInt(right - left + 1) + left;
        // 把基准元素 nums[randomIndex] 移动到 最左边来(与nums[left] 交换位置)
        swap(nums, left, randomIndex);
        // 基准元素
        int pivot = nums[left];
        // 定义两个指针 l 和 r
        int l = left, r = right;
        while (l < r) {
            while (l < r && nums[r] >= pivot) r--;
            nums[l] = nums[r];
            while (l < r && nums[l] <= pivot) l++;
            nums[r] = nums[l];
        }
        nums[l] = pivot;
        return l;
    }


    /**
     交互函数
     把数组 nums 中下标 index1 和 index2 的元素交互位置
     */
    public static void swap(int[] nums, int index1, int index2) {
        int tmp = nums[index2];
        nums[index2] = nums[index1];
        nums[index1] = tmp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,7,3,5,6,2,8,1};
        quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

}
