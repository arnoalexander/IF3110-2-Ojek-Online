/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ojekservice;

import javax.xml.ws.Endpoint;

/**
 *
 * @author Asus
 */
public class OjekWebServicePublisher {
  public static void main(String[] args) {
    Endpoint.publish("http://localhost:8080/Ojek_Service/OjekWebService", new OjekWebService());
  }
}
