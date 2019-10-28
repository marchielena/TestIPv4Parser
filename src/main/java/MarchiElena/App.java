package MarchiElena;

public class App {
    public static void main(String[] args) {
        String ip_addr = "192.168.50.1";
        String mask = "255.255.255.0";
        ipv4parser(ip_addr, mask);
    }

    public static String ipv4parser(String ip_addr, String mask) {
        String networkblock = networkblockparser(ip_addr, mask);
        String hostidentifier = hostidentifierparser(ip_addr, networkblock);
        System.out.println(networkblock);
        System.out.println(hostidentifier);
        return ip_addr;
    }

    public static String networkblockparser(String ip_addr, String mask) {
        String[] iparr = ip_addr.split("\\.");
        String[] maskarr = mask.split("\\.");
        String ris = "";
        StringBuffer networkblock = new StringBuffer();

        for (int i = 0; i < iparr.length; i++) {
            iparr[i] = converter(Integer.valueOf(iparr[i]));
            maskarr[i] = converter(Integer.valueOf(maskarr[i]));
            String[] ipbin = iparr[i].split("");
            String[] maskbin = maskarr[i].split("");
            ris = comparison(ipbin, maskbin);
            networkblock.append(Integer.parseInt(ris, 2));
            if(i<3){
                networkblock.append(".");
            }
        }
        return networkblock.toString();
    }

    public static String hostidentifierparser(String ip_addr, String networkblock) {
        String hostidentifier = "";
        String[] iparr = ip_addr.split("\\.");
        String[] nwarr = networkblock.split("\\.");
        int[] iparrn = new int[iparr.length];
        int[] nwarrn = new int[nwarr.length];
        for (int i = 0; i < iparr.length; i++) {
            iparrn[i] = Integer.valueOf(iparr[i]);
            nwarrn[i] = Integer.valueOf(nwarr[i]);
            hostidentifier += Integer.toString(iparrn[i] - nwarrn[i]) + ".";
        }
        return hostidentifier;
    }

    public static String converter(int n) {
        StringBuffer str = new StringBuffer();
        while (n > 0) {
            str.append(n % 2);
            n /= 2;
        }
        if (str.length() < 8) {
            int value = 8 - str.length();
            for (int i = 0; i < value; i++) {
                str.append("0");
            }
        }
        return str.reverse().toString();
    }

    public static String comparison(String[] ipbin, String[] maskbin) {
        String ris = "";
        for (int i = 0; i < ipbin.length; i++) {
            if (ipbin[i].equals(maskbin[i])) {
                ris += ipbin[i];
            } else {
                ris += "0";
            }
        }
        return ris;
    }
}
