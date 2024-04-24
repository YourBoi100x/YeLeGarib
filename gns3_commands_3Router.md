Configurations of PCs:
Pc1 ->
	Ip 10.10.23.2 255.255.255.0
PC2 ->
	ip 10.10.23.3 255.255.255.0
PC7->
	ip 132.10.24.130  255.255.255.128
PC8->
	ip 132.10.24.131 255.255.255.128

R1->
int f0/0 		(Configuring PC wala part to router)
ip address 10.10.23.1 255.255.255.0
no sh

int f1/0
ip address 10.0.0.1 255.255.255.0
no sh

int e2/0 
ip address 11.0.0.1 255.255.255.0
no sh



ip route 132.10.24.128 255.255.255.128 11.0.0.2
ip route 192.0.0.0 255.0.0.0  10.0.0.2


do sh ip int br

do sh ip route