/*
package at.htl.mealcounter.control;

import jline.Terminal;
import jline.TerminalFactory;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

public class ReaderTest {

    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1,data));
    }

    @Test
    public void t010() {

        try {

            // Display the list of terminals
            Terminal terminal = TerminalFactory.getFlavor(TerminalFactory.Flavor.UNIX);
            List<CardTerminal> terminals = factory.terminals().list();
            System.out.println("Terminals: " + terminals);

            // Use the first terminal
            CardTerminal terminal = terminals.get(0);

            // Connect wit hthe card
            Card card = terminal.connect("*");
            System.out.println("Card: " + card);
            CardChannel channel = card.getBasicChannel();

            // Send test command
            ResponseAPDU response = channel.transmit(new CommandAPDU( new byte[] { (byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00 }));
            System.out.println("Response: " + response.toString());

            if (response.getSW1() == 0x63 && response.getSW2() == 0x00)  System.out.println("Failed");

            System.out.println("UID: " + bin2hex(response.getData()));

            // Disconnect the card
            card.disconnect(false);

        } catch(Exception e) {

            System.out.println("Ouch: " + e.toString());

        }
    }
}
*/
