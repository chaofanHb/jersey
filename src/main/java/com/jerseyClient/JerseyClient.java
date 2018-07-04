package com.jerseyClient;

/**
 * Created by Administrator on 2017/12/18.
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Iterator;

public class JerseyClient {

    public static void main(String[] args) {
//      Ҫʹ��Jersey Client API���������ȴ���Client��ʵ��
//      ���������ִ���Clientʵ���ķ�ʽ

//     ��ʽһ
        ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10*1000);
//      Clientʵ��������ϵͳ��Դ����Ҫ����
//      ����web��Դ���������󣬽�����Ӧ�����̰߳�ȫ��
//      ����Clientʵ����WebResourceʵ�������ڶ���̼߳䰲ȫ�Ĺ���
        Client client = Client.create(cc);

//      ��ʽ��
//      Client client = Client.create();
//      client.setConnectTimeout(10*1000);
//      client.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, 10*1000);

//      WebResource����̳�Client��timeout������
        WebResource resource = client.resource("http://127.0.0.1:10000/service/sean?desc=description");

        String str = resource
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN)
                .get(String.class);
        System.out.println("String:" + str);

        URI uri = UriBuilder.fromUri("http://127.0.0.1/service/sean").port(10000)
                .queryParam("desc", "description").build();
        resource = client.resource(uri);

        //header�������������HTTPͷ
        ClientResponse response = resource.header("auth", "123456")
                .accept(MediaType.TEXT_PLAIN)
                .type(MediaType.TEXT_PLAIN)
                .get(ClientResponse.class);
//      ��HTTP��Ӧ��ӡ����
        System.out.println("****** HTTP response ******");
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("HTTP/1.1 ");
        strBuilder.append(response.getStatus() + " ");
        strBuilder.append(response.getStatusInfo() + "[\\r\\n]");
        System.out.println(strBuilder.toString());
        MultivaluedMap<String, String> headers = response.getHeaders();
        Iterator<String> iterator = headers.keySet().iterator();
        while(iterator.hasNext()){
            String headName = iterator.next();
            System.out.println(headName + ":" + headers.get(headName) + "[\\r\\n]");
        }
        System.out.println("[\\r\\n]");
        System.out.println(response.getEntity(String.class) + "[\\r\\n]");
    }
}
