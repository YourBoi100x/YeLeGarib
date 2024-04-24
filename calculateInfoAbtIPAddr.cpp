#include<bits/stdc++.h>
using namespace std;


class ipAdress{
    private:
        string ip;
        int sub;
    public:
    ipAdress(string ip,int sub):ip(ip),sub(sub){}

    char checkClass(){
        int checkfirstOctet=stoi(ip.substr(0,ip.find('.')));

        if(checkfirstOctet>=1 && checkfirstOctet<=126) return 'A';
        else if(checkfirstOctet>=128 && checkfirstOctet<=191) return 'B';
        else if(checkfirstOctet>=192 && checkfirstOctet<=223) return 'C';
        else if(checkfirstOctet>=224 && checkfirstOctet<=239) return 'D';
        else if(checkfirstOctet>=240 && checkfirstOctet<=255) return 'E';
        else return 'N';
    }
    void subnetCalcA(){
        int n=ceil(log(sub)/log(2));
        cout<<"No. of network bits:"<<n<<endl;

        int sub2=pow(2,n);
        cout<<"No. of subnets:"<<sub2<<endl;

        int h;
        int host;
        int csm=0;

        h=24-n;
        cout<<"No. of host bits:"<<h<<endl;

        host=pow(2, h);
        cout<<"No. of hosts/subnet:"<<host<<endl;

        for(int i=0;i<n;i++)
        csm+=pow(2,7-i);

        cout<<"Customize Subnet Mask: 255."<<csm<<".0.0"<<endl;
        printInvalidSubnets(host);
        
    }
    void subnetCalcB(){
        int n=ceil(log(sub)/log(2));
        cout<<"No. of network bits:"<<n<<endl;

        int sub2=pow(2,n);
        cout<<"No. of subnets:"<<sub2<<endl;

        int h;
        int host;
        int csm=0;

        h=16-n;
        cout<<"No. of host bits:"<<h<<endl;

        host=pow(2, h);
        cout<<"No. of hosts/subnet:"<<host<<endl;

        for(int i=0;i<n;i++)
        csm+=pow(2,7-i);

        cout<<"Customize Subnet Mask: 255.255."<<csm<<".0"<<endl;
        printInvalidSubnets(host);
        
    }
    void subnetCalcC(){
        int n=ceil(log(sub)/log(2));
        cout<<"No. of network bits:"<<n<<endl;

        int sub2=pow(2,n);
        cout<<"No. of subnets:"<<sub2<<endl;

        int h;
        int host;
        int csm=0;

        h=8-n;
        cout<<"No. of host bits:"<<h<<endl;

        host=pow(2, h);
        cout<<"No. of hosts/subnet:"<<host<<endl;

        for(int i=0;i<n;i++)
        csm+=pow(2,7-i);

        cout<<"Customize Subnet Mask: 255.255.255."<<csm<<endl;

        for(int i=0,k=0;i<255;k++){
            if(k==sub){
                cout<<endl<<"Below Invalid subnet: "<<endl;
            }
            cout<<"network ID: "<<ip.substr(0,10)<<i<<"   -   "<<"Brodcase ID: "<<ip.substr(0,10)<<i+host-1<<endl;
            i=i+host;
        }
        
    }

    void printInvalidSubnets(int host){
        for(int i=0,k=0;i<255;k++){
            if(k==sub){
                cout<<endl<<"Below Invalid subnet: "<<endl;
            }
            cout<<"network ID: "<<ip.substr(0,11)<<i<<"   -   "<<"Brodcase ID: "<<ip.substr(0,11)<<i+host-1<<endl;
            i=i+host;
        }
    }

    void printAccordingToClass(){
        if(checkClass()=='A'){
            display_A();
            subnetCalcA();
        }
        else if(checkClass()=='B'){
            display_B();
            subnetCalcB();
        }
        else if(checkClass()=='C'){
            display_C();
            subnetCalcC();
        }
        else if(checkClass()=='D'){
            display_D();
        }
        else if(checkClass()=='E'){
            display_E();
        }
        else cout<<"Invalid IP address";
    }

    void display_A(){
        cout<<endl;
        cout<<"---Details about IP Address---"<<endl;
        cout<<endl;
        cout<<"IP address: "<<ip<<endl;
        cout<<"Class: "<<checkClass()<<endl;
        cout<<"Default Subnet Mask: 255.0.0.0"<<endl;
        cout<<"Network ID: 10.0.0.0"<<endl;
    }
    void display_B(){
        cout<<endl;
        cout<<"---Details about IP Address---"<<endl;
        cout<<endl;
        cout<<"IP address: "<<ip<<endl;
        cout<<"Class: "<<checkClass()<<endl;
        cout<<"Default Subnet Mask: 255.255.0.0"<<endl;
        cout<<"Network ID: 172.16.0.0"<<endl;
    }
    void display_C(){
        cout<<endl;
        cout<<"---Details about IP Address---"<<endl;
        cout<<endl;
        cout<<"IP address: "<<ip<<endl;
        cout<<"Class: "<<checkClass()<<endl;
        cout<<"Default Subnet Mask: 255.255.255.0"<<endl;
        cout<<"Network ID:  192.168.0.0"<<endl;
    }
    void display_D(){
        cout<<endl;
        cout<<"---Details about IP Address---"<<endl;
        cout<<endl;
        cout<<"IP address: "<<ip<<endl;
        cout<<"Class: "<<checkClass()<<endl;
    }
    void display_E(){
        cout<<endl;
        cout<<"---Details about IP Address---"<<endl;
        cout<<endl;
        cout<<"IP address: "<<ip<<endl;
        cout<<"Class: "<<checkClass()<<endl;
    }
};


int main(){
    
    cout<<"---IP ADDRESS DETAILS CALCULATION---"<<endl;
    string ip_address;
    cout<<"Enter the IP address: ";
    cin>>ip_address;
    cout<<endl;
    int sub;
    cout<<"Enter the subnet requirement: ";
    cin>>sub;

    ipAdress ipAddress(ip_address,sub);
    ipAddress.printAccordingToClass();
    return 0;
}