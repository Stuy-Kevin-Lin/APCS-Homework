/*
Team Lonely Island
Kevin Lin
APCS1 Pd8
Hw24 --  Imetay Otay Ineshay (Time To Shine)
2017-10-30
*/

/*
Expansions: 
When there are no vowels in a word, the letter y can now be treated as a vowel.
Punctuation is dealt with. When a word is posessive, indicated by an ending of "'s", then it will have the same ending once translated.
End punctuation remains the same.
Capitalization of the first letter of the input results in capitalization of the first letter of the output.
Scanner 
*/

import java.util.Scanner;

public class Pig
{
    //initializing VOWELS here allows the instance variable to be accesible to all methods
    private static final String VOWELS = "aeiouAEIOU";
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String ENDCHRS = ",.?/!;:";

    /*=====================================
    boolean hasA(String,String) -- checks for a letter in a String
    pre:  w != null, letter.length() == 1
    post: hasA("cat", "a") -> true
    hasA("cat", "p")       -> false
    =====================================*/
    public static boolean hasA( String w, String letter ) 
    {
	return w.indexOf(letter) >= 0;
    }//end hasA()

    
    /*=====================================
    boolean isAVowel(String) -- tells whether a letter is a vowel
    precondition: letter.length() == 1
    =====================================*/
    public static boolean isAVowel( String letter ) 
    {
	return VOWELS.indexOf(letter) >= 0;
    }


    /*=====================================
    int countVowels(String) -- counts vowels in a String
    pre:  w != null
    post: countVowels("meatball") -> 3
    =====================================*/
    public static int countVowels( String w ) 
    {
	int vowelCnt = 0;
	for (int x = 0; x < w.length(); x+= 1){
	    String letter = w.substring(x, x+1);
	    if (isAVowel(letter)){
		vowelCnt += 1;
	    }
	}
	return vowelCnt;
    }


    /*=====================================
    boolean hasAVowel(String) -- tells whether a String has a vowel
    pre:  w != null
    post: hasAVowel("cat") -> true
    hasAVowel("zzz")       -> false
    =====================================*/
    public static boolean hasAVowel( String w ) 
    {
	/*	int index = 0;
	int length = w.length();
	int vowelCnt = 0;
	while (index < length && vowelCnt == 0){
	if (*/
	return countVowels(w) > 0;
    }


    /*=====================================
    String allVowels(String) -- returns vowels in a String
    pre:  w != null
    post: allVowels("meatball") -> "eaa"
    =====================================*/
    public static String allVowels( String w ) 
    {
	String retStr = "";
	for (int x= 0; x < w.length(); x += 1){
	    String letter = w.substring(x, x+1);
	    if (isAVowel(letter)){
		retStr += letter;
	    }
	}
	return retStr; 
    }

    /*=====================================  
    String firstVowel(String) -- returns first vowel in a String
    pre: w != null
    post: firstVowel("") --> ""
          firstVowel("zzz") --> ""
	  firstVowel("meatball") --> "e"
    =====================================*/
    public static String firstVowel(String w){
	int index = 0;
	String firstVowel = "";
	while (index < w.length()){
	    if (isAVowel(w.substring(index, index + 1))){
		firstVowel =  w.substring(index, index + 1);
		return firstVowel;
	    }
	    index += 1;
	}
	return firstVowel;
    }

    /*=====================================     
    boolean beginsWithVowel(String) -- returns whether String begins with vowel
    pre: w != null and w.length() > 0
    post: beginsWithVowel("apple")  --> true
          beginsWithVowel("strong") --> false
     =====================================*/
    public static boolean beginsWithVowel(String w){
	return isAVowel(w.substring(0, 1));
    }

    /*=====================================
    boolean allLetters(String) -- returns whether the string is composed entirely of letters
    pre: w.length() > 0
    post: allLetters("apple") --> true
          allLetters("1apple") --> false
          allLetters("b-day") --> false
    =======================================*/
    public static boolean allLetters(String w){
	String lowerC = w.toLowerCase();

	for (int x = 0; x < lowerC.length(); x+= 1){

	    String letter = lowerC.substring(x, x+1);

	    if (LETTERS.indexOf(letter) < 0){
		return false;
	    }
	}
	return true;
    }
    
