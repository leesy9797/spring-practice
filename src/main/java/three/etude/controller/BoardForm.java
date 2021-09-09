package three.etude.controller;

public class BoardForm {
    String m_id, b_title, b_content, b_img;
    int b_idx;
    int b_like;
    int b_read;

    public int getB_like() {
        return b_like;
    }

    public void setB_like(int b_like) {
        this.b_like = b_like;
    }

    public int getB_read() {
        return b_read;
    }

    public void setB_read(int b_read) {
        this.b_read = b_read;
    }

    public int getB_replycount() {
        return b_replycount;
    }

    public void setB_replycount(int b_replycount) {
        this.b_replycount = b_replycount;
    }

    int b_replycount;

    public int getB_idx() {
        return b_idx;
    }

    public void setB_idx(int b_idx) {
        this.b_idx = b_idx;
    }

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getB_content() {
        return b_content;
    }

    public void setB_content(String b_content) {
        this.b_content = b_content;
    }

    public String getB_img() {
        return b_img;
    }

    public void setB_img(String b_img) {
        this.b_img = b_img;
    }
}
