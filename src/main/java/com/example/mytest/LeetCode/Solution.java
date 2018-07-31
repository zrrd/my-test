package com.example.mytest.LeetCode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 *
 * @author shaoyijiong
 * @date 2018/7/27
 */
public class Solution {

  //自己写的
  public static int[] twoSum(int[] nums, int target) {
    //返回结果
    int[] result = new int[2];
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          result[0] = i;
          result[1] = j;
          return result;
        }
      }
    }
    return result;
  }

  //比较好的
  public static int[] twoSum2(int[] numbers, int target) {
    int [] res = new int[2];
    if (numbers == null || numbers.length < 2) {
      return res;
    }
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int i = 0; i < numbers.length; i++){
      if(!map.containsKey(target-numbers[i])){
        map.put(numbers[i],i);
      }else{
        res[0]= map.get(target-numbers[i]);
        res[1]= i;
        break;
      }
    }
    return res;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{2, 7, 11, 15, 2};
    int target = 9;
    System.out.println(Arrays.toString(twoSum2(nums, target)));
  }
}
