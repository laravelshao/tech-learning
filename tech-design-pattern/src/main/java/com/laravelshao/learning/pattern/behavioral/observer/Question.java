package com.laravelshao.learning.pattern.behavioral.observer;

/**
 * @author qinghua.shao
 * @date 2020/4/18
 * @since 1.0.0
 */
public class Question {

    private String userName;

    private String questionContent;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
}
