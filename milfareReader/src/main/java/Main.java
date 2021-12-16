import at.ac.htl.leonding.mifare.MifareReader;
import com.fazecast.jSerialComm.SerialPort;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Main {
    public static void main(String[] args) {
        findReader();

    }

    private static void findReader() {
        var reader = MifareReader.findReader();
        System.out.println(reader.toString());
        reader.open();
        reader.beep();
        reader.close();

        reader.open();
        reader.enquiryCard();
        reader.close();

        reader.open();
        reader.anticollision();
        reader.close();
    }

/*    byte[] prot ={
        "enquiry_module": "\x03\x12\x00\x15",
                'enquiry_module_return': '\x02\x12\x14',
                'active_buzzer': '\x02\x13\x15',
                'enquiry_card': pack('BBBB', 03, 02, 00, 05),
                'enquiry_cards_return': '\x03\x02\x01\x06',
        'enquiry_no_card_found': '\x02\x01\x03',
        'enquiry_all_cards': '\x03\x02\x01\x05',
                'anticollision' : '\x02\x03\x05\x00',
                'select_card' : '\x02\x04\x06"}*/



    public static boolean portIsOpen(SerialPort comPort) {
        var reader = new MifareReader(comPort);
        reader.open();
        var isOpen = reader.hello();
        System.out.println(isOpen? "open\n" : "closed\n");
        reader.close();
        return isOpen;
    }

}
