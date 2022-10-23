/*Bruce Dong Project 2
 * This class contains various function of array and string and also a hangman game
 */
public class HW2 {
  
  // This is the  method No.1
  // It takes a string and replace the targeted character (char repalce) with the input char ( char now)
  // It only replaces certain times ( inc time)
  public static String replaceFirstK( String old, char repalce, char now, int time){
    // This int counts how many time the targeted char is replaced
    int count = 0;
    
    // This if is for some nuts who do not want any change
    if( time == 0)
      return old;
    //This builder will be the output of the string
    StringBuilder builder = new StringBuilder();
    
    // Now let's replace all the targeted char
    for(int index = 0; index < old.length(); ++index){
      
      // This if judge whether the replacing time is reached and 
      // whether the char now needs to be replaced
      if(old.charAt(index) == repalce && count <= time){
        builder.append(now);
        
        // If it is replaced, accumulate the time of replacement 
        ++count;
      }
      
      else
        builder.append(old.charAt(index));
    }
      
      return builder.toString();
  }
  
  // This is method No.2
  // This method return a string that contain all char between ( inclusive) first and second input char
  public static String allChars( char first, char last ){
    
    // The builder is the targeted string builder
    StringBuilder builder = new StringBuilder();
    
    // This loop just add chars one by one
    for(int index = 0; index < last-first+1; ++index)
      builder.append( (char)(first + index));
    
    return builder.toString();
  }
      
    

  // This is method No.3
  // In this method replace each char in String old to a '_' if the char is not in string weWant
  public static String showCharOfString( String old, String weWant){
    
    // The old friend, string builder
    StringBuilder builder = new StringBuilder();
    
    // In this loop, it tests each char and to see if this char is the one that we want to keep
    for( int index = 0; index < old.length(); ++index){
      //The loop below test each char that is picked by the loop above and to see whether it is or not
      for (int count =0; count < weWant.length(); ++ count){
        
        // This if just judge it is or not 
        if( old.charAt(index) == weWant.charAt(count)){
          builder.append(weWant.charAt(count));
          count = weWant.length();
        }
        //When it test the last char in String weWant,  if it is not what we want, append the '_' 
        else{ 
          if( count == weWant.length() -1)
            builder.append('_');
        }
      }
    }
    return builder.toString();
  }
    

  
  // This is method No.4
  // This is a hangman game, and you just need to input the vocalbulary and total time that is allowed)
  public static boolean hangman ( String trueWord, int totalTime){
    
    //This Stringbuilder contains all the word that the user guesed 
    StringBuilder builder = new StringBuilder();
    
    // append ' ' to make it has a length
    builder.append(' ');
    
    // This is the string shows all the word thast has been guessed on the intial volcalbulary
    // Like: st_eet when guesed ste and the original word is street
    String result = new String();
    
    // This int counts how many valid bad guess. ( guess two time same word is not counted as twice)
    int badGuess = 0;
    
    // This int is an indicator to see whether the guess letter is the same or not
    int indi = 0;
    
    // For debug reason, this if will make the loop work, if some people want 0 failure in guess
    if( totalTime == 0)
      totalTime = 1;
    
    // This loop stops when the total allowed guess time have all been used or the user gusses the right word
    while (badGuess < totalTime){
      
      //Reset the indicator to 0
      indi = 0;
      
      // This variable stores like st_eet 
      result =showCharOfString( trueWord, builder.toString());
      
      System.out.println( "current word: " +result + "  Bad guess times: " + badGuess);
      
      System.out.println("All of your guess letters are: " + builder.toString());
      
      // This string stores the current guess letter
      String guess = javax.swing.JOptionPane.showInputDialog("Please type your guess letter.");
      
      
      
      //This is the for loop to judge whether the typed letter is the same guess or not 
      for ( int count = 0; count < ( (builder.toString()).length() ) ; ++ count){
        if ( guess.charAt(0)  == ( (builder.toString()).charAt(count) ) )
          indi = 1;
      }
      //if the input is a new letter and it is only one letter , append it
      if (indi !=1 && (guess.toString()).length() == 1 ){
        builder.append(guess);
        indi = 99;
      }
      
      // This is the loop to judge whether it is a correct guess or not 
      for( int count = 0; count < trueWord.length() ; ++count){
        if(guess.charAt(0) == trueWord.charAt(count)){
          System.out.println("Correct Guess");
          count = trueWord.length();
        }
        else{
          if( count == trueWord.length()-1 && indi ==99 ){
            System.out.println("Bad Guess");
            ++badGuess;
          }
        }
      }
      
      //This if judge whether the user type a different letter or not
      if(indi == 1)
        System.out.println("You should type a different letter!");
      
      // This if turns the badGuess really big if the user gussed all the letters as an indicator and also break the loop
      if ( trueWord.equals( showCharOfString ( trueWord, builder.toString())))
        badGuess = Integer.MAX_VALUE;
    }
    
    System.out.println("All of your guess letters are: " + builder.toString());
    
    // This if judges lose or not
    if( badGuess == Integer.MAX_VALUE){
      System.out.println("The word is: " + trueWord + " and you win!");
      return true;
    }
    System.out.println("The word is: " + trueWord + " and you die!");
    return false;
  }
  
