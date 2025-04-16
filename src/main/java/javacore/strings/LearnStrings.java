package javacore.strings;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LearnStrings {
    public static void main(String[] args) {
        String str = " higher ";
        System.out.println("str.length(): " + str.length());
        System.out.println("str.contains(\"hi\"): " + str.contains("hi"));
        System.out.println("str.isEmpty(): " + str.isEmpty());
        System.out.println("str.toUpperCase(): " + str.toUpperCase());
        System.out.println("str.startsWith(\"h\"): " + str.startsWith("h"));
        System.out.println("str.endsWith(\" \"): " + str.endsWith(" "));
        System.out.println("str.replace(\"r\", \"st\"): " + str.replace("r", "st"));
        System.out.println("str.trim(): " + str.trim() + ", new length: " + str.trim().length());
        System.out.println("str.strip(): " + str.strip());
        System.out.println("str.substring(0, 4): " + str.substring(4, 7));
        System.out.println("Arrays.toString(str.getBytes()): " + Arrays.toString(str.getBytes()));
        System.out.println("Arrays.toString(str.toCharArray()): " + Arrays.toString(str.toCharArray()));
        System.out.println("str.charAt(3): " + str.charAt(3));
        System.out.println("Arrays.toString(str.split(\"i\"): " + Arrays.toString(str.split("h")));  // [ , ig, er ]

        System.out.println(str);  // String-type data are immutable.

        System.out.println("\n---- String comparisons ----");
        String str2 = " higher ";
        System.out.println("str == str2: " + (str == str2));  // true
        String str3 = new String(" higher ");
        System.out.println("str == str3: " + (str == str3));  // false
        System.out.println("str.equals(str3): " + str.equals(str3));  // true
        System.out.println("str == str3.intern(): " + (str == str3.intern()));  // true
        System.out.println();

        String me = "Aurèle";
        String itIsI = "aurele";
        System.out.println("me.equals(itIsI): " + (me.equals(itIsI)));  // false
        System.out.println("me.equalsIgnoreCase(itIsI): " + (me.equalsIgnoreCase(itIsI)));  // true

        System.out.println("\n---- Escape sequences ----");
        System.out.println("Say \t\"Hi\" for me");
        System.out.println("The location path is: C:\\format\\you_naive\\readme.md");
        System.out.println("My first line\nends in the second one");
        System.out.println("Respect copyright: \u00A9 Oxus");
        
        System.out.println("\n---- String formatting ----");
        String greetings = "Good %s, my dear %s! You are the %dth guest arrived.";
        String morning = "morning";
        String evening = "evening";
        System.out.println(greetings.formatted(morning, me, 12));
        System.out.printf((greetings) + "%n", evening, itIsI, 13, 15, me);  // other arguments are ignored.

        System.out.println("\n---- Regex ----");
        String tutaPattern = "[a-zA-Z-\\.\\d]+@tuta\\.io";

        Pattern pattern = Pattern.compile(tutaPattern);  // Compiles a regex into a pattern.
        String sampleText = """
                Some random text here including a tuta adress
                like this one aurele.45-programmer@tuta.io followed
                by some other text and another IAmThatIam-666@tuta.io backup email.""";
        Matcher matcher = pattern.matcher(sampleText);
        matcher.find();
        String tutaAddress = matcher.group();
        System.out.println(tutaAddress);

        String sampleText2 = "They are 3 sentences in this line. Are you certain? Oh yes, there are indeed 3 of them!";
        String[] sentences = sampleText2.split("[\\.!?]");
        System.out.println(Arrays.toString(sentences));
    }
}
