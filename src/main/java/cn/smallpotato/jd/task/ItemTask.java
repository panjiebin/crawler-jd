package cn.smallpotato.jd.task;

import cn.smallpotato.jd.util.HttpUtils;
import org.springframework.stereotype.Component;

/**
 * @author Panjb
 */
@Component
public class ItemTask {

    private final HttpUtils httpUtils;

    public ItemTask(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public void itemTask() throws Exception {
        String url = "https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&pvid=8858151673f941e9b1a4d2c7214b2b52&s=1&click=0&page=";

        for (int i = 0; i < 2; i = i + 2) {
            String page = this.httpUtils.doGetHtml(url + i);
        }
    }

    p
}
