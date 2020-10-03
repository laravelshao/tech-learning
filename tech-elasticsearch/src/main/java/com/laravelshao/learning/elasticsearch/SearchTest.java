package com.laravelshao.learning.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

/**
 * es搜索测试
 *
 * @author qinghua.shao
 * @date 2020/10/3
 * @since 1.0.0
 */
public class SearchTest {

    public static void main(String[] args) throws Exception {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));

        // 准备数据
        //prepareData(client);
        // 存在1s延迟查询出刚添加数据
        //Thread.sleep(2000L);

        // 执行搜索
        executeSearch(client);
    }

    /**
     * 执行搜索操作
     *
     * @param client
     */
    private static void executeSearch(RestHighLevelClient client) throws Exception {

        /**
         * 1.搜索职位中包含technique的员工
         * 2.同时要求age在30到40岁之间
         * 3.分页查询，查找第一页
         */
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchQuery("position", "technique"))
                .postFilter(QueryBuilders.rangeQuery("age").from(30).to(40))
                .from(0).size(1);

        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (int i = 0; i < searchHits.length; i++) {
            System.out.println(searchHits[i].getSourceAsString());
        }
    }

    /**
     * 准备数据
     *
     * @param client
     */
    private static void prepareData(RestHighLevelClient client) throws Exception {

        client.index(new IndexRequest("company").id("1").source(
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "jack")
                        .field("age", 27)
                        .field("position", "technique software")
                        .field("country", "china")
                        .field("join_date", "2017-01-01")
                        .field("salary", 10000)
                        .endObject()), RequestOptions.DEFAULT);

        client.index(new IndexRequest("company").id("2").source(
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "marry")
                        .field("age", 35)
                        .field("position", "technique manager")
                        .field("country", "china")
                        .field("join_date", "2017-01-01")
                        .field("salary", 12000)
                        .endObject()), RequestOptions.DEFAULT);

        client.index(new IndexRequest("company").id("3").source(
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "tom")
                        .field("age", 32)
                        .field("position", "senior technique software")
                        .field("country", "china")
                        .field("join_date", "2016-01-01")
                        .field("salary", 11000)
                        .endObject()), RequestOptions.DEFAULT);

        client.index(new IndexRequest("company").id("4").source(
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "jen")
                        .field("age", 25)
                        .field("position", "junior finance")
                        .field("country", "usa")
                        .field("join_date", "2016-01-01")
                        .field("salary", 7000)
                        .endObject()), RequestOptions.DEFAULT);

        client.index(new IndexRequest("company").id("5").source(
                XContentFactory.jsonBuilder()
                        .startObject()
                        .field("name", "mike")
                        .field("age", 37)
                        .field("position", "finance manager")
                        .field("country", "usa")
                        .field("join_date", "2015-01-01")
                        .field("salary", 15000)
                        .endObject()), RequestOptions.DEFAULT);
    }
}
