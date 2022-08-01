package com.laravelshao.learning.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.xcontent.XContentBuilder;
import org.elasticsearch.xcontent.XContentFactory;

/**
 * es document 增删改查测试
 *
 * @author qinghua.shao
 * @date 2019/8/20
 * @since 1.0.0
 */
public class EmployeeDocumentTest {

    public static void main(String[] args) throws Exception {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 创建员工doc
        //createEmployee(client);
        // 获取员工doc
        getEmployee(client);
        // 修改员工doc
        //updateEmployee(client);
        // 删除员工doc
        //deleteEmployee(client);

        client.close();
    }

    /**
     * 创建员工doc
     *
     * @param client
     */
    private static void createEmployee(RestHighLevelClient client) throws Exception {

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("name", "jack")
                .field("age", 27)
                .field("position", "technique")
                .field("country", "china")
                .field("join_date", "2017-01-01")
                .field("salary", 10000)
                .endObject();

        IndexRequest request = new IndexRequest("employee").id("1").source(builder);
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    /**
     * 获取员工doc
     *
     * @param client
     * @throws Exception
     */
    private static void getEmployee(RestHighLevelClient client) throws Exception {

        GetRequest request = new GetRequest("employee", "1");
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
    }

    /**
     * 修改员工doc
     *
     * @param client
     * @throws Exception
     */
    private static void updateEmployee(RestHighLevelClient client) throws Exception {

        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("position", "technique manager")
                .endObject();

        UpdateRequest request = new UpdateRequest("employee", "1").doc(builder);
        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }

    /**
     * 删除员工doc
     *
     * @param client
     * @throws Exception
     */
    private static void deleteEmployee(RestHighLevelClient client) throws Exception {

        DeleteRequest request = new DeleteRequest("employee", "1");
        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
        System.out.println(response.getResult());
    }
}
