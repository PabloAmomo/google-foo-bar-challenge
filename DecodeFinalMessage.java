import java.util.Base64;
import java.nio.charset.StandardCharsets;

public class DecodeFinalMessage {
    // This is the decoded message received when finishing the Level 5 challenge. :-)
    // 
    // Submitting solution...
    // 
    // With one last roar of the escape pod's engines, you and your bunny companions jump to lightspeed.
    // 
    // Congratulations! You've destroyed the LAMBCHOP, relieved the bunnies, gotten Commander Lambda off your tail, and saved the galaxy. Time for a little rest and relaxation back on Bunny Planet. Pat yourself on the back -- you've earned it!
    // 
    // Submission: SUCCESSFUL.
    // 
    // Completed in: 10 hrs, 54 mins, 0 secs..
    // 
    // <encrypted>b'C0YRGQwCCBweSFBbQksIEwgOGUhcQUUPAA0BCgwIBQRFTFVBSgoeGxUEDwkLRkFPSgoWBw0eGxJK\nT1dPVwgMDx0ECQYPAxVGTkxIAA4HBAoGBA8JARVKT1dPVxQMAAACBgoJSFxBRR4OAw8GGRxXQVhM\nSBIMCQhIXEFFCgAOSk9XT1cWCwJORhA='</encrypted>
    //       
    //     For your eyes only!
    //     Use the status command to repeat this message.
    // 
    
    public static void main(String[] args) {
        String message = "C0YRGQwCCBweSFBbQksIEwgOGUhcQUUPAA0BCgwIBQRFTFVBSgoeGxUEDwkLRkFPSgoWBw0eGxJK\nT1dPVwgMDx0ECQYPAxVGTkxIAA4HBAoGBA8JARVKT1dPVxQMAAACBgoJSFxBRR4OAw8GGRxXQVhM\nSBIMCQhIXEFFCgAOSk9XT1cWCwJORhA=";
        String key = "pabloamomo";
        
        byte[] b64toASCII = Base64.getDecoder().decode(message.replace("\n", ""));
        byte[] decoded = xorWithKey(b64toASCII, key.getBytes(StandardCharsets.UTF_8));

        String jsonString = new String(decoded, StandardCharsets.UTF_8).replace("'", "\"");
        
        System.out.println("---------------------------------------------------------------------");
        System.out.println(jsonString);
        System.out.println("---------------------------------------------------------------------");
    }

    private static byte[] xorWithKey(byte[] input, byte[] key) {
        byte[] output = new byte[input.length];
        for (int i = 0; i < input.length; i++) {
            output[i] = (byte) (input[i] ^ key[i % key.length]);
        }
        return output;
    }
}