  // This is method No.5
  // This method search the whole array to see whether the input string is in side the array or not (front and bak)
  public static boolean hiddenString( char[] cha, String stg){
    
    // This indicator counts how many letters match the input stg in the loop bellow
    int indicator = 0;
    
    // This loop search the whole array forward
    for(int index = 0; index< cha.length-stg.length()+1; ++index){
      
      // After the big loop pick up a letter, the loop below search the array  forward (the start point is the letter that the big loop picked)
      for ( int indexs = 0; indexs< stg.length(); ++ indexs){
        
        // This loop sees whether the picked letter in the array match one of the letter in the string
        if( cha[index+indexs] == stg.charAt(indexs))
          indicator =indicator +1;
      }
      
      // This if judge whether the matches letter number equal to the length of the input string
      if( indicator == stg.length())
        return true;
      indicator =0;
    }
    
    // This loop search the whole array backward
    for(int index = cha.length-1; index > stg.length()-2; --index){
      
      // After the big loop pick up a letter, the loop below search the array backwards (the start point is the letter that the big loop picked)
      for ( int indexs = 0; indexs < stg.length(); ++ indexs){
        
        // This loop sees whether the picked letter in the array match one of the letter in the string
        if( cha[index-indexs] == stg.charAt(indexs))
          indicator = indicator +1;
      }
      
      // This if judge whether the matches letter number equal to the length of the input string
      if( indicator == stg.length())
        return true;
      indicator = 0;
    }
    return false;
  }

  
  // This is method No.6
  // This method search the 2d array horizontally, vertically and on diagnoal 
  // It will deterime whether the loop has agroup of char that matches the input string
  public static boolean hiddenString ( char [][] cha, String stg){
    // To better understand how the whole methods works, here is the explantion:
    // First, I turn the 2d array into an 1d array according to how we read it. 
    // Thus, there are four 1d array, one is reading horizontally, one is reading from down to up vertically, one reads from up to down, and one reads two diagonal lines
    
    // First, it count the total units in the char array, and int total stores the number
    int total = 0;
    
    // If we see an 2d array that has the same length in each sub array and it is like this:
    //  { a,b,c,d}
    //  { a,b,c,_}
    //  { a,_,_,_ }
    //  The _ means the space that is not filled in yet.
    // Then it can be considered as a rectangle, and it has a long side and a short side
    // The varaible below stores the longer side's length and shorter side's length of the rectangle 2d array
    int longSide = 0;
    int shortSide = 0;
    
    // This veriable means index targeted 1d array, which is the 1d array that we are going to create
    // It is used to count that how many units has been filled into that array, and it will appear when I creat each 1d array
    int indext = 0;
    
    // Because of my sepcial way, I need an variable that counts which 1d array inside the input 2d array has most value
    // To store the longest value, I use the variable longest and initally it is the first 1d array in the input 2d array
    int longest = cha[0].length;
    
    // This loop counts the total char units in the 2d input array
    for(int index = 0; index<cha.length; ++index){
      for (int indexr= 0; indexr<cha[index].length; ++indexr)
        total=total+1;
    }
    
    // This loop finds out the longest 1d array's length inside the 2d input array 
    for(int index = 0; index<cha.length; ++index){
      if(cha[index].length > longest)
        longest=cha[index].length;
    }
    
    // This if and if and else determines the longside and shortside's length of the "rectangle shape" 2d array
    longSide = longest;
    if(longest < cha.length)
      longSide = cha.length;
    
    if(longest < cha.length)
      shortSide = longest;
    else
      shortSide = cha.length;
    
    // up, and down array is the 1d array that I will create below 
    // If we print out a 2d array one unit by another vertically (down to up and up to down) it will be identicall to the up and down array
    // eg: {a,b}
    //       {c,d}
    // Will turn to {a,c,��,b,d} or {c,a,��,d,b}
    // The �� here just breaks the two line 
    char[] up = new char[total+longSide];
    char[] down = new char[total+longSide];
    // Similar concept, right is when we print 2d array horrizontally 
    // eg: {a,b}
    //       {c,d}
    // Will turn to {a,b,��,c,d}
    // The �� here just breaks the two line
    char[] right = new char[total+cha.length];
    // Similar concept, diagonal is when we print 2d array's diagonal line
    // eg: {a,b}
    //       {c,d}
    // Will turn to {a,d,��,c,b}
    // The �� here just breaks the two line
    char[] diagonal = new char[2*longSide+2];
    // This rectangle is the 2d array that will have the "rectangle shape above that I will create below
    // eg: {a,b,f}
    //       {c,d}
    // Will trun to
    //       {a,b,f}
    //       {c,d, ,}
    // The �� here just breaks the two line
    char[][] rectangle = new char[cha.length][longest];
    
    // This loop is when we copy every unit of the input array into the new reactangle array 
    for(int index = 0; index<cha.length; ++index){
      for (int indexr = 0; indexr<cha[index].length; ++indexr){
        rectangle[index][indexr] = cha[index][indexr];
        ++indext;
      }
    }
    
    indext = 0;
    
    // This loop will trun the 2d input array into 1d, just like we read the array horizontally (from right to left)
    for(int row = 0; row<cha.length; ++row){
      for (int col = 0; col<cha[row].length; ++col){
        right[indext] = cha[row][col];
        ++indext;
      }
      right[indext] = '.';
      ++indext;
    }
    
    indext =0;
    
    // This loop will trun the 2d input array into 1d, just like we read the array vertially (from down to up)
    for(int row = 0; row < longest; ++row){
     for (int col = rectangle.length-1; col >= 0; --col){
       if(rectangle[col][row] != 0){
         up[indext] = rectangle[col][row];
         ++indext;
       }
     }
     up[indext] = '.';
     ++indext;
    }
    
    indext = 0;
    
    // This loop will trun the 2d input array into 1d, just like we read the array vertially (from up to down)
    for(int row = 0; row < longest; ++row){
     for (int col = 0; col < rectangle.length; ++col){
       if(rectangle[col][row] != 0){
         down[indext] = rectangle[col][row];
         ++indext;
       }
     }
     down[indext] = '.';
     ++indext;
    }
    
    indext = 0;
    
    // Two loops bellow will stroe the digonal line of the 2d array 
    for(int index = 0; index < shortSide; ++index){
      diagonal[indext] = rectangle[index][index];
      ++indext;
    }
    diagonal[indext] = '.';
    ++indext;
    
    for(int index = shortSide-1; index >= 0; --index){
      diagonal[indext] = rectangle[index][shortSide-index-1];
      ++indext;
    }
    diagonal[indext] = '.';
    ++indext;
    
    // All the if just call the method 5, and put in each 1d array created above
    // If when we read the array in a certain way, it contains the input string, it will return true
    if(hiddenString(right, stg))
      return true;
    
    if(hiddenString(up, stg))
      return true;
    
    if(hiddenString(down, stg))
      return true;
    
    if(hiddenString(diagonal, stg))
      return true;

    
    return false;
  }
  
