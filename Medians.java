/* Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays... The overall run time complexity should be O(log (m+n))...
 * Eg 1: nums1 = [1,3]     nums2 = [2]     median = 2.0
 * Eg 2: nums1 = [1,2]     nums2 = [3,4]   median = 2.5
 * Eg 3: nums1 = [1]       nums2 = []      median = 1.0
 * Eg 4: nums1 = [2,4]     nums2 = [3,7]   median = 3.5
 */
import java.util.*;
public class Medians
{
    public double MedianOfSorted(int nums1[], int nums2[])
    {
        double median1 = 0, median2 = 0, medianFinal = 0;
        if(nums1.length % 2 == 0 && nums1.length > 0)
        {
            median1 = nums1[(nums1.length/2)-1] + nums1[nums1.length/2];
            median1 = median1/2;
        }
        else if(nums1.length % 2 != 0 && nums1.length > 0)
            median1 = nums1[(int)(nums1.length/2)];
        System.out.println("Median 1 : "+median1);
        if(nums2.length % 2 == 0 && nums2.length > 0)
        {
            median2 = nums2[(nums2.length/2)-1] + nums2[nums2.length/2];
            median2 = median2/2;
        }
        else if(nums2.length % 2 != 0 && nums2.length > 0)
            median2 = nums2[(int)(nums2.length/2)];
        System.out.println("Median 2 : "+median2);
        if((nums1.length + nums2.length) % 2 == 0 && nums1.length > 0 && nums2.length > 0)
            medianFinal = (median1 + median2) / 2;
        else if((nums1.length + nums2.length) % 2 != 0 && nums1.length > 0 && nums2.length > 0)
        {
            medianFinal = (median1 + median2) / 2;
            medianFinal = (int)(medianFinal);
        }
        else if(nums1.length == 0)
            medianFinal = median2;
        else if(nums2.length == 0)
            medianFinal = median1;
        else
            medianFinal = 0;
        System.out.println("Median Final : "+medianFinal);
        return medianFinal;
    }
    public double MedianDirect(int nums1[], int nums2[])
    {
        if(nums1.length == 0 && nums2.length == 0) 
            return 0.0;
        if(nums1.length > nums2.length)
        {   // If first array is larger...
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;     // Swap the arrays...
        }
        int lo = 0, hi = nums1.length, total = nums1.length + nums2.length;
        while(lo <= hi)
        {
            int aleft = lo + (hi - lo) / 2;       // left boundary...
            int bleft = (total + 1) / 2 - aleft;    // right boundary...
            int maVal = aleft == 0 ? Integer.MIN_VALUE : nums1[aleft - 1];    // Using ternary operators...
            int mbVal = bleft == 0 ? Integer.MIN_VALUE : nums2[bleft - 1];
            int aVal = aleft == nums1.length ? Integer.MAX_VALUE : nums1[aleft];
            int bVal = bleft == nums2.length ? Integer.MAX_VALUE : nums2[bleft];
            if(maVal <= bVal && mbVal <= aVal)
            {
                double res = Math.max(maVal, mbVal);
                if(total % 2 == 0)
                {    // If total length is even...
                    res += Math.min(aVal, bVal);
                    res /= 2.0;
                }
                return res;
            }
            else if(maVal > bVal)
            {
                hi = aleft - 1;
            }
            else
            {
                lo = aleft + 1;
            }
        }
    return 0.0d;
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int m, n;
        System.out.print("Enter length of first sorted array : ");
        m = sc.nextInt();
        System.out.print("Enter the length of Second Sorted array : ");
        n = sc.nextInt();
        int nums1[] = new int[m];
        int nums2[] = new int[n];
        for(int i = 0; i < nums1.length; i++)
        {
            System.out.print("Enter data for first array : ");
            nums1[i] = sc.nextInt();
        }
        for(int i = 0; i < nums2.length; i++)
        {
            System.out.print("Enter data for second array : ");
            nums2[i] = sc.nextInt();
        }
        Medians medians = new Medians();    // Object creation...
        System.out.println("The Median found is : "+medians.MedianOfSorted(nums1, nums2));
        System.out.println("Direct Median : "+medians.MedianDirect(nums1, nums2));
        sc.close();
    }
}

// Time Complexity  - O(log (m+n)) time...
// Space Complexity - O(log (m+n)) space...