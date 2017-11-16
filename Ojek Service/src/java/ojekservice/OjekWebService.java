/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ojekservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author Asus
 */
@WebService(serviceName = "OjekWebService")
@SOAPBinding(style = Style.RPC)
public class OjekWebService {

  /**
   * This is a sample web service operation
   */
  @WebMethod(operationName = "hello")
  public String hello(@WebParam(name = "name") String txt) {
    return "Hello " + txt + " !";
  }
  
  @WebMethod(operationName = "fetchPreferredDriver")
  public String fetchPreferredDriver(@WebParam(name = "preferredUsername") String preferredUsername) {
    return OrderWebService.fetchPreferredDriver(preferredUsername);
  }
  
  @WebMethod(operationName = "fetchOtherDriver")
  public String fetchOtherDriver(@WebParam(name = "preferredUsername") String preferredUsername) {
    return OrderWebService.fetchOtherDriver(preferredUsername);
  }
}
