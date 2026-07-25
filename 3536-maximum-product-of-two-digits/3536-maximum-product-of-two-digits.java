class Solution {
    public int maxProduct(int n) {
        
        int temp=n;

        int ans=1;

        List<Integer> list= new ArrayList<>();
        Stack<Integer> st= new Stack<>();

        while(temp>0)
        {
            int t=temp%10;
            st.add(t);
            temp=temp/10;
        }

        while(!st.isEmpty())
        {
            list.add(st.pop());
        }


        Collections.sort(list);

        int size=list.size();

        return list.get(size-1)*list.get(size-2);

    }
}