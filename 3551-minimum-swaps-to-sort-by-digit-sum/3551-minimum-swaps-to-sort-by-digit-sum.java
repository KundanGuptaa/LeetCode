class Solution {
    public int minSwaps(int[] nums) {
        int n=nums.length;
        Integer[] target=new Integer[n];
        for(int i=0;i<n;i++){
            target[i]=nums[i];
        }
        Arrays.sort(target,(a,b)->{
            int sumA=getDigitSum(a);
            int sumB=getDigitSum(b);
            if(sumA!=sumB){
                return Integer.compare(sumA,sumB);
            }
            return Integer.compare(a,b);
        });
        Map<Integer, Integer> targetPos = new HashMap<>();
        for(int i=0;i<n;i++){
            targetPos.put(target[i],i);
        }
        boolean[] visited = new boolean[n];
        int cycles=0;
        for(int i=0;i<n;i++){
            if(visited[i]) continue;
            int curr = i;
            while (!visited[curr]){
                visited[curr]=true;
                curr=targetPos.get(nums[curr]);
            }
            cycles++;
        }
        return n- cycles;
    }
    private int getDigitSum(int num){
        int sum=0;
        while(num>0){
            sum+=num%10;
            num/=10;
        }
        return sum;
    }
}