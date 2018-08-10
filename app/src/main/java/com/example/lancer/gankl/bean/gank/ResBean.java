package com.example.lancer.gankl.bean.gank;

import java.util.List;

/**
 * author: Lancer
 * date：2018/8/9
 * des:拓展资源bean
 * email:tyk790406977@126.com
 */

public class ResBean {

    /**
     * error : false
     * results : [{"_id":"5b69a0669d21226f4a61f70e","createdAt":"2018-08-09T10:54:14.976Z","desc":"如何快速从0开发一个完整的 Flutter APP，并针对开发过程中可能遇到的问题进行填坑。","publishedAt":"2018-08-09T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://juejin.im/entry/5b631e3e51882519861c2ef1","used":true,"who":"Shuyu Guo"},{"_id":"5b6ba0aa9d21226f4e09c770","createdAt":"2018-08-09T10:50:23.848Z","desc":"终于，我还是码造一个了中国地图","publishedAt":"2018-08-09T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s/f505bQ8w89WHaAkB1NWp9A","used":true,"who":"codeGoogler"},{"_id":"5b5e65eb9d21220fc36e035c","createdAt":"2018-07-30T11:36:15.596Z","desc":"金9银10的面试黄金季节，分享几个重要的面试题","publishedAt":"2018-08-03T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://mp.weixin.qq.com/s/Rt5i3VTIoefRRqEsRZJafw","used":true,"who":"codeGoogler"},{"_id":"5b6030a69d2122477951a331","createdAt":"2018-07-31T17:58:52.999Z","desc":"慕课网iOS面试实战项目总结：iOS面试题思维导图与回答","publishedAt":"2018-07-31T00:00:00.0Z","source":"api","type":"拓展资源","url":"https://github.com/MisterBooo/ReadyForBAT","used":true,"who":"lijinshan"},{"_id":"5b515f55421aa917a1e8ef11","createdAt":"2018-07-20T12:04:37.376Z","desc":"Java实现的盲水印","images":["https://storage.gank.io/gank/63943c95-de95-4a23-a4f3-130df2823f2e","https://storage.gank.io/gank/f577f430-9b96-40ae-97b9-3b38a8db9052","https://storage.gank.io/gank/65358e3e-c054-4ac6-9ab7-112b2060fbd4"],"publishedAt":"2018-07-30T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://github.com/ww23/BlindWaterMark","used":true,"who":"Kaming"},{"_id":"5b5b43ea9d21220fc36e035b","createdAt":"2018-07-28T00:10:18.418Z","desc":"一个针对高并发、低延迟应用设计的高性能且无侵入的实时Java方法性能监控和统计工具","images":["https://storage.gank.io/gank/52ec1211-5282-4cbe-b269-cbebfad7139d","https://storage.gank.io/gank/370d5df0-150b-4045-8e62-8a8c107c4719"],"publishedAt":"2018-07-30T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://github.com/ThinkpadNC5/MyPerf4J","used":true,"who":"LinShunkang"},{"_id":"5b42d278421aa92d1cba2919","createdAt":"2018-07-11T11:33:04.427Z","desc":"让 Retrofit 支持多 BaseUrl，以及运行时动态替换复杂的 BaseUrl，是全网目前最完善的一个解决方案","publishedAt":"2018-07-11T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://juejin.im/post/5b42aa98f265da0f82021350","used":true,"who":"jess"},{"_id":"5b456292421aa92fc7503ed1","createdAt":"2018-07-11T11:33:42.65Z","desc":"脚本实现代码规范检测","publishedAt":"2018-07-11T00:00:00.0Z","source":"chrome","type":"拓展资源","url":"https://juejin.im/post/5b44d0cc5188251aa0162abe","used":true,"who":"无问西东"},{"_id":"5b42ed04421aa92fc67266c3","createdAt":"2018-07-10T11:15:06.341Z","desc":"EasyExecutor: 让线程任务的使用变得高效、安全、方便、灵活","publishedAt":"2018-07-10T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://juejin.im/post/5b3ec9fd6fb9a04fe6376227","used":true,"who":"yjfnypeu"},{"_id":"5b43185d421aa92fc7503ec6","createdAt":"2018-07-10T10:52:35.221Z","desc":"Android ConstraintLayout 最新使用小结","publishedAt":"2018-07-10T00:00:00.0Z","source":"web","type":"拓展资源","url":"https://juejin.im/post/5b3cec1ee51d4519596b488e","used":true,"who":"leon"}]
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
         * _id : 5b69a0669d21226f4a61f70e
         * createdAt : 2018-08-09T10:54:14.976Z
         * desc : 如何快速从0开发一个完整的 Flutter APP，并针对开发过程中可能遇到的问题进行填坑。
         * publishedAt : 2018-08-09T00:00:00.0Z
         * source : web
         * type : 拓展资源
         * url : https://juejin.im/entry/5b631e3e51882519861c2ef1
         * used : true
         * who : Shuyu Guo
         * images : ["https://storage.gank.io/gank/63943c95-de95-4a23-a4f3-130df2823f2e","https://storage.gank.io/gank/f577f430-9b96-40ae-97b9-3b38a8db9052","https://storage.gank.io/gank/65358e3e-c054-4ac6-9ab7-112b2060fbd4"]
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
