package com.xuxue.spider.download.impl.download;

import com.xuxue.spider.download.DocumentDownloader;
import com.xuxue.spider.download.impl.event.IDocumentEvent;
import com.xuxue.spider.event.DocumentSpiderEvent;
import com.xuxue.spider.pool.DocumentCache;
import com.xuxue.spider.task.Task;
import com.xuxue.spider.task.TaskProcessStatus;
import com.xuxue.tools.stream.ReadStream;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;

import java.io.InputStream;


/**
 * Created by HanHan on 2016/5/13.
 */
public class IDocumentDownloader extends AbstractDownloader implements DocumentDownloader {

    private Logger logger = Logger.getLogger(IDocumentDownloader.class);

    private DocumentCache documentCache;

    private CloseableHttpClient client;

    private PoolingHttpClientConnectionManager connectionManager;

    public IDocumentDownloader() {
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(3);
        client = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();
    }

    @Override
    public void addDocumentPool(DocumentCache documentCache) {
        this.documentCache = documentCache;
    }

    @Override
    public DocumentCache getDocumentCache() {
        return documentCache;
    }

    @Override
    public void process(Task task) {
        DocumentSpiderEvent event = new IDocumentEvent();
        event.setTask(task);
        event.setTaskProcessStatus(TaskProcessStatus.PROCESSING);
        notifyBefor(event);//在即将执行任务的时候先通知插件
        CloseableHttpResponse res = null;
        InputStream in = null;
        HttpContext contex = null;
        try {
            if (event.getProcessStatus() == TaskProcessStatus.CANCELED) {//判断插件是否取消任务
                return;
            }
            HttpUriRequest request = task.getHttpUriRequest();
            contex = getHttpContexPool().getHttpContex(request.getURI().getHost());
            res = client.execute(request, contex);
            int responseCode = res.getStatusLine().getStatusCode();
            event.setResponseCode(responseCode);
            if (responseCode == 200) {
                in = res.getEntity().getContent();
                Document document = ReadStream.toDocument(in, task.getHttpUriRequest().toString());
                event.setDocument(document);
            }
            event.setTaskProcessStatus(TaskProcessStatus.SUCCESS);
            notifyCreateChildTask(event);
            notifyPipeLine(event);

        } catch (Exception e) {
            event.setTaskProcessStatus(TaskProcessStatus.ERROR);
            event.setThrowable(e);
            notifyExceptionThrow(event);
        } finally {
            try {
                res.close();
            } catch (Exception e) {
                logger.warn("关闭HttpResponse是出现错误", e);
            }
        }
    }

    @Override
    public PoolingHttpClientConnectionManager getConnectionManager() {
        return connectionManager;
    }


}
