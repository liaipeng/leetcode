package com.leetcode.algorithm.easy;



/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
*/

/*
 * 罗马数字：
 *		Ⅰ－1、Ⅱ－2、Ⅲ－3、Ⅳ－4、Ⅴ－5、Ⅵ－6、Ⅶ－7、Ⅷ－8、Ⅸ－9、X - 10、L-50、C-100、D-500、M-1000
 *		1、相同的数字连写、所表示的数等于这些数字相加得到的数、如：Ⅲ=3；
		2、小的数字在大的数字的右边、所表示的数等于这些数字相加得到的数、 如：Ⅷ=8、Ⅻ=12；
		3、小的数字、（限于 Ⅰ、X 和 C）在大的数字的左边、所表示的数等于大数减小数得到的数、如：Ⅳ=4、Ⅸ=9；
		4、正常使用时、连写的数字重复不得超过三次。（表盘上的四点钟“IIII”例外）；
		5、在一个数的上面画一条横线、表示这个数扩大 1000 倍。
 * 		
 */

/*
 * 用方法①这种笨方法居然还能52.19%,耗时13ms
 * 
 * 方法②，耗时跟方法①差不多, 但是思维6666666666666666666666666666666666666
 * 
 * 方法③，6ms，98%！
 */

public class RomanToInteger {
	//方法①
    public static int romanToInt(String s) {
		boolean isOver = false;
		int num = 0;
		while (!isOver) { 
			if (s.startsWith("M")) {
				num += 1000;
				s = s.substring(1, s.length());
			}
			else if (s.startsWith("CM")) {
				num += 900;
				s = s.substring(2, s.length());
			}
			else if (s.startsWith("D")) {
				num += 500;
				s = s.substring(1, s.length());
			}
			else if (s.startsWith("CD")) {
				num += 400;
				s = s.substring(2, s.length());
			}
			else if (s.startsWith("C")) {
				num += 100;
				s = s.substring(1, s.length());
			}
			else if (s.startsWith("XC")) {
				num += 90;
				s = s.substring(2, s.length());
			}
			else if (s.startsWith("L")) {
				num += 50;
				s = s.substring(1, s.length());
			}
			else if (s.startsWith("XL")) {
				num += 40;
				s = s.substring(2, s.length());
			}
			else if ((s.startsWith("X"))) {
				num += 10;
				s = s.substring(1, s.length());
			}
			else {
				isOver = true;
				switch (s){
					case "I" : 
						num += 1; break;
					case "II" : num += 2; break;
					case "III" : num += 3; break;
					case "IV" : num += 4; break;
					case "V" : num += 5; break;
					case "VI" : num += 6; break;
					case "VII" : num += 7; break;
					case "VIII" : num += 8; break;
					case "IX" : num += 9; break;
				}
			}
		}
		return num;
    }
    
	/*    
	 * 方法②
	    先对数据做预处理，然后就可以不管三七二十一出现什么字符就当做多少
	    比如出现M，sum就+1000，因为前面预处理的时候已经预防了出现CM的情况：
	    如果按照出现什么字符就当做多少，那么CM会被当成 100+1000=1100，但是实际上CM=900，所以预处理的时候判断，如果s中存在CM，那么总数-200
	    而且按照罗马数字的规则，诸如CM这种数有且只可能出现一次
	 */
    public int romanToInt_2(String s) {
       int sum=0;
       if(s.indexOf("IV")!=-1){sum-=2;}
       if(s.indexOf("IX")!=-1){sum-=2;}
       if(s.indexOf("XL")!=-1){sum-=20;}
       if(s.indexOf("XC")!=-1){sum-=20;}
       if(s.indexOf("CD")!=-1){sum-=200;}
       if(s.indexOf("CM")!=-1){sum-=200;}

       char c[]=s.toCharArray();
       int count=0;

      for(;count<=s.length()-1;count++){
          if(c[count]=='M') sum+=1000;
          if(c[count]=='D') sum+=500;
          if(c[count]=='C') sum+=100;
          if(c[count]=='L') sum+=50;
          if(c[count]=='X') sum+=10;
          if(c[count]=='V') sum+=5;
          if(c[count]=='I') sum+=1;

      }

      return sum;

   }
    
    /* 
     * 方法③
	       效率最高。从后往前遍历字符串s的每个字符，根据字符代表的阿拉伯数字大小进行res的相加
	       当碰到I、X、C这三个可以用在大字符左边的字符时，需要单独判断：
	       	例如，对于I，假如此时res已经大于等于5，说明I后面跟着的是V或者X，所以此时不是+1，而是-1(因为I出现在V或者X前面要减去1)
	       	同理，对于X，假如此时res已经大于等于50，说明X后面跟着的是L或者C，所以此时不是+10，而是-10（因为X出现在L或者C前面要减去10）
	       	C也一样
     */
    public static int romanToInt_3(String s) {
        int res = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            switch (c) {
            case 'I':
                res += (res >= 5 ? -1 : 1);
                break;
            case 'V':
                res += 5;
                break;
            case 'X':
            	//为什么 res += 10 * (res >= 50 ? -1 : 1)比 res += (res >= 50 ? -10 : 10)快？？？
                res += 10 * (res >= 50 ? -1 : 1);
                break;
            case 'L':
                res += 50;
                break;
            case 'C':
                res += 100 * (res >= 500 ? -1 : 1);
                break;
            case 'D':
                res += 500;
                break;
            case 'M':
                res += 1000;
                break;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
		String str = "IX";
		System.out.println(romanToInt(str));
	}
}
