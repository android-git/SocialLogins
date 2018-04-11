package com.social.facebook;

public class FacebookUser {

    private String id;

    private String birthday;

    private String first_name;

    private String timezone;

    private String verified;

    private String locale;

    private String name;

    private String link;

    private String last_name;

    private String email;

    private String gender;

    private String updated_time;

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    private Picture picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "ClassPojo [id = " + id + ", birthday = " + birthday + ", first_name = " + first_name + ", timezone = " + timezone + ", verified = " + verified + ", locale = " + locale + ", name = " + name + ", link = " + link + ", last_name = " + last_name + ", gender = " + gender + ", updated_time = " + updated_time + "]";
    }

    public class Picture {

        /**
         * data : {"height":100,"is_silhouette":false,"url":"https://scontent.xx.fbcdn.net/v/t1.0-1/p100x100/14141682_1100392730055355_5019250852605111118_n.jpg?oh=5166304b127e27555ba433f22eb8cd03&oe=5918B08B","width":100}
         */

        private DataBean data;

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * height : 100
             * is_silhouette : false
             * url : https://scontent.xx.fbcdn.net/v/t1.0-1/p100x100/14141682_1100392730055355_5019250852605111118_n.jpg?oh=5166304b127e27555ba433f22eb8cd03&oe=5918B08B
             * width : 100
             */

            private int height;
            private boolean is_silhouette;
            private String url;
            private int width;

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public boolean isIs_silhouette() {
                return is_silhouette;
            }

            public void setIs_silhouette(boolean is_silhouette) {
                this.is_silhouette = is_silhouette;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }
        }
    }
}
