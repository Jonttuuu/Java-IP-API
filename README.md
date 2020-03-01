# Java-IP-API
Simple java class example for https://api.jonttu.fi/ip.php API

When using this API you will need token and token has to be placed in IPResolver class to token variable!

Example:
```java
// Makes new instansce of IPResolver and gets data from API
// Running this calls api and recommended to run asyncronoysly
IPResolver resolver = new IPResolver("127.0.0.1");

// If request succeeded returns true
boolean status = resolver.getStatus();

// Returns request status message "success" is output when everything is alright.
String statusMsg = resolver.getStatusMsg();

// General information about ip
String address = resolver.getIP();
String country = resolver.getCountry();
String city = resolver.getCity();
String isp = resolver.getIsp();

// This is kind of special parameter...
// Parameter tells if ip address belongs to somekind of hosting company or is vpn or proxy service.
boolean hosting = resolver.isHosting();
```
