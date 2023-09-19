package project.healthcare.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import project.healthcare.dto.PillDto;

import java.io.IOException;
import java.util.ArrayList;

public class PillTableService extends PillDto{
    public static void main(String[] args) throws IOException {
        final String URL = "https://www.pillyze.com/hig/broad/";

        String[] arr = {"fatigue/recommend-product", "eyes", "skincare", "fat", "blood_circulation",
                "liver/recommend-product", "intestine", "sleep", "immunity/recommend-product", "cholesterol"};

        ArrayList<String> list = new ArrayList<>();

        for(int i=0; i<arr.length; i++){
            list.add(arr[i]);
        }

        for (int i = 0; i < list.size(); i++) {
            String connectUrl = URL + list.get(i);
            Connection conn = Jsoup.connect(connectUrl);

            try {
                Document document = conn.get();
                Elements imageElements = document.select(".item-cards > a > .item-img");
                Elements componyElements = document.select(".item-cards > a > .txt1");
                Elements nameElements = document.select(".item-cards > a > .txt2");
                Elements effectElements = document.select(".item-cards > a > .tag-type1-wrap");
                //Elements detailElements = document.select(".item-cards > a > .tag-type2-wrap");

                for (int j = 0; j < imageElements.size(); j++) {
                    final String image = imageElements.get(j).attr("abs:src");
                    final String compony = componyElements.get(j).text();
                    final String name = nameElements.get(j).text();
                    final String effect = effectElements.get(j).text();
                    //final String detail = detailElements.get(j).text();


                    System.out.println("상품 이미지: " + image);
                    System.out.println("상품 회사: " + compony);
                    System.out.println("상품 이름 : " + name);
                    System.out.println("상품 효과 : " + effect);
                   // System.out.println("상품 성분 : " + detail);
                    System.out.println("==========================================================================");
                }
                connectUrl = "";

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