    /*=======================================
    String begVowelTransl(String) -- returns String in pig latin knowing first letter is vowel
    pre: lower.length() > 0
         beginsWithVowel(lower) --> true
    post: lower + "way"
    =========================================*/
    public static String begVowelTransl(String lower){
	return lower + "way";
    }

    /*========================================
     String hasVowelTransl(String)  consonants moved to end until lower begins with a vowel. Then, "ay" is appended
     pre: lower.length() > 0
          hasAVowel(lower) --> true     
      =======================================*/
    public static String hasVowelTransl(String lower){
	while ( !beginsWithVowel(lower)){
	    lower = lower.substring(1, (lower.length()))
		+ lower.substring(0, 1);
	}
	return  lower + "ay";
    }

    /*=======================================
     String noVowelTransl(String) -- translates a word if the word has no vowels
     pre: hasAVowel(lower) --> false
          lower = lower.toLowerCase(lower)
      ======================================*/
    public static String noVowelTransl(String lower){
	while (!lower.substring(0, 1).equals("y")){
	    lower = lower.substring(1, (lower.length()))
		+ lower.substring(0, 1);
	}
	return lower + "ay";
    }

    /*=======================================
    int findPunc(String) -- identifies the punctuation within the word
    pre: length.lower() > 0
         ! allLetters(lower)
	 lower.toLowerCase()
    post: findPunc("b-day") --> 1
          findPunc("e'er") --> 1
	  findPunc("browns'") --> 6 
    ========================================*/
    public static int findPunc(String lower){
	for (int x = 0; x < lower.length(); x += 1){
	    String chr = lower.substring(x, x+1);
	    if (LETTERS.indexOf(chr) < 0){
		return x;
	    }
	}
	return -1;
    }    

    /*=======================================
     String withPuncTransl(String) -- translates words that have punctuation
     -- a word that has punctuation at the end will have the punctuation appended to its pigL
     -- a word that has "'s" at the end will have "'s" at the end of it's pigL and the rest will be translated to pig latin
     -- for words with punctuation not at the end: 
          - the punctuation is removed
	  - the translation is done to the word without punctuation
	  - the punctuation is inserted back to the same position it was at in the original
     pre: lower = lower.toLowerCase()
     post: withPuncTransl("sky.") --> yskay
           withPuncTransl("b-day") --> a-ybday
     =========================================*/
    public static String withPuncTransl(String lower){
	
	int puncIndex = findPunc(lower);
	String punc = lower.substring(puncIndex, puncIndex +1);
	String pigL = "";

	if (findPunc(lower) == lower.length()-1){
	    pigL = engToPig(lower.substring(0, puncIndex)) + punc;
	    return pigL;
	}

	if ((lower.substring(puncIndex)).equals("'")
	    || (lower.substring(puncIndex)).equals("'s")){
	    pigL = engToPig(lower.substring(0, puncIndex))
		+ punc;
	    return pigL;
	}

	else{
	    String woPunc = lower.substring(0, puncIndex) 
		+ lower.substring(puncIndex + 1);
	    pigL = engToPig(woPunc);
	    pigL = pigL.substring(0, puncIndex) + punc + pigL.substring(puncIndex);
	}
	/*	
	if (ENDCHRS.indexOf(lower.substring(lower.length()-1)) >= 0
	    && puncIndex != lower.length()-1){
	    System.out.println("ITOSKR");
	    }*/
	return pigL;

    }

    /*============================================
      String engToPig(String) directs words to other translator functions based on characteristics
    pre: w.length() > 0
    post: engToPig("apple")  --> "appleway"
          engToPig("strong") --> "ongstray"
	  engToPig("java")   --> "avajay"
      ==========================================*/
    public static String engToPig(String w){
	String lower = w.toLowerCase();
	String pigL = "";
	
	if (beginsWithVowel(lower)){
	    pigL = begVowelTransl(lower);
	}

	if (hasAVowel(w)){
	    pigL = hasVowelTransl(lower);

	}
	else {
	    pigL = noVowelTransl(lower);
	}

	if (! allLetters(lower)){
	    pigL = withPuncTransl(lower);
	    } 

	return upperFix(w, pigL); 
    }

