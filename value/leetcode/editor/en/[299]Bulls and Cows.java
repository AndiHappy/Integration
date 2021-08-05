// You are playing the Bulls and Cows game with your friend.
//
// You write down a secret number and ask your friend to guess what the number is.
// When your friend makes a guess, you provide a hint with the following info:
//
// 
// The number of "bulls", which are digits in the guess that are in the correct position.

// The number of "cows", which are digits in the guess that are in your secret numberbut are located in the wrong position.
// Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
// 
//
// Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
//
// The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
// Note that both secret and guess may contain duplicate digits.
//
// 
// Example 1: 
//
// 
//Input: secret = "1807", guess = "7810"
//Output: "1A3B"
//Explanation: Bulls are connected with a '|' and cows are underlined:
//"1807"
//  |
//"7810" 
//
// Example 2: 
//
// 
//Input: secret = "1123", guess = "0111"
//Output: "1A1B"
//Explanation: Bulls are connected with a '|' and cows are underlined:
//"1123"        "1123"
//  |      or     |
//"0111"        "0111"

//Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
// 
//
// Example 3: 
//
// 
//Input: secret = "1", guess = "0"
//Output: "0A0B"
// 
//
// Example 4: 
//
// 
//Input: secret = "1", guess = "1"
//Output: "1A0B"
// 
//
// 
// Constraints: 
//
// 
// 1 <= secret.length, guess.length <= 1000 
// secret.length == guess.length 
// secret and guess consist of digits only. 
// 
// Related Topics Hash Table String Counting 
// 👍 1042 👎 1123


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String getHint(String secret, String guess) {
        int len = secret.length();
        int[] secretarr = new int[10];
        int[] guessarr = new int[10];
        int bull = 0, cow = 0;
        for (int i = 0; i < len; ++i) {
            if (secret.charAt(i) == guess.charAt(i)) {
                ++bull;
            } else {
                ++secretarr[secret.charAt(i) - '0'];
                ++guessarr[guess.charAt(i) - '0'];
            }
        }
        for (int i = 0; i < 10; ++i) {
            cow += Math.min(secretarr[i], guessarr[i]);
        }
        return "" + bull + "A" + cow + "B";
    }

    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) bulls++;
            else {
                if (numbers[s] < 0) cows++;
                if (numbers[g] > 0) cows++;
                numbers[s] ++;
                numbers[g] --;
            }
        }
        return bulls + "A" + cows + "B";
    }
}
//leetcode submit region end(Prohibit modification and deletion)
