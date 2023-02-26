public class Question3b {
    boolean solve(String str, String pattern, int i, int j, boolean[][] dp){
        //base cases
        // if both string ends at same time
        if(i<0&&j<0){
            return true;
        }
        //if pattern string ends but str is left
        if(i>=0&&j<0){return false;
        }
        //if pattern is left but str is ended
        if(i<0&&j>=0){
            for(int k=0; k<=j;k++){
                if(pattern.charAt(k)!='@'){
                    return false;
                }
            }
            return true;
        }
        //matching
        if(str.charAt(i)==pattern.charAt(j)&&pattern.charAt(j)=='#'){
            return dp[i][j]=solve(str, pattern, i-1, j-1,dp);
        }
        else if(pattern.charAt(j)=='@'){
            return dp[i][j]=(solve(str,pattern,i-1,j,dp));
        }
        else{
            return false;
        }
    }
    boolean match(String str, String pattern){
        int i=str.length()-1;
        int j=pattern.length()-1;
        boolean matrix[][] = new boolean[i+1][j+1];
        return solve(str, pattern, i, j , matrix);
    }
    public static void main(String[] args) {
        Question3b dp = new Question3b();
        String str ="tt";
        String pattern="@";
        System.out.println(dp.match(str,pattern));
    }
}