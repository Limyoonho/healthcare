package project.healthcare.api;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.healthcare.entity.PillEntity;
import project.healthcare.repository.PillRepository;
import project.healthcare.util.NaverImgSearchUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class PillApi extends PillEntity {
    @Autowired
    private PillRepository pillRepository;
    @Autowired
    private NaverImgSearchUtil naverImgSearchUtil;

    @Transactional
    public void Pill() throws IOException {
        List<PillEntity> parsingData = new ArrayList<>();

        try {
            // 웹 페이지 URL
            String baseUrl = "https://www.pillyze.com/hig/broad/";

            String[] srvUrl = {"eyes", "skincare", "fat", "blood_circulation", "intestine","sleep", "cholesterol"};
            String[] category = {"눈", "피부", "체지방", "혈관 및 혈액 순환", "장", "스트레스 및 수면", "콜레스테롤"};

            for (int i = 0; i < srvUrl.length; i++) {
                String url = baseUrl + srvUrl[i];

                // 웹 페이지를 가져옴
                Document doc = Jsoup.connect(url).get();

                // 원하는 요소 선택
                Elements itemCards = doc.select("div.item-cards-pc > a.item-card");

                for (Element itemCard : itemCards) {
                    PillEntity pillEntity = new PillEntity();

                    // 카테고리
                    pillEntity.setCategory(category[i]);
                    // 이미지 링크
                    pillEntity.setImage(itemCard.select("img.item-img").attr("src"));
                    // 회사명
                    pillEntity.setCompany(itemCard.select("span.txt1").text());
                    // 제품명
                    pillEntity.setProduct(itemCard.select("span.txt2").text());
                    // 효과 태그
                    Elements effectElements = itemCard.select("div.tag-type1");
                    String[] effect = new String[effectElements.size()];
                    for (int j = 0; j < effectElements.size(); j++) {
                        effect[j] = effectElements.get(j).text();
                    }
                    pillEntity.setEffect(effect);
                    // 상세 태그
                    Elements detailElements = itemCard.select("div.tag-type2");
                    String[] detail = new String[detailElements.size()];
                    for (int j = 0; j < detailElements.size(); j++) {
                        detail[j] = detailElements.get(j).text();
                    }
                    pillEntity.setDetail(detail);

                    var savaData = pillRepository.findByCompanyAndProduct(pillEntity.getCompany(), pillEntity.getProduct());

                    if (savaData == null || (!savaData.getCompany().equals(pillEntity.getCompany())
                            && !savaData.getProduct().equals(pillEntity.getProduct()))) {
                        parsingData.add(pillEntity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (PillEntity pillEntity : parsingData) {
            System.out.println(("Category : " + pillEntity.getCategory()));
            System.out.println(("Company : " + pillEntity.getCompany()));
            System.out.println(("Product : " + pillEntity.getProduct()));
            System.out.println(("Effect : " + Arrays.toString(pillEntity.getEffect())));
            System.out.println(("Detail : " + Arrays.toString(pillEntity.getDetail())));
            System.out.println(("Image : " + pillEntity.getImage()));

            pillRepository.save(pillEntity);
        }
    }
}
