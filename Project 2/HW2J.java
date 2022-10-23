/* This is the class for HW2 by Sujin Jiang */
  
public class HW2J extends Object{

  // this is method1 returns String
  public static String replaceFirstK(String s, char a, char b, int x){
    
    //this sum accounts for the summation of the appearance of char a 
    int sum = 0;
    StringBuilder builder = new StringBuilder();
    
    //this for loop accounts for the 
    for(int index = 0; index < s.length(); index = index + 1){
      if(s.charAt(index) == a && sum <= x){
      builder.append(b);
      sum = sum + 1;
      }
      else builder.append(s.charAt(index));
    }
    return builder.toString();
  } 


//this is method2 returns String
public static String allChars(char front, char back){
  StringBuilder builder = new StringBuilder();
  for(int index = front; index <= back; index = index + 1)
  {
    builder.append((char)index);
  }
  return builder.toString();
}

//this is method3 returns Strings 
public static String showCharOfString(String s, String t){
  StringBuilder builder = new StringBuilder();
   for(int index = 0; index < s.length(); index = index + 1){
     if(t.indexOf(s.charAt(index)) == -1)
     {
       builder.append('_');
     }
     else
       builder.append(s.charAt(index));
   }
   return builder.toString();
}

//this is method4 returns boolean
public static boolean hangman(String guessWord, int badguess){
  StringBuilder builder = new StringBuilder();
  int count = 0;
  while (count < badguess){
    System.out.println(showCharOfString(guessWord,builder.toString()));
     String peopleGuess = javax.swing.JOptionPane.showInputDialog("guess a letter" + (badguess - count) + "remained chance");
for(int index = 0; index < guessWord.length(); index = index + 1){
  if(guessWord.charAt(index) != peopleGuess.charAt(0)){
     count = count + 1;
     System.out.println("bad guess" + count);
}

  else{
      builder.append(peopleGuess.charAt(0));
    }
}
  
if(count == badguess)
      return false;
     else 
      if(peopleGuess.equals(guessWord))
      return true;
  }
if (count == badguess)
      return false;
else 
  return true;
}



//this is method5 returns boolean 
public static boolean hiddenString(char[] array1, String s){
  for(int index = 0; index <= (array1.length - s.length()); index = index + 1){
    String t = new String();
    for (int front = index; front < (index + s.length()); front = front + 1){
      t = t + array1[front];
    }
    if(t.equals(s))
    {
      return true;
    }
  }
  
  for(int index1 = array1.length-1; index1 >= (s.length()-1); index1 = index1-1){
     String t = new String();
    for(int back = index1; back > (index1-s.length()); back = back - 1){
    t = t + array1[back];
  }
      if(t.equals(s))
        {
        return true;
      }
      }
return false;
}



      
 // this is method6 returns boolean
public static boolean hiddenString(char[][] array1, String s){
  for(int index = 0; index < array1.length; index = index + 1){
    int a;
    a = array1[index].length;
    for(int index0 = 0; index0 <= (array1[index].length - s.length()); index0 = index0 + 1){
     String x =new String();
     for(int left = index0; left <= (index0 + s.length()) && left < (array1.length-1); left = left + 1){
     x = x + array1[index][left];
     }
     if(x.equals(s)){
       return true;
     }
    }
    for(int index1 = (array1[index].length-1); index1 >= (s.length()-1); index1 = index1-1)
    {
      String x =new String();
      for(int right = index1; right < (array1.length-1) && right > (index1 - s.length()); right = right - 1)
      {
        x = x + array1[index][right];
      }
       if(x.equals(s))
       {
         return true;
       }
    }
    for (int index2 = 0; index2 <= (array1.length-s.length()); index2 = index2 + 1){
      String x = new String();
      for (int down = index2; down < (array1.length-1) && down < (index2 + s.length()); down = down + 1){
        x = x + array1[down][index];
      }
      if (x.equals(s))
      {
        return true;
      }
    }
    for(int index3 = (array1.length - 1); index3 >= (s.length()-1); index3 = index3 + 1){
      String x = new String();
      for(int up = index3; up < (array1.length-1) && up > (index3 - s.length()); up = up - 1)
      {
        x = x + array1[up][index];
      }
      if (x.equals(s))
      {
        return true;
      }
    } 
  }
return false;
}
}
