package project.healthcare.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project.healthcare.dto.PillDto;
import project.healthcare.entity.PillBasic;
import project.healthcare.entity.PillDetail;
import project.healthcare.service.PillBasicService;
import project.healthcare.service.PillDetailService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PillController {
    @Autowired
    private PillDetailService PillDetailService;
    @Autowired
    PillBasicService pillBasicService;
    @Autowired
    PillDetailService pillDetailService;
   /*
   *
   * @author an-eun-ug
   * @date 2023/09/21
   * @description api test
   *
   */
    @RequestMapping("/apiTest")
        public String apiTest(Model model) throws IOException, ParseException {
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/HtfsInfoService03/getHtfsItem01"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=N6YNoQqTapDv9A%2BfGAzqVwzRkddWoYfJrSfx1eRoFLAIPZrBxcB%2FtfelMEPnRfGeuL9ev7sFvXLoWXcM8lm1yQ%3D%3D"); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/

            URL url = new URL(urlBuilder.toString());
            System.out.println(url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            //System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            String data;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            data = sb.toString();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(data);
            JSONObject body = (JSONObject) obj.get("body");
            JSONArray items = (JSONArray) body.get("items");


            List<PillBasic> pillBasics = pillBasicService.getAllPillBasics();
            List<PillDetail> pillDetails = pillDetailService.getAllDetails();

            for(int i=0; i<items.size(); i++){ //ok you search HA GI JEON DELETE -> UPDATE YOU ADD DET_AT//<- what is this??? DELETE ﻿여﻿부 dkgka COLUMN FOR YOUR TABLE OK?
                JSONObject item = (JSONObject) items.get(i);
                JSONObject itemIn = (JSONObject) item.get("item");

                PillBasic basic = new PillBasic();

                basic.setCompany(String.valueOf(itemIn.get("ENTRPS")));
                basic.setProduct(String.valueOf(itemIn.get("PRDUCT")));
                basic.setExDate(String.valueOf(itemIn.get("DISTB_PD")));

                //pillBasicService.save(basic);

                PillDetail detail = new PillDetail();

                detail.setCompany(String.valueOf(itemIn.get("ENTRPS")));
                detail.setProduct(String.valueOf(itemIn.get("PRDUCT")));
                detail.setBaseStandard(String.valueOf(itemIn.get("BASE_STANDARD")).replace("\n","#%$"));
                detail.setEffect(String.valueOf(itemIn.get("MAIN_FNCTN")).replace("\n","#%$"));
                detail.setSrvUse(String.valueOf(itemIn.get("SRV_USE")).replace("\n","#%$"));
                detail.setIntakeHint(String.valueOf(itemIn.get("INTAKE_HINT1")).replace("\n","#%$"));

                //pillDetailService.save(detail);

                model.addAttribute("pillBasics", pillBasics);
                model.addAttribute("pillDetails", pillDetails);
            }
        return "pill";
        }

    @RequestMapping("/take-care")
    public String main(Model model) {
        return "main";
    }
    }
