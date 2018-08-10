package com.example.lancer.gankl.bean.gank;

import java.util.List;

/**
 * author: Lancer
 * date：2018/8/9
 * des:前端bean
 * email:tyk790406977@126.com
 */

public class BeforeBean {

    /**
     * error : false
     * results : [{"_id":"5b67d98f9d2122195a5d4278","createdAt":"2018-08-06T13:15:59.171Z","desc":"微信小程序WebStorm插件，支持PHPSTORM,WEBSTORM等全部intellij产品","publishedAt":"2018-08-09T00:00:00.0Z","source":"web","type":"前端","url":"https://www.jianshu.com/p/a436b4f9e4ed","used":true,"who":"Ysnow"},{"_id":"5b69a09e9d21226f48f68bee","createdAt":"2018-08-09T10:53:28.67Z","desc":"对cordova、ionic、react-native、weex、kotlin-native、flutter等跨平台框架的分析。","publishedAt":"2018-08-09T00:00:00.0Z","source":"web","type":"前端","url":"https://juejin.im/post/5b395eb96fb9a00e556123ef","used":true,"who":"Shuyu Guo"},{"_id":"5b64277d9d2122195e3db643","createdAt":"2018-08-03T17:59:25.703Z","desc":"基于Vue实现动态组织结构图","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1ftztjir736j30o10dvwg2"],"publishedAt":"2018-08-06T00:00:00.0Z","source":"web","type":"前端","url":"https://refined-x.com/2018/08/03/%E5%9F%BA%E4%BA%8EVue%E5%AE%9E%E7%8E%B0%E5%8A%A8%E6%80%81%E7%BB%84%E7%BB%87%E7%BB%93%E6%9E%84%E5%9B%BE/","used":true,"who":"zangtao"},{"_id":"5b67bb209d2122195bdbd807","createdAt":"2018-08-06T11:06:08.284Z","desc":"超赞的Vue管理后台。","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1ftztjmmmesj31fl0p5ael"],"publishedAt":"2018-08-06T00:00:00.0Z","source":"chrome","type":"前端","url":"https://github.com/PanJiaChen/vue-element-admin","used":true,"who":"lijinshan"},{"_id":"5b67bbdc9d2122195e3db64c","createdAt":"2018-08-06T11:09:16.63Z","desc":"基于G2和React的数据可视化库","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1ftztjniqadj318g0t2qpn"],"publishedAt":"2018-08-06T00:00:00.0Z","source":"chrome","type":"前端","url":"https://github.com/alibaba/BizCharts","used":true,"who":"lijinshan"},{"_id":"5b67bd729d2122195e3db64e","createdAt":"2018-08-06T11:17:00.165Z","desc":"React Slider","images":["https://ww1.sinaimg.cn/large/0073sXn7ly1ftztjo7nf8g30fv028dvb"],"publishedAt":"2018-08-06T00:00:00.0Z","source":"chrome","type":"前端","url":"https://github.com/react-component/slider","used":true,"who":"lijinshan"},{"_id":"5b62bde59d21225e0b0777f8","createdAt":"2018-08-02T16:16:37.740Z","desc":"一个零依赖javascript库，可以轻松地在您的网站中启用夜间模式。","publishedAt":"2018-08-03T00:00:00.0Z","source":"api","type":"前端","url":"https://github.com/Fcmam5/nightly.js","used":true,"who":"lijinshan"},{"_id":"5b5e9cdd9d21220fc36e035f","createdAt":"2018-07-30T13:06:37.427Z","desc":"前端周刊，给前端同学准备的每周1小时阅读清单","publishedAt":"2018-07-31T00:00:00.0Z","source":"web","type":"前端","url":"https://frontend-weekly.com/","used":true,"who":"zangtao"},{"_id":"5b60334e9d212247776a2e0c","createdAt":"2018-07-31T18:00:46.708Z","desc":"一个检查所有前端部件的linter。","images":["https://storage.gank.io/gank/919cb1a9-4aa0-47f4-be9b-6700686a57cd"],"publishedAt":"2018-07-31T00:00:00.0Z","source":"api","type":"前端","url":"https://github.com/everylint/everylint","used":true,"who":"lijinshan"},{"_id":"5b6033d79d2122477951a332","createdAt":"2018-07-31T18:03:03.563Z","desc":"用于tcping服务器的Web App。","publishedAt":"2018-07-31T00:00:00.0Z","source":"api","type":"前端","url":"https://github.com/Indexyz/Torch-Web","used":true,"who":"lijinshan"}]
     */

    private boolean error;
    private List<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * _id : 5b67d98f9d2122195a5d4278
         * createdAt : 2018-08-06T13:15:59.171Z
         * desc : 微信小程序WebStorm插件，支持PHPSTORM,WEBSTORM等全部intellij产品
         * publishedAt : 2018-08-09T00:00:00.0Z
         * source : web
         * type : 前端
         * url : https://www.jianshu.com/p/a436b4f9e4ed
         * used : true
         * who : Ysnow
         * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1ftztjir736j30o10dvwg2"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
