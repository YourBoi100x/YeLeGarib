import java.util.*;
public class Main{
    public static void main(String[] args){
        // INPUTS:
        Scanner scan = new Scanner(System.in);
        System.out.println("Choose Any one as input :");
        System.out.println("1.Classless IP");
        System.out.println("2.Classfull IP");
        int t = scan.nextInt();
        String s;
        int no_of_required_networks;
        if(t == 1){
            System.out.println("Enter Classless IP:");
            String str = scan.next();
            s = "";
            int size = str.length();
            inner2:
            for(int i = 0;i<size;i++){
                if(str.charAt(i) == '/') break inner2;
                s+=Character.toString(str.charAt(i));
            }
            System.out.println("Enter fixed length: ");
            no_of_required_networks = scan.nextInt();
        }
        else if(t == 2){
            System.out.println("Enter Classfull IP:");
            s = scan.next();
            System.out.println("Enter no of required subnets: ");
            no_of_required_networks = scan.nextInt();
        }
        else{
            return;
        }
        // code to check which class does the ip belongs to:
        String check = "";
        int n =s.length();
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='.'){
                break;
            }
            else check= check+Character.toString(s.charAt(i));
        }
        int find = Integer.parseInt(check);
        int[] arr = new int[8];
        for(int i = 7;i>=0;i--){
            arr[i] = (int)(find&1);
            find= find>>1;
        }
        int cnt = 0;
        for(int i =0;i<4;i++){
            if(arr[i] ==1) cnt++;
            else break;
        }

        // calling functions accordingly:
        if(cnt == 0){
            A();
            System.out.println();
            System.out.println("Details of Subnets:");
            subnet(s,no_of_required_networks,1);
        }
        else if(cnt == 1){
            B();
            System.out.println();
            System.out.println("Details of Subnets:");
            subnet(s,no_of_required_networks,2);
        }
        else if(cnt == 2){
            C();
            System.out.println();
            System.out.println("Details of Subnets:");
            subnet(s,no_of_required_networks,3);
        }
        else {
            System.out.println("Not a valid ip to be used to subnet!!!");
        }

        //printing the local ip address of the machine
        try {
            String ip = getLocalIPv4();
            System.out.println("Local IPv4 Address: " + ip);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
   
    }
    public static void subnet(String s,int no_of_required_networks, int getClass){
        // 2^n>=15
        // calculating n (NO of Network Bits:)
        double nd = Math.log((double)(no_of_required_networks))/Math.log((double)(2));
        int n;
        if((int)nd == nd){
            n = (int)nd;
        }
        else{
            n = (int)nd +1;
        }


        System.out.println("No of Network Bits: "+n);
        int h;
        if(getClass == 3)  h= 8-n;
        else if(getClass == 2) h = 16-n;
        else h = 24-n;
        System.out.println("No of Host bits: "+h);

        //calculating Subnets:
        int subnets = (int)Math.pow(2,n);
        //Calculating hosts/subnet
        int hosts_per_subnet = (int)(Math.pow(2,h));
        System.out.println("No of Subnets: "+subnets);
        System.out.println("No of Hosts/Subnet: "+hosts_per_subnet);


        int[] str_id = new int[]{7,15,23,31};
        int j =0;
        int size = s.length();

        // dividing IP into 4 strings
        int[] ip = new int[32];

        for(int i = 0;i<4;i++){
            String temp = "";
            inner:
            for(int k = j;k<size;k++,j++){
                if(s.charAt(k)=='.'){
                    j++;
                    break inner;
                }
                else{
                    temp+=Character.toString(s.charAt(k));
                }
            }
            int val = Integer.parseInt(temp);
            int idx = str_id[i];
            while(val!=0){
                ip[idx] = (int)(val&1);
                idx--;
                val=val/2;
            }
        }

        //calculating customised subnet mask and additions to print the range of IPs
        int[] custom_mask = new int[32];
        int[] static_addition = new int[32];
        Arrays.fill(custom_mask, 1);
        for(int i = 31;i>31-h;i--){
            custom_mask[i] = 0; 
            static_addition[i] = 1;
            ip[i] = 0;
        }
        int[] customised_subnet_mask = new int[4];
        for(int i = 0;i<4;i++){
            int idx = str_id[i]-7;
            String temp = "";
            while(idx<=str_id[i]){
                temp+=Integer.toString(custom_mask[idx]);
                idx++;
            }
            customised_subnet_mask[i] = Integer.parseInt(temp,2);
        }
        //Printing customised subnet mask:
        System.out.print("Customised Subnet Mask:");
        for (int i = 0; i < 4; i++) {
            if(i!=3) System.out.print(customised_subnet_mask[i]+".");
            else System.out.println(customised_subnet_mask[i]);
        }
        // Printing required subnets range
        System.out.println("Subnets ranges from :");
        int cnt = 0;
        for(int i = 0;i<subnets;i++){
            if(i!=0){
                //updating ip by adding 1 at last
                int id = 31;
                ip[id]+=1;
                while(ip[id]!=1){
                    ip[id-1]+=1;
                    ip[id] = 0;
                    id--;
                }
            }
            cnt++;
            if(cnt>no_of_required_networks){
                // ip to string and ip last
                System.out.println();

                System.out.println("Wasted IPs: ");
                String res = "";
                String temp = "";
                for(int l = 0;l<32;l++){
                    if(l%8 == 0 || l == 31){
                        if(l == 31) {
                            
                            temp+=Integer.toString(ip[l]);
                            res+= (Integer.toString(Integer.parseInt(temp,2)));
                        }
                        else if(l!=0){
                            res+= (Integer.toString(Integer.parseInt(temp,2))+".");
                            temp = "";
                        }
                    }
                    temp+=Integer.toString(ip[l]);
                }
                temp ="";
                String res2="";
                if(getClass == 3) for(int l = 24;l <32;l++) ip[l] =1;
                else if(getClass == 2) for(int l = 16;l <32;l++) ip[l] =1;
                else for(int l = 8;l <32;l++) ip[l] =1;
                inner3:
                for(int l = 31;l>=0;l--){
                    if(ip[l] == 0) ip[l] = 1;
                    else break inner3;
                }
                for(int l = 0;l<32;l++){
                    if(l%8 == 0 || l == 31){
                        if(l == 31) {
                            
                            temp+=Integer.toString(ip[l]);
                            res2+= (Integer.toString(Integer.parseInt(temp,2)));
                        }
                        else if(l!=0){
                            res2+= (Integer.toString(Integer.parseInt(temp,2))+".");
                            temp = "";
                        }
                    }
                    temp+=Integer.toString(ip[l]);
                }
                System.out.println(res+"    to    "+res2);
                break;
            }
            getStr(ip,static_addition);    
        }
       
    }

    // method to calculate range:
    public static void getStr(int[] ip,int[] static_addition){
        String[] start = new String[4];
        String[] end = new String[4];
        int idx = 0;
        String temp = "";
        for(int i  = 0;i<32;i++){
            
            if(i%8 == 0 || i == 31){
                if(i == 31) temp+=Integer.toString(ip[i]);
                if(i!=0){
                    start[idx] = Integer.toString(Integer.parseInt(temp,2));
                    idx++;
                    temp = "";
                }
            }
            temp+=Integer.toString(ip[i]);
        }
        System.out.print(start[0]+"."+start[1]+"."+start[2]+"."+start[3]+"    to     ");
        for(int i = 31;i>=0;i--){
            ip[i] += static_addition[i];
            if(ip[i] == 2){
                ip[i-1] += 1;
                ip[i] = 0; 
            } 
            if(ip[i] == 3){
                ip[i-1]+=1;
                ip[i] = 1;
            }
        }

        idx = 0;
        temp = "";
        for(int i  = 0;i<32;i++){
            
            if(i%8 == 0 || i == 31){
                if(i == 31) temp+=Integer.toString(ip[i]);
                if(i!=0){
                    end[idx] = Integer.toString(Integer.parseInt(temp,2));
                    idx++;
                    temp = "";
                }
            }
            temp+=Integer.toString(ip[i]);
        }
        System.out.println(end[0]+"."+end[1]+"."+end[2]+"."+end[3]);
    }
    public static void A(){
        System.out.println("Basic Details of Class: ");
        System.out.println("The IP Address entered is of Class A.");
        System.out.println("Default Subnet: 255.0.0.0");
        System.out.println("Range: 0.0.0.0 to 127.255.255.255");
    }
   
    public static void B(){
        System.out.println("Basic Details of Class: ");
        System.out.println("The IP Address entered is of Class B.");
        System.out.println("Default Subnet: 255.255.0.0");
        System.out.println("Range: 128.0.0.0 to 191.255.255.255");
    }
   
    public static void C(){
        System.out.println("Basic Details of Class: ");
        System.out.println("The IP Address entered is of Class C.");
        System.out.println("Default Subnet: 255.255.255.0");
        System.out.println("Range: 192.0.0.0 to 223.255.255.255");
    }
    //Method to return the local IP Address of the machine 
    public static String getLocalIPv4() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("ipconfig");
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        Pattern pattern = Pattern.compile("IPv4 Address[. ]*: ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)");
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }
   
}