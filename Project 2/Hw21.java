public class Hw21{
  

  //task 6:
   public static String repeatWords (String str,int n){

     int start = 0;
     int end = 0;

     StringBuilder stb = new StringBuilder();

     if(n == 0){
       return null;
     }

     for(int i = 0; i < str.length(); i++){
       if(Character.isLetter(str.charAt(i))){
         start  = i;
         for(int j = 0; j < n; j++) {
           i = start;
           while (i < str.length() && Character.isLetter(str.charAt(i))) {
             stb.append(str.charAt(i));
             i++;
           }
           if(j+1 != n)
            stb.append(' ');
         }
         i--;
       }
       else{
        stb.append(str.charAt(i));
      }
    }

    return stb.toString();
   }
  


  public static void main (String args[]) {
    String test = "Abc def ! ? h k Jou";
    Character ab = 'a';
    System.out.println(repeatWords(test, 2));
    test  = "'How are you?', I asked.";
    System.out.println(repeatWords(test, 2));
  }

  
}