    /*=====================================
    //pre: w.length() > 0
    //     pigL.length() > 0
    //alters pigL to match the capitalization of w
    =======================================*/
    public static String upperFix(String w, String pigL){
	String firstChr = w.substring(0, 1);
	if (firstChr.equals(firstChr.toUpperCase())){
	    String firstChrPig = pigL.substring(0, 1);
	    pigL = firstChrPig.toUpperCase() + pigL.substring(1, pigL.length());
	}
	return pigL;
    }

    


    public static void main( String[] args ) 
    {
	/*	System.out.println("Testing hasA()");
	System.out.println("hasA('hello', 'h')");
	System.out.println(hasA("hello", "h"));
	System.out.println("hasA('hello', 'a')");
	System.out.println(hasA("hello", "a"));
	System.out.println("=======================");

	System.out.println("Testing isAVowel()");
	System.out.println("isAVowel('a')");
	System.out.println(isAVowel("a"));
	System.out.println("isAVowel('c')");
	System.out.println(isAVowel("c"));
	System.out.println("=======================");

	System.out.println("Testing countVowels()");
	System.out.println("countVowels('meatballs')");
	System.out.println(countVowels("meatballs"));
	System.out.println("countVowels('aaa')");
	System.out.println(countVowels("aaa"));
	System.out.println("countVowels('v')");
	System.out.println(countVowels("v"));
	System.out.println("=======================");

	System.out.println("Testing hasAVowel()");
	System.out.println("hasAVowel('hello')");
	System.out.println(hasAVowel("hello"));
	System.out.println("hasAVowel('yyy')");
	System.out.println(hasAVowel("yyy"));
	System.out.println("=======================");

	System.out.println("Testing allVowels()");
	System.out.println("allVowels('meatball')");
	System.out.println(allVowels("meatball"));
	System.out.println("allVowels('lllll')");
	System.out.println(allVowels("lllll"));
	System.out.println(allVowels("lAAA"));
	System.out.println("=======================");


	System.out.println("Testing allLetters()"); 
	System.out.println(allLetters("apple"));
	System.out.println(allLetters("APPLE"));
	System.out.println(allLetters("1apple"));
	System.out.println(allLetters("b-day"));

	System.out.println("Testing firstVowel()");
	System.out.println("firstVowel('meatball')");
	System.out.println(firstVowel("meatball"));
	System.out.println("firstVowel('')");
	System.out.println(firstVowel(""));
	System.out.println("firstVowel('zzz')");
	System.out.println(firstVowel("zzz"));
	System.out.println("firstVowel('mEAtball')");
	System.out.println(firstVowel("mEAtball"));
	System.out.println("======================="); 

	System.out.println("Testing beginsWithVowel()");
	System.out.println("beginsWithVowel('Apple')");
	System.out.println(beginsWithVowel("Apple"));
	System.out.println("beginsWithVowel('apple')");
	System.out.println(beginsWithVowel("apple"));
	System.out.println("beginsWithVowel('bagel')");
	System.out.println(beginsWithVowel("bagel"));
	System.out.println("=======================");
	
	System.out.println(findPunc("b-day"));
	System.out.println(findPunc("e'er"));
	System.out.println(findPunc("browns'"));

	System.out.println(withPuncTransl("b-day"));
	System.out.println(withPuncTransl("bday."));
	System.out.println(withPuncTransl("sky."));
	System.out.println(withPuncTransl("browns'"));


	System.out.println(upperFix("Yes", "eys"));
	System.out.println("Testing engToPig()");
	System.out.println("engToPig('Hello')");
	System.out.println(engToPig("Hello"));
	System.out.println("engToPig('apple')");
	System.out.println(engToPig("apple"));
	System.out.println("engToPig('strong')");
	System.out.println(engToPig("strong"));
	System.out.println("engToPig('java')");
	System.out.println(engToPig("java"));
	System.out.println("engToPig('Strong')");
	System.out.println(engToPig("Strong"));
	System.out.println("engToPig('sky')");
	System.out.println(engToPig("sky"));
	System.out.println("engToPig('b-day')");
	System.out.println(engToPig("b-day")); 
	System.out.println("engToPig('Browns'.')");
	System.out.println(engToPig("Browns'."));
	*/	
	Scanner sc = new Scanner(System.in);


	while(sc.hasNext()) {
	    String nxt = sc.next();
	    System.out.println("Input: " + nxt);
	    System.out.println("Output:" +  engToPig(nxt));
	}


    }//end main()

}//end class Pig