  // This is the extra credit, mtehod No.7, 
  // It turns every english word to capital when the word contain at leat one captical letter
  //ab-bc counts as one word 
  public static String capitalizeWords( String str){
    //In this method, here is the main method
    // I will use an array to collect each word's start position and end position
    // eg: "abc bac"
    // In the arry will look like
    // {0, 2, 4, 6}
    // Then, just judge whether each single word contains capital letters or not
    // Then append each word and the stuff between each word accordingly to the stringbuilder 
    // This is the array that contains every information 
    int[] count = new int[str.length()];
    // This is the index for store each letter's information inside the 1d array
    int indexa = 0;
    // This variable stores whether the letter contains capital letters or not
    int indicator = 0;
    // old friend
    StringBuilder builder = new StringBuilder();
    
    // This if makes sure that some nuts type in nothing or only one letter and the method still works
    if(str.length() == 0 || str.length() == 1)
      return str;
    
    // starts form now until next comment, the code here will store each letter's information to the array
    // Also, for debug reason, I store the first unit of the string and the last unit in the string alone
    count[indexa] = 0;
    indexa++;
    for(int index = 1; index < str.length()-1; ++index){
      if((str.charAt(index) > 64 && str.charAt(index) < 91 ) || (str.charAt(index) > 96 && str.charAt(index) < 123 )){
        if(  (str.charAt(index+1) < 65 || (str.charAt(index+1) > 90 &&  str.charAt(index+1) < 97) ||  str.charAt(index+1) >122 || str.charAt(index-1) < 65 || (str.charAt(index-1) > 90 &&  str.charAt(index-1) < 97) ||  str.charAt(index-1) > 122) && (str.charAt(index-1) != 45&& str.charAt(index+1) != 45)){
          count[indexa] = index;
          ++indexa;
        }
      }
    }
    if( (str.charAt(str.length()-1) < 65 & str.charAt(str.length()-1) > 0) || (str.charAt(str.length()-1) > 90 && str.charAt(str.length()-1) < 97) || str.charAt(str.length()-1) >122)
      count[indexa] = str.length()-1;
    count[indexa] = str.length()-1;
    
    // Now, just append each letter and stuff in between into the string builder
    for(int index = 0; index <= indexa; index = index+2){
      //The first part determines whether the letter contains capital or not
      for( int start = count[index] ; start <= count[ index+1]; ++start){
        if( str.charAt(start) > 64 && str.charAt(start) < 91)
          indicator = 1;
      }
      //For debug reason, I store the first and last letter into the builder along
      if( indicator == 1 && (index == 0 || index == indexa)){
        for( int start = count[index]  ; start <= count[ index+1]; ++start){
          // This is the part that turns every letter to upper case
          if( str.charAt(start) >64 && str.charAt(start) < 91 && str.charAt(start) != 45)
            builder.append ( (char) (str.charAt(start) ));
          else
            builder.append ( (char) (str.charAt(start) -32));
          // This is the part that stores the '-' part
          if( str.charAt(start) == 45)
            builder.append ( (char) (str.charAt(start)));
        }
      }
      // This else if part store the whole-lower case letter to the builder
      else if( index == 0|| index == indexa){
        for( int start = count[index]; start <= count[ index+1]; ++start){
          builder.append( (char)str.charAt(start));
        }
      }
      // This is the part that stores all other letters
      // This if-loop code has no actuall difference comparing to the if-loop code above,
      if( indicator == 1 && (index > 0 && index < indexa) ){
        for( int start = count[index]  ; start <= count[ index+1]; ++start){
          if( str.charAt(start) > 64 && str.charAt(start) < 91)
            builder.append ( (char) (str.charAt(start ) ));
          else
            builder.append ( (char) (str.charAt(start) -32));
          if( str.charAt(start) == 45)
            builder.append ( (char) (str.charAt(start) ));
        }
      }

      else if( index > 0 && index < indexa && (str.charAt(count[index]) > 96 && str.charAt(count[index]) < 123)) {
        for( int start = count[index]  ; start <= count[ index+1] ; ++start){
          builder.append( (char)str.charAt(start));
        }
      }
      
      indicator =0;
      // This part stores all the stuff between two words into the builder
      for (  int start = count[index+1]+1 ; start <= count[ index+2] -1; ++start){
        builder. append ( (char)str.charAt(start));
      }
      // if we finish store the words, the if state will change the index and break the loop
      if( count[index] == 0 && count[index+1] == 0)
        index = 999;
    }
    // This if part stores the last punctuation into the builder
    if( (str.charAt(str.length()-1) < 65 & str.charAt(str.length()-1) > 0) || (str.charAt(str.length()-1) > 90 && str.charAt(str.length()-1) < 97) || str.charAt(str.length()-1) >122)
      builder.append(str.charAt(str.length()-1));
      
    return builder.toString();
  }

}