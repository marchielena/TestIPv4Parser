package MarchiElena;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AppTest {

    @Test
    public void Test1() {
        String ip_addr = "192.168.50.1";
        String mask = "255.255.255.0";
        assertEquals(App.networkblockparser(ip_addr, mask), "192.168.50.0");
        assertEquals(App.hostidentifierparser(ip_addr, App.networkblockparser(ip_addr, mask)), "0.0.0.1.");
    }

    @Test
    public void Test2() {
        String ip_addr = "192.168.50.129";
        String mask = "255.255.255.192";
        assertEquals(App.networkblockparser(ip_addr, mask), "192.168.50.128");
        assertEquals(App.hostidentifierparser(ip_addr, App.networkblockparser(ip_addr, mask)), "0.0.0.1.");
    }

    @Test
    public void Test3() {
        String ip_addr = "65.196.188.53";
        String mask = "255.255.240.0";
        assertEquals(App.networkblockparser(ip_addr, mask), "65.196.176.0");
        assertEquals(App.hostidentifierparser(ip_addr, App.networkblockparser(ip_addr, mask)), "0.0.12.53.");
    }
}
