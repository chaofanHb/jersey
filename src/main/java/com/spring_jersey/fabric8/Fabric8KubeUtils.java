package com.spring_jersey.fabric8;

/**
 * Created by Administrator on 2017/12/19.
 */

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.apache.log4j.Logger;

import java.util.List;

public class Fabric8KubeUtils implements KubeUtils<KubernetesClient> {
    private KubernetesClient client;
    private static final int CONNECTION_TIMEOUT = 3 * 1000;
    private static final int REQUEST_TIMEOUT = 3 * 1000;

    private static Logger logger = Logger.getLogger(Fabric8KubeUtils.class);

    public KubernetesClient getClient() {
        return client;
    }

    public void setClient(KubernetesClient client) {
        this.client = client;
    }


    private Fabric8KubeUtils(KubernetesClient client) {
        this.client = client;
    }

    private static final KubernetesClient _kube=new DefaultKubernetesClient("http://192.168.99.102:8080");

    private static void testCreateNamespace(){
        Namespace namespace=new Namespace();
        namespace.setApiVersion("v1");
        namespace.setKind("Namespace");
        ObjectMeta objectMeta=new ObjectMeta();
        objectMeta.setName("ns-fabric8");
        namespace.setMetadata(objectMeta);
        _kube.namespaces().create(namespace);
    }

    private static void testGetPodList(){
        List<Pod> list=_kube.pods().list().getItems();
        for (int i = 0; i <list.size() ; i++) {
            System.out.print(list.get(i).toString());

        }
    }

    private static void testGetNodeList(){
        List<Node> list=_kube.nodes().list().getItems();
        for (int i = 0; i <list.size() ; i++) {
            System.out.print(list.get(i).toString());
        }
    }

    public static void main(String[] args) {
        testGetNodeList();
        /*List<ReplicationController> li= _kube.replicationControllers().list().getItems();
        System.out.println(li.size());
        System.out.println(li.get(0).getStatus().toString());*/

        /*ObjectMeta meta=new ObjectMeta();
        meta.setName("myapp1");
        Map<String,String> labels=new HashMap<String, String>();
        labels.put("name","myapp1");
        meta.setLabels(labels);
        meta.setNamespace("default");

        ReplicationControllerSpec spec=new ReplicationControllerSpec();
        spec.setReplicas(1);
        PodTemplateSpec template=new PodTemplateSpec();
        ObjectMeta meta1=new ObjectMeta();
        Map<String,String> labels1=new HashMap<String, String>();
        labels.put("name","myapp1");
        meta1.setLabels(labels1);
        template.setMetadata(meta1);
        PodSpec spec1=new PodSpec();

        List<Container> containers=new ArrayList<Container>();
        Container container=new Container();
        container.setName("myapp1");
        container.setImagePullPolicy("Always");
        container.setImage("192.168.99.100:5000/tomcat8011");
        List<ContainerPort> list=new ArrayList<ContainerPort>();
        ContainerPort containerPort=new ContainerPort();
        containerPort.setContainerPort(8090);
        list.add(containerPort);
        container.setPorts(list);
        containers.add(container);
        spec1.setContainers(containers);

        List<LocalObjectReference> imagePullSecrets=new ArrayList<LocalObjectReference>();
        LocalObjectReference localObjectReference=new LocalObjectReference();
        localObjectReference.setName("registrysecret");
        spec1.setImagePullSecrets(imagePullSecrets);

        template.setSpec(spec1);
        spec.setTemplate(template);

        ReplicationControllerStatus status=new ReplicationControllerStatus();
        status.setAvailableReplicas(1);
        status.setFullyLabeledReplicas(1);
        status.setReplicas(1);
        status.setObservedGeneration(1L);

        ReplicationController controller=new ReplicationController("v1","ReplicationController",meta,spec,status);


        _kube.replicationControllers().create(controller);*/
    }
}
