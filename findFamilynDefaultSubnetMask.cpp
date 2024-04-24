#include<bits/stdc++.h>
using namespace std;


class ipAdress{
    private:
        string ip;
    public:
    ipAdress(string ip):ip(ip){}

    char checkClass(){
        int checkfirstOctet=stoi(ip.substr(0,ip.find('.')));

        if(checkfirstOctet>=1 && checkfirstOctet<=126) return 'A';
        else if(checkfirstOctet>=128 && checkfirstOctet<=191) return 'B';
        else if(checkfirstOctet>=192 && checkfirstOctet<=223) return 'C';
        else if(checkfirstOctet>=224 && checkfirstOctet<=239) return 'D';
        else if(checkfirstOctet>=240 && checkfirstOctet<=255) return 'E';
        else return 'N';
    }

    void printAccordingToClass(){
        if(checkClass()=='A') display_A();
        else if(checkClass()=='B') display_B();
        else if(checkClass()=='C') display_C();
        else if(checkClass()=='D') display_D();
        else if(checkClass()=='E') display_E();
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

std::string getLocalIPv4() {
    FILE *pipe = popen("ipconfig", "r");
    if (!pipe) throw runtime_error("popen() failed!");

    regex pattern("IPv4 Address[. ]*: ([0-9]+\\.[0-9]+\\.[0-9]+\\.[0-9]+)");

    char buffer[128];
    string result = "";
    while (!feof(pipe)) {
        if (fgets(buffer, 128, pipe) != nullptr) {
            string line(buffer);
            smatch matches;
            if (regex_search(line, matches, pattern)) {
                result = matches[1];
                break;
            }
        }
    }
    pclose(pipe);
    return result;
}

int main(){
    cout<<"---IP ADDRESS DETAILS---"<<endl;
    cout<<endl;
    cout<<"Local IPv4 Address: "<<getLocalIPv4()<<endl;

    cout<<endl;
    cout<<"---IP ADDRESS DETAILS CALCULATION---"<<endl;
    string ip_address;
    cout<<"Enter the IP address: ";
    cin>>ip_address;

    ipAdress ipAddress(ip_address);
    ipAddress.printAccordingToClass();
    return 0;
}