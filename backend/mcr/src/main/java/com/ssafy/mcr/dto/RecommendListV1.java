package com.ssafy.mcr.dto;

import java.util.List;

public class RecommendListV1 {
    String recommendMent;
    List<RecommendV1> list;

    public RecommendListV1(String recommendMent, List<RecommendV1> list) {
        this.recommendMent = recommendMent;
        this.list = list;
    }

    public String getRecommendMent() {
        return recommendMent;
    }

    public void setRecommendMent(String recommendMent) {
        this.recommendMent = recommendMent;
    }

    public List<RecommendV1> getList() {
        return list;
    }

    public void setList(List<RecommendV1> list) {
        this.list = list;
    }
}
