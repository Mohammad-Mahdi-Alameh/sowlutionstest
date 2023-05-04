import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(validateEmail("john.doe@gmail.com")) ;// Returns true
        System.out.println(validateEmail("john@doe@gmail.com")) ;// Returns true
        System.out.println(validateEmail("john@gmail.c")) ;// Returns true
        System.out.println(validateEmail("john@.com")) ;// Returns true
    }
    public static List<Character> convertStringToCharList(String str){
        List<Character> chars = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }
    public static boolean checkDotCom(String email){
        String lastFourChars = email.substring(email.length() - 4);
        if (lastFourChars.equals(".com")){
            return true;
        }
        return false;
    }
    public static boolean checkBeforeAndAfterDot(String email){
        char[] emailCharacters = email.toCharArray();
        for (int i = 0; i < emailCharacters.length; i++) {
            if(emailCharacters[i] == '.'){
                if(emailCharacters[i-1] == '@' || emailCharacters[i+1] == '@'){
                    return false;
                }
            }
        }
        return true;
    }
    public static boolean validateEmail(String email){
        email = email.trim();
        int emailLength = email.length();
        if(emailLength == 0 || emailLength > 256 || emailLength < 6){
            return false;
        }
        long countOfAt = email.chars().filter(ch -> ch == '@').count();
        if(countOfAt != 1){
            return false;
        }
        int indexOfAt = email.indexOf("@");
        if(indexOfAt == 0 || indexOfAt == email.length()-1 ){
            return false;
        }
        String[] afterAtStrings = email.split("@");
        String afterAtString = afterAtStrings[1];
        long countOfDot = afterAtString.chars().filter(ch -> ch == '.').count();
        if(countOfDot == 0 || countOfDot < 0){
            return false;
        }
        int indexOfDot = email.indexOf(".");
        if(indexOfDot == 0 || indexOfDot == email.length()-1 ){
            return false;
        }
        if(!checkBeforeAndAfterDot(email) || !checkDotCom(email)){
            return false;
        }
        return true;
    }
}

