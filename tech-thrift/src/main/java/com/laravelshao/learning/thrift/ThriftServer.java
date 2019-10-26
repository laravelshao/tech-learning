package com.laravelshao.learning.thrift;

import com.laravelshao.learning.thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * Thrift 服务端
 *
 * @author qinghua.shao
 * @date 2019/10/26
 * @since 1.0.0
 */
public class ThriftServer {

    public static void main(String[] args) throws TTransportException {

        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(8080);
        THsHaServer.Args arg = new THsHaServer.Args(serverSocket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor =
                new PersonService.Processor<PersonServiceImpl>(new PersonServiceImpl());
        arg.protocolFactory(new TCompactProtocol.Factory());
        arg.transportFactory(new TFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server = new THsHaServer(arg);
        System.out.println("thrift server start ......");
        server.serve();
    }
}
