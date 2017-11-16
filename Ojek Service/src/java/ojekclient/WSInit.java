/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ojekclient;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import ojekservice.OjekWebService;

/**
 *
 * @author Asus
 */
public class WSInit {
  public static OjekWebService init() throws Exception {
    URL url = new URL("http://localhost:8080/Ojek_Service/OjekWebService?wsdl");
    //1st argument service URI, refer to wsdl document above
    //2nd argument is service name, refer to wsdl document above
    QName qname = new QName("http://localhost:8080/Ojek_Service/", "OjekWebService");
    Service service = Service.create(url, qname);
    OjekWebService ojekWebService = service.getPort(OjekWebService.class);
    return ojekWebService;
  }
}
